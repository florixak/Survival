package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.utility.TextUtil;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class JoinManager {

    private int scoreboard_updater;
    private int first_money;
    private int davky;
    private int davky_time;

    FileConfiguration config;
    FileConfiguration scoreboard;

    public JoinManager(){
        config = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
        scoreboard = Survival.plugin.getConfigManager().getFile(ConfigType.SCOREBOARD).getConfig();
    }

    public void playerJoin(Player p) {

        scoreboard_updater = scoreboard.getInt("scoreboard.update");
        first_money = config.getInt("join.first_join_money");

        davky = config.getInt("jobs.davky.amount");
        davky_time = config.getInt("jobs.davky.time");

        Server server = getServer();
        Economy economy = Survival.getEconomy();
        User user = LuckPermsProvider.get().getUserManager().getUser(p.getName());
        String prefix = user.getCachedData().getMetaData().getPrefix();

        SpawnManager spawnManager = new SpawnManager();
        KitsManager kitsManager = new KitsManager();

        if (!p.hasPlayedBefore()) {
            p.teleport(spawnManager.getLocation());

            p.sendTitle(Messages.JOIN_TITLE.toString(), "", 10, 20, 10);

            kitsManager.starterToInv(p);
            economy.depositPlayer(p, first_money);
        }

        PlayerManager.players.add(p);

        Survival.plugin.getJobsManager().ifNoJob(p);

        server.broadcastMessage(Messages.PLAYER_JOIN.toString()
                .replace("%player%", TextUtil.color(prefix) + "" + p.getName()));
    }

    public void setScoreboard(Player p){
        Survival.plugin.getScoreboardManager().createScoreboard(p);
        new BukkitRunnable(){
            @Override
            public void run() {
                if (PlayerManager.isPlayers(p)){
                    Survival.plugin.getScoreboardManager().createScoreboard(p);
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(Survival.plugin, 0, scoreboard_updater*20);
    }

    public void setDavky(Player p){
        Economy economy = Survival.getEconomy();
        new BukkitRunnable(){
            @Override
            public void run() {

                if (!PlayerManager.isPlayers(p)){
                    cancel();
                    return;
                }
                new BukkitRunnable(){
                    @Override
                    public void run() {

                        if (!PlayerManager.isPlayers(p)){
                            cancel();
                            return;
                        }
                        if (JobsManager.isNezamestnany(p.getUniqueId())){
                            economy.depositPlayer(p, davky);
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                    TextComponent.fromLegacyText(Messages.JOBS_NO_JOB_MONEY.toString().replace("%money%", "" + davky)));
                        }
                    }
                }.runTaskTimer(Survival.plugin, 0L, davky_time*20);
            }
        }.runTaskLater(Survival.plugin, davky_time*20);
    }
}