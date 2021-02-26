package net.excentrix.core.events;

import net.excentrix.core.CentralHandler;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Slime;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.Plugin;

public class mobSpawn implements Listener {
	private static final Plugin plugin = CentralHandler.getPlugin(CentralHandler.class);
	
	@EventHandler
	public void disableAI(CreatureSpawnEvent event) {
		event.getEntity().setAI(plugin.getConfig().getBoolean("mobAI"));
	}
	
	@EventHandler
	public void disableSlimes(CreatureSpawnEvent event) {
		if (plugin.getConfig().getBoolean("slimesDisabled")) {
			if (event.getEntity() instanceof Slime) {
				event.getEntity().remove();
			}
		}
	}
	
	@EventHandler
	public void disableDrowned(CreatureSpawnEvent event) {
		if (plugin.getConfig().getBoolean("disableDrowned")) {
			if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.DROWNED) {
				event.getEntity().remove();
			}
		}
	}
	
	@EventHandler
	public void disableWanderingTrader(CreatureSpawnEvent event) {
		if (plugin.getServer().getBukkitVersion().contains("1.14") || (plugin.getServer().getBukkitVersion().contains("1.16"))) {
			if (event.getEntity() instanceof WanderingTrader) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void disableInsomnia(CreatureSpawnEvent event) {
		if (!(plugin.getConfig().getBoolean("doInsomnia"))) {
			if (event.getEntity() instanceof Phantom) {
				event.setCancelled(true);
			}
		}
	}
}
