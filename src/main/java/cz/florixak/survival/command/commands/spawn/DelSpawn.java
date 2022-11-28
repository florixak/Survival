package cz.florixak.survival.command.commands.spawn;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.SpawnManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelSpawn extends Command {

    private SpawnManager manager;

    public DelSpawn(Survival plugin) {
        super(plugin);
        this.manager = plugin.getSpawnManager();

        this.addAlias("delspawn");
        this.addRequirement("survival.delspawn");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {

        Player p = (Player) sender;

        if (!manager.exist()){
            p.sendMessage(Messages.SPAWN_NOT_EXIST.toString());
            return true;
        }

        manager.delSpawn();
        p.sendMessage(Messages.SPAWN_DELETED.toString());
        return true;
    }
}