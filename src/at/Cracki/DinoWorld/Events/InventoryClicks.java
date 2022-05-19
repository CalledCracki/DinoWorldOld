package at.Cracki.DinoWorld.Events;

import at.Cracki.DinoWorld.ChestOpening.Inventories.ChestGUI;
import at.Cracki.DinoWorld.ChestOpening.Inventories.COGUI;
import at.Cracki.DinoWorld.ChestOpening.Inventories.Preview;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClicks implements Listener {

    @EventHandler
    public void onChestOpeningClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        if (!event.getClickedInventory().equals(ChestGUI.co)) return;
        if (event.getCurrentItem() == null) return;

            switch(event.getCurrentItem().getType()) {
                case CHEST:
                    if(event.getClick().equals(ClickType.LEFT)) {
                        COGUI.spin((Player) event.getWhoClicked());
                        break;
                    } else if(event.getClick().equals(ClickType.RIGHT)) {
                        Preview.openItemPreview((Player) event.getWhoClicked());
                        break;
                    }
                case ENDER_CHEST:
                    break;
                case END_PORTAL_FRAME:
                    break;
            }

        event.setCancelled(true);
        }

        @EventHandler
        public void onSpinGUIClick(InventoryClickEvent event) {
            if (event.getClickedInventory() == null) return;
            if (!(event.getWhoClicked() instanceof Player)) return;
            if (!event.getClickedInventory().equals(COGUI.inv)) return;

            event.setCancelled(true);
        }

        @EventHandler
        public void onPreviewClick(InventoryClickEvent event) {
            if (event.getClickedInventory() == null) return;
            if (!(event.getWhoClicked() instanceof Player)) return;
            if (!event.getClickedInventory().equals(Preview.preview)) return;

            event.setCancelled(true);
        }

    }

