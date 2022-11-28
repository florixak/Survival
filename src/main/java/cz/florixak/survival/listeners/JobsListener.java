package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.JobsManager;
import cz.florixak.survival.manager.SpawnManager;
import cz.florixak.survival.utility.Utils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.*;

public class JobsListener implements Listener {

    private List<Material> miner = Arrays.asList(Material.COBBLESTONE, Material.COBBLESTONE_SLAB, Material.COBBLESTONE_STAIRS, Material.COBBLESTONE_WALL, Material.STONE,
            Material.COAL_ORE, Material.IRON_ORE, Material.DIAMOND_ORE, Material.IRON_ORE, Material.COPPER_ORE, Material.REDSTONE_ORE, Material.DIORITE, Material.DIORITE_SLAB,
            Material.DIORITE_STAIRS, Material.DIORITE_WALL, Material.ANDESITE, Material.ANDESITE_SLAB, Material.ANDESITE_STAIRS, Material.ANDESITE_WALL, Material.GRANITE,
            Material.GRANITE_SLAB, Material.GRANITE_STAIRS, Material.GRANITE_WALL, Material.MOSSY_COBBLESTONE, Material.INFESTED_COBBLESTONE, Material.MOSSY_COBBLESTONE_SLAB,
            Material.MOSSY_COBBLESTONE_STAIRS, Material.DEEPSLATE, Material.COBBLED_DEEPSLATE, Material.DEEPSLATE_COAL_ORE, Material.DEEPSLATE_GOLD_ORE, Material.DEEPSLATE_IRON_ORE,
            Material.DEEPSLATE_LAPIS_ORE, Material.DEEPSLATE_EMERALD_ORE, Material.DEEPSLATE_DIAMOND_ORE, Material.DEEPSLATE_COPPER_ORE, Material.DEEPSLATE_REDSTONE_ORE,
            Material.DRIPSTONE_BLOCK, Material.POINTED_DRIPSTONE, Material.TUFF);

    private List<Material> digger = Arrays.asList(Material.SAND, Material.SNOW, Material.SNOW_BLOCK, Material.POWDER_SNOW, Material.GRAVEL, Material.RED_SAND,
            Material.DIRT, Material.COARSE_DIRT, Material.PODZOL, Material.ROOTED_DIRT, Material.CRIMSON_NYLIUM, Material.WARPED_NYLIUM, Material.GRASS_BLOCK);

    private List<Material> woodcutter = Arrays.asList(Material.OAK_LOG, Material.DARK_OAK_LOG, Material.BIRCH_LOG, Material.SPRUCE_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG,
            Material.STRIPPED_OAK_LOG, Material.STRIPPED_DARK_OAK_LOG, Material.STRIPPED_BIRCH_LOG, Material.STRIPPED_SPRUCE_LOG, Material.STRIPPED_JUNGLE_LOG, Material.STRIPPED_ACACIA_LOG,
            Material.OAK_PLANKS, Material.DARK_OAK_PLANKS, Material.BIRCH_PLANKS, Material.SPRUCE_PLANKS, Material.JUNGLE_PLANKS, Material.ACACIA_PLANKS);

    private List<Material> farmer = Arrays.asList(Material.WHEAT, Material.WHEAT_SEEDS, Material.BEETROOT_SEEDS, Material.BEETROOT, Material.BEETROOTS, Material.MELON_SEEDS,
            Material.MELON, Material.PUMPKIN_SEEDS, Material.PUMPKIN, Material.CARROT, Material.CARROTS, Material.POTATO, Material.POTATOES, Material.SWEET_BERRIES,
            Material.SWEET_BERRY_BUSH);

    private HashMap<UUID, Integer> jobMoney = new HashMap<>();

    private Survival plugin;
    private SpawnManager spawnManager;
    private Economy economy;

    private FileConfiguration config;

    private Random ran;
    private DecimalFormat format;

    private int jobMoneyDelay;

    private int max_money;
    private int min_money;

    private int protection;

    public JobsListener(Survival plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
        this.spawnManager = plugin.getSpawnManager();
        this.protection = spawnManager.getSpawnProtection();
        this.economy = Survival.getEconomy();

        this.ran = new Random();
        this.format = new DecimalFormat("##,###,##0.00");

        this.min_money = 1;
        this.jobMoneyDelay = 3;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player p = event.getPlayer();

        if (p.getLocation().distance(spawnManager.getLocation()) < protection) return;

        if (JobsManager.isMiner(p.getUniqueId())) {
            for (Material material : miner) {
                if (event.getBlock().getType().equals(material)) {
                    max_money = config.getInt("jobs.salary.miner");
                    double amount = min_money + ran.nextDouble() * (max_money - min_money);
                    String formatted = format.format(amount);

                    plugin.data.addMinerBlock(p.getUniqueId(), 1);

                    if (plugin.data.getMinerBlocks(p.getUniqueId()) == 250) {
                        Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
                    }
                    if (plugin.data.getMinerBlocks(p.getUniqueId()) == 500) {
                        Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
                    }
                    if (plugin.data.getMinerBlocks(p.getUniqueId()) == 750) {
                        Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
                    }

                    if (!jobMoney.containsKey(p.getUniqueId())) {

                        if (JobsManager.isMiner(p.getUniqueId())) amount *= 1.5;
                        if (JobsManager.isMiner(p.getUniqueId())) amount *= 1.75;
                        if (JobsManager.isMiner(p.getUniqueId())) amount *= 2;

                        economy.depositPlayer(p, amount);
                        Utils.sendHotbarMessage(p, Messages.JOBS_GAIN_MONEY.toString().replace("%money%", "" + formatted));
                        jobMoney.put(p.getUniqueId(), jobMoneyDelay);

                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                if (jobMoney.get(p.getUniqueId()) == 0){
                                    jobMoney.remove(p.getUniqueId());
                                    cancel();
                                    return;
                                }
                                jobMoney.put(p.getUniqueId(), jobMoney.get(p.getUniqueId())-1);
                            }
                        }.runTaskTimerAsynchronously(plugin, 0L, 20L);
                    }
                }
            }
        }

        if (JobsManager.isDigger(p.getUniqueId())) {
            for (Material material : digger) {
                if (event.getBlock().getType().equals(material)) {
                    max_money = config.getInt("jobs.salary.digger");
                    double amount = min_money + ran.nextDouble() * (max_money - min_money);
                    String formatted = format.format(amount);

                    plugin.data.addDiggerBlock(p.getUniqueId(), 1);

                    if (plugin.data.getDiggerBlocks(p.getUniqueId()) == 250) {
                        Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
                    }
                    if (plugin.data.getDiggerBlocks(p.getUniqueId()) == 500) {
                        Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
                    }
                    if (plugin.data.getDiggerBlocks(p.getUniqueId()) == 750) {
                        Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
                    }

                    if (!jobMoney.containsKey(p.getUniqueId())) {

                        if (JobsManager.isDigger(p.getUniqueId())) amount *= 1.5;
                        if (JobsManager.isDigger(p.getUniqueId())) amount *= 1.75;
                        if (JobsManager.isDigger(p.getUniqueId())) amount *= 2;

                        economy.depositPlayer(p, amount);
                        Utils.sendHotbarMessage(p, Messages.JOBS_GAIN_MONEY.toString().replace("%money%", "" + formatted));

                        jobMoney.put(p.getUniqueId(), jobMoneyDelay);
                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                if (jobMoney.get(p.getUniqueId()) == 0){
                                    jobMoney.remove(p.getUniqueId());
                                    cancel();
                                    return;
                                }
                                jobMoney.put(p.getUniqueId(), jobMoney.get(p.getUniqueId())-1);
                            }
                        }.runTaskTimerAsynchronously(plugin, 0L, 20L);
                    }
                }
            }
        }

        if (JobsManager.isWoodcutter(p.getUniqueId())) {
            for (Material material : woodcutter) {
                if (event.getBlock().getType().equals(material)) {
                    max_money = config.getInt("jobs.salary.woodcutter");
                    double amount = min_money + ran.nextDouble() * (max_money - min_money);
                    String formatted = format.format(amount);

                    plugin.data.addWoodcutterBlock(p.getUniqueId(), 1);

                    if (plugin.data.getMinerBlocks(p.getUniqueId()) == 250) {
                        Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
                    }
                    if (plugin.data.getMinerBlocks(p.getUniqueId()) == 500) {
                        Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
                    }
                    if (plugin.data.getMinerBlocks(p.getUniqueId()) == 750) {
                        Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
                    }

                    if (!jobMoney.containsKey(p.getUniqueId())) {

                        if (JobsManager.isWoodcutter(p.getUniqueId())) amount *= 1.5;
                        if (JobsManager.isWoodcutter(p.getUniqueId())) amount *= 1.75;
                        if (JobsManager.isWoodcutter(p.getUniqueId())) amount *= 2;

                        economy.depositPlayer(p, amount);
                        Utils.sendHotbarMessage(p, Messages.JOBS_GAIN_MONEY.toString().replace("%money%", "" + formatted));

                        jobMoney.put(p.getUniqueId(), jobMoneyDelay);
                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                if (jobMoney.get(p.getUniqueId()) == 0){
                                    jobMoney.remove(p.getUniqueId());
                                    cancel();
                                    return;
                                }
                                jobMoney.put(p.getUniqueId(), jobMoney.get(p.getUniqueId())-1);
                            }
                        }.runTaskTimerAsynchronously(plugin, 0L, 20L);
                    }
                }
            }
        }

        if (JobsManager.isFarmer(p.getUniqueId())) {
            for (Material material : farmer) {
                if (event.getBlock().getType() == material) {
                    max_money = config.getInt("jobs.salary.farmer");
                    double amount = min_money + ran.nextDouble() * (max_money - min_money);
                    String formatted = format.format(amount);

                    plugin.data.addFarmerBlock(p.getUniqueId(), 1);

                    if (plugin.data.getFarmerBlocks(p.getUniqueId()) == 250) {
                        Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
                    }
                    if (plugin.data.getFarmerBlocks(p.getUniqueId()) == 500) {
                        Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
                    }
                    if (plugin.data.getFarmerBlocks(p.getUniqueId()) == 750) {
                        Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
                    }

                    if (!jobMoney.containsKey(p.getUniqueId())) {

                        if (JobsManager.isFarmer(p.getUniqueId())) amount *= 1.5;
                        if (JobsManager.isFarmer(p.getUniqueId())) amount *= 1.75;
                        if (JobsManager.isFarmer(p.getUniqueId())) amount *= 2;

                        economy.depositPlayer(p, amount);
                        Utils.sendHotbarMessage(p, Messages.JOBS_GAIN_MONEY.toString().replace("%money%", "" + formatted));

                        jobMoney.put(p.getUniqueId(), jobMoneyDelay);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (jobMoney.get(p.getUniqueId()) == 0) {
                                    jobMoney.remove(p.getUniqueId());
                                    cancel();
                                    return;
                                }
                                jobMoney.put(p.getUniqueId(), jobMoney.get(p.getUniqueId()) - 1);
                            }
                        }.runTaskTimerAsynchronously(plugin, 0L, 20L);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player p = event.getPlayer();

        if (p.getLocation().distance(spawnManager.getLocation()) < protection) return;

        if (JobsManager.isBuilder(p.getUniqueId())) {
            max_money = config.getInt("jobs.salary.builder");
            double amount = min_money + ran.nextDouble() * (max_money - min_money);
            String formatted = format.format(amount);

            plugin.data.addBuilderBlock(p.getUniqueId(), 1);

            if (plugin.data.getBuilderBlocks(p.getUniqueId()) == 250) {
                Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
            }
            if (plugin.data.getBuilderBlocks(p.getUniqueId()) == 500) {
                Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
            }
            if (plugin.data.getBuilderBlocks(p.getUniqueId()) == 750) {
                Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
            }

            if (!jobMoney.containsKey(p.getUniqueId())) {

                if (JobsManager.isBuilder(p.getUniqueId())) amount *= 1.5;
                if (JobsManager.isBuilder(p.getUniqueId())) amount *= 1.75;
                if (JobsManager.isBuilder(p.getUniqueId())) amount *= 2;

                economy.depositPlayer(p, amount);
                Utils.sendHotbarMessage(p, Messages.JOBS_GAIN_MONEY.toString().replace("%money%", "" + formatted));

                jobMoney.put(p.getUniqueId(), jobMoneyDelay);

                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if (jobMoney.get(p.getUniqueId()) == 0){
                            jobMoney.remove(p.getUniqueId());
                            cancel();
                            return;
                        }
                        jobMoney.put(p.getUniqueId(), jobMoney.get(p.getUniqueId())-1);
                    }
                }.runTaskTimerAsynchronously(plugin, 0L, 20L);
            }
        }

        if (JobsManager.isFarmer(p.getUniqueId())) {
            for (Material material : farmer) {
                if (event.getBlock().getType().equals(material)) {
                    max_money = config.getInt("jobs.salary.farmer");
                    double amount = min_money + ran.nextDouble() * (max_money - min_money);
                    String formatted = format.format(amount);

                    plugin.data.addFarmerBlock(p.getUniqueId(), 1);

                    if (plugin.data.getFarmerBlocks(p.getUniqueId()) >= 250) {
                        Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
                    }
                    if (plugin.data.getFarmerBlocks(p.getUniqueId()) >= 500) {
                        Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
                    }
                    if (plugin.data.getFarmerBlocks(p.getUniqueId()) >= 750) {
                        Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
                    }

                    if (!jobMoney.containsKey(p.getUniqueId())) {

                        if (JobsManager.isFarmer(p.getUniqueId())) amount *= 1.5;
                        if (JobsManager.isFarmer(p.getUniqueId())) amount *= 1.75;
                        if (JobsManager.isFarmer(p.getUniqueId())) amount *= 2;

                        economy.depositPlayer(p, amount);
                        Utils.sendHotbarMessage(p, Messages.JOBS_GAIN_MONEY.toString().replace("%money%", "" + formatted));

                        jobMoney.put(p.getUniqueId(), jobMoneyDelay);
                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                if (jobMoney.get(p.getUniqueId()) == 0){
                                    jobMoney.remove(p.getUniqueId());
                                    cancel();
                                    return;
                                }
                                jobMoney.put(p.getUniqueId(), jobMoney.get(p.getUniqueId())-1);
                            }
                        }.runTaskTimerAsynchronously(plugin, 0L, 20L);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        if (!(event.getEntity().getKiller() instanceof Player)) return;

        Player p = event.getEntity().getKiller();

        if (p.getLocation().distance(spawnManager.getLocation()) < protection) return;

        if (JobsManager.isKiller(p.getUniqueId())) {
            double amount = min_money + ran.nextDouble() * (max_money - min_money);
            String formatted = format.format(amount);

            plugin.data.addKillerKill(p.getUniqueId(), 1);

            if (plugin.data.getKillerKills(p.getUniqueId()) == 250) {
                Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
            }
            if (plugin.data.getKillerKills(p.getUniqueId()) == 500) {
                Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
            }
            if (plugin.data.getKillerKills(p.getUniqueId()) == 750) {
                Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
            }

            if (!jobMoney.containsKey(p.getUniqueId())) {

                if (JobsManager.isKiller(p.getUniqueId())) amount *= 1.5;
                if (JobsManager.isKiller(p.getUniqueId())) amount *= 1.75;
                if (JobsManager.isKiller(p.getUniqueId())) amount *= 2;

                economy.depositPlayer(p, amount);
                Utils.sendHotbarMessage(p, Messages.JOBS_GAIN_MONEY.toString().replace("%money%", "" + formatted));

                jobMoney.put(p.getUniqueId(), jobMoneyDelay);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (jobMoney.get(p.getUniqueId()) == 0) {
                            jobMoney.remove(p.getUniqueId());
                            cancel();
                            return;
                        }
                        jobMoney.put(p.getUniqueId(), jobMoney.get(p.getUniqueId()) - 1);
                    }
                }.runTaskTimerAsynchronously(plugin, 0L, 20L);
            }
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        Player p = (Player) event.getWhoClicked();

        if (p.getLocation().distance(spawnManager.getLocation()) < protection) return;

        if (JobsManager.isCrafter(p.getUniqueId())){
            max_money = config.getInt("jobs.salary.crafter");
            double amount = min_money + ran.nextDouble() * (max_money - min_money);
            String formatted = format.format(amount);

            plugin.data.addCrafterItem(p.getUniqueId(), 1);

            if (plugin.data.getCrafterItems(p.getUniqueId()) == 250) {
                Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
            }
            if (plugin.data.getCrafterItems(p.getUniqueId()) == 500) {
                Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
            }
            if (plugin.data.getCrafterItems(p.getUniqueId()) == 750) {
                Utils.sendHotbarMessage(p, Messages.JOBS_LEVEL_UP.toString());
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 0.2f);
            }

            if (!jobMoney.containsKey(p.getUniqueId())) {

                if (JobsManager.getJobLevel(p.getUniqueId()) == "1") amount *= 1.5;
                if (JobsManager.getJobLevel(p.getUniqueId()) == "2") amount *= 1.75;
                if (JobsManager.getJobLevel(p.getUniqueId()) == "3") amount *= 2;

                economy.depositPlayer(p, amount);
                Utils.sendHotbarMessage(p, Messages.JOBS_GAIN_MONEY.toString().replace("%money%", "" + formatted));

                jobMoney.put(p.getUniqueId(), jobMoneyDelay);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if (jobMoney.get(p.getUniqueId()) == 0){
                            jobMoney.remove(p.getUniqueId());
                            cancel();
                            return;
                        }
                        jobMoney.put(p.getUniqueId(), jobMoney.get(p.getUniqueId())-1);
                    }
                }.runTaskTimerAsynchronously(plugin, 0L, 20L);
            }
        }
    }
}