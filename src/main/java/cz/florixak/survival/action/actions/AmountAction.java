package cz.florixak.survival.action.actions;

import cz.florixak.survival.Survival;
import cz.florixak.survival.action.Action;
import cz.florixak.survival.action.ActionManager;
import cz.florixak.survival.inventory.AbstractInventory;
import cz.florixak.survival.inventory.InventoryListener;
import org.bukkit.entity.Player;

public class AmountAction implements Action {

    @Override
    public String getIdentifier() {
        return "SHOP";
    }

    @Override
    public void execute(Survival plugin, Player player, String data) {
        AbstractInventory inventory = plugin.getInventoryManager().getInventory("amount");
        ActionManager.cena = Integer.parseInt(data);
        ActionManager.item = InventoryListener.itemStack;
        inventory.openInventory(player);
    }
}
