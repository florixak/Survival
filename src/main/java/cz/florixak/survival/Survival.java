package cz.florixak.survival;

import cz.florixak.survival.action.ActionManager;
import cz.florixak.survival.commands.Roulette;
import cz.florixak.survival.commands.home.Home;
import cz.florixak.survival.commands.tpa.TpaCommand;
import cz.florixak.survival.config.ConfigManager;
import cz.florixak.survival.inventory.InventoryManager;
import cz.florixak.survival.listeners.*;
import cz.florixak.survival.manager.ItemManager;
import cz.florixak.survival.manager.JobsManager;
import cz.florixak.survival.manager.KitsManager;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import cz.florixak.survival.manager.scoreboardManager.ScoreboardManager;
import cz.florixak.survival.sql.MySQL;
import cz.florixak.survival.sql.SQLGetter;
import cz.florixak.survival.utility.TeleportUtil;
import cz.florixak.survival.utility.TextUtil;
import net.luckperms.api.LuckPerms;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public final class Survival extends JavaPlugin {

    private static Economy econ = null;
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

        configManager = new ConfigManager();
        configManager.loadFiles(this);

        this.SQL = new MySQL();
        this.data = new SQLGetter(this);

        scoreboardManager = new ScoreboardManager(this);
        itemManager = new ItemManager(this);
        TeleportUtil yeet = new TeleportUtil(this);
        actionManager = new ActionManager(this);
        inventoryManager = new InventoryManager();
        inventoryManager.onEnable(this);
        jobsManager = new JobsManager(this);
        kitsManager = new KitsManager();


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


        // MYSQL PŘIPOJENÍ
        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getLogger().info(TextUtil.color("&cDatabase not connected"));
        }
        if (SQL.isConnected()) {
            Bukkit.getLogger().info(TextUtil.color("&aDatabase is connected"));
            data.createTable();
        }


        // REGISTRACE PŘÍKAZŮ/EVENTŮ
        String packageName = getClass().getPackage().getName();
        for (Class<? extends PluginCommand> clazz : new Reflections(packageName + ".commands").getSubTypesOf(PluginCommand.class)) {
            try {
                PluginCommand pluginCommand = clazz.getDeclaredConstructor().newInstance();
                getCommand(pluginCommand.getCommandInfo().name()).setExecutor(pluginCommand);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        getCommand("tpa").setExecutor(new TpaCommand());
        getCommand("tpaccept").setExecutor(new TpaCommand());
        getCommand("tpdeny").setExecutor(new TpaCommand());
        getCommand("tpblock").setExecutor(new TpaCommand());

        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        getServer().getPluginManager().registerEvents(new RespawnListener(this), this);
        getServer().getPluginManager().registerEvents(new ValidCommand(this), this);
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockDestroyListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new SpecialMobSpawnListener(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getServer().getPluginManager().registerEvents(new ExplosionListener(), this);
        getServer().getPluginManager().registerEvents(new DropItemListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new Roulette(), this);
        getServer().getPluginManager().registerEvents(new JobsListener(this), this);
        getServer().getPluginManager().registerEvents(new Home(), this);
//        getServer().getPluginManager().registerEvents(new Back(), this);
    }

    @Override
    public void onDisable() {
        this.SQL = new MySQL();
        this.data = new SQLGetter(this);
        SQL.disconnect();
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
}