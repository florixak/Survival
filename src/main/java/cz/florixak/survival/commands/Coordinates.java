package cz.florixak.survival.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PlayerManager;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import cz.florixak.survival.utility.TextUtil;
import cz.florixak.survival.utility.TimeConvertor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.UUID;

@CommandInfo(name = "coordinates", permission = "", requiresPlayer = true)
public class Coordinates extends PluginCommand {

    @Override
    public void perform(Player p, String[] args){

        UUID uuid = p.getUniqueId();

        if (PlayerManager.isInPlayerTime(p)){
            PlayerManager.playerTime.remove(p);
            p.sendMessage(Messages.COORDINATES_OFF.toString());
            return;
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
//                String dire = p.getLocation().getDirection().toString();

//                p.getLocation().getPitch().

                p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                        TextComponent.fromLegacyText(
                                TextUtil.color("&bXYZ&7: &f" + X + " " + Y + " " + Z + "   " + " &bČas&8: &f" + TimeConvertor.convertTime(p))));
            }
        }.runTaskTimer(Survival.plugin, 0L, 0L);
    }
}