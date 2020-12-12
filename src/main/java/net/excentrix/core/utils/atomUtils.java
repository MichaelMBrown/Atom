package net.excentrix.core.utils;

import net.excentrix.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class atomUtils {
	private static final Plugin plugin = Core.getPlugin(Core.class);
	
	public static void atomShowCommand(Player player, String command, String usage) {
		if (player.hasPermission("atom.command." + command)) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- /&f" + command + " &6&m  &f&r " + usage));
//            player.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/" + command + ChatColor.YELLOW + " -- " + ChatColor.WHITE + usage + ".");
		}
	}
	
	public static void pluginSetup() {
		switch (plugin.getConfig().getString("server-name").toLowerCase()) {
			case "skyblock":
			case "development":
				Core.enchantSupport = true;
				break;
			case "prison":
				Core.isPrison = true;
				Core.enchantSupport = true;
				break;
			default:
				Core.enchantSupport = false;
				break;
		}
	}
}
