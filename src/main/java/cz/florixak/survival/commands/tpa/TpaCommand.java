package cz.florixak.survival.commands.tpa;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.utility.TimeConvertor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class TpaCommand implements CommandExecutor {

    private static HashMap<UUID, UUID> requests = new HashMap<>();
    private HashMap<UUID, Integer> cmd_cooldown = new HashMap<>();

    private int cmd_cooldown_delay;
    private int tpa_delay;

    private FileConfiguration config;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        config = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();

        cmd_cooldown_delay = config.getInt("tpa.cooldown");
        tpa_delay = config.getInt("tpa.tp_delay");

        if(!(sender instanceof Player)) {
            sender.sendMessage(Messages.NOT_PLAYER.toString());
            return true;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("tpa")) {

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

            if (!cmd_cooldown.containsKey(player.getUniqueId())) {
                cmd_cooldown.put(player.getUniqueId(), cmd_cooldown_delay);
                requests.put(target.getUniqueId(), player.getUniqueId());
                player.sendMessage(Messages.TPA_SENT.toString().replace("%player%", "" + target.getName()));
                target.sendMessage(Messages.TPA_GAIN.toString().replace("%player%", "" + player.getName()));
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if (cmd_cooldown.get(player.getUniqueId()) == 0){
                            cmd_cooldown.remove(player.getUniqueId());
                            cancel();
                            return;
                        }
                        cmd_cooldown.put(player.getUniqueId(), cmd_cooldown.get(player.getUniqueId())-1);
                    }
                }.runTaskTimerAsynchronously(Survival.plugin, 0L, 20L);
                return true;
            } else {
                player.sendMessage(Messages.TPA_COOLDOWN.toString()
                        .replace("%time_remaining%", TimeConvertor.convertMinute(cmd_cooldown.get(player.getUniqueId()))));
                return true;
            }
        }

        if (command.getName().equalsIgnoreCase("tpaccept")) {

            if (args.length >= 1) {
                player.sendMessage(Messages.TPA_USAGE.toString());
                return true;
            }

            if (!PlayerManager.isPlayers(Bukkit.getPlayer(requests.get(player.getUniqueId())))) {
                player.sendMessage(Messages.OFFLINE_PLAYER.toString());
                return true;
            }
            Player teleporting_p = Bukkit.getPlayer(requests.get(player.getUniqueId()));
            if (PlayerManager.isTeleporting(teleporting_p)) {
                teleporting_p.sendMessage(Messages.CANNOT_TELEPORT_NOW.toString());
                return true;
            }
            PlayerManager.teleporting.add(teleporting_p);

            if (requests.containsKey(player.getUniqueId())) {
                player.sendMessage(Messages.TPA_ACCEPTED.toString());
                Bukkit.getPlayer(requests.get(player.getUniqueId())).sendMessage(Messages.TPA_ACCEPTED.toString());
                new BukkitRunnable(){
                    @Override
                    public void run() {

                        if (tpa_delay == 0){
                            Bukkit.getPlayer(requests.get(player.getUniqueId())).teleport(player);
                            Bukkit.getPlayer(requests.get(player.getUniqueId()))
                                    .sendMessage(Messages.TELEPORT_TELEPORTED.toString().replace("%player%", player.getName()));
                            requests.remove(player.getUniqueId());
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
                        teleporting_p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.TELEPORTING.toString()
                                .replace("%delay%", "" + TimeConvertor.convertSeconds(tpa_delay))));
                        tpa_delay--;
                    }
                }.runTaskTimer(Survival.plugin, 0L, 20L);
                return true;
            }
            player.sendMessage(Messages.TPA_NO_TP.toString());
            return true;
        }




        if (command.getName().equalsIgnoreCase("tpdeny")) {

            if (args.length >= 1) {
                player.sendMessage(Messages.TPA_USAGE.toString());
                return true;
            }

            if (!PlayerManager.isPlayers(Bukkit.getPlayer(requests.get(player.getUniqueId())))) {
                player.sendMessage(Messages.OFFLINE_PLAYER.toString());
                return true;
            }

            if (requests.containsKey(player.getUniqueId())) {
                player.sendMessage(Messages.TPA_DENIED.toString());
                Bukkit.getPlayer(requests.get(player.getUniqueId())).sendMessage(Messages.TPA_DENIED.toString());
                requests.remove(player.getUniqueId());
                return true;
            }
            player.sendMessage(Messages.TPA_NO_TP.toString());
            return true;
        }



        if (command.getName().equalsIgnoreCase("tpblock")){

            if (!PlayerManager.hasTpBlock(player.getUniqueId())){
                PlayerManager.tpBlock.add(player.getUniqueId());
                player.sendMessage(Messages.TELEPORT_BLOCKED.toString());
                return true;
            } else {
                PlayerManager.tpBlock.remove(player.getUniqueId());
                player.sendMessage(Messages.TELEPORT_ALLOWED.toString());
                return true;
            }
        }
        return false;
    }
}