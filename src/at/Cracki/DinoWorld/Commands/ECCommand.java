package at.Cracki.DinoWorld.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ECCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.openInventory(player.getEnderChest());
            } else if (args.length == 1 &&
                    player.hasPermission("dw.enderchest.see")) {
                Player target = Bukkit.getPlayerExact(args[0]);
                player.openInventory(target.getEnderChest());
            }
        }
        return false;
    }
}
