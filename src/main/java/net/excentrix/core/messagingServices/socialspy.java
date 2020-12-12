package net.excentrix.core.messagingServices;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class socialspy implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player commandSender = (Player) sender;
		if (!sender.hasPermission("atom.command.socialspy")) {
			staffUtils.noPerm(commandSender);
			return true;
		} else {
			if (Core.nowSpying.contains(commandSender)) {
				Core.nowSpying.remove(commandSender);
				staffUtils.informativeMessage(commandSender, "You have disabled admin spying.");
			} else {
				Core.nowSpying.add(commandSender);
				staffUtils.informativeMessage(commandSender, "You have enabled admin spying.");
			}
			
			return true;
		}
	}
}
