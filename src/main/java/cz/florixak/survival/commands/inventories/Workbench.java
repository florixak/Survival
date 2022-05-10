package cz.florixak.survival.commands.inventories;

import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "wb", permission = "", requiresPlayer = true)
public class Workbench extends PluginCommand {

    @Override
    public void perform(Player p, String[] args){

        p.sendMessage(Messages.WB_OPEN.toString());
        p.openWorkbench(null, true);

    }
}