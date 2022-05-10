package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.SpawnManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItemListener implements Listener {

    private boolean disabled;

    private int protection;

    Survival plugin;

    private FileConfiguration config;

    public DropItemListener(Survival plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event){

        config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();

        protection = config.getInt("spawn.protection");

        SpawnManager spawnManager = new SpawnManager();

        Player p = event.getPlayer();

        if (p.getLocation().getWorld().equals("world") && spawnManager.exist() && p.getLocation().distance(spawnManager.getLocation()) < protection) {
            event.setCancelled(true);
            p.sendMessage(Messages.CANT_DROP.toString());
        }
    }
}
