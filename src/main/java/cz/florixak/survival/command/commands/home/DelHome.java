package cz.florixak.survival.command.commands.home;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.HomeManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelHome extends Command {

    private HomeManager manager;

    public DelHome(Survival plugin) {
        super(plugin);
        this.plugin = plugin;
        this.manager = plugin.getHomeManager();

        this.addAlias("delhome");
        this.setOnlyPlayer(true);

    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(Messages.HOME_USAGE_DELHOME.toString());
        }

        else if (args.length == 1) {

            if (!manager.exist(p.getUniqueId(), args[0])){
                p.sendMessage(Messages.HOME_NO_EXISTS.toString().replace("%home_name%", args[0]));
                return true;
            }

            manager.delHome(p.getUniqueId(), args[0]);
            p.sendMessage(Messages.HOME_DELETED.toString().replace("%home_name%", args[0]));

        }
        else {
            p.sendMessage(Messages.HOME_USAGE_DELHOME.toString());
        }
        return true;
    }
}