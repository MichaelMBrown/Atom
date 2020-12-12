package net.excentrix.core.messagingServices;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class whisper implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player commandSender = (Player) sender;
		String message = "";
		if (Core.freezeList.contains(sender)) {
			staffUtils.actionForbidden(commandSender);
			return true;
		} else {
			if (args.length < 2) {
				staffUtils.printUsage(commandSender, command.getName(), "<player> <message>");
			} else {
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target != null) {
					if (messageUtils.messageEligibility(target)) {
						for (int i = 1; i < args.length; ++i) {
							message = message + args[i] + " ";
						}
						messageUtils.messagePlayer(commandSender, target, message);
					} else if (commandSender.hasPermission("atom.messaging.bypass")) {
						staffUtils.errorMessage(commandSender, "WARNING: " + staffUtils.getPlayerColor(target) + target.getName() + ChatColor.translateAlternateColorCodes('&', "&c has their private messages turned off."));
						for (int i = 1; i < args.length; ++i) {
							message = message + args[i] + " ";
						}
						messageUtils.messagePlayer(commandSender, target, message);
						
					} else {
						staffUtils.errorMessage(commandSender, "Sorry, you cannot message this player.");
					}
				} else {
					staffUtils.playerNotFound(commandSender);
				}
			}
			
			return true;
		}
	}
}
