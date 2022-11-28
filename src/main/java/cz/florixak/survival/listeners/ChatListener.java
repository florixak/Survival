package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.utility.TextUtil;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

public class ChatListener implements Listener {

    private Survival plugin;
    private FileConfiguration config;

    private HashMap<UUID, Integer> chat_cd = new HashMap<>();
    private String format;

    public ChatListener(Survival plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){

        Player p = event.getPlayer();

        LocalTime localTime = LocalTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = localTime.format(myFormatObj);

        User user = LuckPermsProvider.get().getUserManager().getUser(p.getName());
        String prefix = user.getCachedData().getMetaData().getPrefix();

        format = config.getString("chat.format")
                .replace("%player%", prefix + "" + p.getName())
                .replace("%message%", event.getMessage()).replace("%localTime%", "" + formattedTime);
        event.setFormat(TextUtil.color(format));

        if (chat_cd.containsKey(p.getUniqueId())){
            event.setCancelled(true);
            p.sendMessage(Messages.CHAT_COOLDOWN.toString());
            return;
        }

        User user_uuid = LuckPermsProvider.get().getUserManager().getUser(p.getUniqueId());
        Group hrac = LuckPermsProvider.get().getGroupManager().getGroup("default");

        if (!user_uuid.getPrimaryGroup().equals(hrac.getName())) {
            return;
        }

        if (event.getMessage().contains("zmrd")){
            event.setCancelled(true);
            p.sendMessage("nenadávej!");
        }

        chat_cd.put(p.getUniqueId(), 3);

        new BukkitRunnable(){
            @Override
            public void run() {
                if (chat_cd.get(p.getUniqueId()) == 0){
                    chat_cd.remove(p.getUniqueId());
                    cancel();
                    return;
                }
                chat_cd.put(p.getUniqueId(), chat_cd.get(p.getUniqueId())-1);
            }
        }.runTaskTimerAsynchronously(plugin, 0L, 20L);
    }
}