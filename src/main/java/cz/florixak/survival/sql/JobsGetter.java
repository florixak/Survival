package cz.florixak.survival.sql;

import cz.florixak.survival.Survival;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class JobsGetter {

    private Survival plugin;

    public JobsGetter(Survival plugin){
        this.plugin = plugin;
    }

    public void createTable(){
        PreparedStatement ps;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS jobs "
                    + "(NAME VARCHAR(100),UUID VARCHAR(100),KILLER INT(100),MINER INT(100),BUILDER INT(100),CRAFTER INT(100),DIGGER INT(100),WOODCUTTER INT(100),FARMER INT(100),PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player) {
        createPlayerJobs(player);
    }

    public void createPlayerJobs(Player player) {
        UUID uuid = player.getUniqueId();

        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM jobs WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            results.next();

            if (!exists(uuid)) {
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
    public boolean exists(UUID uuid) {
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

    public void addKillerKill(UUID uuid, int num) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET KILLER=? WHERE UUID=?");
            ps.setInt(1, (getKillerKills(uuid) + num));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addMinerBlock(UUID uuid, int num) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET MINER=? WHERE UUID=?");
            ps.setInt(1, (getMinerBlocks(uuid) + num));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addBuilderBlock(UUID uuid, int num){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET BUILDER=? WHERE UUID=?");
            ps.setInt(1, (getBuilderBlocks(uuid) + num));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addDiggerBlock(UUID uuid, int num){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET DIGGER=? WHERE UUID=?");
            ps.setInt(1, (getDiggerBlocks(uuid) + num));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addFarmerBlock(UUID uuid, int num){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET FARMER=? WHERE UUID=?");
            ps.setInt(1, (getFarmerBlocks(uuid) + num));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addWoodcutterBlock(UUID uuid, int num){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET WOODCUTTER=? WHERE UUID=?");
            ps.setInt(1, (getWoodcutterBlocks(uuid) + num));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addCrafterItem(UUID uuid, int num){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET CRAFTER=? WHERE UUID=?");
            ps.setInt(1, (getCrafterItems(uuid) + num));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMinerBlocks(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT MINER FROM jobs WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("MINER");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getDiggerBlocks(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT DIGGER FROM jobs WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("DIGGER");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getBuilderBlocks(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT BUILDER FROM jobs WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("BUILDER");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getKillerKills(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT KILLER FROM jobs WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("KILLER");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getWoodcutterBlocks(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT WOODCUTTER FROM jobs WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("WOODCUTTER");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getFarmerBlocks(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT FARMER FROM jobs WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("FARMER");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getCrafterItems(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT CRAFTER FROM jobs WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("CRAFTER");
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void emptyTable() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("TRUNCATE jobs");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetJobs(UUID uuid, String job) {
        if (job.equals("miner") || job.equals("*")) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET MINER=? WHERE UUID=?");
                ps.setInt(1, 0);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (job.equals("digger") || job.equals("*")) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET DIGGER=? WHERE UUID=?");
                ps.setInt(1, 0);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (job.equals("woodcutter") || job.equals("*")) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET WOODCUTTER=? WHERE UUID=?");
                ps.setInt(1, 0);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (job.equals("builder") || job.equals("*")) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET BUILDER=? WHERE UUID=?");
                ps.setInt(1, 0);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (job.equals("killer") || job.equals("*")) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET KILLER=? WHERE UUID=?");
                ps.setInt(1, 0);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (job.equals("crafter") || job.equals("*")) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET CRAFTER=? WHERE UUID=?");
                ps.setInt(1, 0);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (job.equals("farmer") || job.equals("*")) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE jobs SET FARMER=? WHERE UUID=?");
                ps.setInt(1, 0);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
