package net.excentrix.core.enchants;

import net.excentrix.core.Central;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.text.DecimalFormat;

public class trueDamage implements Listener {
	@EventHandler
	public void playerHit(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			if (Central.enchantSupport = true) {
				Player player = (Player) event.getDamager();
				try {
					if (player.getEquipment().getItemInMainHand().getLore().toString().contains("True Damage")) {
						double rollProc = Math.random();
						if (rollProc >= 0.95) {
							double newDamage = event.getDamage() * 1.2;
							event.setDamage(newDamage);
							DecimalFormat df = new DecimalFormat("#.##");
							coreUtils.informativeMessage(player, "&b&lPROC! &eYour &fTrue Damage&e enchant just proc'ed! You dealt &f" + df.format(event.getFinalDamage() / 2) + "&c♥");
						}
					}
				} catch (NullPointerException ignored) {
				}
				
			}
		}
	}
}
