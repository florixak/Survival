package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.utility.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class KitsManager {

    private Survival plugin;
    private ItemManager itemManager;

    public KitsManager(Survival plugin) {
        this.plugin = plugin;
        this.itemManager = plugin.getItemManager();
    }

    public void starterToInv(Player p){

        ItemStack starter_sword = new ItemStack(Material.STONE_SWORD);
        ItemStack starter_pick = new ItemStack(Material.STONE_PICKAXE);
        ItemStack starter_axe = new ItemStack(Material.STONE_AXE);
        ItemStack starter_shovel = new ItemStack(Material.STONE_SHOVEL);
        ItemStack starter_helm = new ItemStack(Material.LEATHER_HELMET);
        ItemStack starter_chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack starter_leg = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack starter_boot = new ItemStack(Material.LEATHER_BOOTS);
        ItemStack starter_bread = new ItemStack(Material.BREAD);

        Survival.plugin.getItemManager().setItemMeta(starter_sword, "&8Začátečnický Meč", 1);
        Survival.plugin.getItemManager().setItemMeta(starter_pick, "&8Začátečnický Krumpáč", 1);
        Survival.plugin.getItemManager().setItemMeta(starter_axe, "&8Začátečnická Sekera", 1);
        Survival.plugin.getItemManager().setItemMeta(starter_shovel, "&8Začátečnická Lopata", 1);
        Survival.plugin.getItemManager().setItemMeta(starter_bread, null, 16);
        Survival.plugin.getItemManager().setItemMeta(starter_helm, "&8Začátečnická Čepka", 1);
        Survival.plugin.getItemManager().setItemMeta(starter_chest, "&8Začátečnická Vesta", 1);
        Survival.plugin.getItemManager().setItemMeta(starter_leg, "&8Začátečnické Kraťasy", 1);
        Survival.plugin.getItemManager().setItemMeta(starter_boot, "&8Začátečnické Sandály", 1);

        itemManager.dropItemIfInvFull(starter_sword, p);
        itemManager.dropItemIfInvFull(starter_pick, p);
        itemManager.dropItemIfInvFull(starter_axe, p);
        itemManager.dropItemIfInvFull(starter_shovel, p);
        itemManager.dropItemIfInvFull(starter_bread, p);
        itemManager.dropItemIfInvFull(starter_helm, p);
        itemManager.dropItemIfInvFull(starter_chest, p);
        itemManager.dropItemIfInvFull(starter_leg, p);
        itemManager.dropItemIfInvFull(starter_boot, p);

        if (itemManager.invFull(p)){
            itemManager.invFullMessage(p);
        }
    }

    public void farmerToInv(Player p){

        ItemStack farmer_axe = new ItemStack(Material.STONE_AXE);
        ItemStack farmer_hoe = new ItemStack(Material.STONE_HOE);
        ItemStack farmer_shovel = new ItemStack(Material.STONE_SHOVEL);
        ItemStack farmer_wheat = new ItemStack(Material.WHEAT);
        ItemStack farmer_carrot = new ItemStack(Material.CARROT);
        ItemStack farmer_potato = new ItemStack(Material.POTATO);
        ItemStack farmer_helm = new ItemStack(Material.LEATHER_HELMET);
        ItemStack farmer_chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack farmer_leg = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack farmer_boot = new ItemStack(Material.LEATHER_BOOTS);

        itemManager.setItemMeta(farmer_axe, "&aFarmářská Sekera", 1);
        itemManager.setItemMeta(farmer_shovel, "&aFarmářská Lopata", 1);
        itemManager.setItemMeta(farmer_hoe, "&aFarmářská Lopata", 1);
        itemManager.setItemMeta(farmer_wheat, null, 32);
        itemManager.setItemMeta(farmer_carrot, null, 16);
        itemManager.setItemMeta(farmer_potato, null, 16);

        itemManager.setArmorItemMeta(farmer_helm, "&aFarmářský Slamák", Color.YELLOW);
        itemManager.setArmorItemMeta(farmer_chest, "&aFarmářská Vesta", Color.YELLOW);
        itemManager.setArmorItemMeta(farmer_leg, "&aFarmářské Kšandy", Color.YELLOW);
        itemManager.setArmorItemMeta(farmer_boot, "&aFarmářské Holinky", Color.YELLOW);


        itemManager.dropItemIfInvFull(farmer_hoe, p);
        itemManager.dropItemIfInvFull(farmer_axe, p);
        itemManager.dropItemIfInvFull(farmer_shovel, p);
        itemManager.dropItemIfInvFull(farmer_wheat, p);
        itemManager.dropItemIfInvFull(farmer_carrot, p);
        itemManager.dropItemIfInvFull(farmer_potato, p);
        itemManager.dropItemIfInvFull(farmer_helm, p);
        itemManager.dropItemIfInvFull(farmer_chest, p);
        itemManager.dropItemIfInvFull(farmer_leg, p);
        itemManager.dropItemIfInvFull(farmer_boot, p);

        if (itemManager.invFull(p)){
            itemManager.invFullMessage(p);
        }
    }

    public void knightToInv(Player p){

        ItemStack knight_sword = new ItemStack(Material.IRON_SWORD);
        ItemStack knight_pick = new ItemStack(Material.IRON_PICKAXE);
        ItemStack knight_axe = new ItemStack(Material.IRON_AXE);
        ItemStack knight_shovel = new ItemStack(Material.IRON_SHOVEL);
        ItemStack knight_helm = new ItemStack(Material.IRON_HELMET);
        ItemStack knight_chest = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack knight_leg = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack knight_boot = new ItemStack(Material.IRON_BOOTS);
        ItemStack knight_bread = new ItemStack(Material.BREAD);
        ItemStack knight_gapple = new ItemStack(Material.GOLDEN_APPLE);

        itemManager.setItemMeta(knight_sword, "&dRytířský Meč", 1);
        itemManager.addEnchant(knight_sword, Enchantment.DAMAGE_ALL, 1, false);
        itemManager.addEnchant(knight_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(knight_pick, "&dRytířský Krumpáč", 1);
        itemManager.addEnchant(knight_sword, Enchantment.DIG_SPEED, 1, false);
        itemManager.addEnchant(knight_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(knight_axe, "&dRytířská Sekera", 1);
        itemManager.addEnchant(knight_sword, Enchantment.DIG_SPEED, 1, false);
        itemManager.addEnchant(knight_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(knight_shovel, "&dRytířská Lopata", 1);
        itemManager.addEnchant(knight_sword, Enchantment.DIG_SPEED, 1, false);
        itemManager.addEnchant(knight_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(knight_helm, "&dRytířská Helma", 1);
        itemManager.addEnchant(knight_sword, Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        itemManager.addEnchant(knight_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(knight_chest, "&dRytířská Vesta", 1);
        itemManager.addEnchant(knight_sword, Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        itemManager.addEnchant(knight_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(knight_leg, "&dRytířské Kalhoty", 1);
        itemManager.addEnchant(knight_sword, Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        itemManager.addEnchant(knight_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(knight_boot, "&dRytířské Boty", 1);
        itemManager.addEnchant(knight_sword, Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        itemManager.addEnchant(knight_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(knight_gapple, null, 3);
        itemManager.setItemMeta(knight_bread, null, 16);

        itemManager.dropItemIfInvFull(knight_sword, p);
        itemManager.dropItemIfInvFull(knight_pick, p);
        itemManager.dropItemIfInvFull(knight_axe, p);
        itemManager.dropItemIfInvFull(knight_shovel, p);
        itemManager.dropItemIfInvFull(knight_bread, p);
        itemManager.dropItemIfInvFull(knight_gapple, p);
        itemManager.dropItemIfInvFull(knight_helm, p);
        itemManager.dropItemIfInvFull(knight_chest, p);
        itemManager.dropItemIfInvFull(knight_leg, p);
        itemManager.dropItemIfInvFull(knight_boot, p);

        if (itemManager.invFull(p)){
            itemManager.invFullMessage(p);
        }
    }

    public void enchToInv(Player p){

        ItemStack ench_sharp = new ItemStack(Material.ENCHANTED_BOOK);
        ItemStack ench_effi = new ItemStack(Material.ENCHANTED_BOOK);
        ItemStack ench_prot = new ItemStack(Material.ENCHANTED_BOOK);
        ItemStack ench_bottle = new ItemStack(Material.EXPERIENCE_BOTTLE, 32);
        ItemStack ench_table = new ItemStack(Material.ENCHANTING_TABLE);
        ItemStack ench_bookshelf = new ItemStack(Material.BOOKSHELF, 16);
        ItemStack ench_lapis = new ItemStack(Material.LAPIS_LAZULI, 48);

        itemManager.addBookEnchantment(ench_sharp, Enchantment.DAMAGE_ALL, 2);
        itemManager.addBookEnchantment(ench_effi, Enchantment.DIG_SPEED, 2);
        itemManager.addBookEnchantment(ench_prot, Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        itemManager.setItemMeta(ench_bottle, "&5Očarované Lahvičky", 32);
        itemManager.setItemMeta(ench_table, "&5Stolek na Očarování", 32);
        itemManager.setItemMeta(ench_bookshelf, "&5Knihovny", 32);

        itemManager.dropItemIfInvFull(ench_sharp, p);
        itemManager.dropItemIfInvFull(ench_effi, p);
        itemManager.dropItemIfInvFull(ench_prot, p);
        itemManager.dropItemIfInvFull(ench_bottle, p);
        itemManager.dropItemIfInvFull(ench_table, p);
        itemManager.dropItemIfInvFull(ench_bookshelf, p);
        itemManager.dropItemIfInvFull(ench_lapis, p);

        if (itemManager.invFull(p)){
            itemManager.invFullMessage(p);
        }
    }

    public void kingToInv(Player p){

        ItemStack king_sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack king_pick = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemStack king_axe = new ItemStack(Material.DIAMOND_AXE);
        ItemStack king_shovel = new ItemStack(Material.DIAMOND_SHOVEL);
        ItemStack king_bread = new ItemStack(Material.COOKED_BEEF);
        ItemStack king_gapple = new ItemStack(Material.GOLDEN_APPLE);
        ItemStack king_egapple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
        ItemStack king_helm = new ItemStack(Material.GOLDEN_HELMET);
        ItemStack king_chest = new ItemStack(Material.GOLDEN_CHESTPLATE);
        ItemStack king_leg = new ItemStack(Material.GOLDEN_LEGGINGS);
        ItemStack king_boot = new ItemStack(Material.GOLDEN_BOOTS);

        itemManager.setItemMeta(king_sword, "&eKrálovský Meč", 1);
        itemManager.addEnchant(king_sword, Enchantment.DAMAGE_ALL, 2, false);
        itemManager.addEnchant(king_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(king_pick, "&eKrálovský Krumpáč", 1);
        itemManager.addEnchant(king_pick, Enchantment.DIG_SPEED, 2, false);
        itemManager.addEnchant(king_pick, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(king_axe, "&eKrálovská Sekera", 1);
        itemManager.addEnchant(king_axe, Enchantment.DIG_SPEED, 2, false);
        itemManager.addEnchant(king_axe, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(king_shovel, "&eKrálovská Lopata", 1);
        itemManager.addEnchant(king_shovel, Enchantment.DIG_SPEED, 2, false);
        itemManager.addEnchant(king_shovel, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(king_gapple, null, 6);
        itemManager.setItemMeta(king_egapple, null, 1);
        itemManager.setItemMeta(king_bread, null, 32);
        itemManager.setItemMeta(king_helm, "&eKrálovská Koruna", 1);
        itemManager.addEnchant(king_helm, Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        itemManager.addEnchant(king_helm, Enchantment.DURABILITY, 3, true);
        itemManager.setItemMeta(king_chest, "&eKrálovské Brnění", 1);
        itemManager.addEnchant(king_chest, Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        itemManager.addEnchant(king_chest, Enchantment.DURABILITY, 3, true);
        itemManager.setItemMeta(king_leg, "&eKrálovské Kalhoty", 1);
        itemManager.addEnchant(king_leg, Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        itemManager.addEnchant(king_leg, Enchantment.DURABILITY, 3, true);
        itemManager.setItemMeta(king_boot, "&eKrálovské Boty", 1);
        itemManager.addEnchant(king_boot, Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        itemManager.addEnchant(king_boot, Enchantment.DURABILITY, 3, true);

        itemManager.dropItemIfInvFull(king_sword, p);
        itemManager.dropItemIfInvFull(king_pick, p);
        itemManager.dropItemIfInvFull(king_axe, p);
        itemManager.dropItemIfInvFull(king_shovel, p);
        itemManager.dropItemIfInvFull(king_bread, p);
        itemManager.dropItemIfInvFull(king_gapple, p);
        itemManager.dropItemIfInvFull(king_egapple, p);
        itemManager.dropItemIfInvFull(king_helm, p);
        itemManager.dropItemIfInvFull(king_chest, p);
        itemManager.dropItemIfInvFull(king_leg, p);
        itemManager.dropItemIfInvFull(king_boot, p);

        if (itemManager.invFull(p)){
            itemManager.invFullMessage(p);
        }
    }

    public void cisarToInv(Player p){

        ItemStack cisar_sword = new ItemStack(Material.NETHERITE_SWORD);
        ItemStack cisar_pick = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemStack cisar_axe = new ItemStack(Material.NETHERITE_AXE);
        ItemStack cisar_shovel = new ItemStack(Material.NETHERITE_SHOVEL);
        ItemStack cisar_helm = new ItemStack(Material.NETHERITE_HELMET);
        ItemStack cisar_chest = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemStack cisar_leg = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemStack cisar_boot = new ItemStack(Material.NETHERITE_BOOTS);
        ItemStack cisar_gapple = new ItemStack(Material.GOLDEN_APPLE);
        ItemStack cisar_egapple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);

        itemManager.setItemMeta(cisar_sword, "&4Císařský Meč", 1);
        itemManager.addEnchant(cisar_sword, Enchantment.DAMAGE_ALL, 1, false);
        itemManager.addEnchant(cisar_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(cisar_pick, "&4Císařský Krumpáč", 1);
        itemManager.addEnchant(cisar_sword, Enchantment.DIG_SPEED, 1, false);
        itemManager.addEnchant(cisar_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(cisar_axe, "&4Císařská Sekera", 1);
        itemManager.addEnchant(cisar_sword, Enchantment.DIG_SPEED, 1, false);
        itemManager.addEnchant(cisar_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(cisar_shovel, "&4Císařská Lopata", 1);
        itemManager.addEnchant(cisar_sword, Enchantment.DIG_SPEED, 1, false);
        itemManager.addEnchant(cisar_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(cisar_helm, "&4Císařská Helma", 1);
        itemManager.addEnchant(cisar_sword, Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        itemManager.addEnchant(cisar_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(cisar_chest, "&4Císařské Brnění", 1);
        itemManager.addEnchant(cisar_sword, Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        itemManager.addEnchant(cisar_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(cisar_leg, "&4Císařské Kalhoty", 1);
        itemManager.addEnchant(cisar_sword, Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        itemManager.addEnchant(cisar_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(cisar_boot, "&4Císařské Boty", 1);
        itemManager.addEnchant(cisar_sword, Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        itemManager.addEnchant(cisar_sword, Enchantment.DURABILITY, 2, false);
        itemManager.setItemMeta(cisar_gapple, null, 12);
        itemManager.setItemMeta(cisar_egapple, null, 6);

        itemManager.dropItemIfInvFull(cisar_sword, p);
        itemManager.dropItemIfInvFull(cisar_pick, p);
        itemManager.dropItemIfInvFull(cisar_axe, p);
        itemManager.dropItemIfInvFull(cisar_shovel, p);
        itemManager.dropItemIfInvFull(cisar_gapple, p);
        itemManager.dropItemIfInvFull(cisar_gapple, p);
        itemManager.dropItemIfInvFull(cisar_egapple, p);
        itemManager.dropItemIfInvFull(cisar_helm, p);
        itemManager.dropItemIfInvFull(cisar_chest, p);
        itemManager.dropItemIfInvFull(cisar_leg, p);
        itemManager.dropItemIfInvFull(cisar_boot, p);

        if (itemManager.invFull(p)){
            itemManager.invFullMessage(p);
        }
    }
}