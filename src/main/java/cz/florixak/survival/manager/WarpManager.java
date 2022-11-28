package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Set;

public class WarpManager {
    private Survival plugin;
    private FileConfiguration config;

    public WarpManager(Survival plugin){
        this.plugin = plugin;
        this.config = plugin.getConfigManager().getFile(ConfigType.WARPS).getConfig();
    }

    public void addWarp(Location loc, String name){

        config.set("warps." + name + ".world", loc.getWorld().getName());
        config.set("warps." + name + ".x", loc.getX());
        config.set("warps." + name + ".y", loc.getY());
        config.set("warps." + name + ".z", loc.getZ());
        config.set("warps." + name + ".yaw", loc.getYaw());
        config.set("warps." + name + ".pitch", loc.getPitch());

        plugin.getConfigManager().getFile(ConfigType.WARPS).save();
    }

    public boolean exist(String name){
        return config.get("warps." + name) != null;
    }

    public void warpsList(Player p){

        if (warpIsNull()) {
            p.sendMessage(Messages.WARP_NO_WARPS.toString());
            return;
        }

        String out = "";
        for (String s : config.getConfigurationSection("warps").getKeys(false)){
            out = s + "§f, " + out;
        }
        out = out.trim();
        if (!(config.getConfigurationSection("warps").getKeys(false).size() <= 0)){
            p.sendMessage(Messages.WARP_LIST.toString()
                    .replace("%warp_list%", out));
        } else {
            p.sendMessage(Messages.WARP_NO_WARPS.toString());
        }

    }

    public Set<String> getWarps(){
        return config.getKeys(false);
    }

    public Location getLocation(String name){
        return new Location(
                Bukkit.getWorld(config.getString("warps." + name + ".world")),
                config.getDouble("warps." + name + ".x"),
                config.getDouble("warps." + name + ".y"),
                config.getDouble("warps." + name + ".z"),
                (float) config.getDouble("warps." + name + ".yaw"),
                (float) config.getDouble("warps." + name + ".pitch"));
    }

    public void delWarp(String name){
        config.set("warps." + name, null);
        plugin.getConfigManager().getFile(ConfigType.WARPS).save();
    }

    public boolean warpIsNull(){
        return config.getConfigurationSection("warps") == null;
    }


}
