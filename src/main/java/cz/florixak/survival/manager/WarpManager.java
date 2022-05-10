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

    private FileConfiguration config;

    public WarpManager(){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.WARPS).getConfig();
    }

    public void addWarp(Location loc, String name){

        config = Survival.plugin.getConfigManager().getFile(ConfigType.WARPS).getConfig();

        config.set("warps." + name + ".world", loc.getWorld().getName());
        config.set("warps." + name + ".x", loc.getX());
        config.set("warps." + name + ".y", loc.getY());
        config.set("warps." + name + ".z", loc.getZ());
        config.set("warps." + name + ".yaw", loc.getYaw());
        config.set("warps." + name + ".pitch", loc.getPitch());

        Survival.plugin.getConfigManager().getFile(ConfigType.WARPS).save();
    }

    public boolean exist(String name){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.WARPS).getConfig();
        return config.get("warps." + name) != null;
    }

    public void warpsList(Player p){

        config = Survival.plugin.getConfigManager().getFile(ConfigType.WARPS).getConfig();

        if (warpIsNull()){

            p.sendMessage(Messages.WARP_NO_WARPS.toString());

        } else {

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
    }

    public Set<String> getWarps(){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.WARPS).getConfig();

        return config.getKeys(false);
    }

    public Location getLocation(String name){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.WARPS).getConfig();

        return new Location(
                Bukkit.getWorld(config.getString("warps." + name + ".world")),
                config.getDouble("warps." + name + ".x"),
                config.getDouble("warps." + name + ".y"),
                config.getDouble("warps." + name + ".z"),
                (float) config.getDouble("warps." + name + ".yaw"),
                (float) config.getDouble("warps." + name + ".pitch"));
    }

    public void delWarp(String name){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.WARPS).getConfig();

        config.set("warps." + name, null);
        Survival.plugin.getConfigManager().getFile(ConfigType.WARPS).save();
    }

    public boolean warpIsNull(){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.WARPS).getConfig();
        return config.getConfigurationSection("warps") == null;
    }


}
