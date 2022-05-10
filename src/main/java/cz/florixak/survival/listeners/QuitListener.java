package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.utility.TextUtil;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    Survival plugin;

    public QuitListener(Survival plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player p = event.getPlayer();

        event.setQuitMessage(null);

        playerQuit(p);
    }

    public void playerQuit(Player p){

        User user = LuckPermsProvider.get().getUserManager().getUser(p.getName());
        String prefix = user.getCachedData().getMetaData().getPrefix();

        PlayerManager.players.remove(p);
        if (PlayerManager.isInPlayerTime(p)){
            PlayerManager.playerTime.remove(p);
        }
        if (PlayerManager.isNoteWriting(p)){
            PlayerManager.noteWriter.remove(p);
        }
        plugin.getScoreboardManager().removeScoreboard(p);
        Bukkit.getServer().broadcastMessage(Messages.PLAYER_QUIT.toString()
                .replace("%player%", TextUtil.color(prefix) + "" + p.getName()));
    }
}