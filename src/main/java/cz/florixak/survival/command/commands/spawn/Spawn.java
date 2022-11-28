package cz.florixak.survival.command.commands.spawn;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.manager.SpawnManager;
import cz.florixak.survival.utility.TimeConvertor;
import cz.florixak.survival.utility.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Spawn extends Command {

    private int tp_delay;

    private FileConfiguration config;
    private SpawnManager manager;

    public Spawn(Survival plugin) {
        super(plugin);
        this.manager = plugin.getSpawnManager();
        this.config = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
        this.tp_delay = config.getInt("spawn.tp_delay");

        this.addAlias("spawn");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {

        Player p = (Player) sender;

        if (!manager.exist()) {
            p.sendMessage(Messages.SPAWN_NOT_EXIST.toString());
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
                    p.teleport(manager.getLocation());
                    Utils.sendHotbarMessage(p, Messages.SPAWN_TELEPORTED.toString());
                    PlayerManager.teleporting.remove(p);
                    cancel();
                    return;
                }

//                p.sendTitle(Messages.SPAWN_WAIT_TO_TP.toString()
//                        .replace("%teleport_delay%", "" + TimeConvertor.convertSeconds(tp_delay)), Messages.DONT_MOVE.toString(), 20, 40, 20);
                Utils.sendHotbarMessage(p, Messages.TELEPORTING.toString()
                        .replace("%delay%", "" + TimeConvertor.convertSeconds(tp_delay)));
                tp_delay--;
            }
        }.runTaskTimer(Survival.plugin, 0L, 20L);
        return false;
    }
}