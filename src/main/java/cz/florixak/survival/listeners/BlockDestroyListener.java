package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.manager.WarpManager;
import cz.florixak.survival.utility.XSeries.XMaterial;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class BlockDestroyListener implements Listener {

    private Survival plugin;
    private WarpManager warpManager;
    private Random r;

    private HashMap<UUID, Integer> breakBlock = new HashMap<>();

    private int replace_wheat;
    private int replace_iron;
    private int replace_redstone;
    private int replace_lapis;
    private int replace_copper;
    private int replace_coal;

    private int protection;
    private Material destroyed;


    public BlockDestroyListener(Survival plugin) {
        this.plugin = plugin;
        this.warpManager = plugin.getWarpManager();
        this.protection = 50;
        this.r = new Random();

        this.replace_wheat = 5;
        this.replace_iron = 15;
        this.replace_redstone = 15;
        this.replace_lapis = 20;
        this.replace_copper = 10;
        this.replace_coal = 15;

        this.destroyed = Material.BEDROCK;
    }

    @EventHandler
    public void onDestroy(BlockBreakEvent event) {

        Player p = event.getPlayer();
        Block block = event.getBlock();

        if (p.getWorld().getName().equalsIgnoreCase("world")) {

            if (block.getLocation().distance(warpManager.getLocation("doly")) < protection
                    || block.getLocation().distance(warpManager.getLocation("mines")) < protection) {

                if (PlayerManager.isInBuilderMode(p)) return;
                event.setCancelled(true);

                if (block.getType() == XMaterial.WHEAT.parseMaterial()) {
                    int growthStatus = block.getData();
                    if (growthStatus < 7) {
                        p.sendMessage(Messages.WHEAT_GROW.toString());
                        return;
                    }
                    p.getInventory().addItem(new ItemStack(XMaterial.WHEAT.parseMaterial(), r.nextInt(2)));
                    p.getInventory().addItem(new ItemStack(XMaterial.WHEAT_SEEDS.parseMaterial(), r.nextInt(3)));
                    block.setType(Material.AIR, false);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.WHEAT, false);
                        }
                    }.runTaskLater(plugin, replace_wheat * 20L);
                    return;
                }

                if (block.getType() == Material.IRON_ORE) {
                    p.getInventory().addItem(new ItemStack(XMaterial.RAW_IRON.parseMaterial()));
                    block.setType(destroyed, false);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.IRON_ORE, false);
                        }
                    }.runTaskLater(plugin, replace_iron * 20L);
                    return;
                }
                if (block.getType() == Material.REDSTONE_ORE) {
                    p.getInventory().addItem(new ItemStack(Material.REDSTONE));
                    block.setType(Material.COBBLESTONE, false);
                    int xp = r.nextInt(4) + 1;
                    p.giveExp(xp);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.1F, 1);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.getLocation().getBlock().setType(Material.REDSTONE_ORE, false);
                        }
                    }.runTaskLater(plugin, replace_redstone * 20L);
                    return;
                }
                if (block.getType() == Material.LAPIS_ORE) {
                    p.getInventory().addItem(new ItemStack(Material.LAPIS_LAZULI));
                    block.setType(destroyed, false);
                    int xp = r.nextInt(4) + 1;
                    p.giveExp(xp);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.1F, 1);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.LAPIS_ORE, false);
                        }
                    }.runTaskLater(plugin, replace_lapis * 20L);
                    return;
                }
                if (block.getType() == XMaterial.COPPER_ORE.parseMaterial()) {
                    p.getInventory().addItem(new ItemStack(XMaterial.COPPER_ORE.parseMaterial()));
                    block.setType(Material.BEDROCK, false);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(XMaterial.COPPER_ORE.parseMaterial(), false);
                        }
                    }.runTaskLater(plugin, replace_copper * 20L);
                    return;
                }
                if (block.getType() == Material.COAL_ORE) {
                    p.getInventory().addItem(new ItemStack(Material.COAL));
                    block.setType(destroyed, false);
                    int xp = r.nextInt(1) + 1;
                    p.giveExp(xp);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.1F, 1);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.COAL_ORE, false);
                        }
                    }.runTaskLater(plugin, replace_coal * 20L);
                    return;
                }

                if (block.getType() == Material.GRAVEL) {
                    int chance = r.nextInt(100) + 1;
                    if (chance > 40) p.getInventory().addItem(new ItemStack(Material.GRAVEL));
                    else p.getInventory().addItem(new ItemStack(Material.FLINT));
                    block.setType(destroyed, false);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.GRAVEL, false);
                        }
                    }.runTaskLater(plugin, replace_coal * 20L);
                    return;
                }

                if (!breakBlock.containsKey(p.getUniqueId())) {
                    p.sendMessage(Messages.BLOCK_CANT_DESTROY.toString());
                    breakBlock.put(p.getUniqueId(), 5);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (breakBlock.get(p.getUniqueId()) == 0) {
                                breakBlock.remove(p.getUniqueId());
                                cancel();
                                return;
                            }
                            breakBlock.put(p.getUniqueId(), breakBlock.get(p.getUniqueId()) - 1);
                        }
                    }.runTaskTimerAsynchronously(plugin, 0L, 20L);

                }
            }
        }
    }
}