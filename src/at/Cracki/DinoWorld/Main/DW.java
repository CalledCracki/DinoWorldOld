package at.Cracki.DinoWorld.Main;

import at.Cracki.DinoWorld.Commands.*;
import at.Cracki.DinoWorld.Events.EventClass;
import at.Cracki.DinoWorld.MySQL.DataManager;
import at.Cracki.DinoWorld.MySQL.MoneyAPI;
import at.Cracki.DinoWorld.MySQL.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class DW extends JavaPlugin {

    public static final String pre = "§6§lDino§e§lWorld §7» ";
    public static final String noperms = pre + "§cDazu hast du keine Rechte!";
    private String ver = "v1.0";

    //Liste mit Spielern, die im Vanish sind.
    public static ArrayList<Player> vanish = new ArrayList<>();
    //Liste mit Spielern, die im Fly Modus sind. #abgehoben.
    public static ArrayList<Player> fly = new ArrayList<>();

    public DataManager data;
    public static DW plugin;

    @Override
    public void onEnable() {
        plugin = this;
        init(Bukkit.getPluginManager());
        this.data = new DataManager(this);
        data.reloadConfig();

        MySQL.setStandards(data);
        MySQL.readData(data);

        System.out.println(pre + ChatColor.GREEN + "DinoWorld System " + ver + " wurde geladen!");
    }

    //initialisiert den PluginManager und die Commands.
    private void init(PluginManager pluginManager) {
        pluginManager.registerEvents(new EventClass(), this);

        getCommand("dinocoins").setExecutor(new DCCommand());
        getCommand("gamemode").setExecutor(new GMCommand());
        getCommand("enderchest").setExecutor(new ECCommand());
        getCommand("invsee").setExecutor(new InvseeCMD());
    }

    @Override
    public void onDisable() {
        System.out.println(pre + ChatColor.RED + "DinoWorld System" + ver + "  wurde gestoppt!");
    }

    public static DW getPlugin() {
        return plugin;
    }

}
