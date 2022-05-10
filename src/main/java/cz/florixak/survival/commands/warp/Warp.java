package cz.florixak.survival.commands.warp;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.manager.WarpManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import cz.florixak.survival.utility.TimeConvertor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@CommandInfo(name = "warp", permission = "", requiresPlayer = true)
public class Warp extends PluginCommand {

    private int tp_delay;

    private FileConfiguration config;

    @Override
    public void perform(Player p, String[] args){

        config = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
        tp_delay = config.getInt("warp.tp_delay");

        WarpManager manager = new WarpManager();

        if (args.length == 0) {
            p.sendMessage(Messages.WARP_USAGE.toString());

        } else if (args.length == 1) {

            if (!manager.exist(args[0])) {
                p.sendMessage(Messages.WARP_NOT_EXISTS.toString().replace("%warp_name%", args[0]));
                return;
            }

            if (PlayerManager.isTeleporting(p)) {
                p.sendMessage(Messages.CANNOT_TELEPORT_NOW.toString());
                return;
            }
            PlayerManager.teleporting.add(p);

            new BukkitRunnable() {

                private final Location initial = p.getLocation();

                @Override
                public void run() {

                    if (p.getLocation().distance(initial) > 0.5) {
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.YOU_MOVED.toString()));
                        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 0.1f, 0.1f);
                        PlayerManager.teleporting.remove(p);
                        cancel();
                        return;
                    }

                    if (tp_delay == 0){
                        p.teleport(manager.getLocation(args[0]));
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.WARP_TELEPORTED.toString()
                                .replace("%warp_name%", args[0])));
                        PlayerManager.teleporting.remove(p);
                        cancel();
                        return;
                    }

//                    p.sendTitle(Messages.WARP_WAIT_MESSAGE.toString().replace("%warp_name%", args[0])
//                                    .replace("%teleport_delay%", "" + TimeConvertor.convertSeconds(tp_delay)),
//                            Messages.DONT_MOVE.toString(), 20, 40, 20);

                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.TELEPORTING.toString()
                            .replace("%delay%", "" + TimeConvertor.convertSeconds(tp_delay))));

                    tp_delay--;

                }
            }.runTaskTimer(Survival.plugin, 0L, 20L);

        } else {
            p.sendMessage(Messages.WARP_USAGE.toString());
        }
    }
}