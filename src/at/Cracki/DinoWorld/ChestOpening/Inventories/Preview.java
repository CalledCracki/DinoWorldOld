package at.Cracki.DinoWorld.ChestOpening.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Preview {

    public static Inventory preview = Bukkit.createInventory(null, 9*6, "§c§lItem Vorschau");

    public static void openItemPreview(Player player) {
        preview.addItem(COGUI.contents);
        player.openInventory(preview);
    }
}
