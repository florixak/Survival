package cz.florixak.survival.command.commands.tpa;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.listeners.TeleportManager;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.utility.TimeConvertor;
import cz.florixak.survival.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Tpaccept extends Command {

    private TeleportManager tpm;
    private int tpa_delay;

    public Tpaccept(Survival plugin) {
        super(plugin);

        this.tpm = plugin.getTeleportManager();
        this.tpa_delay = tpm.getTpaDelay();

        this.addAlias("tpaccept");
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
        Player teleporting_p = Bukkit.getPlayer(tpm.requests.get(p.getUniqueId()));
        if (PlayerManager.isTeleporting(teleporting_p)) {
            teleporting_p.sendMessage(Messages.CANNOT_TELEPORT_NOW.toString());
            return true;
        }
        PlayerManager.teleporting.add(teleporting_p);

        if (tpm.requests.containsKey(p.getUniqueId())) {
            p.sendMessage(Messages.TPA_ACCEPTED.toString());
            Bukkit.getPlayer(tpm.requests.get(p.getUniqueId())).sendMessage(Messages.TPA_ACCEPTED.toString());
            new BukkitRunnable(){
                @Override
                public void run() {

                    if (tpa_delay == 0){
                        Bukkit.getPlayer(tpm.requests.get(p.getUniqueId())).teleport(p);
                        Bukkit.getPlayer(tpm.requests.get(p.getUniqueId()))
                                .sendMessage(Messages.TELEPORT_TELEPORTED.toString().replace("%player%", p.getName()));
                        tpm.requests.remove(p.getUniqueId());
                        PlayerManager.teleporting.remove(teleporting_p);
                        cancel();
                        return;
                    }
//                        Bukkit.getPlayer(requests.get(player.getUniqueId())).sendTitle(
//                                Messages.TPA_TP_DELAY.toString().replace("%time_remaining%", "" + tpa_delay),
//                                Messages.DONT_MOVE.toString(),
//                                10,
//                                20,
//                                10);
                    Utils.sendHotbarMessage(teleporting_p, Messages.TELEPORTING.toString()
                            .replace("%delay%", "" + TimeConvertor.convertSeconds(tpa_delay)));
                    tpa_delay--;
                }
            }.runTaskTimer(plugin, 0L, 20L);
            return true;
        }
        p.sendMessage(Messages.TPA_NO_TP.toString());
        return true;
    }
}
