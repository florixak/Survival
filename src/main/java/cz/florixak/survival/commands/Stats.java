package cz.florixak.survival.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import cz.florixak.survival.utility.TextUtil;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

@CommandInfo(name = "stats", permission = "", requiresPlayer = true)
public class Stats extends PluginCommand {

    @Override
    public void perform(Player p, String[] args){

        Economy economy = Survival.getEconomy();

        if (args.length == 0) {

            User user = LuckPermsProvider.get().getUserManager().getUser(p.getName());
            String prefix = user.getCachedData().getMetaData().getPrefix();

            p.sendMessage(Messages.STATS_TITLE.toString());
            p.sendMessage(Messages.STATS_NAME.toString().replace("%player%", TextUtil.color(prefix) + "" + p.getName()));
            p.sendMessage(Messages.STATS_MONEY.toString().replace("%money%", "" + economy.getBalance(p) + " peněz"));
            p.sendMessage(Messages.STATS_PLAYER_KILLED.toString().replace("%player_killed%", "" + p.getStatistic(Statistic.PLAYER_KILLS)));
            p.sendMessage(Messages.STATS_DEATHS.toString().replace("%deaths%", "" + p.getStatistic(Statistic.DEATHS)));
            p.sendMessage(Messages.STATS_MOB_KILLED.toString().replace("%mob_killed%", "" + p.getStatistic(Statistic.MOB_KILLS)));
            p.sendMessage(Messages.STATS_TITLE.toString());

        } else if (args.length == 1) {

            Player target = Bukkit.getPlayer(args[0]);

            if (!PlayerManager.isPlayers(target)) {
                p.sendMessage(Messages.OFFLINE_PLAYER.toString());
                return;
            }

            User user = LuckPermsProvider.get().getUserManager().getUser(target.getName());
            String prefix = user.getCachedData().getMetaData().getPrefix();

            p.sendMessage(Messages.STATS_TITLE.toString());
            p.sendMessage(Messages.STATS_NAME.toString().replace("%player%", TextUtil.color(prefix) + "" + target.getName()));
            p.sendMessage(Messages.STATS_MONEY.toString().replace("%money%", "" + economy.getBalance(target) + " peněz"));
            p.sendMessage(Messages.STATS_PLAYER_KILLED.toString().replace("%player_killed%", "" + target.getStatistic(Statistic.PLAYER_KILLS)));
            p.sendMessage(Messages.STATS_DEATHS.toString().replace("%deaths%", "" + target.getStatistic(Statistic.DEATHS)));
            p.sendMessage(Messages.STATS_MOB_KILLED.toString().replace("%mob_killed%", "" + target.getStatistic(Statistic.MOB_KILLS)));
            p.sendMessage(Messages.STATS_TITLE.toString());

        } else {
            p.sendMessage(Messages.STATS_USAGE.toString());
        }
    }
}