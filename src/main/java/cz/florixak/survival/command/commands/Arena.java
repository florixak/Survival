package cz.florixak.survival.command.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PvPArenaManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Arena extends Command {

    private PvPArenaManager arenaManager;

    public Arena(Survival plugin) {
        super(plugin);
        this.arenaManager = plugin.getArenaManager();
        this.addAlias("arena");
        this.addAlias("pvparena");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;

        if (args[0].equalsIgnoreCase("set")){

            if (arenaManager.exist()) {
                p.sendMessage(Messages.PVPARENA_EXIST.toString());
                return true;
            }
            arenaManager.addArena(p.getLocation());
            p.sendMessage(Messages.PVPARENA_SET.toString());
            return true;
        }
        else if (args[0].equalsIgnoreCase("delete")){

            if (!arenaManager.exist()){
                p.sendMessage(Messages.PVPARENA_NOT_EXIST.toString());
                return true;
            }
            arenaManager.delArena();
            p.sendMessage(Messages.PVPARENA_DELETED.toString());
            return true;
        }
        return false;
    }
}