package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.JobsManager;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.manager.PvPArenaManager;
import cz.florixak.survival.manager.SpawnManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class DamageListener implements Listener {

    private HashMap<UUID, Integer> pvp = new HashMap<>();
    private HashMap<UUID, Integer> jobMoney = new HashMap<>();

    private int protection;

    private int arenaSize;

    private FileConfiguration config;
    private FileConfiguration pvpArena;

    @EventHandler
    public void onHitOnSpawn(EntityDamageByEntityEvent event){

        config = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
        pvpArena = Survival.plugin.getConfigManager().getFile(ConfigType.PVPARENA).getConfig();

        arenaSize = pvpArena.getInt("arena.size");

        protection = config.getInt("spawn.protection");

        SpawnManager spawnManager = new SpawnManager();
        PvPArenaManager arenaManager = new PvPArenaManager();

        if (event.getDamager().getWorld().getName().equalsIgnoreCase("world")) {
            if (event.getEntity() instanceof Player) {
                if (event.getDamager() instanceof Player
                                || event.getDamager() instanceof Arrow
                                || event.getDamager() instanceof FishHook
                                || event.getDamager() instanceof Snowball
                                || event.getDamager() instanceof Egg) {

                    if (arenaManager.exist() && event.getDamager().getLocation().distance(arenaManager.getLocation()) < arenaSize){
                        return;
                    }

                    if (spawnManager.exist() && event.getDamager().getLocation().distance(spawnManager.getLocation()) < protection) {

                        event.setCancelled(true);

                        if (!pvp.containsKey(event.getDamager().getUniqueId())) {
                            event.getDamager().sendMessage(Messages.CANT_FIGHT.toString());
                            pvp.put(event.getDamager().getUniqueId(), 5);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (pvp.get(event.getDamager().getUniqueId()) == 0) {
                                        pvp.remove(event.getDamager().getUniqueId());
                                        cancel();
                                        return;
                                    }
                                    pvp.put(event.getDamager().getUniqueId(), pvp.get(event.getDamager().getUniqueId())-1);
                                }
                            }.runTaskTimerAsynchronously(Survival.plugin, 0L, 20L);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {

        config = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();

        protection = config.getInt("spawn.protection");

        SpawnManager spawnManager = new SpawnManager();

        if (event.getEntity() instanceof Player) {

            Player p = (Player) event.getEntity();

            if (p.getLocation().getWorld().equals("world")) {

                if (spawnManager.exist()
                        && p.getLocation().distance(spawnManager.getLocation()) < protection) {

                    if (event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK
                            || event.getCause() == EntityDamageEvent.DamageCause.FIRE
                            || event.getCause() == EntityDamageEvent.DamageCause.LAVA) {

                        event.setCancelled(true);
                    }
                }
            }
        }
    }

//    @EventHandler
//    public void onWarpPvpEnterAndLeave(PlayerMoveEvent event){
//
//        pvpArena = Survival.plugin.getConfigManager().getFile(ConfigType.PVPARENA).getConfig();
//
//        arenaSize = pvpArena.getInt("arena.size");
//
//        PvPArenaManager arenaManager = new PvPArenaManager();
//
//        Player p = event.getPlayer();
//
//        if (arenaManager.exist() && p.getLocation().distance(arenaManager.getLocation()) == arenaSize){
//            p.sendMessage(Messages.PVPARENA_ENTER.toString());
//        }
//        else if (arenaManager.exist() && p.getLocation().distance(arenaManager.getLocation()) >= arenaSize) {
//
//        }
//    }
}