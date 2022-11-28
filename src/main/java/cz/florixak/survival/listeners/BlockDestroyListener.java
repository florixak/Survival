package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.SpawnManager;
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

    private SpawnManager spawnManager;

    private HashMap<UUID, Integer> breakWheat = new HashMap<>();
    private HashMap<UUID, Integer> breakBlock = new HashMap<>();

    private int replace_wheat;
    private int replace_iron;
    private int replace_redstone;
    private int replace_lapis;
    private int replace_copper;
    private int replace_coal;

    private int protection;

    public BlockDestroyListener(Survival plugin) {
        this.spawnManager = plugin.getSpawnManager();
        this.protection = spawnManager.getSpawnProtection();
    }

    @EventHandler
    public void onDestroy(BlockBreakEvent event) {

        Player p = event.getPlayer();
        Block block = event.getBlock();

        replace_wheat = 5;
        replace_iron = 15;
        replace_redstone = 15;
        replace_lapis = 20;
        replace_copper = 10;
        replace_coal = 15;

        if (p.getWorld().getName().equalsIgnoreCase("world")) {

            if (block.getType() == XMaterial.WHEAT.parseMaterial() && block.getLocation().distance(spawnManager.getLocation()) < protection) {
                int growthStatus = block.getData();

                if (growthStatus < 7) {
                    event.setCancelled(true);
                    if (!breakWheat.containsKey(p.getUniqueId())) {
                        p.sendMessage(Messages.WHEAT_GROW.toString());
                        breakWheat.put(p.getUniqueId(), 3);

                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                if (breakWheat.get(p.getUniqueId()) == 0){
                                    breakWheat.remove(p.getUniqueId());
                                    cancel();
                                    return;
                                }
                                breakWheat.put(p.getUniqueId(), breakWheat.get(p.getUniqueId())-1);
                            }
                        }.runTaskTimerAsynchronously(Survival.plugin, 0L, 20L);
                    }
                    return;
                }
                event.setCancelled(true);
                p.getInventory().addItem(new ItemStack(Material.WHEAT));
                block.setType(Material.AIR, false);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        block.setType(Material.WHEAT, false);
                    }
                }.runTaskLater(Survival.plugin, replace_wheat*20);
                return;
            }

            if (block.getType() == Material.IRON_ORE && block.getLocation().distance(spawnManager.getLocation()) < protection) {
                event.setCancelled(true);
                p.getInventory().addItem(new ItemStack(XMaterial.RAW_IRON.parseMaterial()));
                block.setType(Material.COBBLESTONE, false);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        block.setType(Material.IRON_ORE, false);
                    }
                }.runTaskLater(Survival.plugin, replace_iron*20);
                return;
            }
            if (block.getType() == Material.REDSTONE_ORE && block.getLocation().distance(spawnManager.getLocation()) < protection) {
                event.setCancelled(true);
                p.getInventory().addItem(new ItemStack(Material.REDSTONE));
                block.setType(Material.COBBLESTONE, false);
                p.giveExp(5);
                Random r = new Random();
                int am = r.nextInt(5);
                if (am != 0) {
                    p.giveExp(am);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.1F, 1);
                }
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        block.getLocation().getBlock().setType(Material.REDSTONE_ORE, false);
                    }
                }.runTaskLater(Survival.plugin, replace_redstone*20);
                return;
            }
            if (block.getType() == Material.LAPIS_ORE && block.getLocation().distance(spawnManager.getLocation()) < protection) {
                event.setCancelled(true);
                p.getInventory().addItem(new ItemStack(Material.LAPIS_LAZULI));
                block.setType(Material.COBBLESTONE, false);
                Random r = new Random();
                int am = r.nextInt(5);
                if (am != 0) {
                    p.giveExp(am);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.1F, 1);
                }
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        block.setType(Material.LAPIS_ORE, false);
                    }
                }.runTaskLater(Survival.plugin, replace_lapis*20);
                return;
            }
            if (block.getType() == XMaterial.COPPER_ORE.parseMaterial() && block.getLocation().distance(spawnManager.getLocation()) < protection) {
                event.setCancelled(true);
                p.getInventory().addItem(new ItemStack(XMaterial.COPPER_ORE.parseMaterial()));
                block.setType(Material.COBBLESTONE, false);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        block.setType(XMaterial.COPPER_ORE.parseMaterial(), false);
                    }
                }.runTaskLater(Survival.plugin, replace_copper*20);
                return;
            }
            if (block.getType() == Material.COAL_ORE && block.getLocation().distance(spawnManager.getLocation()) < protection) {
                event.setCancelled(true);
                p.getInventory().addItem(new ItemStack(Material.COAL));
                block.setType(Material.COBBLESTONE, false);
                Random r = new Random();
                int am = r.nextInt(2);
                if (am != 0) {
                    p.giveExp(am);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.1F, 1);
                }
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        block.setType(Material.COAL_ORE, false);
                    }
                }.runTaskLater(Survival.plugin, replace_coal*20);
                return;
            }

            User user = LuckPermsProvider.get().getUserManager().getUser(p.getUniqueId());
            Group hbuild = LuckPermsProvider.get().getGroupManager().getGroup("sr.builder");

            if (block.getLocation().distance(spawnManager.getLocation()) < protection) {
                if (!(user.getPrimaryGroup().equals(hbuild.getName()) || p.getName().equals("FloriXak") || p.getName().equals("Romiiiiiiiii"))) {
                    event.setCancelled(true);
                    if (!breakBlock.containsKey(p.getUniqueId())) {
                        p.sendMessage(Messages.BLOCK_CANT_DESTROY.toString());
                        breakBlock.put(p.getUniqueId(), 5);
                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                if (breakBlock.get(p.getUniqueId()) == 0){
                                    breakBlock.remove(p.getUniqueId());
                                    cancel();
                                    return;
                                }
                                breakBlock.put(p.getUniqueId(), breakBlock.get(p.getUniqueId())-1);
                            }
                        }.runTaskTimerAsynchronously(Survival.plugin, 0L, 20L);
                    }
                }
            }
        }
    }
}