package net.excentrix.core.events;

import net.excentrix.core.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class freezeEvent implements Listener {
    Core plugin;

    @EventHandler
    public void moveEvent(PlayerMoveEvent event) {
        if (Core.freezeList.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    public void openInvent(InventoryOpenEvent event) {
        if (Core.freezeList.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}
