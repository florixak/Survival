package cz.florixak.survival.command.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.utility.TimeConvertor;
import cz.florixak.survival.utility.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;

public class Coordinates extends Command {

    public Coordinates(Survival plugin) {
        super(plugin);
        this.addAlias("coordinates");
        this.addAlias("coords");
        this.addAlias("coord");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {

        Player p = (Player) sender;

        if (PlayerManager.isInPlayerTime(p)){
            PlayerManager.playerTime.remove(p);
            p.sendMessage(Messages.COORDINATES_OFF.toString());
            return true;
        }

        PlayerManager.playerTime.add(p);
        p.sendMessage(Messages.COORDINATES_ON.toString());

        new BukkitRunnable(){
            @Override
            public void run() {

                if (!PlayerManager.isInPlayerTime(p)){
                    cancel();
                    return;
                }

                DecimalFormat format = new DecimalFormat("##,###,##0");
                String X = format.format(p.getLocation().getX());
                String Y = format.format(p.getLocation().getY());
                String Z = format.format(p.getLocation().getZ());

                Utils.sendHotbarMessage(p, "&bXYZ&7: &f" + X + " " + Y + " " + Z + "   " + " &bČas&8: &f" + TimeConvertor.convertTime(p));
            }
        }.runTaskTimer(Survival.plugin, 0L, 0L);

        return true;
    }
}