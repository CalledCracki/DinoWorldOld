package at.Cracki.DinoWorld.Utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {

    private ItemStack current;

    public Item(ItemStack i) {
        this.current = i;
    }

    public Item(Material m, int amount, short ID) {
        this(new ItemStack(m, amount, ID));
    }

    public Item(Material m, int amount) {
        this(new ItemStack(m, amount));
    }

    public Item setDisplayName(String displayname) {
        ItemMeta m = current.getItemMeta();
        m.setDisplayName(displayname);
        current.setItemMeta(m);
        return this;
    }

    public Item setLore(List<String> lore) {
        ItemMeta m = current.getItemMeta();
        m.setLore(lore);
        current.setItemMeta(m);
        return this;
    }

    public Item setAmount(int amount) {
        current.setAmount(amount);
        return this;
    }

    public Item addEnchantment(Enchantment ench, int level, boolean bool) {
        ItemMeta m = current.getItemMeta();
        m.addEnchant(ench, level, bool);
        current.setItemMeta(m);
        return this;
    }

    public Item addHiddenEnchantment(Enchantment ench, int level, boolean bool) {
        ItemMeta m = current.getItemMeta();
        m.addEnchant(ench, level, bool);
        m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        m.addItemFlags(ItemFlag.HIDE_DESTROYS);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        current.setItemMeta(m);
        return this;
    }

    public ItemStack build() {
        return this.current;
    }

}