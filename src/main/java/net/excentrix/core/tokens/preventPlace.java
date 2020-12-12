package net.excentrix.core.tokens;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class preventPlace implements Listener {
	@EventHandler()
	public void onBlockPlace(BlockPlaceEvent event) {
		try {
			if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("RANK TOKEN") || event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName().contains("RANK TOKEN")) {
				event.setCancelled(true);
			}
		} catch (NullPointerException ignored) {
		}
	}
}
