package cz.florixak.survival.commands.kits;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.inventory.AbstractInventory;
import cz.florixak.survival.manager.KitsManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "kits", permission = "", requiresPlayer = true)
public class KitsCmd extends PluginCommand {

    AbstractInventory inventory = Survival.plugin.getInventoryManager().getInventory("kits");

    @Override
    public void perform(Player p, String[] args){

        if (args.length >= 1){
            p.sendMessage(Messages.KITS_USAGE.toString());
            return;
        }

        inventory.openInventory(p);
    }
}