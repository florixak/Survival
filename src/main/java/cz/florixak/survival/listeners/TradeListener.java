package cz.florixak.survival.listeners;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class TradeListener implements Listener {

    public HashMap<Player, Player> tradingPlayers = new HashMap<Player, Player>();

    public void addPlayersToTradelist(Player p1, Player p2){
        tradingPlayers.put(p1, p2);
    }

    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent event){

        if (event.getView().getTitle().equals("TRADE INVENTORY")){
            Player p = (Player) event.getWhoClicked();

            if (!tradingPlayers.containsKey(p)){ // player 2
                accept(p, event.getCurrentItem());
                event.setCancelled(true);
                return;
            }
            event.setCancelled(true);

        }
    }

    public void accept(Player p, ItemStack item){
        if (item.getType().equals(Material.REDSTONE_BLOCK)) {
            finishTrade(p.getOpenInventory().getTopInventory());
        }
    }

    public void finishTrade(Inventory inv){
        List<HumanEntity> viewers = inv.getViewers();
        Player p1;
        Player p2;
        if (tradingPlayers.containsKey((Player) viewers.get(0))){
            p1 = (Player) viewers.get(0);
            p2 = (Player) viewers.get(1);
        } else {
            p1 = (Player) viewers.get(1);
            p2 = (Player) viewers.get(0);
        }

        if (inv.getViewers().size() == 0 || inv.getViewers().size() == 1){
            p1.closeInventory();
            p2.closeInventory();
        }

        p1.closeInventory();
        p2.closeInventory();

        if (!inv.isEmpty()){
            p2.getInventory().addItem(inv.getContents());
        }
        tradingPlayers.remove(p1);
    }
}