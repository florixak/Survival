package cz.florixak.survival.command.commands.inventories;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Workbench extends Command {

    public Workbench(Survival plugin) {
        super(plugin);

        this.addAlias("wb");
        this.addAlias("workbench");
        this.addAlias("ct");
        this.addAlias("craftingtable");
        this.addRequirement("survival.workbench");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;

        p.sendMessage(Messages.WB_OPEN.toString());
        p.openWorkbench(null, true);
        return false;
    }
}