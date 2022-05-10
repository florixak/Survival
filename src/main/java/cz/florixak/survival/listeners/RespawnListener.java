package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.SpawnManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Arrays;
import java.util.List;

public class RespawnListener implements Listener {

    private List<Material> bed = Arrays.asList(
            Material.RED_BED,
            Material.LIME_BED,
            Material.GREEN_BED,
            Material.YELLOW_BED,
            Material.BLUE_BED,
            Material.LIGHT_BLUE_BED,
            Material.BROWN_BED,
            Material.BLACK_BED,
            Material.CYAN_BED,
            Material.GRAY_BED,
            Material.LIGHT_GRAY_BED,
            Material.MAGENTA_BED,
            Material.PURPLE_BED,
            Material.ORANGE_BED,
            Material.PINK_BED,
            Material.WHITE_BED);

    Survival plugin;

    public RespawnListener(Survival plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){

        Player p = event.getPlayer();
        Location bed = p.getBedSpawnLocation();
        SpawnManager spawnManager = new SpawnManager();

        if (bed == null){
            event.setRespawnLocation(spawnManager.getLocation());

            p.teleport(spawnManager.getLocation());
            p.sendMessage(Messages.RESPAWN_NO_BED_SPAWN.toString());

        } else {

            p.teleport(bed);
        }
    }

    @EventHandler
    public void onBedClick(PlayerInteractEvent event){

        Player p = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK){

            for (Material material : bed){

                if (event.getClickedBlock().getType() == material){

                    p.setBedSpawnLocation(p.getLocation());
                    p.sendMessage(Messages.RESPAWN_BED_SPAWN_SET.toString());
                }
            }
        }
    }
}