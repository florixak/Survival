package cz.florixak.survival.commands.warp;

import cz.florixak.survival.manager.WarpManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "warps", permission = "", requiresPlayer = true)
public class Warps extends PluginCommand {

    @Override
    public void perform(Player p, String[] args) {

        WarpManager manager = new WarpManager();

        manager.warpsList(p);
    }
}
