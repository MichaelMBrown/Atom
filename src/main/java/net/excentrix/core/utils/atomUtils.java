package net.excentrix.core.utils;

import net.excentrix.core.CentralHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class atomUtils {
	private static final Plugin plugin = CentralHandler.getPlugin(CentralHandler.class);
	
	public static void atomShowCommand(Player player, String command, String usage, String permission) {
		if (permission.equalsIgnoreCase("none")) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- /&f" + command + " &6&m  &f&r " + usage));
		} else if (player.hasPermission("atom.command." + permission)) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- /&f" + command + " &6&m  &f&r " + usage));
//            player.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/" + command + ChatColor.YELLOW + " -- " + ChatColor.WHITE + usage + ".");
		}else if (permission.equalsIgnoreCase("SA")){
			if (coreUtils.getRankInteger(player.getName())>=3){
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- /&f" + command + " &6&m  &f&r " + usage));
			}
		}else if (permission.equalsIgnoreCase("mod+")){
			if (coreUtils.getRankInteger(player.getName())>=2){
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- /&f" + command + " &6&m  &f&r " + usage));
			}
		}
	}
	
	public static void pluginSetup() {
		switch (plugin.getConfig().getString("server-name").toLowerCase()) {
			case "skyblock":
			case "development":
				CentralHandler.enchantSupport = true;
				break;
			case "prison":
				CentralHandler.isPrison = true;
				CentralHandler.enchantSupport = true;
				break;
			default:
				CentralHandler.enchantSupport = false;
				break;
		}
	}
}
