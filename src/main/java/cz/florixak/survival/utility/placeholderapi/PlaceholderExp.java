package cz.florixak.survival.utility.placeholderapi;

import cz.florixak.survival.Survival;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlaceholderExp extends PlaceholderExpansion {

    private Survival plugin;

    public PlaceholderExp(Survival plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "survival";
    }

    @Override
    public String getAuthor() {
        return "FloriXak";
    }

    @Override
    public String getVersion() {
        return "alpha";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (player != null && player.isOnline()){
            return onPlaceholderRequest(player.getPlayer(), params);
        }

        return null; // Placeholder is unknown by the Expansion
    }

    @Override
    public String onPlaceholderRequest(Player p, String params){
        if (p == null){
            return null;
        }

        if (params.equalsIgnoreCase("team")) {

        }
        return null;
    }
}
