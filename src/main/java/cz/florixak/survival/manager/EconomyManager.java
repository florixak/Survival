package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.utility.Utils;
import org.bukkit.entity.Player;

public class EconomyManager {

    private Survival plugin;

    public EconomyManager(Survival plugin) {
        this.plugin = plugin;
    }

    public void deposit(Player p, double money) {
        plugin.statsData.depositMoney(p.getUniqueId(), money);
    }

    public void withdraw(Player p, double money) {
        plugin.statsData.depositMoney(p.getUniqueId(), -money);
    }

    public double get(Player p) {
        return plugin.statsData.getMoney(p.getUniqueId());
    }

    public void reset(Player p) {
        plugin.statsData.resetMoney(p.getUniqueId());
    }

    public void sendMessage(Player p) {
        p.sendMessage(Messages.MONEY.toString().replace("%money%", "" + Utils.formatMoney(plugin.statsData.getMoney(p.getUniqueId()))));
    }
}
