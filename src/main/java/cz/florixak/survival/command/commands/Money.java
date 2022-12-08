package cz.florixak.survival.command.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.EconomyManager;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Money extends Command {

    private EconomyManager moneyManager;

    public Money(Survival plugin) {
        super(plugin);
        this.plugin = plugin;
        this.moneyManager = plugin.getEconomyManager();
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
        if (args.length == 0) {
            moneyManager.sendMessage(p);
            Utils.restartServer();
            return true;
        }
        if (!p.hasPermission("survival.balance")) return true;
        if (args[0].equalsIgnoreCase("deposit")) {
            if (args.length == 3) {
                Player target = Bukkit.getPlayer(args[1]);
                long money = Long.valueOf(args[2]);
                if (!PlayerManager.isPlayers(target)) {
                    p.sendMessage(Messages.OFFLINE_PLAYER.toString());
                    return true;
                }
                if (args[1].equalsIgnoreCase(target.getName())) {
                    moneyManager.deposit(target, money);
                    p.sendMessage("You give " + money + " money to " + target.getName());
                }
            }
            else {
                p.sendMessage("False usage");
            }
        }
        else if (args[0].equalsIgnoreCase("withdraw")) {
            if (args.length == 3) {
                Player target = Bukkit.getPlayer(args[1]);
                long money = Long.valueOf(args[2]);
                if (!PlayerManager.isPlayers(target)) {
                    p.sendMessage(Messages.OFFLINE_PLAYER.toString());
                    return true;
                }
                if (args[1].equalsIgnoreCase(target.getName())) {
                    moneyManager.withdraw(target, money);
                    p.sendMessage("You take " + money + " money from " + target.getName());
                }
            }
            else {
                p.sendMessage("False usage");
            }
        }
        else if (args[0].equalsIgnoreCase("reset")) {
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[1]);
                if (!PlayerManager.isPlayers(target)) {
                    p.sendMessage(Messages.OFFLINE_PLAYER.toString());
                    return true;
                }
                if (args[1].equalsIgnoreCase(target.getName())) {
                    moneyManager.reset(target);
                    p.sendMessage("You reset player " + target.getName());
                }
            }
            else {
                p.sendMessage("False usage");
            }
        }
        else {
            p.sendMessage("False usage");
        }

        return true;
    }
}
