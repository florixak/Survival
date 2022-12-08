package cz.florixak.survival.tasks;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.utility.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class RestartTask extends BukkitRunnable {

    private FileConfiguration config;
    public static int time = 86600;

    public RestartTask(Survival plugin) {
        this.config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
    }

    @Override
    public void run() {
        if (time <= 0) {
            Utils.restartServer();
            cancel();
            return;
        }
        if (time <= 10) Utils.broadcastMessage("Server restarts in " + time);
        if (time == 30) Utils.broadcastMessage("Server restarts in " + time);
        if (time == 60) Utils.broadcastMessage("Server restarts in " + time);
        if (time == 900) Utils.broadcastMessage("Server restarts in " + time);
        if (time == 1800) Utils.broadcastMessage("Server restarts in " + time);

        time--;
    }
}
