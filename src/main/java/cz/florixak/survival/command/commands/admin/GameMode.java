package cz.florixak.survival.command.commands.admin;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameMode extends Command {

    public GameMode(Survival plugin) {
        super(plugin);

        this.addAlias("gamemode");
        this.addAlias("game");
        this.addAlias("gm");
        this.addRequirement("survival.gamemode");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {

        Player p = (Player) sender;

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
        return true;
    }
}