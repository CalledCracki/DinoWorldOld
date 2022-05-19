package at.Cracki.DinoWorld.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class EventClass implements Listener {

    @EventHandler
    public void onHungerEvent(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = (event.getPlayer());
        if(event.getItemDrop().getType().equals(Material.BEACON)) {

        }
    }
    public void onBlockPlace(BlockPlaceEvent event) {
        if(event.getPlayer().hasPermission("dw.team")) {
            event.setCancelled(false);
        } else
            event.setCancelled(true);
        return;
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageEvent(final EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) event.getEntity();
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }
    }
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.getPlayer().hasPermission("dw.team")) {
            event.setCancelled(false);
        } else
            event.setCancelled(true);
        return;
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if(event.getPlayer().hasPermission("dw.team")) {
            event.setCancelled(false);
        } else
            event.setCancelled(true);
        return;
    }

}