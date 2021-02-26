package net.excentrix.core.events;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class damageEvents implements Listener {
	@EventHandler
	public void damageMe(EntityDamageByEntityEvent entityDamageByEntityEvent) {
		if (entityDamageByEntityEvent.getDamager() instanceof Player) {
			if (entityDamageByEntityEvent.getEntity() instanceof Player) {
				if (entityDamageByEntityEvent.getEntity().getName().equals("qr0") || entityDamageByEntityEvent.getEntity().getName().equalsIgnoreCase("Azara")) {
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
				if (!CentralHandler.globalPVP) {
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void noSweep(EntityDamageByEntityEvent event) {
		if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK)){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void customItemHandler(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player attacker = (Player) event.getDamager();
			try {
				String itemName = ChatColor.stripColor(attacker.getEquipment().getItemInMainHand().getItemMeta().getDisplayName());
				if (itemName.equals("Sword of the Universe")) {
					if (attacker.getEquipment().getItemInMainHand().getType().equals(Material.GOLDEN_SWORD))
						if (event.getEntity() instanceof LivingEntity)
							if (coreUtils.getRankInteger(attacker.getName()) >= 3) {
								event.setCancelled(true);
								((LivingEntity) event.getEntity()).setHealth(0);
							} else {
								attacker.getEquipment().getItemInMainHand().setType(Material.AIR);
								coreUtils.errorMessage(attacker, "That was stupid of you.");
							}
				}
			} catch (NullPointerException ignored) {
			}
		}
	}
}
