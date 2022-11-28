package cz.florixak.survival.command.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.inventory.AbstractInventory;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Jobs extends Command {

    private AbstractInventory inventory;

    public Jobs(Survival plugin) {
        super(plugin);
        this.inventory = plugin.getInventoryManager().getInventory("jobs");
        this.addAlias("jobs");
        this.addAlias("job");
        this.addAlias("prace");
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