package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class PvPArenaManager {

    private Survival plugin;
    private FileConfiguration arena;

    private int arenaSize;

    public PvPArenaManager(Survival plugin){
        this.plugin = plugin;
        this.arena = plugin.getConfigManager().getFile(ConfigType.PVPARENA).getConfig();
        this.arenaSize = 50;
    }

    public void addArena(Location loc){

        arena.set("arena" + ".location" + ".world", loc.getWorld().getName());
        arena.set("arena" + ".location" + ".x", loc.getX());
        arena.set("arena" + ".location" + ".y", loc.getY());
        arena.set("arena" + ".location" + ".z", loc.getZ());
        arena.set("arena" + ".location" + ".yaw", loc.getYaw());
        arena.set("arena" + ".location" + ".pitch", loc.getPitch());

        plugin.getConfigManager().getFile(ConfigType.PVPARENA).save();
    }

    public boolean exist(){
        return arena.getConfigurationSection("arena" + ".location") != null;
    }

    public Location getLocation(){
        return new Location(
                Bukkit.getWorld(arena.getString("arena" + ".location" + ".world")),
                arena.getDouble("arena" + ".location" + ".x"),
                arena.getDouble("arena" + ".location" + ".y"),
                arena.getDouble("arena" + ".location" + ".z"),
                (float) arena.getDouble("arena" + ".location" + ".yaw"),
                (float) arena.getDouble("arena" + ".location" + ".pitch"));
    }

    public void delArena(){
        arena.set("arena" + ".location", null);
        plugin.getConfigManager().getFile(ConfigType.PVPARENA).save();
    }

    public int getArenaSize() {
        return arenaSize;
    }
}