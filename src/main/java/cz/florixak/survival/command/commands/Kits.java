package cz.florixak.survival.command.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.inventory.AbstractInventory;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kits extends Command {

    AbstractInventory inventory;

    public Kits(Survival plugin) {
        super(plugin);
        this.inventory = plugin.getInventoryManager().getInventory("kits");
        this.addAlias("kit");
        this.addAlias("kits");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;

        if (args.length >= 1){
            p.sendMessage(Messages.KITS_USAGE.toString());
            return true;
        }
        inventory.openInventory(p);
        return true;
    }
}
