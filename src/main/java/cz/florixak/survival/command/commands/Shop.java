package cz.florixak.survival.command.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.inventory.AbstractInventory;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Shop extends Command {

    private AbstractInventory inventory;

    public Shop(Survival plugin) {
        super(plugin);
        this.inventory = plugin.getInventoryManager().getInventory("shop");
        this.addAlias("obchod");
        this.addAlias("shop");
        this.addAlias("store");
        this.addAlias("market");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;
        p.sendMessage(Messages.SHOP_OPENING.toString());
        inventory.openInventory(p);
        return true;
    }
}