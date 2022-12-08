package cz.florixak.survival.action.actions;

import cz.florixak.survival.Survival;
import cz.florixak.survival.action.Action;
import cz.florixak.survival.inventory.AbstractInventory;
import org.bukkit.entity.Player;

public class QuestAction implements Action {

    @Override
    public String getIdentifier() {
        return "QUEST";
    }

    @Override
    public void execute(Survival plugin, Player player, String data) {
        AbstractInventory inventory = plugin.getInventoryManager().getInventory(data);

        if (inventory != null) {
            inventory.openInventory(player);
        } else {
            String quest;
            switch (data) {
                case "1":
                    quest = "DAILY_1";
                    if (plugin.getQuestData().isCompleted(player.getUniqueId(), quest)) {
                        plugin.getQuestManager().endQuest(player, "DAILY_1", true);
                        return;
                    }
                    if (plugin.getQuestData().isStarted(player.getUniqueId(), quest)) return;
                    plugin.getQuestData().setStarted(player.getUniqueId(), quest);
                    plugin.getQuestManager().startQuest(player, quest);
                    break;

                case "2":
                    quest = "DAILY_2";
                    if (plugin.getQuestData().isCompleted(player.getUniqueId(), quest)) {
                        plugin.getQuestManager().endQuest(player, "DAILY_2", true);
                        return;
                    }
                    if (plugin.getQuestData().isStarted(player.getUniqueId(), quest)) return;
                    plugin.getQuestManager().startQuest(player, quest);
                    break;

                case "3":
                    quest = "DAILY_3";
                    if (plugin.getQuestData().isCompleted(player.getUniqueId(), quest)) {
                        plugin.getQuestManager().endQuest(player, "DAILY_3", true);
                        return;
                    }
                    if (plugin.getQuestData().isStarted(player.getUniqueId(), quest)) return;
                    plugin.getQuestData().setStarted(player.getUniqueId(), quest);
                    plugin.getQuestManager().startQuest(player, quest);
                    break;

                case "4":
                    quest = "DAILY_4";
                    if (plugin.getQuestData().isCompleted(player.getUniqueId(), quest)) {
                        plugin.getQuestManager().endQuest(player, "DAILY_4", true);
                        return;
                    }
                    if (plugin.getQuestData().isStarted(player.getUniqueId(), quest)) return;
                    plugin.getQuestData().setStarted(player.getUniqueId(), quest);
                    break;
            }
        }
    }
}
