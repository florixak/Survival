package cz.florixak.survival.commands.home;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.inventory.InventoryListener;
import cz.florixak.survival.manager.HomeManager;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import cz.florixak.survival.utility.TimeConvertor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

@CommandInfo(name = "home", permission = "", requiresPlayer = true)
public class Home extends PluginCommand implements Listener {

    private int tp_delay;

    private FileConfiguration config;

    private HomeManager homeManager;

    @Override
    public void perform(Player p, String[] args){

        config = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
        tp_delay = config.getInt("home.tp_delay");
        homeManager = new HomeManager();

        if (args.length == 0) {
            p.sendMessage(Messages.HOME_USAGE_HOME.toString());
        }

        if (args.length == 1) {

            if (!homeManager.exist(p.getUniqueId(), args[0])) {
                p.sendMessage(Messages.HOME_NO_EXISTS.toString().replace("%home_name%", args[0]));
                return;
            }

            if (PlayerManager.isTeleporting(p)) {
                p.sendMessage(Messages.CANNOT_TELEPORT_NOW.toString());
                return;
            }
            PlayerManager.teleporting.add(p);

            new BukkitRunnable() {

                private final Location initial = p.getLocation();

                @Override
                public void run() {

                    if (p.getLocation().distance(initial) > 0.5) {
                        p.sendTitle(Messages.YOU_MOVED.toString(), "", 20, 40, 20);
                        PlayerManager.teleporting.remove(p);
                        cancel();
                        return;
                    }

                    if (tp_delay == 0){
                        p.teleport(homeManager.getLocation(p.getUniqueId(), args[0]));
                        p.sendMessage(Messages.HOME_TELEPORTED.toString().replace("%home_name%", args[0]));
                        PlayerManager.teleporting.remove(p);
                        cancel();
                        return;
                    }

//                    p.sendTitle(Messages.HOME_WAIT_MESSAGE.toString().replace("%home_name%", args[0])
//                            .replace("%teleport_delay%", "" + TimeConvertor.convertSeconds(tp_delay)),
//                            Messages.DONT_MOVE.toString(), 20, 40, 20);
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.TELEPORTING.toString()
                            .replace("%delay%", "" + TimeConvertor.convertSeconds(tp_delay))));
                    tp_delay--;
                }

            }.runTaskTimer(Survival.plugin, 0L, 20L);
        }
    }

    @EventHandler
    public void onHomeClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();

        config = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
        tp_delay = config.getInt("home.tp_delay");
        homeManager = new HomeManager();

        if (event.getView().getTitle().equalsIgnoreCase("Domovy")) {

            if (event.getCurrentItem() == null
                    || event.getCurrentItem().getType().equals(Material.AIR)
                    || event.getCurrentItem().getType().equals(Material.GRAY_STAINED_GLASS_PANE)) {
                event.setCancelled(true);
                return;
            }

            if (event.getCurrentItem().getType().equals(Material.OAK_SIGN)) {

                event.setCancelled(true);
                p.closeInventory();

                if (PlayerManager.isTeleporting(p)) {
                    p.sendMessage(Messages.CANNOT_TELEPORT_NOW.toString());
                    return;
                }
                PlayerManager.teleporting.add(p);

                new BukkitRunnable() {

                    private final Location initial = p.getLocation();

                    @Override
                    public void run() {

                        if (p.getLocation().distance(initial) > 0.5) {
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.YOU_MOVED.toString()));
                            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 0.1f, 0.1f);
                            PlayerManager.teleporting.remove(p);
                            cancel();
                            return;
                        }

                        if (tp_delay == 0){
                            p.teleport(homeManager.getLocation(p.getUniqueId(), event.getCurrentItem().getItemMeta().getDisplayName()));
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.HOME_TELEPORTED.toString()
                                    .replace("%home_name%", event.getCurrentItem().getItemMeta().getDisplayName())));
                            PlayerManager.teleporting.remove(p);
                            cancel();
                            return;
                        }

//                        p.sendTitle(Messages.HOME_WAIT_MESSAGE.toString().replace("%home_name%", event.getCurrentItem().getItemMeta().getDisplayName())
//                                        .replace("%teleport_delay%", "" + TimeConvertor.convertSeconds(tp_delay)),
//                                Messages.DONT_MOVE.toString(), 20, 40, 20);
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.TELEPORTING.toString()
                                .replace("%delay%", "" + TimeConvertor.convertSeconds(tp_delay))));
                        tp_delay--;
                    }

                }.runTaskTimer(Survival.plugin, 0L, 20L);
            }
        }
    }
}