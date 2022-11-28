package cz.florixak.survival.command.commands.tpa;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tp extends Command {

    public Tp(Survival plugin) {
        super(plugin);
        this.addAlias("tp");
        this.addAlias("teleport");
        this.addRequirement("survival.tp");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;

        if (args.length == 1) {

            Player target = Bukkit.getPlayer(args[0]);

            if (!PlayerManager.isPlayers(target)) {
                p.sendMessage(Messages.OFFLINE_PLAYER.toString());
                return true;
            }

            if (PlayerManager.hasTpBlock(target.getUniqueId())){
                p.sendMessage(Messages.TELEPORT_BLOCKED_PLAYER.toString());
                return true;
            }

            p.teleport(target.getLocation());
            p.sendTitle(Messages.TELEPORT_TELEPORTED.toString().replace("%player%", target.getName()), "", 10, 20, 10);

        } else if (args.length > 1) {

            Player target = Bukkit.getPlayer(args[0]);
            Player target2 = Bukkit.getPlayer(args[1]);

            if (!PlayerManager.isPlayers(target) || !PlayerManager.isPlayers(target2)) {
                p.sendMessage(Messages.OFFLINE_PLAYER.toString());
                return true;
            }

            if (PlayerManager.hasTpBlock(target2.getUniqueId()) || PlayerManager.hasTpBlock(target.getUniqueId())){
                p.sendMessage(Messages.TELEPORT_BLOCKED_PLAYER.toString());
                return true;
            }

            target.teleport(target2.getLocation());
            target.sendTitle(Messages.TELEPORT_TELEPORTED.toString().replace("%player%", target2.getName()), "", 10, 20, 10);

        } else {
            p.sendMessage(Messages.TP_USAGE.toString());
        }
        return true;
    }
}