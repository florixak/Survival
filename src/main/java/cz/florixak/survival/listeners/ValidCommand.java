package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class ValidCommand implements Listener {

    private FileConfiguration config;

    private List<String> blockedCommands;

    public ValidCommand(Survival plugin){
        this.config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
        this.blockedCommands = config.getStringList("command_blocker");
    }

    @EventHandler
    public void onPlayerCommandProcess(PlayerCommandPreprocessEvent event){

        Player p = event.getPlayer();
        String msg = event.getMessage();
        String args[] = msg.split(" ");

        if (blockedCommands.contains(event.getMessage().toLowerCase())) {
            event.setCancelled(true);
            p.sendMessage(Messages.NO_PERM.toString());
            return;
        }

        if (Bukkit.getServer().getHelpMap().getHelpTopic(args[0]) == null){
            event.setCancelled(true);
            p.sendMessage(Messages.NO_PERM.toString());
            return;
        }
    }
}