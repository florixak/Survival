package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.manager.JoinManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private boolean davky;

    FileConfiguration config;

    Survival plugin;

    public JoinListener(Survival plugin){
        this.plugin = plugin;
        config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        davky = config.getBoolean("jobs.davky.enabled");

        JoinManager joinManager = new JoinManager();
        Player p = event.getPlayer();

        event.setJoinMessage(null);

        plugin.data.createPlayer(p);
        joinManager.playerJoin(p);
        joinManager.setScoreboard(p);
        if (davky == true) {
            joinManager.setDavky(p);
        }
    }
}