package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.SpawnManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItemListener implements Listener {

    private SpawnManager spawnManager;

    private int protection;

    public DropItemListener(Survival plugin){
        this.spawnManager = plugin.getSpawnManager();
        this.protection = spawnManager.getSpawnProtection();
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event){

        Player p = event.getPlayer();

        if (p.getLocation().getWorld().equals("world") && spawnManager.exist() && p.getLocation().distance(spawnManager.getLocation()) < protection) {
            event.setCancelled(true);
            p.sendMessage(Messages.CANT_DROP.toString());
        }
    }
}
