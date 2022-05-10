package cz.florixak.survival.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import cz.florixak.survival.utility.TeleportUtil;
import cz.florixak.survival.utility.TimeConvertor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

@CommandInfo(name = "rtp", permission = "", requiresPlayer = true)
public class RandomTP extends PluginCommand {

    private HashMap<UUID, Integer> cd = new HashMap<>();

    private int delay;
    private int cooldown;

    private FileConfiguration config;

    @Override
    public void perform(Player p, String[] args){

        config = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();

        delay = config.getInt("randomtp.delay");
        cooldown = config.getInt("randomtp.cooldown");

        if (p.getWorld().getName().equals("world_nether") || p.getWorld().getName().equals("world_the_end")) {
            p.sendMessage(Messages.RANDOMTP_YOU_CANT.toString().replace("%world%", p.getWorld().getName()));
            return;
        }

        if (cd.containsKey(p.getUniqueId())) {
            p.sendMessage(Messages.RANDOMTP_WAIT.toString()
                    .replace("%cooldown%", "" + TimeConvertor.convertMinute(cd.get(p.getUniqueId()))));
            return;
        }

        Location randomLocation = TeleportUtil.findSafeLocation(p);

        PlayerManager.rtp.add(p.getUniqueId());
        cd.put(p.getUniqueId(), cooldown);

        new BukkitRunnable(){

            private final Location initial = p.getLocation();

            @Override
            public void run() {

                if (p.getLocation().distance(initial) > 0.5) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.YOU_MOVED.toString()));
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 0.1f, 0.1f);
                    PlayerManager.rtp.remove(p.getUniqueId());
                    cd.remove(p.getUniqueId());
                    cancel();
                    return;
                }

                if (delay == 0) {
                    p.teleport(randomLocation);
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.RANDOMTP_NEW_COORDS.toString()
                            .replace("%x%", "" + randomLocation.getX())
                            .replace("%y%", "" + randomLocation.getY())
                            .replace("%z%", "" + randomLocation.getZ())));
                    cancel();
                    return;
                }
//                p.sendTitle(Messages.RANDOMTP_WAIT_TO_TP.toString()
//                                .replace("%delay%", "" + TimeConvertor.convertSeconds(delay)),
//                        Messages.DONT_MOVE.toString(), 10, 20, 10);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.TELEPORTING.toString()
                        .replace("%delay%", "" + TimeConvertor.convertSeconds(delay))));
                delay--;
            }
        }.runTaskTimer(Survival.plugin, 0L, 20L);

        new BukkitRunnable(){
            @Override
            public void run() {
                if (PlayerManager.isRtp(p.getUniqueId())) {
                    if (cd.get(p.getUniqueId()) == 0) {
                        PlayerManager.rtp.remove(p.getUniqueId());
                        cd.remove(p.getUniqueId());
                        cancel();
                        return;
                    }
                    cd.put(p.getUniqueId(), cd.get(p.getUniqueId()) - 1);
                } else {
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(Survival.plugin, 0L, 20L);
    }
}