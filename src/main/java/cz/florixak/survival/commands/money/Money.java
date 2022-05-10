package cz.florixak.survival.commands.money;

import cz.florixak.survival.manager.MoneyManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "money", permission = "", requiresPlayer = true)
public class Money extends PluginCommand {

    @Override
    public void perform(Player p, String[] args){
        MoneyManager moneyManager = new MoneyManager();

        moneyManager.sendMessage(p);
    }
}
