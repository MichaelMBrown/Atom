package net.excentrix.core.events;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import net.excentrix.core.CentralHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class deathEvents implements Listener {
	public deathEvents() {
	}
	
	@EventHandler
	public void godDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		if (CentralHandler.godList.contains(player)) {
			event.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void deathReformat(PlayerDeathEvent event) {
		Player player = event.getEntity();
		if (event.getEntity().getKiller() != null) {
			event.setDeathMessage(ChatColor.RED + "" + ChatColor.BOLD + "☠ " + ChatColor.GRAY + player.getName() + " was killed by " + event.getEntity().getKiller().getName());
			//event.setDeathMessage("");
			//World world = player.getWorld();
			//world.strikeLightning(player.getLocation());
		} else {
			event.setDeathMessage(ChatColor.RED + "" + ChatColor.BOLD + "☠ " + ChatColor.GRAY + player.getName() + " has died.");
			//event.setDeathMessage("");
			//World world = player.getWorld();
			//world.strikeLightning(player.getLocation());
		}
	}
	
	@EventHandler
	public void respawnEvent(PlayerPostRespawnEvent event) {
		event.getPlayer().teleport(CentralHandler.spawn, PlayerTeleportEvent.TeleportCause.PLUGIN);
	}
}
