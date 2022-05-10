package cz.florixak.survival.commands.home;

import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.HomeManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "delhome", permission = "", requiresPlayer = true)
public class DelHome extends PluginCommand {

    @Override
    public void perform(Player p, String[] args) {

        HomeManager manager = new HomeManager();

        if (args.length == 0) {
            p.sendMessage(Messages.HOME_USAGE_DELHOME.toString());
        }

        else if (args.length == 1) {

            if (!manager.exist(p.getUniqueId(), args[0])){
                p.sendMessage(Messages.HOME_NO_EXISTS.toString().replace("%home_name%", args[0]));
                return;
            }

            manager.delHome(p.getUniqueId(), args[0]);
            p.sendMessage(Messages.HOME_DELETED.toString().replace("%home_name%", args[0]));

        }
        else {
            p.sendMessage(Messages.HOME_USAGE_DELHOME.toString());
        }
    }
}