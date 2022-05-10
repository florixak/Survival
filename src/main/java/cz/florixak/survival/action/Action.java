package cz.florixak.survival.action;

import cz.florixak.survival.Survival;
import org.bukkit.entity.Player;

public interface Action {

    String getIdentifier();

    void execute(Survival plugin, Player player, String data);

}
