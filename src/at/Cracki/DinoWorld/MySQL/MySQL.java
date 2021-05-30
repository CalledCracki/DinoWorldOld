package at.Cracki.DinoWorld.MySQL;

import at.Cracki.DinoWorld.Main.DW;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

    public static String host;//	Host-Adresse deines SQL-Servers (Bei lokalen: 'localhost').
    public static String port;//	Der Port deines SQL-Servers (Standard: '3306').
    public static String db;//		Der Name der von dir erstellten Datenbank.
    public static String username;//Dein Username (Bei lokalen: 'root').
    public static String pass;//	Dein Passwort (Bei lokalen standard. leer).
    public static Connection con;

    public static void connect() {//	Baut Verbindung zum MySQL-Server auf
        if(!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, username, pass);
                Bukkit.getConsoleSender().sendMessage(DW.pre + "§aMit MySQL verbunden!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void disconnect() {//	Schließt die MySQL-Verbindung
        if(isConnected()) {
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage(DW.pre + "§cMySQL Verbindung wurde getrennt!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected() {// Abfrage ob bereits mit MySQL verbunden.
        return(con == null ? false : true);
    }

    public static void update(String query) {// MySQL Update Befehl.
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getResult(String query) {
        try {
            PreparedStatement ps = con.prepareStatement(query);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createTable() {// Methode um Tabelle zu erstellen.
        if(isConnected()) {
            try {
                con.prepareStatement("CREATE TABLE IF NOT EXISTS moneyTable (UUID VARCHAR(100), money BIGINT(16))").executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
