package cz.florixak.survival.command.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.manager.MoneyManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Money extends Command {

    private MoneyManager moneyManager;

    public Money(Survival plugin) {
        super(plugin);
        this.plugin = plugin;
        this.moneyManager = plugin.getMoneyManager();
        this.addAlias("money");
        this.addAlias("balance");
        this.addAlias("coins");
        this.addAlias("cash");
        this.addAlias("bank");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;
        moneyManager.sendMessage(p);
        return true;
    }
}
