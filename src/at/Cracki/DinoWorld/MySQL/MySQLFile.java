package at.Cracki.DinoWorld.MySQL;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MySQLFile {

    public void setStandard() {// 	Setzt Standard Werte für MySQL-Server (siehe MySQL.yml)
        FileConfiguration cfg = getFileConfiguration();
        cfg.options().copyDefaults(true);

        cfg.addDefault("MySQL.host", "localhost");//	Host-Adresse deines SQL-Servers (Bei lokalen: 'localhost').
        cfg.addDefault("MySQL.database", "localdb");//		Der Name der von dir erstellten Datenbank.
        cfg.addDefault("MySQL.username", "root");//Dein Username (Bei lokalen: 'root').
        cfg.addDefault("MySQL.password", " ");//	Dein Passwort (Bei lokalen standard. leer).
        cfg.addDefault("MySQL.port", "3306");//	Der Port deines SQL-Servers (Standard: '3306').

        try {
            cfg.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData() {// 	Methode um Werte in der 'MySQL' Klasse zu definieren.
        FileConfiguration cfg = getFileConfiguration();
        MySQL.host = cfg.getString("MySQL.host");
        MySQL.db = cfg.getString("MySQL.database");
        MySQL.username = cfg.getString("MySQL.username");
        MySQL.pass = cfg.getString("MySQL.password");
        MySQL.port = cfg.getString("MySQL.port");
    }

    private File getFile() {//	Methode um MySQL File zu erstellen.
        return new File("plugins/DinoWorld", "MySQL.yml");
    }

    private FileConfiguration getFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getFile());
    }

}