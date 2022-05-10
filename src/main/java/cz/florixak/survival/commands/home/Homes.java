package cz.florixak.survival.commands.home;

import cz.florixak.survival.manager.HomeManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "homes", permission = "", requiresPlayer = true)
public class Homes extends PluginCommand {

    @Override
    public void perform(Player p, String[] args) {

        HomeManager manager = new HomeManager();
        // manager.homesList(p.getUniqueId());
        manager.openHomeList(p);
    }
}