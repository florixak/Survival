package cz.florixak.survival.command.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.manager.RouletteManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Roulette extends Command implements Listener {

    private RouletteManager rouletteManager;

    public Roulette(Survival plugin) {
        super(plugin);
        this.rouletteManager = plugin.getRouletteManager();

        this.addAlias("roulette");
        this.addAlias("ruleta");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;

        rouletteManager.openInv(p);
        return true;
    }

}