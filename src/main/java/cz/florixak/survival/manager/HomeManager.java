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

    private Survival plugin;
    private FileConfiguration homes;

    public HomeManager(Survival plugin) {
        this.plugin = plugin;
        this.homes = plugin.getConfigManager().getFile(ConfigType.HOMES).getConfig();
    }

    public void addHome(UUID uuid, Location loc, String name){

        homes.set("homes." + uuid.toString() + "." + name + ".world", loc.getWorld().getName());
        homes.set("homes." + uuid.toString() + "." + name + ".x", loc.getX());
        homes.set("homes." + uuid.toString() + "." + name + ".y", loc.getY());
        homes.set("homes." + uuid.toString() + "." + name + ".z", loc.getZ());
        homes.set("homes." + uuid.toString() + "." + name + ".yaw", loc.getYaw());
        homes.set("homes." + uuid.toString() + "." + name + ".pitch", loc.getPitch());

        plugin.getConfigManager().getFile(ConfigType.HOMES).save();
    }

    public boolean exist(UUID uuid, String name){
        return homes.get("homes." + uuid.toString() + "." + name) != null;
    }

    public void openHomeList(Player p) {
        UUID uuid = p.getUniqueId();
        Inventory inventory = Bukkit.createInventory(p, 27, "Domovy");
        // int homes = config.getConfigurationSection("homes." + uuid.toString()).getKeys(false).size();
        ItemStack sign = new ItemStack(Material.OAK_SIGN);
        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        int items = 0;

        Set<String> homeList = homes.getConfigurationSection("homes." + uuid.toString()).getKeys(false);

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

    public void homesList(UUID uuid) {

        Player p = Bukkit.getPlayer(uuid);

        if (homeIsNull(uuid)){
            p.sendMessage(Messages.HOME_NO_HOMES.toString());
            return;
        }
        String out = "";
        for (String s : homes.getConfigurationSection("homes." + uuid.toString()).getKeys(false)){
            out = s + "§f, " + out;
        }
        out = out.trim();
        if (!(homes.getConfigurationSection("homes." + uuid.toString()).getKeys(false).size() <= 0)){
            p.sendMessage(Messages.HOME_LIST.toString()
                    .replace("%home_list%", out));
        } else {
            p.sendMessage(Messages.HOME_NO_HOMES.toString());
        }
    }

    public Set<String> getHomes(UUID uuid){
        return homes.getConfigurationSection("homes." + uuid.toString()).getKeys(false);
    }

    public Location getLocation(UUID uuid, String name){
        return new Location(
                Bukkit.getWorld(homes.getString("homes." + uuid.toString() + "." + name + ".world")),
                homes.getDouble("homes." + uuid.toString() + "." + name + ".x"),
                homes.getDouble("homes." + uuid.toString() + "." + name + ".y"),
                homes.getDouble("homes." + uuid.toString() + "." + name + ".z"),
                (float) homes.getDouble("homes." + uuid.toString() + "." + name + ".yaw"),
                (float) homes.getDouble("homes." + uuid.toString() + "." + name + ".pitch"));
    }

    public void delHome(UUID uuid, String name){
        homes.set("homes." + uuid.toString() + "." + name, null);
        plugin.getConfigManager().getFile(ConfigType.HOMES).save();
    }

    public boolean homeIsNull(UUID uuid){
        return homes.getConfigurationSection("homes." + uuid.toString()) == null;
    }
}