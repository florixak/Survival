package cz.florixak.survival.command.commands.warp;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.manager.WarpManager;
import cz.florixak.survival.utility.TimeConvertor;
import cz.florixak.survival.utility.Utils;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Warp extends Command {

    private int tp_delay;
    private WarpManager manager;
    private FileConfiguration config;

    public Warp(Survival plugin) {
        super(plugin);
        this.config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
        this.tp_delay = config.getInt("warp.tp_delay");
        this.manager = plugin.getWarpManager();

        this.addAlias("warp");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(Messages.WARP_USAGE.toString());

        } else if (args.length == 1) {

            if (!manager.exist(args[0])) {
                p.sendMessage(Messages.WARP_NOT_EXISTS.toString().replace("%warp_name%", args[0]));
                return true;
            }

            if (PlayerManager.isTeleporting(p)) {
                p.sendMessage(Messages.CANNOT_TELEPORT_NOW.toString());
                return true;
            }
            PlayerManager.teleporting.add(p);

            new BukkitRunnable() {

                private final Location initial = p.getLocation();

                @Override
                public void run() {

                    if (p.getLocation().distance(initial) > 0.5) {
                        Utils.sendHotbarMessage(p, Messages.YOU_MOVED.toString());
                        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 0.1f, 0.1f);
                        PlayerManager.teleporting.remove(p);
                        cancel();
                        return;
                    }

                    if (tp_delay == 0){
                        p.teleport(manager.getLocation(args[0]));
                        Utils.sendHotbarMessage(p, Messages.WARP_TELEPORTED.toString()
                                .replace("%warp_name%", args[0]));
                        PlayerManager.teleporting.remove(p);
                        cancel();
                        return;
                    }

//                    p.sendTitle(Messages.WARP_WAIT_MESSAGE.toString().replace("%warp_name%", args[0])
//                                    .replace("%teleport_delay%", "" + TimeConvertor.convertSeconds(tp_delay)),
//                            Messages.DONT_MOVE.toString(), 20, 40, 20);

                    Utils.sendHotbarMessage(p, Messages.TELEPORTING.toString()
                            .replace("%delay%", "" + TimeConvertor.convertSeconds(tp_delay)));

                    tp_delay--;

                }
            }.runTaskTimer(plugin, 0L, 20L);

        } else {
            p.sendMessage(Messages.WARP_USAGE.toString());
        }
        return true;
    }

}