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
        if(player.hasPermission("dw.coins")) {
            if(args.length == 0) {
                player.sendMessage(DW.pre + "§aDeine Coins: §e" + MoneyAPI.getMoney(player.getUniqueId().toString()));
            }else if(args.length == 3) {
                Integer amount = Integer.valueOf(args[2]);
                if(args[0].equalsIgnoreCase("add")) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if(target != null) {
                        MoneyAPI.addMoney(target.getUniqueId().toString(), amount);
                        player.sendMessage(DW.pre + "§aDem Spieler §b" + target.getName() + " §awurden §e" + amount + " Coins §aüberwiesen.");
                        target.sendMessage(DW.pre + "§aDeinem Konto wurden §e" + amount + " Coins §ahinzugefügt.");
                        target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3F, 3F);
                    }else
                        player.sendMessage(DW.pre + "§cDieser Spieler ist nicht online!");
                    return true;
                }else if(args[0].equalsIgnoreCase("remove")) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if(target != null) {
                        MoneyAPI.takeMoney(target.getUniqueId().toString(), amount);
                        target.sendMessage(DW.pre + "§aDir wurden §e" + amount + " Coins §aabgezogen!");
                        player.sendMessage(DW.pre + "§aDem Spieler §b" + target.getName() + " §awurden §e" + amount + " Coins §aabgezogen.");
                        target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3F, 3F);
                    }else
                        player.sendMessage(DW.pre + "§cDieser Spieler ist nicht online!");
                    return true;
                }else if(args[0].equalsIgnoreCase("set")) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if(target != null) {
                        MoneyAPI.setMoney(target.getUniqueId().toString(), amount);
                        player.sendMessage(DW.pre + "§aDer Kontostand von §b" + target.getName() + " §awurde auf §e" + amount + " Coins §agesetzt.");
                        target.sendMessage(DW.pre + "§aDein Kontostand wurde auf §e" + amount + " Coins §agesetzt");
                        target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3F, 3F);
                    }else
                        player.sendMessage(DW.pre + "§cDieser Spieler ist nicht online!");
                    return true;
                }else
                    player.sendMessage(DW.pre + "§cVerwendung: /coins <add/remove/set> <Spieler> <Coins>");
            }else
                player.sendMessage(DW.pre + "§cVerwendung: /coins <add/remove/set> <Spieler> <Coins>");
        }else
            player.sendMessage(DW.noperms);
        return false;
    }
}