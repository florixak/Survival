package cz.florixak.survival.action.actions;


import cz.florixak.survival.Survival;
import cz.florixak.survival.action.Action;
import cz.florixak.survival.utility.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BroadcastMessageAction implements Action {

    @Override
    public String getIdentifier() {
        return "BROADCAST";
    }

    @Override
    public void execute(Survival plugin, Player player, String data) {

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(TextUtil.color(data));
        }
    }
}
