package at.Cracki.DinoWorld.MySQL;

import at.Cracki.DinoWorld.Main.DW;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

    public static String host; //	Host-Adresse deines SQL-Servers (Bei lokalen: 'localhost').
    public static String port; //	Der Port deines SQL-Servers (Standard: '3306').
    public static String db; //		Der Name der von dir erstellten Datenbank.
    public static String username; //Dein Username (Bei lokalen: 'root').
    public static String pass; //	Dein Passwort (Bei lokalen standard. leer).
    public static Connection con;

    public static void connect() throws ClassNotFoundException, SQLException  {
        if (!isConnected())
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, username, pass);
                System.out.println("[MySQL] Verbindung aufgebaut!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static void disconnect() {
        if (isConnected())
            try {
                con.close();
                System.out.println("[MySQL] Verbindung geschlossen!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static boolean isConnected() {
        return !(con == null);
    }

    public static void update(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getResult(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createTable() {
        try {
            con.prepareStatement("CREATE TABLE IF NOT EXISTS moneyTable (UUID VARCHAR(100), money INT(16))").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readData(DataManager data) {

        MySQL.host = (String) data.getConfig().get("MySQL.host");
        MySQL.port = (String) data.getConfig().get("MySQL.port");
        MySQL.db = (String) data.getConfig().get("MySQL.db");
        MySQL.pass = (String) data.getConfig().get("MySQL.pass");
        MySQL.username = (String) data.getConfig().get("MySQL.username");
    }

    public static void setStandards(DataManager data) {
        data.getConfig().addDefault("MySQL.host", "localhost");
        data.getConfig().addDefault("MySQL.port", "3306");
        data.getConfig().addDefault("MySQL.db", "localdb");
        data.getConfig().addDefault("MySQL.pass", "123456");
        data.getConfig().addDefault("MySQL.username", "root");

        data.saveConfig();
    }
}
