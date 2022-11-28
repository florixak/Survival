package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.manager.ItemManager;
import cz.florixak.survival.manager.SpawnManager;
import cz.florixak.survival.utility.TextUtil;
import cz.florixak.survival.utility.XSeries.XMaterial;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Random;

public class SpecialMobSpawnListener implements Listener {

    private SpawnManager spawnManager;
    private ItemManager itemManager;

    private int protection;

    public SpecialMobSpawnListener(Survival plugin) {
        this.spawnManager = plugin.getSpawnManager();
        this.itemManager = plugin.getItemManager();
        this.protection = spawnManager.getSpawnProtection();
    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event){

        if (event.getEntity().getWorld().equals("world")
                && spawnManager.exist()
                && event.getEntity().getLocation().distance(spawnManager.getLocation()) < protection) {

            if (event.getEntity() instanceof Monster || event.getEntity() instanceof Phantom) {
                event.setCancelled(true);
                return;
            }
        }

        if (event.getEntity() instanceof Zombie){

            Random r = new Random();
            int choice = r.nextInt(100) + 1;
            Zombie zombie = (Zombie) event.getEntity();

            if (choice < 5){
                zombie.setMaxHealth(40);
                zombie.setHealth(zombie.getMaxHealth());
                zombie.setCustomName(TextUtil.color("&6&lLegendární Zombie"));

            } else if (choice < 15){
                zombie.setMaxHealth(30);
                zombie.setHealth(zombie.getMaxHealth());
                zombie.setCustomName(TextUtil.color("&9Vzácný Zombie"));
            }
        }

        if (event.getEntity() instanceof Spider){
            Random r = new Random();
            int choice = r.nextInt(100) + 1;
            Spider spider = (Spider) event.getEntity();

            if (choice < 5){
                spider.setMaxHealth(60);
                spider.setHealth(spider.getMaxHealth());
                spider.setCustomName(TextUtil.color("&c&lSpider&7-&9&lMan"));
            }
        }

        if (event.getEntity() instanceof Skeleton){
            Random r = new Random();
            int choice = r.nextInt(100) + 1;
            Skeleton skeleton = (Skeleton) event.getEntity();

            if (choice < 2){

                ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta chest_meta = (LeatherArmorMeta) chest.getItemMeta();
                chest_meta.setDisplayName(TextUtil.color("&cG&fh&co&fs&ct &fR&ci&fd&ce&fr &cB&fu&cn&fd&ca"));
                chest_meta.addEnchant(Enchantment.PROTECTION_FIRE, 10, true);
                chest_meta.addEnchant(Enchantment.DURABILITY, 10, true);
                chest_meta.setColor(Color.BLACK);
                chest.setItemMeta(chest_meta);

                ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
                LeatherArmorMeta leg_meta = (LeatherArmorMeta) leg.getItemMeta();
                leg_meta.setDisplayName(TextUtil.color("&cG&fh&co&fs&ct &fR&ci&fd&ce&fr &cK&fa&cl&fh&co&ft&cy"));
                leg_meta.addEnchant(Enchantment.PROTECTION_FIRE, 10, true);
                leg_meta.addEnchant(Enchantment.DURABILITY, 10, true);
                leg_meta.setColor(Color.BLACK);
                leg.setItemMeta(leg_meta);

                ItemStack boot = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta boot_meta = (LeatherArmorMeta) boot.getItemMeta();
                boot_meta.setDisplayName(TextUtil.color("&cG&fh&co&fs&ct &fR&ci&fd&ce&fr &cB&fo&ct&fy"));
                boot_meta.addEnchant(Enchantment.PROTECTION_FIRE, 10, true);
                boot_meta.addEnchant(Enchantment.DURABILITY, 10, true);
                boot_meta.setColor(Color.BLACK);
                boot.setItemMeta(boot_meta);

                skeleton.setMaxHealth(40);
                skeleton.setHealth(skeleton.getMaxHealth());
                skeleton.getEquipment().setChestplate(chest);
                skeleton.getEquipment().setLeggings(leg);
                skeleton.getEquipment().setBoots(boot);
                skeleton.setCustomName(TextUtil.color("&cG&fh&co&fs&ct &fR&ci&fd&ce&fr"));
            }
        }

        if (event.getEntity() instanceof Creeper){
            Random r = new Random();
            int choice = r.nextInt(100) + 1;
            Creeper creeper = (Creeper) event.getEntity();

            if (choice < 5){
                creeper.setMaxHealth(35);
                creeper.setHealth(creeper.getMaxHealth());
                creeper.setCustomName(TextUtil.color("&aDoom Boom!"));
                creeper.setPowered(true);
            }
        }
    }

    @EventHandler
    public void onModKilled(EntityDeathEvent event){

        if (event.getEntity() instanceof Zombie){
            Zombie zombie = (Zombie) event.getEntity();

            if (zombie.getCustomName() == null){ return; }

            if (zombie.getCustomName().equals(TextUtil.color("&9Vzácný Zombie"))){

                Random r = new Random();
                int choice = r.nextInt(4);

                event.getDrops().clear();

                switch (choice) {
                    case 0:
                        event.getDrops().add(new ItemStack(Material.GOLD_INGOT));
                        break;
                    case 1:
                        event.getDrops().add(new ItemStack(Material.IRON_INGOT));
                        break;
                    case 2:
                        event.getDrops().add(new ItemStack(XMaterial.COPPER_INGOT.parseMaterial()));
                        break;
                    case 3:
                        event.getDrops().add(new ItemStack(Material.COAL));
                        break;
                }

            } else if (zombie.getCustomName().equals(TextUtil.color("&6&lLegendární Zombie"))) {

                Random r = new Random();
                int choice = r.nextInt(100) + 1;

                event.getDrops().clear();

                if (choice < 5){
                    event.getDrops().add(new ItemStack(XMaterial.NETHERITE_SCRAP.parseMaterial()));
                } else if (choice < 15){
                    event.getDrops().add(new ItemStack(Material.DIAMOND));
                } else if (choice < 30){
                    event.getDrops().add(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
                } else {
                    Random ench_book = new Random();
                    int choice2 = ench_book.nextInt(4);
                    ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);

                    switch (choice2) {
                        case 0:
                            itemManager.addBookEnchantment(book, Enchantment.DAMAGE_ALL, 2);
                            event.getDrops().add(book);
                            break;
                        case 1:
                            itemManager.addBookEnchantment(book, Enchantment.DIG_SPEED, 2);
                            event.getDrops().add(book);
                            break;
                        case 2:
                            itemManager.addBookEnchantment(book, Enchantment.FIRE_ASPECT, 1);
                            event.getDrops().add(book);
                            break;
                        case 3:
                            itemManager.addBookEnchantment(book, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                            event.getDrops().add(book);
                            break;
                    }
                }
            }
        }

        if (event.getEntity() instanceof Spider){

            Spider spider = (Spider) event.getEntity();

            if (spider.getCustomName() == null){ return; }

            if (spider.getCustomName().equals(TextUtil.color("&c&lSpider&7-&9&lMan"))){

                Random r = new Random();
                int choice = r.nextInt(100) + 1;

                event.getDrops().clear();
                ItemStack item;
                LeatherArmorMeta meta;

                switch (choice) {
                    case 0:
                        item = new ItemStack(Material.LEATHER_HELMET);
                        meta = (LeatherArmorMeta) item.getItemMeta();
                        meta.setDisplayName(TextUtil.color("&cSpidermanova &9Maska"));
                        meta.setColor(Color.RED);
                        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false);
                        meta.addEnchant(Enchantment.PROTECTION_FIRE, 2, false);
                        meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, false);
                        meta.addEnchant(Enchantment.OXYGEN, 1, false);
                        meta.addEnchant(Enchantment.DURABILITY, 2, false);
                        item.setItemMeta(meta);

                        event.getDrops().add(item);
                        break;
                    case 1:
                        item = new ItemStack(Material.LEATHER_CHESTPLATE);
                        meta = (LeatherArmorMeta) item.getItemMeta();
                        meta.setDisplayName(TextUtil.color("&cSpidermanovo &9Tričko"));
                        meta.setColor(Color.RED);
                        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false);
                        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
                        meta.addEnchant(Enchantment.PROTECTION_FIRE, 2, false);
                        meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, false);
                        meta.addEnchant(Enchantment.DURABILITY, 2, false);
                        item.setItemMeta(meta);

                        event.getDrops().add(item);
                        break;
                    case 2:
                        item = new ItemStack(Material.LEATHER_LEGGINGS);
                        meta = (LeatherArmorMeta) item.getItemMeta();
                        meta.setDisplayName(TextUtil.color("&cSpidermanova &9Legíny"));
                        meta.setColor(Color.BLUE);
                        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false);
                        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
                        meta.addEnchant(Enchantment.PROTECTION_FIRE, 2, false);
                        meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, false);
                        meta.addEnchant(Enchantment.DURABILITY, 2, false);
                        item.setItemMeta(meta);

                        event.getDrops().add(item);
                        break;
                    case 3:
                        item = new ItemStack(Material.LEATHER_BOOTS);
                        meta = (LeatherArmorMeta) item.getItemMeta();
                        meta.setDisplayName(TextUtil.color("&cSpidermanovo &9Adidasky"));
                        meta.setColor(Color.RED);
                        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false);
                        meta.addEnchant(Enchantment.PROTECTION_FIRE, 2, false);
                        meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, false);
                        meta.addEnchant(Enchantment.PROTECTION_FALL, 4, false);
                        meta.addEnchant(Enchantment.DURABILITY, 2, false);
                        item.setItemMeta(meta);

                        event.getDrops().add(item);
                        break;
                }
            }
        }
        if (event.getEntity() instanceof Skeleton){

            Skeleton skeleton = (Skeleton) event.getEntity();

            if (skeleton.getCustomName() == null){
                return;
            }

            if (skeleton.getCustomName().equals(TextUtil.color("&cG&fh&co&fs&ct &fR&ci&fd&ce&fr"))){

                event.getDrops().clear();

                ItemStack skull = new ItemStack(Material.SKELETON_SKULL);
                ItemMeta skull_meta = skull.getItemMeta();
                skull_meta.setDisplayName(TextUtil.color("&cGhost Riderova Lebka"));
                skull.setItemMeta(skull_meta);

                ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta chest_meta = (LeatherArmorMeta) chest.getItemMeta();
                chest_meta.setDisplayName(TextUtil.color("&cGhost Riderova Bunda"));
                chest_meta.addEnchant(Enchantment.PROTECTION_FIRE, 10, true);
                chest_meta.addEnchant(Enchantment.DURABILITY, 10, true);
                chest_meta.setColor(Color.BLACK);
                chest.setItemMeta(chest_meta);

                ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
                LeatherArmorMeta leg_meta = (LeatherArmorMeta) leg.getItemMeta();
                leg_meta.setDisplayName(TextUtil.color("&cGhost Riderovy Kalhoty"));
                leg_meta.addEnchant(Enchantment.PROTECTION_FIRE, 10, true);
                leg_meta.addEnchant(Enchantment.DURABILITY, 10, true);
                leg_meta.setColor(Color.BLACK);
                leg.setItemMeta(leg_meta);

                ItemStack boot = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta boot_meta = (LeatherArmorMeta) boot.getItemMeta();
                boot_meta.setDisplayName(TextUtil.color("&cGhost Riderovy Boty"));
                boot_meta.addEnchant(Enchantment.PROTECTION_FIRE, 10, true);
                boot_meta.addEnchant(Enchantment.DURABILITY, 10, true);
                boot_meta.setColor(Color.BLACK);
                boot.setItemMeta(boot_meta);

                Random ran = new Random();
                int choice = ran.nextInt(4);

                switch (choice){
                    case 0:
                        event.getDrops().add(chest);
                        break;
                    case 1:
                        event.getDrops().add(leg);
                        break;
                    case 2:
                        event.getDrops().add(boot);
                        break;
                    default:
                        event.getDrops().add(skull);
                        break;
                }
            }
        }

        if (event.getEntity() instanceof Creeper){

            Creeper creeper = (Creeper) event.getEntity();

            if (creeper.getCustomName() == null){
                return;
            }

            if (creeper.getCustomName().equals(TextUtil.color("&aDoom Boom!"))){

                ItemStack tnt = new ItemStack(Material.TNT);

                Random r = new Random();
                int choice = r.nextInt(100) + 1;

                event.getDrops().clear();

                if (choice < 10){
                    tnt.setAmount(6);
                    event.getDrops().add(tnt);
                } else if (choice < 40){
                    tnt.setAmount(3);
                    event.getDrops().add(tnt);
                } else if (choice < 75){
                    tnt.setAmount(2);
                    event.getDrops().add(tnt);
                } else {
                    tnt.setAmount(1);
                    event.getDrops().add(tnt);
                }
            }
        }
    }
}