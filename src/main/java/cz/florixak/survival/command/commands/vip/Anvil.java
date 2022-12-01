package cz.florixak.survival.command.commands.vip;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class Anvil extends Command {

    public Anvil(Survival plugin) {
        super(plugin);

        this.addAlias("anvil");
        this.addAlias("anv");
        this.addRequirement("survival.anvil");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {

        Player p = (Player) sender;
        Inventory anvil = Bukkit.createInventory(null, InventoryType.ANVIL);

        p.sendMessage(Messages.ANVIL_OPEN.toString());
        p.openInventory(anvil);
        return true;
    }
}
