package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.manager.SpawnManager;
import cz.florixak.survival.manager.WarpManager;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class BlockPlaceListener implements Listener {

    private SpawnManager spawnManager;
    private WarpManager warpManager;

    private HashMap<UUID, Integer> placeBlock = new HashMap<>();
    private int protection;

    public BlockPlaceListener(Survival plugin) {
        this.spawnManager = plugin.getSpawnManager();
        this.protection = spawnManager.getSpawnProtection();
        this.warpManager = plugin.getWarpManager();
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){

        Player p = event.getPlayer();
        Block block = event.getBlock();

        if (p.getWorld().getName().equalsIgnoreCase("world")){

            if (block.getLocation().distance(spawnManager.getLocation()) < protection) {

                if (PlayerManager.isInBuilderMode(p)) return;

                event.setCancelled(true);
                if (!placeBlock.containsKey(p.getUniqueId())) {
                    p.sendMessage(Messages.BLOCK_CANT_PLACE.toString());
                    placeBlock.put(p.getUniqueId(), 5);
                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            if (placeBlock.get(p.getUniqueId()) == 0){
                                placeBlock.remove(p.getUniqueId());
                                cancel();
                                return;
                            }
                            placeBlock.put(p.getUniqueId(), placeBlock.get(p.getUniqueId())-1);
                        }
                    }.runTaskTimerAsynchronously(Survival.plugin, 0L, 20L);
                }
            }
        }
    }

    @EventHandler
    public void onSpawnerPlace(BlockPlaceEvent e) {
        Block b = e.getBlockPlaced();
        ItemStack inh = e.getPlayer().getItemInHand();
        if (b != null && inh != null) {
            if (b.getType() == Material.SPAWNER && inh.getType() == Material.SPAWNER) {
                ItemMeta im = inh.getItemMeta();
                if (im.getDisplayName().equals("Zombie Spawner")) {
                    setSpawner(b, EntityType.ZOMBIE);
                } else if (im.getDisplayName().equals("Skeleton Spawner")){
                    setSpawner(b, EntityType.SKELETON);
                } else if (im.getDisplayName().equals("Spider Spawner")){
                    setSpawner(b, EntityType.SPIDER);
                } else if (im.getDisplayName().equals("Cave Spider Spawner")){
                    setSpawner(b, EntityType.SPIDER);
                } else if (im.getDisplayName().equals("Slime Spawner")){
                    setSpawner(b, EntityType.SLIME);
                } else if (im.getDisplayName().equals("Cow Spawner")){
                    setSpawner(b, EntityType.COW);
                } else if (im.getDisplayName().equals("Sheep Spawner")){
                    setSpawner(b, EntityType.SHEEP);
                } else if (im.getDisplayName().equals("Chicken Spawner")){
                    setSpawner(b, EntityType.SPIDER);
                } else if (im.getDisplayName().equals("Pig Spawner")){
                    setSpawner(b, EntityType.PIG);
                } else if (im.getDisplayName().equals("Pigman Spawner")){
                    setSpawner(b, EntityType.PIG);
                } else if (im.getDisplayName().equals("Horse Spawner")){
                    setSpawner(b, EntityType.HORSE);
                } else if (im.getDisplayName().equals("Mushroom Cow Spawner")){
                    setSpawner(b, EntityType.MUSHROOM_COW);
                }
            }
        }
    }

    public void setSpawner(Block block, EntityType ent) {
        BlockState blockState = block.getState();
        CreatureSpawner spawner = ((CreatureSpawner) blockState);
        spawner.setSpawnedType(ent);
        blockState.update();
    }

    @EventHandler
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {

        Material bucket = event.getBucket();
        Player p = event.getPlayer();

        if (p.getLocation().distance(spawnManager.getLocation()) < protection) {

            if (PlayerManager.isInBuilderMode(p)) return;

            if (bucket.toString().contains("LAVA")) {
                event.setCancelled(true);
                p.sendMessage(Messages.BLOCK_CANT_PLACE.toString());
            }
            if (bucket.toString().contains("WATER")) {
                event.setCancelled(true);
                p.sendMessage(Messages.BLOCK_CANT_PLACE.toString());
            }
        }
    }
}