package net.excentrix.core.events;

import net.excentrix.core.CentralHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class freezeEvent implements Listener {
	CentralHandler plugin;
	
	public freezeEvent() {
	}
	
	@EventHandler
	public void moveEvent(PlayerMoveEvent event) {
		if (CentralHandler.freezeList.contains(event.getPlayer())) {
			event.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void breakBlock(BlockBreakEvent breakEvent) {
		Player breakEventPlayer = breakEvent.getPlayer();
		if (CentralHandler.freezeList.contains(breakEventPlayer)) {
			breakEvent.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void placeBlock(BlockPlaceEvent placeEvent) {
		Player player = placeEvent.getPlayer();
		if (CentralHandler.freezeList.contains(player)) {
			placeEvent.setCancelled(true);
		}
		
	}
}
