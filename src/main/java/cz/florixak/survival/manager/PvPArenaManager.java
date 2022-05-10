package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class PvPArenaManager {

    private FileConfiguration config;

    public PvPArenaManager(){ }

    public void addArena(Location loc){

        config = Survival.plugin.getConfigManager().getFile(ConfigType.PVPARENA).getConfig();

        config.set("arena" + ".location" + ".world", loc.getWorld().getName());
        config.set("arena" + ".location" + ".x", loc.getX());
        config.set("arena" + ".location" + ".y", loc.getY());
        config.set("arena" + ".location" + ".z", loc.getZ());
        config.set("arena" + ".location" + ".yaw", loc.getYaw());
        config.set("arena" + ".location" + ".pitch", loc.getPitch());

        Survival.plugin.getConfigManager().getFile(ConfigType.PVPARENA).save();
    }

    public boolean exist(){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.PVPARENA).getConfig();
        return config.getConfigurationSection("arena" + ".location") != null;
    }

    public Location getLocation(){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.PVPARENA).getConfig();

        return new Location(
                Bukkit.getWorld(config.getString("arena" + ".location" + ".world")),
                config.getDouble("arena" + ".location" + ".x"),
                config.getDouble("arena" + ".location" + ".y"),
                config.getDouble("arena" + ".location" + ".z"),
                (float) config.getDouble("arena" + ".location" + ".yaw"),
                (float) config.getDouble("arena" + ".location" + ".pitch"));
    }

    public void delArena(){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.PVPARENA).getConfig();

        config.set("arena" + ".location", null);
        Survival.plugin.getConfigManager().getFile(ConfigType.PVPARENA).save();
    }
}