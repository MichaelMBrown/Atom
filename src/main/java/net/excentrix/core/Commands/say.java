package net.excentrix.core.Commands;

import net.excentrix.core.utils.coreUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class say implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		StringBuilder message = new StringBuilder();
		Player player = (Player) commandSender;
		if (!commandSender.hasPermission("atom.command.say")) {
			coreUtils.noPerm(player);
			return true;
		}
		if (strings.length > 0) {
			for (String string : strings) {
				message.append(string).append(" ");
			}
			coreUtils.broadcastServer(ChatColor.LIGHT_PURPLE + "[" + commandSender.getName() + "] " + message + ChatColor.WHITE);
		} else coreUtils.printUsage(player, "say", "<message>");
		return true;
	}
}
