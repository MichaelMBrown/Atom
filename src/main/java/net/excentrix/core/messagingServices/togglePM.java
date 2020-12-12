package net.excentrix.core.messagingServices;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class togglePM implements CommandExecutor {
	public togglePM() {
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player commandSender = (Player) sender;
		if (Core.pmToggled.contains(commandSender)) {
			Core.pmToggled.remove(commandSender);
			staffUtils.informativeMessage((Player) sender, "You will now receive private messages from players.");
		} else {
			Core.pmToggled.add(commandSender);
			staffUtils.informativeMessage((Player) sender, "&cYou will no longer receive private messages from players.");
		}
		
		return true;
	}
}
