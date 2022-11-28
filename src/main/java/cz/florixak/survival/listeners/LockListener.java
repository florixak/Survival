package cz.florixak.survival.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LockListener implements Listener {

    @EventHandler
    public void onPlayerPlaceChest(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType().equals(Material.CHEST)) {
            event.getPlayer().openInventory(openLocker(event.getPlayer(), event.getBlockPlaced().getLocation()));
        }
    }

    @EventHandler
    public void onPlayerClickNumber(InventoryClickEvent event) {

        if (event.getView().getTitle().equals(ChatColor.GOLD + "LOCKER")) {
            event.setCancelled(true);
            if (event.getRawSlot() == event.getSlot() && event.getCurrentItem() != null && !event.getCurrentItem().getType().equals(Material.AIR)) {
                ItemStack clicked = event.getCurrentItem();

                if (clicked.getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                    if (clicked.getItemMeta().getDisplayName().equals(ChatColor.RED + "BACK")) {
                        event.setCancelled(true);
                        event.getWhoClicked().sendMessage("back");
                    }
                } else if (clicked.getType().equals(Material.GREEN_STAINED_GLASS_PANE)) {
                    if (clicked.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "ACCEPT")) {
                        event.setCancelled(true);
                        event.getWhoClicked().sendMessage("accept");
                    }
                } else {
                    if (event.getRawSlot() > 8) {
                        event.setCancelled(true);
                        event.getWhoClicked().sendMessage(clicked.getItemMeta().getDisplayName());
                    }
                }
            }
        }
    }

    public Inventory openLocker(Player p, Location loc) {
        Inventory locker = Bukkit.createInventory(null, 45, ChatColor.GOLD + "LOCKER");
        for (int i = 0; i<10; i++) {
            ItemStack number = new ItemStack(Material.STONE_BUTTON, 1);
            ItemMeta numberMeta = number.getItemMeta();
            numberMeta.setDisplayName(ChatColor.AQUA + "" + i);
            number.setItemMeta(numberMeta);

            int slot = 0;
            if (i==0) {
                slot = 40;
            } else if (i <= 3) {
                slot = 11 + 1;
            } else if (i <= 6) {
                slot = 20 + (i-3);
            } else if (i <= 9) {
                slot = 29 + (i-6);
            }
            locker.setItem(slot, number);
        }

        ItemStack confirm = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta confirmMeta = confirm.getItemMeta();
        confirmMeta.setDisplayName(ChatColor.GREEN + "ACCEPT");
        confirm.setItemMeta(confirmMeta);
        locker.setItem(44, confirm);

        ItemStack back = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(ChatColor.RED + "BACK");
        back.setItemMeta(backMeta);
        locker.setItem(36, back);

        return locker;
    }
}