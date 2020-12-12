package net.excentrix.core.events;

import net.excentrix.core.utils.staffUtils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class rightVersion implements Listener {
	@EventHandler
	public void iWantRightVersion(AsyncPlayerChatEvent event) {
		if (event.getMessage().equalsIgnoreCase("I want the right version ♂") || event.getMessage().equalsIgnoreCase("I want the right version :male_sign:")) {
			staffUtils.informativeMessage(event.getPlayer(), "Here's the right version. https://go.excentrix.net/rightversion");
			event.setMessage(ChatColor.translateAlternateColorCodes('&', "&6I got the right version :)"));
		}
	}
}
