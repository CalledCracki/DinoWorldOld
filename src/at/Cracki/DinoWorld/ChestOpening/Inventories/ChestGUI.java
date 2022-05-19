package at.Cracki.DinoWorld.ChestOpening.Inventories;

import at.Cracki.DinoWorld.Main.DW;
import at.Cracki.DinoWorld.Utils.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ChestGUI {

    public static Inventory co = Bukkit.createInventory(null, 3*9, DW.pre + "§8» §c§lSchatztruhen");

    public static void openChestOpeningGUI(Player player) {

        //Glasscheiben
        ItemStack bg = new Item(Material.BLACK_STAINED_GLASS_PANE, 1).setDisplayName(" ").build();
        ItemStack wg = new Item(Material.WHITE_STAINED_GLASS_PANE, 1).setDisplayName(" ").build();
        ItemStack lg = new Item(Material.PURPLE_STAINED_GLASS_PANE, 1).setDisplayName(" ").build();
        ItemStack og = new Item(Material.ORANGE_STAINED_GLASS_PANE, 1).setDisplayName(" ").
                addHiddenEnchantment(Enchantment.ARROW_DAMAGE, 1, false).build();
        //Schatztruhen
        ItemStack nt = new Item(Material.CHEST, 1).setDisplayName("§fNormale §6§lDino §e§lTruhe").
                setLore(Arrays.asList(" ", "§aLinksklick §7zum Öffnen.", "§cRechtsklick §7zum ansehen.")).build();
        ItemStack et = new Item(Material.ENDER_CHEST, 1).setDisplayName("§5Epische §6§lDino §e§lTruhe").
                setLore(Arrays.asList(" ", "§aLinksklick §7zum Öffnen.", "§cRechtsklick §7zum ansehen.")).build();
        ItemStack lt = new Item(Material.END_PORTAL_FRAME, 1).setDisplayName("§6§k§l+§r §6§lLEGENDÄRE §6§lDino §e§lTruhe §6§k§l+").
                setLore(Arrays.asList(" ", "§aLinksklick §7zum Öffnen.", "§cRechtsklick §7zum ansehen.")).build();

        //schwarze Glasscheiben
        co.setItem(0, bg);
        co.setItem(8, bg);
        co.setItem(9, bg);
        co.setItem(17, bg);
        co.setItem(18, bg);
        co.setItem(26, bg);
        //Seltenheiten
        co.setItem(2, wg);
        co.setItem(20, wg);

        co.setItem(4, lg);
        co.setItem(22, lg);

        co.setItem(6, og);
        co.setItem(24, og);
        //Schatztruhen
        co.setItem(11, nt);
        co.setItem(13, et);
        co.setItem(15, lt);

        player.openInventory(co);
    }
}
