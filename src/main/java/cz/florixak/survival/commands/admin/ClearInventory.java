package cz.florixak.survival.commands.admin;

import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "clear", permission = "clear", requiresPlayer = true)
public class ClearInventory extends PluginCommand {

    @Override
    public void perform(Player p, String[] args){

        p.getInventory().clear();
        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);

        p.sendMessage(Messages.CLEAR_CLEARED.toString());
    }
}