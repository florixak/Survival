package cz.florixak.survival.commands.shop;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.inventory.AbstractInventory;
import cz.florixak.survival.manager.ShopManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "obchod", permission = "", requiresPlayer = true)
public class Obchod extends PluginCommand {

    AbstractInventory inventory = Survival.plugin.getInventoryManager().getInventory("shop");

    @Override
    public void perform(Player p, String[] args){
        p.sendMessage(Messages.SHOP_OPENING.toString());
        inventory.openInventory(p);
    }
}