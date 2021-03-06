package net.excentrix.core.Commands;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class buildMode implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (!(sender.hasPermission("atom.command.build")))
			return true;
		if (CentralHandler.buildDenied.contains(player)) {
			coreUtils.informativeMessage(player, "Your build mode has been turned on.");
			CentralHandler.buildDenied.remove(player);
		} else {
			coreUtils.informativeMessage(player, "&cYour build mode has been turned off.");
			CentralHandler.buildDenied.add(player);
		}
		return true;
	}
}
