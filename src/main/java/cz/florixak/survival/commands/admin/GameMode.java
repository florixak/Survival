package cz.florixak.survival.commands.admin;

import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "game", permission = "gamemode", requiresPlayer = true)
public class GameMode extends PluginCommand {

    @Override
    public void perform(Player p, String[] args){

        if (args.length == 0){
            p.sendMessage(Messages.NO_PERM.toString());
        } else if (args[0].equalsIgnoreCase("c")){
            p.setGameMode(org.bukkit.GameMode.CREATIVE);
            p.sendMessage(Messages.GAMEMODE_CHANGED.toString().replace("%gamemode%", p.getGameMode().name()));
        } else if (args[0].equalsIgnoreCase("s")){
            p.setGameMode(org.bukkit.GameMode.SURVIVAL);
            p.sendMessage(Messages.GAMEMODE_CHANGED.toString().replace("%gamemode%", p.getGameMode().name()));
        } else if (args[0].equalsIgnoreCase("sp")){
            p.setGameMode(org.bukkit.GameMode.SPECTATOR);
            p.sendMessage(Messages.GAMEMODE_CHANGED.toString().replace("%gamemode%", p.getGameMode().name()));
        } else if (args[0].equalsIgnoreCase("a")){
            p.setGameMode(org.bukkit.GameMode.ADVENTURE);
            p.sendMessage(Messages.GAMEMODE_CHANGED.toString().replace("%gamemode%", p.getGameMode().name()));
        } else {
            p.sendMessage(Messages.NO_PERM.toString());
        }
    }
}