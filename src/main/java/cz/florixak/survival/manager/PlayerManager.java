package cz.florixak.survival.manager;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerManager {

    public static ArrayList<Player> players = new ArrayList<Player>();
    public static ArrayList<UUID> freeze = new ArrayList<UUID>();
    public static ArrayList<Player> playerTime = new ArrayList<Player>();
    public static ArrayList<UUID> rtp = new ArrayList<UUID>();
    public static ArrayList<UUID> tpBlock = new ArrayList<UUID>();
    public static ArrayList<Player> noteWriter = new ArrayList<Player>();
    public static ArrayList<Player> teleporting = new ArrayList<Player>();

    public static boolean isPlayers(Player p) {
        return players.contains(p);
    }
    public static boolean isFreeze(UUID uuid) {
        return freeze.contains(uuid);
    }
    public static boolean isRtp(UUID uuid){ return rtp.contains(uuid); }
    public static boolean isInPlayerTime(Player p){ return playerTime.contains(p); }
    public static boolean hasTpBlock(UUID uuid){ return tpBlock.contains(uuid); }
    public static boolean isNoteWriting(Player p){ return noteWriter.contains(p); }
    public static boolean isTeleporting(Player p){ return teleporting.contains(p); }


}
