package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class SpawnManager {

    private FileConfiguration config;

    public SpawnManager(){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.SPAWN).getConfig();
    }

    public void addSpawn(Location loc){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.SPAWN).getConfig();

        config.set("spawn" + ".world", loc.getWorld().getName());
        config.set("spawn" + ".x", loc.getX());
        config.set("spawn" + ".y", loc.getY());
        config.set("spawn" + ".z", loc.getZ());
        config.set("spawn" + ".yaw", loc.getYaw());
        config.set("spawn" + ".pitch", loc.getPitch());

        Survival.plugin.getConfigManager().getFile(ConfigType.SPAWN).save();
    }

    public boolean exist(){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.SPAWN).getConfig();
        return config.get("spawn") != null;
    }

    public Location getLocation(){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.SPAWN).getConfig();

        return new Location(
                Bukkit.getWorld(config.getString("spawn" + ".world")),
                config.getDouble("spawn" + ".x"),
                config.getDouble("spawn" + ".y"),
                config.getDouble("spawn" + ".z"),
                (float) config.getDouble("spawn" + ".yaw"),
                (float) config.getDouble("spawn" + ".pitch"));
    }

    public void delSpawn(){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.SPAWN).getConfig();

        config.set("spawn", null);
        Survival.plugin.getConfigManager().getFile(ConfigType.SPAWN).save();
    }
}