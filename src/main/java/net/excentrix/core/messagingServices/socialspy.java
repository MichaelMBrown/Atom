package net.excentrix.core.messagingServices;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class socialspy implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player commandSender = (Player) sender;
		if (!sender.hasPermission("atom.command.socialspy")) {
			coreUtils.noPerm(commandSender);
		} else {
			if (CentralHandler.nowSpying.contains(commandSender)) {
				CentralHandler.nowSpying.remove(commandSender);
				coreUtils.informativeMessage(commandSender, "You have disabled admin spying.");
			} else {
				CentralHandler.nowSpying.add(commandSender);
				coreUtils.informativeMessage(commandSender, "You have enabled admin spying.");
			}
			
		}
		return true;
	}
}
