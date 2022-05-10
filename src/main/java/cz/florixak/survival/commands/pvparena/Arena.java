package cz.florixak.survival.commands.pvparena;

import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PvPArenaManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "pvparena", permission = "pvparena", requiresPlayer = true)
public class Arena extends PluginCommand {

    @Override
    public void perform(Player p, String[] args){

        PvPArenaManager arenaManager = new PvPArenaManager();

        if (args[0].equalsIgnoreCase("set")){

            if (!arenaManager.exist()) {
                arenaManager.addArena(p.getLocation());
                p.sendMessage(Messages.PVPARENA_SET.toString());
            } else {
                p.sendMessage(Messages.PVPARENA_EXIST.toString());
            }

        }
        else if (args[0].equalsIgnoreCase("delete")){

            if (arenaManager.exist()){
                arenaManager.delArena();
                p.sendMessage(Messages.PVPARENA_DELETED.toString());
            } else {
                p.sendMessage(Messages.PVPARENA_NOT_EXIST.toString());
            }
        }
    }
}