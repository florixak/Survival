package cz.florixak.survival.utility;

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
}
