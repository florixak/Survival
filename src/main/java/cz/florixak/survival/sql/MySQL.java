package cz.florixak.survival.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private String host = "167.86.104.34";
    private String port = "3306";
    private String database = "s26_Survival";
    private String username = "u26_Mh1oR75EQX";
    private String password = "^Mxz1szMDf+wKgGBNrVYtW+Q";

//    private String host = config.getString("data.host");
//    private String port = config.getString("data.port");
//    private String database = config.getString("data.database");
//    private String username = config.getString("data.username");
//    private String password = config.getString("data.password");

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
}