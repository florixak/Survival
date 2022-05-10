package cz.florixak.survival.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import cz.florixak.survival.utility.TextUtil;
import cz.florixak.survival.utility.TimeConvertor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

@CommandInfo(name = "fly", permission = "", requiresPlayer = true)
public class Fly extends PluginCommand {

    private FileConfiguration config;

    private HashMap<UUID, Integer> fly_on_day = new HashMap<>();
    private HashMap<UUID, Integer> fly_time = new HashMap<>();

    private int cooldown_day;
    private int flying_time;
    private int warn_time;

    @Override
    public void perform(Player p, String[] args){

        config = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();

        flying_time = config.getInt("fly.flying_time");
        cooldown_day = config.getInt("fly.day_cooldown_time");
        warn_time = config.getInt("fly.warn_time");

        UUID uuid = p.getUniqueId();

        if (args.length == 0) {
            p.sendMessage(TextUtil.color(Messages.FLY_USAGE.toString()));

        } else if (args[0].equalsIgnoreCase("on")) {

            if (!fly_on_day.containsKey(uuid)) {

                if (!fly_time.containsKey(uuid)) {

                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.sendMessage(TextUtil.color(Messages.FLY_ENABLED.toString()
                            .replace("%fly_delay%", "" + TimeConvertor.convertHour(flying_time))));

                    fly_time.put(uuid, flying_time);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (fly_time.get(uuid) <= warn_time){
                                p.sendMessage(Messages.FLY_WARN_MSG.toString()
                                        .replace("%warn_time%", "" + TimeConvertor.convertHour(warn_time)));
                                cancel();
                                return;
                            }
                        }

                    }.runTaskTimerAsynchronously(Survival.plugin, 0L, 20L);

                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            if (fly_time.get(uuid) == 0) {
                                fly_time.remove(uuid);
                                cancel();

                                p.setFlying(false);
                                p.setAllowFlight(false);
                                p.sendMessage(Messages.FLY_DISABLED.toString());
                                fly_on_day.put(uuid, cooldown_day);

                                new BukkitRunnable() {

                                    @Override
                                    public void run() {

                                        if (fly_on_day.get(uuid) == 0) {
                                            fly_on_day.remove(uuid);
                                            cancel();
                                            return;
                                        }
                                        fly_on_day.put(uuid, fly_on_day.get(uuid)-1);
                                    }

                                }.runTaskTimerAsynchronously(Survival.plugin, 0L, 20L);
                                return;
                            }
                            fly_time.put(uuid, fly_time.get(uuid)-1);
                        }
                    }.runTaskTimerAsynchronously(Survival.plugin, 0L, 20L);

                } else {
                    p.sendMessage(Messages.FLY_ALREADY_ACTIVATED.toString()
                            .replace("%time_remaining%", "" + TimeConvertor.convertHour(fly_time.get(uuid))));
                }
            } else {
                p.sendMessage(Messages.FLY_DAY_COOLDOWN_MSG.toString()
                        .replace("%time_remaining%", "" + TimeConvertor.convertHour(fly_on_day.get(uuid))));
            }

        } else {
            p.sendMessage(Messages.FLY_USAGE.toString());
        }
    }
}