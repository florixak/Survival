package cz.florixak.survival.command.commands.vip;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.HealManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Heal extends Command {

    private HashMap<UUID, Integer> heal_cd = new HashMap<>();

    private FileConfiguration config;
    private HealManager healManager;

    private int delay;
    private int prize;

    public Heal(Survival plugin) {
        super(plugin);
        this.config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
        this.healManager = plugin.getHealManager();

        this.delay = config.getInt("heal.cooldown_delay");
        this.prize = config.getInt("heal.prize");

        this.addAlias("heal");
        this.addAlias("doctor");
        this.addRequirement("survival.heal");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;

        if (args.length == 0){
            p.sendMessage(Messages.HEAL_USAGE.toString());

        } else if (args[0].equalsIgnoreCase("buy")){

            Player target = Bukkit.getPlayer(args[1]);

            healManager.openHealMenu(p);
        } else {
            p.sendMessage(Messages.HEAL_USAGE.toString());
        }
        return true;
    }
}