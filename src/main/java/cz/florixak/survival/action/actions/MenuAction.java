package cz.florixak.survival.action.actions;


import cz.florixak.survival.Survival;
import cz.florixak.survival.action.Action;
import cz.florixak.survival.inventory.AbstractInventory;
import org.bukkit.entity.Player;

public class MenuAction implements Action {

    @Override
    public String getIdentifier() {
        return "MENU";
    }

    @Override
    public void execute(Survival plugin, Player player, String data) {
        AbstractInventory inventory = plugin.getInventoryManager().getInventory(data);

        if (inventory != null) {
            inventory.openInventory(player);
        } else {
            plugin.getLogger().warning("[MENU] Action Failed: Menu '" + data + "' not found.");
        }
    }
}
