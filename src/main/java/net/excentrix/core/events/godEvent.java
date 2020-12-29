package net.excentrix.core.events;

import net.excentrix.core.Central;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class godEvent implements Listener {
	
	@EventHandler
	public void godCheck(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (Central.godList.contains(player)) {
				event.setCancelled(true);
			}
		}
	}
//
//    @EventHandler
//    public void instaKill(EntityDamageByEntityEvent event) {
//        if (event.getDamager() instanceof Player) {
//            Player player = (Player) event.getDamager();
//            Entity entity = event.getEntity();
//            if (Central.godList.contains(player) && !(entity instanceof Player) && entity instanceof LivingEntity) {
//                double Damage = ((LivingEntity) entity).getHealth();
//                event.setDamage(Damage);
//                player.sendMessage(ChatColor.RED + "Since you're in God Mode, you insta-killed this " + event.getEntity().getName());
//            } else if (Central.godList.contains(player) && entity instanceof Player) {
//                event.setCancelled(true);
//                ((Player) entity).setHealth(0);
//                player.sendMessage(ChatColor.RED + "Since you're in God Mode, you insta-killed the player " + event.getEntity().getName());
//            }
//        }
//    }
}
