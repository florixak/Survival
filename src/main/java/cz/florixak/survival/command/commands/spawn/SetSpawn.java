package cz.florixak.survival.command.commands.spawn;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.SpawnManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn extends Command {

    private SpawnManager manager;

    public SetSpawn(Survival plugin) {
        super(plugin);
        this.manager = plugin.getSpawnManager();

        this.addAlias("setspawn");
        this.addRequirement("survival.setspawn");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {

        Player p = (Player) sender;

        if (manager.exist()){
            p.sendMessage(Messages.SPAWN_ALREADY_SET.toString());
            return true;
        }

        manager.addSpawn(p.getLocation());
        p.sendMessage(Messages.SPAWN_SET.toString());
        return true;
    }
}