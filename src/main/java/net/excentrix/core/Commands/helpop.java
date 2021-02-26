package net.excentrix.core.Commands;

import net.excentrix.core.utils.coreUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class helpop implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String message = "";
		if (sender instanceof Player) {
			if (command.getName().equalsIgnoreCase("helpop")) {
				if (args.length > 0) {
					for (int i = 0; i < args.length; ++i) {
						message = message + args[i] + " ";
					}
					coreUtils.informativeMessage((Player) sender, "Your message &f&o" + message + "&awas send to the staff!");
					coreUtils.notifyStaff("Console", ChatColor.GOLD + (coreUtils.getPlayerColor((Player) sender) + sender.getName() + ChatColor.YELLOW + " requested help: " + ChatColor.GRAY + "" + ChatColor.ITALIC + message));
				} else {
					coreUtils.printUsage((Player) sender, "helpop", "<message>");
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You need to be a player to execute this command.");
		}
		return true;
	}
}
