package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.utility.TextUtil;
import hps.land.hpscore.utility.universal.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private Survival plugin;

    public ItemManager(Survival plugin){
        this.plugin = plugin;
    }

    public ItemStack getItem(Material material, int amount, String name) {
        ItemStack item = new ItemStack(material);
        setItemMeta(item, name, amount);
        return item;
    }

    public void setItemMeta(ItemStack item, String name, int amount){

        ItemMeta meta = item.getItemMeta();
        if (name != null) meta.setDisplayName(TextUtil.color(name));
        item.setItemMeta(meta);
        item.setAmount(amount);
    }

    public void setArmorItemMeta(ItemStack item, String name, Color armorColor){

        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        if (name != null) meta.setDisplayName(TextUtil.color(name));
        if (armorColor != null) meta.setColor(armorColor);
        item.setItemMeta(meta);
    }

    public void addEnchant(ItemStack item, Enchantment enchantment, int enchantLevel, boolean ignoreLevel){

        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(enchantment, enchantLevel, ignoreLevel);
        item.setItemMeta(meta);
    }

    public void addBookEnchantment(ItemStack item, Enchantment enchantment, int enchantLevel){
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchantment, enchantLevel, true);
        item.setItemMeta(meta);
    }

    public void addGlow(ItemStack item){

        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
    }

    public void removeAttributes(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
    }

    public void addLore(ItemStack item, String line1, String line2, String line3){

        List<String> lore = new ArrayList<>();
        if (line1 != null) lore.add(TextUtil.color(line1));
        if (line2 != null) lore.add(TextUtil.color(line2));
        if (line3 != null) lore.add(TextUtil.color(line3));

        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public void invFullMessage(Player p){
        p.sendMessage(Messages.FULL_INVENTORY.toString());
    }

    public boolean invFull(Player p) {
        return p.getInventory().firstEmpty() == -1;
    }

    public void dropItemIfInvFull(ItemStack item, Player p){
        if (invFull(p)){
            if (p.getInventory().getHelmet() == null) {
                if (item.getType().equals(Material.LEATHER_HELMET)
                        || item.getType().equals(Material.CHAINMAIL_HELMET)
                        || item.getType().equals(Material.GOLDEN_HELMET)
                        || item.getType().equals(Material.IRON_HELMET)
                        || item.getType().equals(Material.DIAMOND_HELMET)
                        || item.getType().equals(XMaterial.NETHERITE_HELMET.parseMaterial())
                ) {
                    p.getInventory().setHelmet(item);
                    p.updateInventory();
                    return;
                }
            }
            if (p.getInventory().getChestplate() == null) {
                if (item.getType().equals(Material.LEATHER_CHESTPLATE)
                        || item.getType().equals(Material.CHAINMAIL_CHESTPLATE)
                        || item.getType().equals(Material.GOLDEN_CHESTPLATE)
                        || item.getType().equals(Material.IRON_CHESTPLATE)
                        || item.getType().equals(Material.DIAMOND_CHESTPLATE)
                        || item.getType().equals(XMaterial.NETHERITE_CHESTPLATE.parseMaterial())
                ) {
                    p.getInventory().setChestplate(item);
                    p.updateInventory();
                    return;
                }
            }
            if (p.getInventory().getLeggings() == null) {
                if (item.getType().equals(XMaterial.LEATHER_LEGGINGS.parseMaterial())
                        || item.getType().equals(XMaterial.CHAINMAIL_LEGGINGS.parseMaterial())
                        || item.getType().equals(XMaterial.GOLDEN_LEGGINGS.parseMaterial())
                        || item.getType().equals(XMaterial.IRON_LEGGINGS.parseMaterial())
                        || item.getType().equals(XMaterial.DIAMOND_LEGGINGS.parseMaterial())
                        || item.getType().equals(XMaterial.NETHERITE_LEGGINGS.parseMaterial())
                ) {
                    p.getInventory().setLeggings(item);
                    p.updateInventory();
                    return;
                }
            }
            if (p.getInventory().getBoots() == null) {
                if (item.getType().equals(Material.LEATHER_BOOTS)
                        || item.getType().equals(Material.CHAINMAIL_BOOTS)
                        || item.getType().equals(Material.GOLDEN_BOOTS)
                        || item.getType().equals(Material.IRON_BOOTS)
                        || item.getType().equals(Material.DIAMOND_BOOTS)
                        || item.getType().equals(XMaterial.NETHERITE_BOOTS.parseMaterial())
                ) {
                    p.getInventory().setBoots(item);
                    p.updateInventory();
                    return;
                }
            }
            Bukkit.getWorld(p.getWorld().getName()).dropItemNaturally(p.getLocation(), item);
            p.sendMessage(Messages.FULL_INVENTORY.toString());

        } else {
            if (p.getInventory().getHelmet() == null) {
                if (item.getType().equals(Material.LEATHER_HELMET)
                        || item.getType().equals(Material.CHAINMAIL_HELMET)
                        || item.getType().equals(Material.GOLDEN_HELMET)
                        || item.getType().equals(Material.IRON_HELMET)
                        || item.getType().equals(Material.DIAMOND_HELMET)
                        || item.getType().equals(Material.NETHERITE_HELMET)
                ) {
                    p.getInventory().setHelmet(item);
                    p.updateInventory();
                    return;
                }
            }
            if (p.getInventory().getChestplate() == null) {
                if (item.getType().equals(Material.LEATHER_CHESTPLATE)
                        || item.getType().equals(Material.CHAINMAIL_CHESTPLATE)
                        || item.getType().equals(Material.GOLDEN_CHESTPLATE)
                        || item.getType().equals(Material.IRON_CHESTPLATE)
                        || item.getType().equals(Material.DIAMOND_CHESTPLATE)
                        || item.getType().equals(Material.NETHERITE_CHESTPLATE)
                ) {
                    p.getInventory().setChestplate(item);
                    p.updateInventory();
                    return;
                }
            }
            if (p.getInventory().getLeggings() == null) {
                if (item.getType().equals(Material.LEATHER_LEGGINGS)
                        || item.getType().equals(Material.CHAINMAIL_LEGGINGS)
                        || item.getType().equals(Material.GOLDEN_LEGGINGS)
                        || item.getType().equals(Material.IRON_LEGGINGS)
                        || item.getType().equals(Material.DIAMOND_LEGGINGS)
                        || item.getType().equals(Material.NETHERITE_LEGGINGS)
                ) {
                    p.getInventory().setLeggings(item);
                    p.updateInventory();
                    return;
                }
            }
            if (p.getInventory().getBoots() == null) {
                if (item.getType().equals(Material.LEATHER_BOOTS)
                        || item.getType().equals(Material.CHAINMAIL_BOOTS)
                        || item.getType().equals(Material.GOLDEN_BOOTS)
                        || item.getType().equals(Material.IRON_BOOTS)
                        || item.getType().equals(Material.DIAMOND_BOOTS)
                        || item.getType().equals(Material.NETHERITE_BOOTS)
                ) {
                    p.getInventory().setBoots(item);
                    p.updateInventory();
                    return;
                }
            }
            p.getInventory().addItem(item);
            p.updateInventory();
        }
    }
}