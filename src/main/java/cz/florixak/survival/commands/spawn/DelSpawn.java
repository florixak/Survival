package cz.florixak.survival.commands.spawn;

import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.SpawnManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "delspawn", permission = "spawn.delete", requiresPlayer = true)
public class DelSpawn extends PluginCommand {

    @Override
    public void perform(Player p, String[] args){

        SpawnManager manager = new SpawnManager();

        if (!manager.exist()){
            p.sendMessage(Messages.SPAWN_NOT_EXIST.toString());
            return;
        }

        manager.delSpawn();
        p.sendMessage(Messages.SPAWN_DELETED.toString());

    }
}