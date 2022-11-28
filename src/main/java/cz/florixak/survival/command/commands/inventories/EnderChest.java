package cz.florixak.survival.command.commands.inventories;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderChest extends Command {

    public EnderChest(Survival plugin) {
        super(plugin);

        this.addAlias("ec");
        this.addAlias("enderchest");
        this.addAlias("echest");
        this.addRequirement("survival.enderchest");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;

        p.sendMessage(Messages.ENDER_CHEST_OPEN.toString());
        p.openInventory(p.getEnderChest());
        return true;
    }
}
