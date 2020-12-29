package net.excentrix.core.messagingServices;

import net.excentrix.core.Central;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class messageUtils {
	private static final Plugin plugin = Central.getPlugin(Central.class);
	
	public static boolean messageEligibility(Player player) {
		return !Central.pmToggled.contains(player);
	}
	
	public static void messagePlayer(Player sender, Player receipt, String message) {
		for (final Player p : Bukkit.getOnlinePlayers()) {
			if (Central.nowSpying.contains(p) && p != sender && p != receipt) {
				coreUtils.informativeMessage(p, "&9(" + coreUtils.getPlayerColor(sender) + sender.getName() + "&9 to " + coreUtils.getPlayerColor(receipt) + receipt.getName() + "&9) " + message);
			}
		}
		plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&c" + sender.getName() + " &f-> &c" + receipt.getName() + " &f" + message));
		coreUtils.informativeMessage(receipt, "&3(From " + coreUtils.getPlayerColor(sender) + sender.getName() + "&3) " + message);
		coreUtils.informativeMessage(sender, "&3(To " + coreUtils.getPlayerColor(receipt) + receipt.getName() + "&3) " + message);
	}
}
