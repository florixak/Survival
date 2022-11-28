//package cz.florixak.survival.commands;
//
//import cz.florixak.survival.manager.commandManager.CommandInfo;
//import cz.florixak.survival.manager.commandManager.PluginCommand;
//import org.bukkit.Location;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerTeleportEvent;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@CommandInfo(name = "back", permission = "", requiresPlayer = true)
//public class Back extends PluginCommand implements Listener {
//
//    public Map<UUID, Location> lastLoc = new HashMap<>();
//
//    @Override
//    public void perform(Player p, String[] args) {
//        if (!(lastLoc.containsKey(p.getUniqueId()))) {
//            p.sendMessage("Nemáš žádnou zpětnou lokaci!");
//            return;
//        }
//        p.sendMessage("" + lastLoc.get(p.getUniqueId()));
//        p.sendMessage("Byl jsi teleportován na svou minulou lokaci!");
//        Location loc = (Location) lastLoc.get(p.getUniqueId());
//        p.teleport(loc);
//        lastLoc.remove(p.getUniqueId());
//    }
//
//    @EventHandler
//    public void onPlayerTeleport(PlayerTeleportEvent event) {
//        Player p = event.getPlayer();
//        Location from = event.getFrom();
//        p.sendMessage("" + from);
//        lastLoc.put(p.getUniqueId(), from);
//    }
//}
