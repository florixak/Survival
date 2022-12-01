package cz.florixak.survival.sql;

import cz.florixak.survival.Survival;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class StatsGetter {

    private Survival plugin;

    public StatsGetter(Survival plugin){
        this.plugin = plugin;
    }

    public void createTable(){
        PreparedStatement ps;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS statistics "
                    + "(NAME VARCHAR(100),UUID VARCHAR(100),MONEY DECIMAL(10,2),PLAYER_KILLED INT(100),MOB_KILLED INT(100),DEATHS INT(100),PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player) {
        UUID uuid = player.getUniqueId();
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM statistics WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            results.next();

            if (!exists(uuid)) {
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO statistics"
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
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM statistics WHERE UUID=?");
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

    public void depositMoney(UUID uuid, double num){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE statistics SET MONEY=? WHERE UUID=?");
            ps.setDouble(1, (getMoney(uuid) + num));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public double getMoney(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT MONEY FROM statistics WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            double number = 0;
            if (rs.next()) {
                number = rs.getDouble("MONEY");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    public void resetMoney(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE statistics SET MONEY=? WHERE UUID=?");
            ps.setDouble(1, 0.0);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addKilledPlayer(UUID uuid, int num){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE statistics SET PLAYER_KILLED=? WHERE UUID=?");
            ps.setInt(1, (getKilledPlayers(uuid) + num));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getKilledPlayers(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT PLAYER_KILLED FROM statistics WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("PLAYER_KILLED");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addKilledMob(UUID uuid, int num){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE statistics SET MOB_KILLED=? WHERE UUID=?");
            ps.setInt(1, (getKilledMobs(uuid) + num));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getKilledMobs(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT MOB_KILLED FROM statistics WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("MOB_KILLED");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addDeath(UUID uuid, int num){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE statistics SET DEATHS=? WHERE UUID=?");
            ps.setInt(1, (getDeaths(uuid) + num));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getDeaths(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT DEATHS FROM statistics WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("DEATHS");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void emptyTable() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("TRUNCATE statistics");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("DELETE FROM statistics WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}