package cz.florixak.survival.manager.scoreboardManager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ScoreboardManager {

    private Map<UUID, ScoreHelper> players;

    private FileConfiguration config;

    private String title;
    private List<String> sb;

    private Survival plugin;

    public ScoreboardManager(Survival plugin){
        this.plugin = plugin;

        players = new HashMap<>();

        this.config = plugin.getConfigManager().getFile(ConfigType.SCOREBOARD).getConfig();

        title = config.getString("scoreboard.title");
        sb = config.getStringList("scoreboard.lines");
    }

    public void createScoreboard(Player p){
        players.put(p.getUniqueId(), updateScoreboard(p.getUniqueId()));
    }

    public ScoreHelper updateScoreboard(UUID uuid){

        Player p = Bukkit.getPlayer(uuid);

        if (p == null) return null;

        int sb = this.sb.size();

        ScoreHelper helper = players.get(p.getUniqueId());
        if (helper == null) helper = new ScoreHelper(p);
        helper.setTitle(title);

        for (String text : this.sb){
            helper.setSlot(sb, text);
            sb--;
        }
        return helper;
    }

    public void removeScoreboard(Player p){

        if (players.containsKey(p.getUniqueId())){
            players.remove(p.getUniqueId());
            p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }
    }
}