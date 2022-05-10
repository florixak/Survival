package cz.florixak.survival.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.ItemManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import cz.florixak.survival.utility.TextUtil;
import cz.florixak.survival.utility.XMaterial;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@CommandInfo(name = "ruleta", permission = "", requiresPlayer = true)
public class Roulette extends PluginCommand implements Listener {

    List<Inventory> invs = new ArrayList<Inventory>();
    public static ItemStack[] contents;
    private int itemIndex = 0;

    private int amount;

    private String gui_name;
    private String roulette_name;

    private FileConfiguration config;

    @Override
    public void perform(Player p, String[] args) {

        config = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();

        gui_name = config.getString("roulette.gui.menu_name");
        roulette_name = config.getString("roulette.gui.roulette_name");
        amount = config.getInt("roulette.amount.money");

        Inventory menu = Bukkit.createInventory(p, 27, TextUtil.color(gui_name));

        ItemStack vypln = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial());
        ItemStack dia = new ItemStack(Material.DIAMOND);
        ItemStack paper = new ItemStack(Material.PAPER);

        ItemMeta vypln_meta = vypln.getItemMeta();
        vypln_meta.setDisplayName(" ");
        vypln.setItemMeta(vypln_meta);

        ItemMeta dia_meta = dia.getItemMeta();
        dia_meta.setDisplayName(TextUtil.color("Diamanty"));
        dia.setItemMeta(dia_meta);

        ItemMeta paper_meta = paper.getItemMeta();
        paper_meta.setDisplayName(TextUtil.color("%money% peněz").replace("%money%", "" + amount));
        paper.setItemMeta(paper_meta);

        ItemStack[] menu_contents = {
                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
                vypln, vypln, vypln, dia, vypln, paper, vypln, vypln, vypln,
                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln
        };

        menu.setContents(menu_contents);
        p.sendMessage(Messages.ROULETTE_OPENING.toString());
        p.openInventory(menu);

    }

    public void shuffle(Inventory inv) {
        if (contents == null) {
            ItemStack[] items = new ItemStack[9];

            ItemStack g_sh = new ItemStack(Material.GOLDEN_SHOVEL, 1);
            ItemMeta g_sh_meta = g_sh.getItemMeta();
            g_sh_meta.addEnchant(Enchantment.DIG_SPEED, 5, false);
            g_sh_meta.addEnchant(Enchantment.DURABILITY, 3, false);
            g_sh_meta.setDisplayName(TextUtil.color("&4Patakova lopatka"));
            g_sh.setItemMeta(g_sh_meta);

            ItemStack cobweb = new ItemStack(Material.COBWEB, 1);
            ItemMeta cobweb_meta = cobweb.getItemMeta();
            cobweb_meta.setDisplayName(TextUtil.color("&cNic"));
            cobweb.setItemMeta(cobweb_meta);

            items[0] = new ItemStack(Material.DIAMOND, 3);
            items[1] = new ItemStack(Material.IRON_INGOT, 16);
            items[2] = new ItemStack(Material.GOLD_INGOT, 32);
            items[3] = new ItemStack(Material.GOLD_BLOCK, 4);
            items[4] = new ItemStack(Material.DIAMOND_PICKAXE, 1);
            items[5] = new ItemStack(Material.IRON_BLOCK, 3);
            items[6] = new ItemStack(Material.DIAMOND_SWORD, 1);
            items[7] = g_sh;
            items[8] = cobweb;

            contents = items;
        }

        int startingIndex = ThreadLocalRandom.current().nextInt(contents.length);

        for (int index = 0; index < startingIndex; index++) {
            for (int itemstacks = 9; itemstacks < 18; itemstacks++) {
                inv.setItem(itemstacks, contents[(itemstacks + itemIndex) % contents.length]);
            }
            itemIndex++;
        }
        // Customize

        ItemStack vypln = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial());
        ItemMeta vypln_meta = vypln.getItemMeta();
        vypln_meta.setDisplayName(" ");
        vypln.setItemMeta(vypln_meta);

        ItemStack item = new ItemStack(Material.HOPPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "|");
        item.setItemMeta(meta);

        inv.setItem(0, vypln);
        inv.setItem(1, vypln);
        inv.setItem(2, vypln);
        inv.setItem(3, vypln);
        inv.setItem(4, item);
        inv.setItem(5, vypln);
        inv.setItem(6, vypln);
        inv.setItem(7, vypln);
        inv.setItem(8, vypln);
        inv.setItem(18, vypln);
        inv.setItem(19, vypln);
        inv.setItem(20, vypln);
        inv.setItem(21, vypln);
        inv.setItem(22, vypln);
        inv.setItem(23, vypln);
        inv.setItem(24, vypln);
        inv.setItem(25, vypln);
        inv.setItem(26, vypln);

    }


    public void spin(final Player player) {

        ItemManager itemManager = Survival.plugin.getItemManager();

        Inventory inv = Bukkit.createInventory(null, 27, TextUtil.color("&6&lHodně štěstí!"));
        shuffle(inv);
        invs.add(inv);
        player.openInventory(inv);

        Random r = new Random();
        double seconds = 7.0 + (12.0 - 7.0) * r.nextDouble();

        new BukkitRunnable() {
            double delay = 0;
            int ticks = 0;
            boolean done = false;

            public void run() {
                if(done)
                    return;
                ticks++;
                delay += 1 / (20 *seconds);
                if (ticks > delay * 10) {
                    ticks = 0;

                    for (int itemstacks = 9; itemstacks < 18; itemstacks++) {
                        inv.setItem(itemstacks, contents[(itemstacks + itemIndex) % contents.length]);
                        player.playSound(player.getLocation(), Sound.BLOCK_STONE_PRESSURE_PLATE_CLICK_ON, 0.1F, 1);
                    }

                    itemIndex++;

                    if (delay >= .5) {
                        done = true;
                        new BukkitRunnable() {
                            public void run() {
                                ItemStack item = inv.getItem(13);
                                if (item.getType() != Material.COBWEB){

                                    if (item.hasItemMeta()){
                                        player.sendMessage(Messages.ROULETTE_WINNER.toString()
                                                .replace("%item%", item.getAmount() + "x + " + item.getItemMeta().getDisplayName()));
                                    } else {
                                        player.sendMessage(Messages.ROULETTE_WINNER.toString()
                                                .replace("%item%", item.getAmount() + "x " + item.getType()));
                                    }
                                    player.closeInventory();
                                    cancel();
                                    if (itemManager.invFull(player)){
                                        Bukkit.getWorld(player.getWorld().getName()).dropItemNaturally(player.getLocation(), item);
                                        player.sendMessage(Messages.ROULETTE_FULL_INV.toString());
                                    } else {
                                        player.getInventory().addItem(item);
                                    }
                                    player.updateInventory();
                                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.3F, 1);


                                } else {
                                    player.closeInventory();
                                    cancel();
                                    player.updateInventory();
                                    player.playSound(player.getLocation(), Sound.ENTITY_CAT_AMBIENT, 0.3F, 1);
                                    player.sendMessage(Messages.ROULETTE_LOSE.toString());

                                }
                            }
                        }.runTaskLater(Survival.plugin, 50);
                    }
                }
            }
        }.runTaskTimer(Survival.plugin, 0, 1);
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {

        config = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();

        Player p = (Player) event.getWhoClicked();

        gui_name = config.getString("roulette.gui.menu_name");

        if (event.getView().getTitle().equalsIgnoreCase(TextUtil.color(gui_name))){

            if (event.getCurrentItem() == null){
                return;
            }

            if (event.getCurrentItem().getType().equals(Material.DIAMOND)){

                amount = config.getInt("roulette.amount.diamond");

                ItemStack fee = new ItemStack(Material.DIAMOND);
                fee.setAmount(amount);

                event.setCancelled(true);

                if (p.getInventory().getItemInMainHand().isSimilar(fee) || p.getInventory().contains(fee)) {
                    p.getInventory().removeItem(fee);
                    spin(p);
                } else {
                    p.closeInventory();
                    p.sendMessage(Messages.ROULETTE_NO_DIA.toString().replace("%amount%", "" + amount));
                }

            } else if (event.getCurrentItem().getType().equals(Material.PAPER)){

                Economy economy = Survival.getEconomy();

                amount = config.getInt("roulette.amount.money");

                event.setCancelled(true);

                if (economy.getBalance(p) > amount){
                    economy.withdrawPlayer(p, amount);
                    spin(p);
                } else {
                    p.closeInventory();
                    p.sendMessage(Messages.NO_MONEY.toString().replace("%money%", "" + amount));
                }
            } else if (event.getCurrentItem().getType().equals(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial())){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onRouletteClick(InventoryClickEvent event) {
        if (!invs.contains(event.getInventory()))
            return;

        event.setCancelled(true);
        return;
    }
}