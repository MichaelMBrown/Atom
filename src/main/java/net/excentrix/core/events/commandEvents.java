package net.excentrix.core.events;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class commandEvents implements Listener {
	
	@EventHandler
	public void commandProcessEvent(PlayerCommandPreprocessEvent event) {
		if (event.getMessage().split(" ")[0].contains(":")){
			if (coreUtils.getRankInteger(event.getPlayer().getName())<3){
				event.setCancelled(true);
				coreUtils.errorMessage(event.getPlayer(),"That syntax was not accepted by the server.");
			}else coreUtils.informativeMessage(event.getPlayer(),"&cThat command would have been blocked, but since you're an administrator it was accepted.");
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void commandFreeze(PlayerCommandPreprocessEvent event) {
		if (CentralHandler.freezeList.contains(event.getPlayer())){
			event.setCancelled(true);
			coreUtils.errorMessage(event.getPlayer(),"You are not permitted to send commands whilst you're frozen.");
		}
	}
}
