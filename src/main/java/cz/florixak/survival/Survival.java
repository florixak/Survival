package cz.florixak.survival;

import cz.florixak.survival.action.ActionManager;
import cz.florixak.survival.command.CommandManager;
import cz.florixak.survival.command.commands.home.Home;
import cz.florixak.survival.command.commands.tpa.Tpa;
import cz.florixak.survival.config.ConfigManager;
import cz.florixak.survival.inventory.InventoryManager;
import cz.florixak.survival.listeners.*;
import cz.florixak.survival.manager.*;
import cz.florixak.survival.manager.scoreboardManager.ScoreboardManager;
import cz.florixak.survival.sql.MySQL;
import cz.florixak.survival.sql.SQLGetter;
import cz.florixak.survival.utility.TeleportUtil;
import cz.florixak.survival.utility.TextUtil;
import cz.florixak.survival.utility.placeholderapi.PlaceholderExp;
import hps.land.hpscore.HpsCore;
import net.luckperms.api.LuckPerms;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Survival extends JavaPlugin {

    private static Economy econ = null;
    private HpsCore hpsCore;
    public MySQL SQL;
    public SQLGetter data;
    private ConfigManager configManager;
    public static Survival plugin;
    public static FileConfiguration config;
    private ScoreboardManager scoreboardManager;
    private ItemManager itemManager;
    private ActionManager actionManager;
    private InventoryManager inventoryManager;
    private JobsManager jobsManager;
    private KitsManager kitsManager;
    private JoinManager joinManager;
    private SpawnManager spawnManager;
    private HomeManager homeManager;
    private WarpManager warpManager;
    private CommandManager commandManager;
    private MoneyManager moneyManager;
    private PvPArenaManager arenaManager;
    private RouletteManager rouletteManager;
    private HealManager healManager;

    private TeleportUtil teleportUtil;

    @Override
    public void onEnable() {

        // START - VÝPIS
        getLogger().info("          _______  _______  _        _______  _        ______  ");
        getLogger().info("|\\     /|(  ____ )(  ____ \\( \\      (  ___  )( (    /|(  __  \\ ");
        getLogger().info("| )   ( || (    )|| (    \\/| (      | (   ) ||  \\  ( || (  \\  )");
        getLogger().info("| (___) || (____)|| (_____ | |      | (___) ||   \\ | || |   ) |");
        getLogger().info("|  ___  ||  _____)(_____  )| |      |  ___  || (\\ \\) || |   | |");
        getLogger().info("| (   ) || (            ) || |      | (   ) || | \\   || |   ) |");
        getLogger().info("| )   ( || )      /\\____) || (____/\\| )   ( || )  \\  || (__/  )");
        getLogger().info("|/     \\||/       \\_______)(_______/|/     \\||/    )_)(______/ ");
        getLogger().info(" ");
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("Author: FloriXak");
        getLogger().info("Created for HPSLand.eu");


        // MANAGERY, UTILITY A OSTATNÍ
        plugin = this;

        this.configManager = new ConfigManager();
        this.configManager.loadFiles(this);

        this.scoreboardManager = new ScoreboardManager(this);
        this.itemManager = new ItemManager(this);
        this.teleportUtil = new TeleportUtil(this);
        this.hpsCore = (HpsCore) Bukkit.getServer().getPluginManager().getPlugin("HpsCore");
        this.actionManager = new ActionManager(this);
        this.inventoryManager = new InventoryManager();
        this.inventoryManager.onEnable(this);
        this.jobsManager = new JobsManager(this);
        this.kitsManager = new KitsManager(this);
        this.joinManager = new JoinManager(this);
        this.spawnManager = new SpawnManager(this);
        this.homeManager = new HomeManager(this);
        this.warpManager = new WarpManager(this);
        this.commandManager = new CommandManager(this);
        this.moneyManager = new MoneyManager();
        this.arenaManager = new PvPArenaManager(this);
        this.rouletteManager = new RouletteManager(this);
        this.healManager = new HealManager(this);

        this.SQL = new MySQL();
        this.data = new SQLGetter(this);

        // MYSQL PŘIPOJENÍ
        connectToDatabase();

        // REGISTRACE DEPENDENCY
        if (!setupEconomy()) {
            System.out.println("&c-------------------------------------------");
            System.out.println("&cNo economy plugin found. Disabling Vault.");
            System.out.println("&c-------------------------------------------");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = provider.getProvider();
        }

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderExp(this).register();
        }

        getCommand("tpa").setExecutor(new Tpa(this));
        getCommand("tpaccept").setExecutor(new Tpa(this));
        getCommand("tpdeny").setExecutor(new Tpa(this));
        getCommand("tpblock").setExecutor(new Tpa(this));

        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        getServer().getPluginManager().registerEvents(new RespawnListener(this), this);
        getServer().getPluginManager().registerEvents(new ValidCommand(this), this);
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockDestroyListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
        getServer().getPluginManager().registerEvents(new SpecialMobSpawnListener(this), this);
        getServer().getPluginManager().registerEvents(new DamageListener(this), this);
        getServer().getPluginManager().registerEvents(new ExplosionListener(this), this);
        getServer().getPluginManager().registerEvents(new DropItemListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        getServer().getPluginManager().registerEvents(new RouletteManager(this), this);
        getServer().getPluginManager().registerEvents(new JobsListener(this), this);
        getServer().getPluginManager().registerEvents(new Home(this), this);
//        getServer().getPluginManager().registerEvents(new Back(), this);
    }

    @Override
    public void onDisable() {
        this.SQL = new MySQL();
        this.data = new SQLGetter(this);
        SQL.disconnect();
    }

    private void connectToDatabase() {
        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getLogger().info(TextUtil.color("&cDatabase not connected"));
            e.printStackTrace();
        }
        if (SQL.isConnected()) {
            Bukkit.getLogger().info(TextUtil.color("&aDatabase is connected"));
            data.createTable();
        }
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    public static Economy getEconomy() {
        return econ;
    }
    public HpsCore getHpsCore() {
        return hpsCore;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }
    public ItemManager getItemManager() { return itemManager; }
    public ActionManager getActionManager() { return actionManager; }
    public InventoryManager getInventoryManager() { return inventoryManager; }
    public JobsManager getJobsManager() {
        return jobsManager;
    }
    public KitsManager getKitsManager() {
        return kitsManager;
    }
    public JoinManager getJoinManager() {
        return joinManager;
    }
    public SpawnManager getSpawnManager() {
        return spawnManager;
    }
    public HomeManager getHomeManager() {
        return homeManager;
    }
    public WarpManager getWarpManager() {
        return warpManager;
    }
    public MoneyManager getMoneyManager() {
        return moneyManager;
    }
    public PvPArenaManager getArenaManager() {
        return arenaManager;
    }
    public RouletteManager getRouletteManager() {
        return rouletteManager;
    }
    public HealManager getHealManager() {
        return healManager;
    }

    public TeleportUtil getTeleportUtil() {
        return teleportUtil;
    }
}