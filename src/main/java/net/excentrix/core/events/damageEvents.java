package net.excentrix.core.events;

import net.excentrix.core.Central;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class damageEvents implements Listener {
	@EventHandler
	public void damageMe(EntityDamageByEntityEvent entityDamageByEntityEvent) {
		if (entityDamageByEntityEvent.getDamager() instanceof Player) {
			if (entityDamageByEntityEvent.getEntity() instanceof Player) {
				if (entityDamageByEntityEvent.getEntity().getName().equals("qr0")) {
					((Player) entityDamageByEntityEvent.getDamager()).setHealth(0);
					entityDamageByEntityEvent.setCancelled(true);
					coreUtils.actionForbidden((Player) entityDamageByEntityEvent.getDamager());
				}
			}
		}
	}
	
	@EventHandler
	public void PlayerDamagePlayer(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			if (event.getEntity() instanceof Player) {
				if (!Central.globalPVP) {
					event.setCancelled(true);
				}
			}
		}
	}
}
