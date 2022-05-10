package cz.florixak.survival.commands.inventories;

import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "ec", permission = "", requiresPlayer = true)
public class EnderChest extends PluginCommand {

    @Override
    public void perform(Player p, String[] args){

        p.sendMessage(Messages.ENDER_CHEST_OPEN.toString());
        p.openInventory(p.getEnderChest());

    }
}
