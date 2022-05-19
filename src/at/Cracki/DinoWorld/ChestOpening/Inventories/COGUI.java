package at.Cracki.DinoWorld.ChestOpening.Inventories;

import at.Cracki.DinoWorld.Main.DW;
import at.Cracki.DinoWorld.Utils.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class COGUI {

    public static Inventory inv = Bukkit.createInventory(null, 27, "§a§lViel Glück!");
    public static List<Inventory> invs = new ArrayList<>();
    public static ItemStack[] contents;
    private static int itemIndex = 0;

    public static void shuffle(Inventory inv) {
        if(contents == null) {
            ItemStack[] items = new ItemStack[6];

            items[0] = new ItemStack(Material.BEACON, 1);
            items[1] = new ItemStack(Material.EMERALD_BLOCK, 2);
            items[2] = new ItemStack(Material.DIAMOND_BLOCK, 2);
            items[3] = new ItemStack(Material.GOLD_BLOCK, 2);
            items[4] = new ItemStack(Material.PORKCHOP, 16);
            items[5] = new ItemStack(Material.SPAWNER, 1);

            contents = items;
        }

        int startingIndex = ThreadLocalRandom.current().nextInt(contents.length);

        for(int index = 0; index < startingIndex; index++) {
            for(int itemstacks = 9; itemstacks < 18; itemstacks++) {
                inv.setItem(itemstacks, contents[(itemstacks + itemIndex) % contents.length]);
            }
            itemIndex++;
        }

        ItemStack item = new Item(Material.HOPPER, 1).setDisplayName("§cDein Gewinn:").build();
        inv.setItem(4, item);
    }

    public static void spin(final Player player) {
        shuffle(inv);
        invs.add(inv);
        player.openInventory(inv);
        Random r = new Random();
        double seconds = 7.0 + (12.0 - 7.0) * r.nextDouble();

        new BukkitRunnable() {
            double delay = 0;
            int ticks = 0;
            boolean done = false;

            public void run() {
                if(done)
                    return;
                ticks++;
                delay += 1 / (20 * seconds);
                if(ticks > delay * 10) {
                    ticks = 0;

                    for(int  itemstacks = 9; itemstacks < 18; itemstacks++)
                        inv.setItem(itemstacks, contents[(itemstacks + itemIndex) % contents.length]);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3F, 3F);

                    itemIndex++;

                    if(delay >= .5) {
                        done = true;
                        new BukkitRunnable() {
                            public void run() {
                                ItemStack item = inv.getItem(13);
                                player.getInventory().addItem(item);
                                player.updateInventory();
                                Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
                                FireworkMeta fwm = fw.getFireworkMeta();
                                fw.detonate();
                                fw.setFireworkMeta(fwm);
                                player.closeInventory();
                                cancel();
                            }
                        }.runTaskLater(DW.getPlugin(DW.class), 50);
                        cancel();
                    }
                }
            }

        }.runTaskTimer(DW.getPlugin(), 0, 1);
    }

}
