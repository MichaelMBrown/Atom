package net.excentrix.core.internalCommands;

import net.excentrix.core.Central;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class announceToStaff implements CommandExecutor {
	private static final Plugin plugin = Central.getPlugin(Central.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender) {
			String message = "";
			if (args.length > 1) {
				Player target = Bukkit.getPlayerExact(args[0]);
//                for (int i = 1; i < args.length; ++i) {
//                    message = message + args[i] + " ";
//                }
				message = String.join(" ", args);
				int substring = args[0].length() + 1;
				String newMessage = message.substring(substring);
				if (target != null) {
					BukkitCommand.broadcastCommandMessage(target, ChatColor.translateAlternateColorCodes('&', newMessage), false);
				}// else plugin.getLogger().warning("Unknown Player specified");
			} else plugin.getLogger().warning("Not enough args supplied");
		}// else {
//            sender.sendMessage("Unknown command. Type \"/help\" for help.");
//        }
		return true;
	}
}
