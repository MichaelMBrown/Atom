package net.excentrix.core.messagingServices;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class togglePM implements CommandExecutor {
	public togglePM() {
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player commandSender = (Player) sender;
		if (CentralHandler.pmToggled.contains(commandSender)) {
			CentralHandler.pmToggled.remove(commandSender);
			coreUtils.informativeMessage((Player) sender, "You will now receive private messages from players.");
		} else {
			CentralHandler.pmToggled.add(commandSender);
			coreUtils.informativeMessage((Player) sender, "&cYou will no longer receive private messages from players.");
		}
		
		return true;
	}
}
