package cz.florixak.survival.sql;

import cz.florixak.survival.Survival;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter {

    private Survival plugin;

    public SQLGetter(Survival plugin){
        this.plugin = plugin;
    }

    public void createTable(){
        PreparedStatement ps;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS statistics "
                    + "(NAME VARCHAR(100),UUID VARCHAR(100),DEATHS INT(100),PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS jobs "
                    + "(NAME VARCHAR(100),UUID VARCHAR(100),KILLER_KILLED INT(100),MINER_MINED INT(100),BUILDER_PLACED INT(100),CRAFTER_CRAFTED INT(100),DIGGER_MINED INT(100),WOODCUTTER_CUT INT(100),FARMER_FARMED INT(100),PRIMARY KEY (NAME))");
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

            if (!existsStats(uuid)) {
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

        try {
            PreparedStatement ps3 = plugin.SQL.getConnection().prepareStatement("SELECT * FROM jobs WHERE UUID=?");
            ps3.setString(1, uuid.toString());
            ResultSet results2 = ps3.executeQuery();
            results2.next();

            if (!existsJobs(uuid)) {
                PreparedStatement ps4 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO jobs"
                        + " (NAME,UUID) VALUES (?,?)");
                ps4.setString(1, player.getName());
                ps4.setString(2, uuid.toString());
                ps4.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existsStats(UUID uuid) {
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
    public boolean existsJobs(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM jobs WHERE UUID=?");
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

    public void addDeath(UUID uuid, int pocet){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE statistics SET DEATHS=? WHERE UUID=?");
            ps.setInt(1, (getDeaths(uuid) + pocet));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addKillerKill(UUID uuid, int pocet) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET KILLER_KILLED=? WHERE UUID=?");
            ps.setInt(1, (getKillerKills(uuid) + pocet));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addMinerBlock(UUID uuid, int pocet) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET MINER_MINED=? WHERE UUID=?");
            ps.setInt(1, (getMinerBlocks(uuid) + pocet));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addBuilderBlock(UUID uuid, int pocet){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET BUILDER_PLACED=? WHERE UUID=?");
            ps.setInt(1, (getBuilderBlocks(uuid) + pocet));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addDiggerBlock(UUID uuid, int pocet){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET DIGGER_MINED=? WHERE UUID=?");
            ps.setInt(1, (getDiggerBlocks(uuid) + pocet));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addFarmerBlock(UUID uuid, int pocet){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET FARMER_FARMED=? WHERE UUID=?");
            ps.setInt(1, (getFarmerBlocks(uuid) + pocet));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addWoodcutterBlock(UUID uuid, int pocet){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET WOODCUTTER_CUT=? WHERE UUID=?");
            ps.setInt(1, (getWoodcutterBlocks(uuid) + pocet));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addCrafterItem(UUID uuid, int pocet){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET CRAFTER_CRAFTED=? WHERE UUID=?");
            ps.setInt(1, (getCrafterItems(uuid) + pocet));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int getMinerBlocks(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT MINER_MINED FROM jobs WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("MINER_MINED");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getDiggerBlocks(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT DIGGER_MINED FROM jobs WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("DIGGER_MINED");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getBuilderBlocks(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT BUILDER_PLACED FROM jobs WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("BUILDER_PLACED");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getKillerKills(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT KILLER_KILLED FROM jobs WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("KILLER_KILLED");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getWoodcutterBlocks(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT WOODCUTTER_CUT FROM jobs WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("WOODCUTTER_CUT");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getFarmerBlocks(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT FARMER_FARMED FROM jobs WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("FARMER_FARMED");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getCrafterItems(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT CRAFTER_CRAFTED FROM jobs WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("CRAFTER_CRAFTED");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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





    public void emptyStatsTable() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("TRUNCATE statistics");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void emptyJobsTable() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("TRUNCATE jobs");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeStats(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("DELETE FROM statistics WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetJobs(UUID uuid, String job) {
        if (job.equals("miner") || job.equals("*")) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET MINER_MINED=? WHERE UUID=?");
                ps.setInt(1, 0);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (job.equals("digger") || job.equals("*")) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET DIGGER_MINED=? WHERE UUID=?");
                ps.setInt(1, 0);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (job.equals("woodcutter") || job.equals("*")) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET WOODCUTTER_CUT=? WHERE UUID=?");
                ps.setInt(1, 0);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (job.equals("builder") || job.equals("*")) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET BUILDER_PLACED=? WHERE UUID=?");
                ps.setInt(1, 0);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (job.equals("killer") || job.equals("*")) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET KILLER_KILLED=? WHERE UUID=?");
                ps.setInt(1, 0);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (job.equals("crafter") || job.equals("*")) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET CRAFTER_CRAFTED=? WHERE UUID=?");
                ps.setInt(1, 0);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (job.equals("farmer") || job.equals("*")) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET FARMER_FARMED=? WHERE UUID=?");
                ps.setInt(1, 0);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}