package net.excentrix.core.events;

import net.excentrix.core.Central;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class freezeEvent implements Listener {
	Central plugin;
	
	public freezeEvent() {
	}
	
	@EventHandler
	public void moveEvent(PlayerMoveEvent event) {
		if (Central.freezeList.contains(event.getPlayer())) {
			event.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void breakBlock(BlockBreakEvent breakEvent) {
		Player breakEventPlayer = breakEvent.getPlayer();
		if (Central.freezeList.contains(breakEventPlayer)) {
			breakEvent.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void placeBlock(BlockPlaceEvent placeEvent) {
		Player player = placeEvent.getPlayer();
		if (Central.freezeList.contains(player)) {
			placeEvent.setCancelled(true);
		}
		
	}
}
