package cz.florixak.survival.command.commands.warp;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.WarpManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarp extends Command {

    private WarpManager manager;

    public DelWarp(Survival plugin) {
        super(plugin);
        this.manager = plugin.getWarpManager();

        this.addAlias("delwarp");
        this.addRequirement("survival.delwarp");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;

        if (args.length == 0){
            p.sendMessage(Messages.WARP_DELWARP_USAGE.toString());
        }
        else if (args.length == 1){

            if (!manager.exist(args[0])){
                p.sendMessage(Messages.WARP_NOT_EXISTS.toString().replace("%warp_name%", args[0]));
                return true;
            }

            manager.delWarp(args[0]);
            p.sendMessage(Messages.WARP_DELETED.toString().replace("%warp_name%", args[0]));
        }
        else {
            p.sendMessage(Messages.WARP_DELWARP_USAGE.toString());
        }
        return true;
    }
}