package cz.florixak.survival.command.commands.warp;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.manager.WarpManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warps extends Command {

    private WarpManager manager;

    public Warps(Survival plugin) {
        super(plugin);
        this.manager = plugin.getWarpManager();

        this.addAlias("warps");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;
        manager.warpsList(p);
        return true;
    }
}
