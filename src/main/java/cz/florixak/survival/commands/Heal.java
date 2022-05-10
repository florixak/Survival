package cz.florixak.survival.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.HealManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@CommandInfo(name = "heal", permission = "", requiresPlayer = true)
public class Heal extends PluginCommand {

    private HashMap<UUID, Integer> heal_cd = new HashMap<>();

    private FileConfiguration config;

    private int delay;
    private int prize;

    @Override
    public void perform(Player p, String[] args){

        config = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();

        Economy economy = Survival.getEconomy();
        UUID uuid = p.getUniqueId();

        HealManager healManager = new HealManager();

        delay = config.getInt("heal.cooldown_delay");
        prize = config.getInt("heal.prize");

        if (args.length == 0){
            p.sendMessage(Messages.HEAL_USAGE.toString());

        } else if (args[0].equalsIgnoreCase("buy")){

            Player target = Bukkit.getPlayer(args[1]);

            healManager.openHealMenu(p);
        } else {
            p.sendMessage(Messages.HEAL_USAGE.toString());
        }
    }
}