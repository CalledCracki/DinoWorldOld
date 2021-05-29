package at.Cracki.DinoWorld.Commands;

import at.Cracki.DinoWorld.Main.DW;
import at.Cracki.DinoWorld.MySQL.MoneyAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("dw.admin")) {
                if (args.length == 0) {
                    player.sendMessage(DW.pre + "§aDein Kontostand: " + MoneyAPI.getMoney(player.getUniqueId().toString()) + " §6Dino§eCoins");
                } else if (args.length == 3) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    int Money = Integer.valueOf(args[2]).intValue();
                    if (args[0].equalsIgnoreCase("add")) {
                        if (Money <= 0) {
                            player.sendMessage(DW.pre + "§cBitte nehm eine Zahl!");
                            return true;
                        }
                        if (Money == 0) {
                            player.sendMessage(DW.pre + "§cDu kannst dem Spieler keine");
                            return true;
                        }
                        MoneyAPI.addMoney(target.getUniqueId().toString(), Money);
                        player.sendMessage(DW.pre + "§aDu hast dem Spieler §e" +
                                target.getDisplayName() + " §b$" + Integer.valueOf(args[2])
                                + "§6§lDino§e§lCoins §aüberwiesen.");
                        target.sendMessage(DW.pre + "§aDer Spieler §e" + target.getDisplayName() + " §ahat dir §b$"
                        + Integer.valueOf(args[2]) + " §6§lDino§e§lCoins §aüberwiesen.");
                    } else if (args[0].equalsIgnoreCase("set")) {
                        if (Money <= 0) {
                            player.sendMessage(DW.pre + "Bitte nehm eine Zahl!");
                            return true;
                        }
                        MoneyAPI.setMoney(target.getUniqueId().toString(), Money);
                        player.sendMessage(DW.pre + "§aDer Kontostand von §e" + target.getDisplayName() +
                                " §awurde auf §b$" + MoneyAPI.getMoney(target.getUniqueId().toString()) + " §e§lCoins §agesetzt.");
                        target.sendMessage(DW.pre + "§aDein Kontostand wurde auf §b$" + MoneyAPI.getMoney(target.getUniqueId().toString()) +
                                " §e§lCoins §agesetzt!");
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        if (Money > MoneyAPI.getMoney(target.getUniqueId().toString())) {
                            player.sendMessage(DW.pre + "Du kannst den gehen!");
                            return true;
                        }
                        if (Money < 0) {
                            player.sendMessage(DW.pre +  "Du kannst den gehen!");
                            return true;
                        }
                        if (Money == 0) {
                            player.sendMessage(DW.pre + "Du kannst dem Spieler keine ");
                            return true;
                        }
                        MoneyAPI.takeMoney(target.getUniqueId().toString(), Money);
                        player.sendMessage(DW.pre + "Du hast dem Spieler + Money + ");
                        target.sendMessage(DW.pre + "Dir wurden + Money + ");
                    }
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    player.sendMessage(DW.pre + "§aDer Spieler §e" + target.getDisplayName() + " §ahat §b$" + MoneyAPI.getMoney(player.getUniqueId().toString()) +
                            " §6§lDino§e§lCoins");
                } else {
                    player.sendMessage(DW.pre + "§cVerwendung: /coins <add/set/remove> <Player> <Betrag>");
                }
            } else {
                player.sendMessage(DW.pre + "§aDein Kontostand: " + MoneyAPI.getMoney(player.getUniqueId().toString()) + " §6Dino§eCoins");
            }
        }

        return false;
    }
}
