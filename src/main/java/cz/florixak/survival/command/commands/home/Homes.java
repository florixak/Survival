package cz.florixak.survival.command.commands.home;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.manager.HomeManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Homes extends Command {

    private HomeManager manager;

    public Homes(Survival plugin) {
        super(plugin);
        this.plugin = plugin;
        this.manager = plugin.getHomeManager();

        this.addAlias("homes");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;
        manager.openHomeList(p);
        return true;
    }
}