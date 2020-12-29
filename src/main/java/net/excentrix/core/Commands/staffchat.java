package net.excentrix.core.Commands;

import net.excentrix.core.Central;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class staffchat implements CommandExecutor {
	private static final Plugin plugin = Central.getPlugin(Central.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((sender instanceof Player)) {
			if (sender.hasPermission("atom.chat.staffchat")) {
				if (command.getName().equalsIgnoreCase("sc")) {
					if (args.length > 0) {
						String message = String.join(" ", args);
						//plugin.getLogger().info(ChatColor.GOLD + ((Player) sender).getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + message);
						coreUtils.notifyStaff(sender.getName(), message);
					} else {
						coreUtils.printUsage((Player) sender, "sc", "<message>");
					}
				}
			} else coreUtils.noPerm((Player) sender);
		} else if ((sender instanceof ConsoleCommandSender)) {
			if (command.getName().equalsIgnoreCase("sc")) {
				if (args.length > 0) {
					String message = String.join(" ", args);
					coreUtils.notifyStaff("Console", message);
				} else
					coreUtils.printUsage((Player) sender, "sc", "<message>");
			}
		}
		return true;
	}
}
