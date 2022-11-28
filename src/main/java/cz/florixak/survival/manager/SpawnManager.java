package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class SpawnManager {

    private Survival plugin;
    private FileConfiguration config, spawn;
    private int spawnProtection;

    public SpawnManager(Survival plugin){
        this.plugin = plugin;
        this.config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
        this.spawn = plugin.getConfigManager().getFile(ConfigType.SPAWN).getConfig();
        this.spawnProtection = config.getInt("spawn.protection");
    }

    public void addSpawn(Location loc){

        spawn.set("spawn" + ".world", loc.getWorld().getName());
        spawn.set("spawn" + ".x", loc.getX());
        spawn.set("spawn" + ".y", loc.getY());
        spawn.set("spawn" + ".z", loc.getZ());
        spawn.set("spawn" + ".yaw", loc.getYaw());
        spawn.set("spawn" + ".pitch", loc.getPitch());

        plugin.getConfigManager().getFile(ConfigType.SPAWN).save();
    }

    public boolean exist(){
        return config.get("spawn") != null;
    }

    public Location getLocation(){
        return new Location(
                Bukkit.getWorld(config.getString("spawn" + ".world")),
                config.getDouble("spawn" + ".x"),
                config.getDouble("spawn" + ".y"),
                config.getDouble("spawn" + ".z"),
                (float) config.getDouble("spawn" + ".yaw"),
                (float) config.getDouble("spawn" + ".pitch"));
    }

    public void delSpawn(){
        config.set("spawn", null);
        plugin.getConfigManager().getFile(ConfigType.SPAWN).save();
    }

    public int getSpawnProtection() {
        return spawnProtection;
    }
}