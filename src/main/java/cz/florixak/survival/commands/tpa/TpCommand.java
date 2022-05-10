package cz.florixak.survival.commands.tpa;

import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandInfo(name = "tp", permission = "tp", requiresPlayer = true)
public class TpCommand extends PluginCommand {

    @Override
    public void perform(Player p, String[] args){

        if (args.length == 1) {

            Player target = Bukkit.getPlayer(args[0]);

            if (!PlayerManager.isPlayers(target)) {
                p.sendMessage(Messages.OFFLINE_PLAYER.toString());
                return;
            }

            if (PlayerManager.hasTpBlock(target.getUniqueId())){
                p.sendMessage(Messages.TELEPORT_BLOCKED_PLAYER.toString());
                return;
            }

            p.teleport(target.getLocation());
            p.sendTitle(Messages.TELEPORT_TELEPORTED.toString().replace("%player%", target.getName()), "", 10, 20, 10);

        } else if (args.length > 1) {

            Player target = Bukkit.getPlayer(args[0]);
            Player target2 = Bukkit.getPlayer(args[1]);

            if (!PlayerManager.isPlayers(target) || !PlayerManager.isPlayers(target2)) {
                p.sendMessage(Messages.OFFLINE_PLAYER.toString());
                return;
            }

            if (PlayerManager.hasTpBlock(target2.getUniqueId()) || PlayerManager.hasTpBlock(target.getUniqueId())){
                p.sendMessage(Messages.TELEPORT_BLOCKED_PLAYER.toString());
                return;
            }

            target.teleport(target2.getLocation());
            target.sendTitle(Messages.TELEPORT_TELEPORTED.toString().replace("%player%", target2.getName()), "", 10, 20, 10);

        } else {
            p.sendMessage(Messages.TP_USAGE.toString());
        }
    }
}