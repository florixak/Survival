package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class MoneyManager {

    public MoneyManager() { }

    public void sendMessage(Player p){

        Economy economy = Survival.getEconomy();

        DecimalFormat format = new DecimalFormat("##,###,##0.00");
        String formatted = format.format(economy.getBalance(p));

        p.sendMessage(Messages.MONEY.toString().replace("%money%", "" + formatted));
    }
}
