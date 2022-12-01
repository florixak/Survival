package cz.florixak.survival.command.commands.tpa;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.listeners.TeleportManager;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.utility.TimeConvertor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Tpa extends Command {

    private TeleportManager tpm;

    private int cmd_cooldown_delay;
    private int tpa_delay;

    public Tpa(Survival plugin) {
        super(plugin);
        this.tpm = plugin.getTeleportManager();

        this.cmd_cooldown_delay = tpm.getCmdCooldown();
        this.tpa_delay = tpm.getTpaDelay();

        this.addAlias("tpa");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(Messages.TPA_USAGE.toString());
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (!PlayerManager.isPlayers(target)) {
            player.sendMessage(Messages.OFFLINE_PLAYER.toString());
            return true;
        }

        if (target == player){
            player.sendMessage(Messages.TPA_CANT_TP_TO_SELF.toString());
            return true;
        }

        if (PlayerManager.hasTpBlock(target.getUniqueId())){
            player.sendMessage(Messages.TELEPORT_BLOCKED_PLAYER.toString());
            return true;
        }

        if (tpm.containsKey(player)) {
            player.sendMessage(Messages.TPA_COOLDOWN.toString()
                    .replace("%time_remaining%", TimeConvertor.convertMinute(tpm.getPlayerCmdCooldown(player))));
            return true;
        }

        tpm.addPlayerCmdCooldown(player, cmd_cooldown_delay);
        tpm.requests.put(target.getUniqueId(), player.getUniqueId());
        player.sendMessage(Messages.TPA_SENT.toString().replace("%player%", "" + target.getName()));
        target.sendMessage(Messages.TPA_GAIN.toString().replace("%player%", "" + player.getName()));
        new BukkitRunnable(){
            @Override
            public void run() {
                if (tpm.getPlayerCmdCooldown(player) == 0){
                    tpm.removePlayerCmdCooldown(player);
                    cancel();
                    return;
                }
                tpm.addPlayerCmdCooldown(player, tpm.getPlayerCmdCooldown(player)-1);
            }
        }.runTaskTimerAsynchronously(Survival.plugin, 0L, 20L);
        return true;
    }
}