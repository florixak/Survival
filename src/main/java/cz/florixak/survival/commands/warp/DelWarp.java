package cz.florixak.survival.commands.warp;

import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.WarpManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "delwarp", permission = "warp.delete", requiresPlayer = true)
public class DelWarp extends PluginCommand {

    @Override
    public void perform(Player p, String[] args) {

        WarpManager manager = new WarpManager();

        if (args.length == 0){
            p.sendMessage(Messages.WARP_DELWARP_USAGE.toString());
        }
        else if (args.length == 1){

            if (!manager.exist(args[0])){
                p.sendMessage(Messages.WARP_NOT_EXISTS.toString().replace("%warp_name%", args[0]));
                return;
            }

            manager.delWarp(args[0]);
            p.sendMessage(Messages.WARP_DELETED.toString().replace("%warp_name%", args[0]));
        }
        else {
            p.sendMessage(Messages.WARP_DELWARP_USAGE.toString());
        }
    }
}