package cz.florixak.survival.sql;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private String host = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig().getString("database.host");
    private String port = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig().getString("database.port");
    private String database = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig().getString("database.database");
    private String username = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig().getString("database.username");
    private String password = Survival.plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig().getString("database.password");

    private Connection connection;

    public boolean isConnected() {
        return (connection == null ? false : true);
    }

    public void connect() throws ClassNotFoundException, SQLException {
        if (!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false", username, password);
        }
    }

    public void disconnect(){
        if (isConnected()){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void signPlayerInToDatabase(Player p) {
        Survival.plugin.statsData.createPlayer(p);
        Survival.plugin.jobsData.createPlayer(p);
    }
}