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

    private List<String> blockedCommands;

    Survival plugin;

    public ValidCommand(Survival plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerCommandProcess(PlayerCommandPreprocessEvent event){

        FileConfiguration config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();

        blockedCommands = config.getStringList("command_blocker");

        Player p = event.getPlayer();
        String msg = event.getMessage();
        String args[] = msg.split(" ");

        if (blockedCommands.contains(event.getMessage().toLowerCase())) {

            event.setCancelled(true);
            p.sendMessage(Messages.NO_PERM.toString());

        }

        if (Bukkit.getServer().getHelpMap().getHelpTopic(args[0]) == null){

            event.setCancelled(true);
            p.sendMessage(Messages.NO_PERM.toString());

        }
    }
}