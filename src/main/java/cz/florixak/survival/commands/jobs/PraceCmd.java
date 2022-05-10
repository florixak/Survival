package cz.florixak.survival.commands.jobs;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.inventory.AbstractInventory;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "prace", permission = "", requiresPlayer = true)
public class PraceCmd extends PluginCommand {

    AbstractInventory inventory = Survival.plugin.getInventoryManager().getInventory("jobs");

    @Override
    public void perform(Player p, String[] args){
        p.sendMessage(Messages.JOBS_OPENING.toString());
        inventory.openInventory(p);
    }
}