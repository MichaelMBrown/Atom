package net.excentrix.core.Commands;

import net.excentrix.core.Central;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class setServer implements CommandExecutor {
	private static final Plugin plugin = Central.getPlugin(Central.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (command.getName().equalsIgnoreCase("setserver") || command.getName().equalsIgnoreCase("set-server")) {
				if (sender.hasPermission("atom.command.setserver")) {
					if (args.length == 1) {
						sender.sendMessage(ChatColor.DARK_GRAY + "Processing request...");
						plugin.getConfig().set("server-name", args[0]);
						coreUtils.informativeMessage((Player) sender, "Done! You've set the server-name to " + ChatColor.YELLOW + args[0]);
						plugin.saveConfig();
						coreUtils.notifyStaff(sender.getName(), "Set the server name to " + ChatColor.RED + args[0]);
					} else {
						coreUtils.printUsage((Player) sender, "setserver", "<new server name>");
					}
				} else coreUtils.noPerm((Player) sender);
			}
		}
		return true;
	}
}
