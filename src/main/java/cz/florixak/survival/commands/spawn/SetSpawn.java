package cz.florixak.survival.commands.spawn;

import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.SpawnManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "setspawn", permission = "spawn.set", requiresPlayer = true)
public class SetSpawn extends PluginCommand {

    @Override
    public void perform(Player p, String[] args){

        SpawnManager manager = new SpawnManager();

        if (manager.exist()){
            p.sendMessage(Messages.SPAWN_ALREADY_SET.toString());
            return;
        }

        manager.addSpawn(p.getLocation());
        p.sendMessage(Messages.SPAWN_SET.toString());

    }
}