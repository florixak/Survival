//package cz.florixak.survival.manager;
//
//import cz.florixak.survival.Survival;
//import cz.florixak.survival.config.ConfigType;
//import cz.florixak.survival.utility.TextUtil;
//import org.bukkit.Bukkit;
//import org.bukkit.configuration.file.FileConfiguration;
//import org.bukkit.entity.Player;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//public class NotePadManager {
//
//    static List<String> list = new ArrayList<String>();
//
//    private FileConfiguration config;
//
//    public NotePadManager(){}
//
//    public void addNote(UUID uuid, String message){
//        config = Survival.plugin.getConfigManager().getFile(ConfigType.NOTES).getConfig();
//        list.add(TextUtil.color(message));
//
//        config.set("notes." + uuid.toString() + ".", list);
//        Survival.plugin.getConfigManager().getFile(ConfigType.NOTES).save();
//    }
//
//    public void getNoteList(UUID uuid){
//        config = Survival.plugin.getConfigManager().getFile(ConfigType.NOTES).getConfig();
//
//        Player p = Bukkit.getPlayer(uuid);
//    }
//
//    public boolean existNote(UUID uuid, String note_name){
//        config = Survival.plugin.getConfigManager().getFile(ConfigType.NOTES).getConfig();
//        return config.get("homes." + uuid.toString() + "." + list.toString()) != null;
//    }
//
//    public void delNote(UUID uuid, String note_name, int numberOfNote){
//        config = Survival.plugin.getConfigManager().getFile(ConfigType.NOTES).getConfig();
//
//        Player p = Bukkit.getPlayer(uuid);
//
//        if (!existNote(uuid, note_name)) {
//            p.sendMessage("Tato poznámka neexistuje");
//        }
//
//        config.set("notes." + uuid.toString() + "." + list, null);
//        Survival.plugin.getConfigManager().getFile(ConfigType.NOTES).save();
//    }
//}