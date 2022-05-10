package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.JobsManager;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.utility.TextUtil;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class DeathListener implements Listener {

    Survival plugin;
    FileConfiguration jobs;

    public DeathListener(Survival plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){

        event.setDeathMessage(null);

        if (event.getEntity().getKiller() instanceof Player) {

            Player killer = event.getEntity().getKiller();
            Player p = event.getEntity();

            User dead_player = LuckPermsProvider.get().getUserManager().getUser(p.getName());
            User killer_player = LuckPermsProvider.get().getUserManager().getUser(killer.getName());

            String prefix1 = dead_player.getCachedData().getMetaData().getPrefix();
            String prefix2 = killer_player.getCachedData().getMetaData().getPrefix();

            Bukkit.getServer().broadcastMessage(Messages.DEATH_MSG_PLAYER_KILLED.toString()
                    .replace("%player%", TextUtil.color(prefix1) + "" + p.getName())
                    .replace("%killer%", TextUtil.color(prefix2) + "" + killer.getName()));
            p.sendMessage(Messages.DEATH_MSG_YOU_WERE_KILLED.toString()
                    .replace("%killer%", TextUtil.color(prefix2) + "" + killer.getName()));
            killer.sendMessage(Messages.DEATH_MSG_YOU_HAVE_KILLED.toString()
                    .replace("%player%", TextUtil.color(prefix1) + "" + p.getName()));
            plugin.data.addDeath(p.getUniqueId(), 1);

        } else {

            Player p = event.getEntity().getPlayer();

            User dead_player = LuckPermsProvider.get().getUserManager().getUser(p.getName());
            String prefix1 = dead_player.getCachedData().getMetaData().getPrefix();

            Bukkit.getServer().broadcastMessage(Messages.DEATH_MSG.toString()
                    .replace("%player%", TextUtil.color(prefix1) + "" + p.getName()));
            p.sendMessage(Messages.DEATH_YOU_DIED.toString());
            plugin.data.addDeath(p.getUniqueId(), 1);
        }
    }
}