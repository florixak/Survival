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
    private JoinManager joinManager;

    public JoinListener(Survival plugin){
        this.plugin = plugin;
        this.joinManager = plugin.getJoinManager();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();

        event.setJoinMessage(null);

        plugin.SQL.signPlayerInToDatabase(p);
        joinManager.playerJoin(p);
        joinManager.setScoreboard(p);
    }
}