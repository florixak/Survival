package cz.florixak.survival.commands.spawn;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.manager.SpawnManager;
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

@CommandInfo(name = "spawn", permission = "", requiresPlayer = true)
public class Spawn extends PluginCommand {

    private int tp_delay;

    private FileConfiguration config;

    @Override
    public void perform(Player p, String[] args){

        config = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
        tp_delay = config.getInt("spawn.tp_delay");

        SpawnManager manager = new SpawnManager();

        if (!manager.exist()) {
            p.sendMessage(Messages.SPAWN_NOT_EXIST.toString());
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
                    p.teleport(manager.getLocation());
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.SPAWN_TELEPORTED.toString()));
                    PlayerManager.teleporting.remove(p);
                    cancel();
                    return;
                }

//                p.sendTitle(Messages.SPAWN_WAIT_TO_TP.toString()
//                        .replace("%teleport_delay%", "" + TimeConvertor.convertSeconds(tp_delay)), Messages.DONT_MOVE.toString(), 20, 40, 20);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.TELEPORTING.toString()
                        .replace("%delay%", "" + TimeConvertor.convertSeconds(tp_delay))));
                tp_delay--;
            }
        }.runTaskTimer(Survival.plugin, 0L, 20L);
    }
}