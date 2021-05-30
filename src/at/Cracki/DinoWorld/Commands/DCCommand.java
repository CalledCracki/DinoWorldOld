package at.Cracki.DinoWorld.Commands;

import at.Cracki.DinoWorld.Main.DW;
import at.Cracki.DinoWorld.MySQL.MoneyAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DCCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        Player player = (Player) sender;
            if(args.length == 0) {
                player.sendMessage(DW.pre + "§aDein Kontostand: §b$" + MoneyAPI.getMoney(player.getUniqueId().toString()) + " §6§lDino§e§lCoins");
            } else if((args.length == 3) && player.hasPermission("dw.admin")) {
                int amount = Integer.valueOf(args[2]);
                if (args[0].equalsIgnoreCase("add")) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (target.getDisplayName().equals(player.getDisplayName())) {
                        player.sendMessage(DW.pre + "§cDu kannst dir selbst keine Coins geben :P");
                        return true;
                    } else if (target != null) {
                            if(amount == 0) {
                                player.sendMessage(DW.pre + "§cBitte gebe eine höhere Zahl an!");
                                return true;
                            } else if(amount == 1) {
                                MoneyAPI.addMoney(target.getUniqueId().toString(), amount);
                                player.sendMessage(DW.pre + "§aDu hast dem Spieler §e" +
                                        target.getDisplayName() + " §b$" + Integer.valueOf(args[2])
                                        + " §6§lDino§e§lCoin §aüberwiesen.");
                                target.sendMessage(DW.pre + "§aDer Spieler §e" + player.getDisplayName() + " §ahat dir §b$"
                                        + amount + " §6§lDino§e§lCoin §aüberwiesen.");
                                target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3F, 3F);
                            } else if(amount > 1){
                                MoneyAPI.addMoney(target.getUniqueId().toString(), amount);
                                player.sendMessage(DW.pre + "§aDu hast dem Spieler §e" +
                                        target.getDisplayName() + " §b$" + Integer.valueOf(args[2])
                                        + " §6§lDino§e§lCoins §aüberwiesen.");
                                target.sendMessage(DW.pre + "§aDer Spieler §e" + player.getDisplayName() + " §ahat dir §b$"
                                        + Integer.valueOf(args[2]) + " §6§lDino§e§lCoins §aüberwiesen.");
                                target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3F, 3F);
                            }
                        } else
                            player.sendMessage(DW.pre + "§cDieser Spieler ist nicht online!");
                        return true;
                    }else if (args[0].equalsIgnoreCase("remove")) {
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if (target != null) {
                            if(MoneyAPI.getMoney(target.getUniqueId().toString()) <= 0) {
                                player.sendMessage(DW.pre + "§cDu kannst diesem Spieler kein Geld abziehen!");
                                return true;
                            } else if((amount == 0)) {
                                player.sendMessage(DW.pre + "§cBitte gebe eine höhere Zahl an!");
                                return true;
                            } else if(amount == 1) {
                                MoneyAPI.takeMoney(target.getUniqueId().toString(), amount);
                                target.sendMessage(DW.pre + "§aDir wurde §b$" + amount + " §6§lDino§e§lCoin §aabgezogen!");
                                player.sendMessage(DW.pre + "§aDem Spieler §e" + target.getDisplayName()
                                        + " §awurde §b$" + amount + " §6§lDino§e§lCoin §aabgezogen.");
                                target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3F, 3F);
                            } else {
                                MoneyAPI.takeMoney(target.getUniqueId().toString(), amount);
                                target.sendMessage(DW.pre + "§aDir wurden §b$" + amount + " §6§lDino§e§lCoins §aabgezogen!");
                                player.sendMessage(DW.pre + "§aDem Spieler §e" + target.getDisplayName()
                                        + " §awurden §b$" + amount + " §6§lDino§e§lCoins §aabgezogen.");
                                target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3F, 3F);
                            }
                        } else
                            player.sendMessage(DW.pre + "§cDieser Spieler ist nicht online!");
                        return true;
                    } else if (args[0].equalsIgnoreCase("set")) {
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if (target != null) {
                            if(amount == 1) {
                                MoneyAPI.setMoney(target.getUniqueId().toString(), amount);
                                player.sendMessage(DW.pre + "§aDer Kontostand von §e" + target.getDisplayName()
                                        + " §awurde auf §b$" + amount + " §6§lDino§e§lCoin §agesetzt.");
                                target.sendMessage(DW.pre + "§aDein Kontostand wurde auf §b$" + amount
                                        + " §6§lDino§e§lCoin §agesetzt");
                                target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3F, 3F);
                            } else {
                                MoneyAPI.setMoney(target.getUniqueId().toString(), amount);
                                player.sendMessage(DW.pre + "§aDer Kontostand von §e" + target.getDisplayName() + " §awurde auf §b$" + amount + " §6§lDino§e§lCoins §agesetzt.");
                                target.sendMessage(DW.pre + "§aDein Kontostand wurde auf §b$" + amount + " §6§lDino§e§lCoins §agesetzt");
                                target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3F, 3F);
                            }
                        } else
                            player.sendMessage(DW.pre + "§cDieser Spieler ist nicht online!");
                        return true;
                    } else
                        player.sendMessage(DW.pre + "§cVerwendung: /coins <add/remove/set> <Spieler> <Coins>");
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    int coins = MoneyAPI.getMoney(player.getUniqueId().toString());
                    if (target != null) {
                        if (coins == 1) {
                            player.sendMessage(DW.pre + "§aDer Spieler §e" + target.getDisplayName()
                                    + " §ahat §b$" + MoneyAPI.getMoney(player.getUniqueId().toString()) +
                                    " §6§lDino§e§lCoin");
                        } else if (coins > 1) {
                            player.sendMessage(DW.pre + "§aDer Spieler §e" + target.getDisplayName()
                                    + " §ahat §b$" + MoneyAPI.getMoney(player.getUniqueId().toString()) +
                                    " §6§lDino§e§lCoins");
                        }
                    } else {
                        player.sendMessage(DW.pre + "§cDieser Spieler ist nicht online!");
                    }
                } else {
                    player.sendMessage(DW.noperms);
                }
            return true;
            }
    }