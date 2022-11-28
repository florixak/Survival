package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.utility.Utils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

public class MoneyManager {

    private Economy economy;

    public MoneyManager() {
        this.economy = Survival.getEconomy();
    }

    public void sendMessage(Player p){
        p.sendMessage(Messages.MONEY.toString().replace("%money%", "" + Utils.formatMoney(economy.getBalance(p))));
    }
}
