package at.Cracki.DinoWorld.Commands;

import at.Cracki.DinoWorld.Main.DW;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvseeCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("cb.invsee")) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    player.openInventory((Inventory) target.getInventory());
                } else {
                    player.sendMessage(DW.pre + "§cVerwendung: /invsee <Player>");
                }
            } else {
                player.sendMessage(DW.noperms);
            }
        }
        return false;
    }
}
