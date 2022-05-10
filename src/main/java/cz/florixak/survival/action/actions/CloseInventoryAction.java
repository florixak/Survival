package cz.florixak.survival.action.actions;


import cz.florixak.survival.Survival;
import cz.florixak.survival.action.Action;
import org.bukkit.entity.Player;

public class CloseInventoryAction implements Action {

    @Override
    public String getIdentifier() {
        return "CLOSE";
    }

    @Override
    public void execute(Survival plugin, Player player, String data) {
        player.closeInventory();
    }
}
