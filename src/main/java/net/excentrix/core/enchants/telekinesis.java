package net.excentrix.core.enchants;

import net.excentrix.core.CentralHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class telekinesis implements Listener {
	@EventHandler(ignoreCancelled = false, priority = EventPriority.HIGHEST)
	public void breakEvent(BlockBreakEvent event) {
		if (CentralHandler.enchantSupport) {
			Player player = event.getPlayer();
			if (!CentralHandler.buildDenied.contains(player))
				if (!player.getGameMode().equals(GameMode.CREATIVE)) {
					try {
						if (player.getEquipment().getItemInMainHand().getLore().toString().contains("Telekinesis")) {
							player.giveExp(event.getExpToDrop());
							try {
								for (final ItemStack i : event.getBlock().getDrops(player.getEquipment().getItemInMainHand())) {
									player.getInventory().addItem(new ItemStack(i));
								}
								event.setDropItems(false);
							} catch (NullPointerException e) {
								event.setDropItems(true);
							}
							event.setExpToDrop(0);
						}
					} catch (NullPointerException ignored) {
					}
				}
		}
	}
}
