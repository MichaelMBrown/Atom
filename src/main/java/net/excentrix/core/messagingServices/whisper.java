package net.excentrix.core.messagingServices;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
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
		if (CentralHandler.freezeList.contains(sender)) {
			coreUtils.actionForbidden(commandSender);
			return true;
		} else {
			if (args.length < 2) {
				coreUtils.printUsage(commandSender, command.getName(), "<player> <message>");
			} else {
				Player targetPlayer = Bukkit.getPlayerExact(args[0]);
				Player target = coreUtils.playerLookup((Player) sender, targetPlayer);
				if (target != null) {
					if (messageUtils.messageEligibility(target)) {
						for (int i = 1; i < args.length; ++i) {
							message = message + args[i] + " ";
						}
						messageUtils.messagePlayer(commandSender, target, message);
					} else if (commandSender.hasPermission("atom.messaging.bypass")) {
						coreUtils.errorMessage(commandSender, "WARNING: " + coreUtils.getPlayerColor(target) + target.getName() + ChatColor.translateAlternateColorCodes('&', "&c has their private messages turned off."));
						for (int i = 1; i < args.length; ++i) {
							message = message + args[i] + " ";
						}
						messageUtils.messagePlayer(commandSender, target, message);
						
					} else {
						coreUtils.errorMessage(commandSender, "Sorry, you cannot message this player.");
					}
				} else {
					coreUtils.playerNotFound(commandSender);
				}
			}
			
			return true;
		}
	}
}
