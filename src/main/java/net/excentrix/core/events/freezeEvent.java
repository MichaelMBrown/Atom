package net.excentrix.core.events;

import net.excentrix.core.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class freezeEvent implements Listener {
    Core plugin;

    @EventHandler
    public void moveEvent(PlayerMoveEvent event) {
        if (Core.freezeList.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent breakEvent) {
        Player breakEventPlayer = breakEvent.getPlayer();
        if (Core.freezeList.contains(breakEventPlayer)) {
            breakEvent.setCancelled(true);
        }
    }

    @EventHandler
    public void placeBlock(BlockPlaceEvent placeEvent) {
        Player player = placeEvent.getPlayer();
        if (Core.freezeList.contains(player)) {
            placeEvent.setCancelled(true);
        }
    }
}
