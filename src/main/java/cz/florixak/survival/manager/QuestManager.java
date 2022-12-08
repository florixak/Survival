package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.utility.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class QuestManager {

    private Survival plugin;
    private FileConfiguration config, quests;
    private EconomyManager economyManager;

    private String reward;

    private HashMap<UUID, String> playing_quest;

    public QuestManager(Survival plugin) {
        this.plugin = plugin;
        this.playing_quest = new HashMap<>();
        this.economyManager = plugin.getEconomyManager();
    }

    public void resetDailyQuests() {
        playing_quest.clear();
        for (Player player : PlayerManager.players) {
            endQuest(player, "DAILY_1", plugin.getQuestData().isCompleted(player.getUniqueId(), "DAILY_1"));
            endQuest(player, "DAILY_2", plugin.getQuestData().isCompleted(player.getUniqueId(), "DAILY_2"));
            endQuest(player, "DAILY_3", plugin.getQuestData().isCompleted(player.getUniqueId(), "DAILY_3"));
            endQuest(player, "DAILY_4", plugin.getQuestData().isCompleted(player.getUniqueId(), "DAILY_4"));
        }
        plugin.getQuestData().emptyTable();
//        plugin.getQuestData().emptyTable();
    }

    public void startQuest(Player p, String quest) {
        playing_quest.put(p.getUniqueId(), quest.toUpperCase());
        plugin.getQuestData().setStarted(p.getUniqueId(), quest.toUpperCase());
    }

    public void endQuest(Player p, String quest, boolean completed) {
        playing_quest.remove(p.getUniqueId());
        if (completed) {
            giveReward(p);
            plugin.getQuestData().setCompleted(p.getUniqueId(), quest.toUpperCase());
            return;
        }
        plugin.getQuestData().setNotStarted(p.getUniqueId(), quest.toUpperCase());
    }

    private void giveReward(Player p) {
        if (Utils.isNum(reward)) {
            economyManager.deposit(p, Double.valueOf(reward));
        }
        else {

        }

    }
}
