package at.Cracki.DinoWorld.ChestOpening.Listeners;

import at.Cracki.DinoWorld.ChestOpening.Inventories.ChestGUI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChestCheck implements Listener {

    @EventHandler
    public void onChestClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        try {
            if(!(event.getClickedBlock().getLocation().subtract(0, 1, 0).getBlock().getType() == Material.GOLD_BLOCK)) return;
            if(event.getClickedBlock().getType().equals(Material.CHEST)
                    && (event.getAction().equals(Action.RIGHT_CLICK_AIR)
                    || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {

                event.setCancelled(true);
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 10F, 10F);
                ChestGUI.openChestOpeningGUI(player);

            }
        } catch (NullPointerException nullPointerException) {
    }
}
}
