package cz.florixak.survival.utility;

import cz.florixak.survival.Survival;
import cz.florixak.survival.tasks.RestartTask;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class Utils {

    public static void sendHotbarMessage(Player p, String message) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(TextUtil.color(message)));
    }

    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(TextUtil.color(message));
    }

    public static String formatMoney(double money) {
        DecimalFormat format = new DecimalFormat("##,###,##0.00");;
        String formatted = format.format(money);
        return formatted;
    }

    public static boolean isNum(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void startRestartCountdown() {
        try {
            RestartTask restartTask = new RestartTask(Survival.plugin);
            restartTask.runTaskTimer(Survival.plugin, 0L, 20L);
            System.out.println("Started countdown of restart! " + RestartTask.time);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void restartServer() {
        kickAllPlayers();
        Bukkit.getServer().spigot().restart();
    }

    public static void kickAllPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer("Survival is restarting, please wait a minute...");
        }
    }
}
