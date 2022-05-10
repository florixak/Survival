package cz.florixak.survival.commands.warp;

import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.WarpManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "setwarp", permission = "warp.set", requiresPlayer = true)
public class SetWarp extends PluginCommand {

    @Override
    public void perform(Player p, String[] args) {

        WarpManager manager = new WarpManager();

        if (args.length == 0) {
            p.sendMessage(Messages.WARP_SETWARP_USAGE.toString());
        }
        else if (args.length == 1){

            if (manager.exist(args[0])){
                p.sendMessage(Messages.WARP_ALREADY_EXISTS.toString().replace("%warp_name%", args[0]));
                return;
            }

            manager.addWarp(p.getLocation(), args[0]);
            p.sendMessage(Messages.WARP_CREATED.toString().replace("%warp_name%", args[0]));
        }
        else {
            p.sendMessage(Messages.WARP_SETWARP_USAGE.toString());
        }
    }
}