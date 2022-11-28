package cz.florixak.survival.command.commands.admin;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInventory extends Command {

    public ClearInventory(Survival plugin) {
        super(plugin);

        this.addAlias("clear");
        this.addRequirement("survival.clear");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {

        Player p = (Player) sender;

        p.getInventory().clear();
        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);

        p.sendMessage(Messages.CLEAR_CLEARED.toString());
        return true;
    }
}