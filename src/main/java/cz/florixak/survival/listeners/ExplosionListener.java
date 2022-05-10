package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.manager.SpawnManager;
import org.bukkit.block.data.type.TNT;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionListener implements Listener {

    private int protection;

    FileConfiguration config;

    @EventHandler
    public void onCreeperBoom(EntityExplodeEvent event){

        config = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();

        protection = config.getInt("spawn.protection");

        SpawnManager spawnManager = new SpawnManager();

        if (spawnManager.exist()) {
            if (event.getEntity() instanceof Creeper) {
                if (event.getEntity().getLocation().distance(spawnManager.getLocation()) < protection){
                    event.setCancelled(true);
                }
            }
            if (event.getEntity() instanceof TNT) {
                if (event.getEntity().getLocation().distance(spawnManager.getLocation()) < protection) {
                    event.setCancelled(true);
                }
            }
            if (event.getEntity() instanceof Wither) {
                if (event.getEntity().getLocation().distance(spawnManager.getLocation()) < protection) {
                    event.setCancelled(true);
                }
            }
            if (event.getEntity() instanceof DragonFireball) {
                if (event.getEntity().getLocation().distance(spawnManager.getLocation()) < protection) {
                    event.setCancelled(true);
                }
            }
            if (event.getEntity() instanceof WitherSkull) {
                if (event.getEntity().getLocation().distance(spawnManager.getLocation()) < protection) {
                    event.setCancelled(true);
                }
            }
        }
    }
}