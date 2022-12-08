package cz.florixak.survival.sql;

import cz.florixak.survival.Survival;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class QuestGetter {

    private Survival plugin;

    public QuestGetter(Survival plugin){
        this.plugin = plugin;
    }

    public void createTable(){
        PreparedStatement ps;
        try {
            ps = plugin.getDatabase().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS quests "
                    + "(NAME VARCHAR(100),UUID VARCHAR(100),DAILY_1 VARCHAR(100),DAILY_2 VARCHAR(100),DAILY_3 VARCHAR(100),DAILY_4 VARCHAR(100),PRIMARY KEY (NAME))");
//            ps.setString(2, "Not Started");
//            ps.setString(3, "Not Started");
//            ps.setString(4, "Not Started");
//            ps.setString(5, "Not Started");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player) {
        UUID uuid = player.getUniqueId();
        try {
            PreparedStatement ps = plugin.getDatabase().getConnection().prepareStatement("SELECT * FROM quests WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            results.next();

            if (!exists(uuid)) {
                PreparedStatement ps2 = plugin.getDatabase().getConnection().prepareStatement("INSERT IGNORE INTO quests"
                        + " (NAME,UUID) VALUES (?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.executeUpdate();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(UUID uuid) {
        try {
            PreparedStatement ps = plugin.getDatabase().getConnection().prepareStatement("SELECT * FROM quests WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isNotStarted(UUID uuid, String quest) {
        try {
            PreparedStatement ps = plugin.getDatabase().getConnection().prepareStatement("SELECT "+quest.toUpperCase()+" FROM quests WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            String status = null;
            if (rs.next()) {
                status = rs.getString(quest.toUpperCase());
                if (status.equalsIgnoreCase("Not Started") || status == null) return true;
                else return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setNotStarted(UUID uuid, String quest){
        try {
            PreparedStatement ps = plugin.getDatabase().getConnection().prepareStatement("UPDATE quests SET "+quest.toUpperCase()+"=? WHERE UUID=?");
            ps.setString(1, "Not Started");
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isCompleted(UUID uuid, String quest) {
        try {
            PreparedStatement ps = plugin.getDatabase().getConnection().prepareStatement("SELECT "+quest.toUpperCase()+" FROM quests WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            String status = null;
            if (rs.next()) {
                status = rs.getString(quest.toUpperCase());
                if (status.equalsIgnoreCase("Completed")) return true;
                else return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setCompleted(UUID uuid, String quest){
        try {
            PreparedStatement ps = plugin.getDatabase().getConnection().prepareStatement("UPDATE quests SET "+quest.toUpperCase()+"=? WHERE UUID=?");
            ps.setString(1, "Completed");
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isStarted(UUID uuid, String quest) {
        try {
            PreparedStatement ps = plugin.getDatabase().getConnection().prepareStatement("SELECT "+quest.toUpperCase()+" FROM quests WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            String status = null;
            if (rs.next()) {
                status = rs.getString(quest.toUpperCase());
                if (status.equalsIgnoreCase("Started")) return true;
                else return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setStarted(UUID uuid, String quest){
        try {
            PreparedStatement ps = plugin.getDatabase().getConnection().prepareStatement("UPDATE quests SET "+quest.toUpperCase()+"=? WHERE UUID=?");
            ps.setString(1, "Started");
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void emptyTable() {
        try {
            PreparedStatement ps = plugin.getDatabase().getConnection().prepareStatement("TRUNCATE quests");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(UUID uuid) {
        try {
            PreparedStatement ps = plugin.getDatabase().getConnection().prepareStatement("DELETE FROM quests WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
