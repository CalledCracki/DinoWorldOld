package at.Cracki.DinoWorld.Commands;

import at.Cracki.DinoWorld.Main.DW;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player player = (Player) sender;
        if(player.hasPermission("lobby.gamemode")) {
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3F, 3F);
                    player.sendMessage(DW.pre + "§7Du bist nun im §aSurvival Modus!");
                }else if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                    player.setGameMode(GameMode.CREATIVE);
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3F, 3F);
                    player.sendMessage(DW.pre + "§7Du bist nun im §aCreative Modus!");
                }else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3F, 3F);
                    player.sendMessage(DW.pre + "§7Du bist nun im §aAdventure Modus!");
                }else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3F, 3F);
                    player.sendMessage(DW.pre + "§7Du bist nun im §aSpectator Modus!");
                }
            }else
                player.sendMessage(DW.pre + "§cVerwendung: /gm <0/1/2/3>");
        }else
            player.sendMessage(DW.noperms);



        return false;
    }
}
