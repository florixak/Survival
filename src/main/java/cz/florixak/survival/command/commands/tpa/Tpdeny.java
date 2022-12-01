package cz.florixak.survival.command.commands.tpa;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.listeners.TeleportManager;
import cz.florixak.survival.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpdeny extends Command {

    private TeleportManager tpm;

    public Tpdeny(Survival plugin) {
        super(plugin);
        this.tpm = plugin.getTeleportManager();

        this.addAlias("tpdeny");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;

        if (args.length >= 1) {
            p.sendMessage(Messages.TPA_USAGE.toString());
            return true;
        }

        if (!PlayerManager.isPlayers(Bukkit.getPlayer(tpm.requests.get(p.getUniqueId())))) {
            p.sendMessage(Messages.OFFLINE_PLAYER.toString());
            return true;
        }

        if (tpm.requests.containsKey(p.getUniqueId())) {
            p.sendMessage(Messages.TPA_DENIED.toString());
            Bukkit.getPlayer(tpm.requests.get(p.getUniqueId())).sendMessage(Messages.TPA_DENIED.toString());
            tpm.requests.remove(p.getUniqueId());
            return true;
        }
        p.sendMessage(Messages.TPA_NO_TP.toString());
        return true;
    }
}
