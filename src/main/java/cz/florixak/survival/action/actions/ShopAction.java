package cz.florixak.survival.action.actions;

import cz.florixak.survival.Survival;
import cz.florixak.survival.action.Action;
import cz.florixak.survival.action.ActionManager;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.inventory.InventoryListener;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ShopAction implements Action {
    @Override
    public String getIdentifier() {
        return "AMOUNT";
    }

    @Override
    public void execute(Survival plugin, Player player, String data) {

        Economy economy = Survival.getEconomy();

        int value = ActionManager.cena * InventoryListener.itemStack.getAmount();

        EconomyResponse r = economy.withdrawPlayer(player, value);

        if (r.transactionSuccess()) {
            plugin.getItemManager().dropItemIfInvFull(new ItemStack(ActionManager.item.getType(), InventoryListener.itemStack.getAmount()), player);
            player.sendMessage(Messages.SHOP_BOUGHT.toString().replace("%item%", "" + ActionManager.item.getType()).replace("%prize%", "" + value + " peněz"));
        }
        else {
            player.sendMessage(Messages.NO_MONEY.toString());
        }

//        if(player.getLevel() >= value){
//            player.setLevel(player.getLevel()- value);
//            player.getInventory().addItem(new ItemStack(InventoryListener.itemStack.getType(), InventoryListener.itemStack.getAmount()));
//        }else {
//            player.sendMessage("Messages.NO_LEVEL.toString()");
//        }
    }
}