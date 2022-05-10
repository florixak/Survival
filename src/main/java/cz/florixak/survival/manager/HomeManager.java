package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.utility.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class HomeManager {

    private FileConfiguration config;

    public HomeManager(){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.HOMES).getConfig();
    }

    public void addHome(UUID uuid, Location loc, String name){

        config = Survival.plugin.getConfigManager().getFile(ConfigType.HOMES).getConfig();

        config.set("homes." + uuid.toString() + "." + name + ".world", loc.getWorld().getName());
        config.set("homes." + uuid.toString() + "." + name + ".x", loc.getX());
        config.set("homes." + uuid.toString() + "." + name + ".y", loc.getY());
        config.set("homes." + uuid.toString() + "." + name + ".z", loc.getZ());
        config.set("homes." + uuid.toString() + "." + name + ".yaw", loc.getYaw());
        config.set("homes." + uuid.toString() + "." + name + ".pitch", loc.getPitch());

        Survival.plugin.getConfigManager().getFile(ConfigType.HOMES).save();
    }

    public boolean exist(UUID uuid, String name){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.HOMES).getConfig();

        return config.get("homes." + uuid.toString() + "." + name) != null;
    }

    public void openHomeList(Player p){
        UUID uuid = p.getUniqueId();
        Inventory inventory = Bukkit.createInventory(p, 27, "Domovy");
        // int homes = config.getConfigurationSection("homes." + uuid.toString()).getKeys(false).size();
        ItemStack sign = new ItemStack(Material.OAK_SIGN);
        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        int items = 0;

        Set<String> homeList = config.getConfigurationSection("homes." + uuid.toString()).getKeys(false);

        String[] home = homeList.toArray(new String[homeList.size()]);

        for (int i = 0; i < homeList.size(); i++) {
            Survival.plugin.getItemManager().setItemMeta(sign, TextUtil.color(home[i]), 1);
            inventory.addItem(sign);
            items = i;
        }
        for (int j = items+1; j < inventory.getSize(); j++) {
            Survival.plugin.getItemManager().setItemMeta(filler, " ", 1);
            inventory.setItem(j, filler);
        }
        p.openInventory(inventory);

    }

    public void homesList(UUID uuid){

        config = Survival.plugin.getConfigManager().getFile(ConfigType.HOMES).getConfig();

        Player p = Bukkit.getPlayer(uuid);

        if (homeIsNull(uuid)){

            p.sendMessage(Messages.HOME_NO_HOMES.toString());

        } else {

            String out = "";
            for (String s : config.getConfigurationSection("homes." + uuid.toString()).getKeys(false)){
                out = s + "§f, " + out;
            }
            out = out.trim();

            if (!(config.getConfigurationSection("homes." + uuid.toString()).getKeys(false).size() <= 0)){
                p.sendMessage(Messages.HOME_LIST.toString()
                        .replace("%home_list%", out));
            } else {
                p.sendMessage(Messages.HOME_NO_HOMES.toString());
            }
        }
    }

    public Set<String> getHomes(UUID uuid){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.HOMES).getConfig();

        return config.getConfigurationSection("homes." + uuid.toString()).getKeys(false);
    }

    public Location getLocation(UUID uuid, String name){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.HOMES).getConfig();

        return new Location(
                Bukkit.getWorld(config.getString("homes." + uuid.toString() + "." + name + ".world")),
                config.getDouble("homes." + uuid.toString() + "." + name + ".x"),
                config.getDouble("homes." + uuid.toString() + "." + name + ".y"),
                config.getDouble("homes." + uuid.toString() + "." + name + ".z"),
                (float) config.getDouble("homes." + uuid.toString() + "." + name + ".yaw"),
                (float) config.getDouble("homes." + uuid.toString() + "." + name + ".pitch"));
    }

    public void delHome(UUID uuid, String name){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.HOMES).getConfig();

        config.set("homes." + uuid.toString() + "." + name, null);
        Survival.plugin.getConfigManager().getFile(ConfigType.HOMES).save();
    }

    public boolean homeIsNull(UUID uuid){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.HOMES).getConfig();

        return config.getConfigurationSection("homes." + uuid.toString()) == null;
    }
}