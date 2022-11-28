package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.utility.TextUtil;
import cz.florixak.survival.utility.Utils;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinManager {

    private Survival plugin;
    private Economy economy;
    private SpawnManager spawnManager;
    private KitsManager kitsManager;

    private FileConfiguration config;
    private FileConfiguration scoreboard;

    private int scoreboard_updater;
    private int first_money;
    private int davky;
    private int davky_time;

    public JoinManager(Survival plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
        this.scoreboard = plugin.getConfigManager().getFile(ConfigType.SCOREBOARD).getConfig();
        this.economy = Survival.getEconomy();
        this.spawnManager = plugin.getSpawnManager();
        this.kitsManager = plugin.getKitsManager();

        this.scoreboard_updater = scoreboard.getInt("scoreboard.update");
        this.first_money = config.getInt("join.first_join_money");

        this.davky = config.getInt("jobs.davky.amount");
        this.davky_time = config.getInt("jobs.davky.time");
    }

    public void playerJoin(Player p) {

        User user = LuckPermsProvider.get().getUserManager().getUser(p.getName());
        String prefix = user.getCachedData().getMetaData().getPrefix();

        if (!p.hasPlayedBefore()) {
            p.teleport(spawnManager.getLocation());

            p.sendTitle(Messages.JOIN_TITLE.toString(), "", 10, 20, 10);

            kitsManager.starterToInv(p);
            economy.depositPlayer(p, first_money);
        }

        PlayerManager.players.add(p);

        plugin.getJobsManager().ifNoJob(p);

        Utils.broadcastMessage(Messages.PLAYER_JOIN.toString()
                .replace("%player%", TextUtil.color(prefix) + "" + p.getName()));
    }

    public void setScoreboard(Player p){
        plugin.getScoreboardManager().createScoreboard(p);
        new BukkitRunnable(){
            @Override
            public void run() {
                if (PlayerManager.isPlayers(p)){
                    plugin.getScoreboardManager().createScoreboard(p);
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, scoreboard_updater*20);
    }

    public void setDavky(Player p){
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
//                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
//                                    TextComponent.fromLegacyText(Messages.JOBS_NO_JOB_MONEY.toString().replace("%money%", "" + davky)));
                        }
                    }
                }.runTaskTimer(plugin, 0L, davky_time*20);
            }
        }.runTaskLater(plugin, davky_time*20);
    }
}