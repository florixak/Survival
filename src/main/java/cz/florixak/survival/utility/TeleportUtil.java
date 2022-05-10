package cz.florixak.survival.utility;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;

public class TeleportUtil {

    //Get an instance of the main class so we can access config
    static Survival plugin;

    public TeleportUtil(Survival plugin) {
        this.plugin = plugin;
    }

    //List of all the unsafe blocks
    public static HashSet<Material> bad_blocks = new HashSet<>();

    static{
        bad_blocks.add(Material.LAVA);
        bad_blocks.add(Material.FIRE);
        bad_blocks.add(Material.CACTUS);
        bad_blocks.add(Material.WATER);
    }

    public static Location generateLocation(Player player){
        //Generate Random Location
        Random random = new Random();

        FileConfiguration config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();

        int x = 0;
        int z = 0;
        int y = 0;

        if(config.getBoolean("world-border")){ //If they want to limit the distance
            x = random.nextInt(config.getInt("border"));
            z = random.nextInt(config.getInt("border"));
            y = 150;
        }else if(!config.getBoolean("world-border")){ //If they dont
            x = random.nextInt(3000); //25000 is default
            z = random.nextInt(3000);
            y = 150;
        }

        Location randomLocation = new Location(player.getWorld(), x, y, z);
        y = randomLocation.getWorld().getHighestBlockYAt(randomLocation);
        randomLocation.setY(y);

        return randomLocation;
    }

    public static Location findSafeLocation(Player player){

        Location randomLocation = generateLocation(player);

        while (!isLocationSafe(randomLocation)){
            //Keep looking for a safe location
            randomLocation = generateLocation(player);
        }
        return randomLocation;
    }

    public static boolean isLocationSafe(Location location){

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        //Get instances of the blocks around where the player would spawn
        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);

        //Check to see if the surroundings are safe or not
        return !(bad_blocks.contains(below.getType())) || (block.getType().isSolid()) || (above.getType().isSolid());
    }

}
