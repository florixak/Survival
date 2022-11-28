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

    private Survival plugin;
//    private FileConfiguration config;

//    private boolean davky;

    public JoinListener(Survival plugin){
        this.plugin = plugin;
//        this.config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
//        this.davky = config.getBoolean("jobs.davky.enabled");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        JoinManager joinManager = plugin.getJoinManager();
        Player p = event.getPlayer();

        event.setJoinMessage(null);

        plugin.data.createPlayer(p);
        joinManager.playerJoin(p);
        joinManager.setScoreboard(p);
//        if (davky == true) {
//            joinManager.setDavky(p);
//        }
    }
}