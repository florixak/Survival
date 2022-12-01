package cz.florixak.survival.command.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.EconomyManager;
import cz.florixak.survival.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pay extends Command {

    private EconomyManager economyManager;

    public Pay(Survival plugin) {
        super(plugin);
        this.economyManager = plugin.getEconomyManager();

        this.addAlias("pay");
        this.addAlias("zaplatit");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;

        if (args.length == 2) {
            Player target = Bukkit.getPlayer(args[0]);
            double money = Double.valueOf(args[1]);
            if (!PlayerManager.isPlayers(target)) {
                p.sendMessage(Messages.OFFLINE_PLAYER.toString());
                return true;
            }
            if (args[0].equalsIgnoreCase(target.getName())) {
                economyManager.withdraw(p, money);
                p.sendMessage("You sent " + money + " money to " + target.getName());
                economyManager.deposit(target, money);
                target.sendMessage("You obtained " + money + " money from " + p.getName());
            }
        }
        else {
            p.sendMessage("Invalid usage");
        }
        return true;
    }
}
