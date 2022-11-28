package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.utility.TextUtil;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class HealManager {

    private FileConfiguration config;

    private String heal_menu_name;
    private int heal_menu_slots;

    private int delay;
    private int prize;

    public HealManager(Survival plugin) {
        this.config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
        this.prize = config.getInt("heal.prize");
        this.heal_menu_name = config.getString("heal.gui.name");
        this.heal_menu_slots = config.getInt("heal.gui.slots");
    }


    public void openHealMenu(Player p) {
        Inventory heal_menu = Bukkit.createInventory(null, heal_menu_slots, TextUtil.color(heal_menu_name));

    }


}