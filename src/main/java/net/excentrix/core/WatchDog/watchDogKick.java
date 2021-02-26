package net.excentrix.core.WatchDog;


import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Array;

@SuppressWarnings("DuplicatedCode")
public class watchDogKick implements CommandExecutor {
	Plugin plugin = CentralHandler.getPlugin(CentralHandler.class);
	private CommandSender sender;
	private Command command;
	private String[] args;
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Plugin plugin = CentralHandler.getPlugin(CentralHandler.class);
		String reason = "";
		if ((sender instanceof Player)) {
			if (sender.hasPermission("atom.command.kick")) {
				if (command.getName().equalsIgnoreCase("kick")) {
					if (args.length > 0) {
						Player target = Bukkit.getPlayerExact(args[0]);
						if (target != null) {
							if (args.length > 1) {
								for (int i = 1; i < args.length; i++) {
									reason = reason + args[i] + " ";
								}
								coreUtils.notifyStaff("none", sender.getName() + " kicked player " + args[0] + " with reason: " + reason);
								target.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&cYou've been kicked for: \n&r" + reason));
								//plugin.getLogger().info(ChatColor.GREEN + sender.getName() + ChatColor.YELLOW + " kicked player " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " with reason " + ChatColor.WHITE + reason);
							} else if (args.length == 1) {
//								coreUtils.errorMessage((Player) sender, "You need to provide a reason for this kick.");
                                coreUtils.notifyStaff("none", sender.getName()+" kicked player " + args[0] + " with reason: You were kicked from the Server");
                                target.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&cYou've been kicked for: \n&r" + "You were kicked from the Server"));
//                                plugin.getLogger().info(ChatColor.GREEN + sender.getName() + ChatColor.YELLOW + " kicked player " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " with reason " + reason);
							}
						} else
							coreUtils.playerNotFound((Player) sender);
					} else coreUtils.printUsage((Player) sender, "kick", "<player> <reason>");
				}
			} else {
				coreUtils.noPerm((Player) sender);
			}
		} else if (sender instanceof ConsoleCommandSender) {
			if (command.getName().equalsIgnoreCase("kick")) {
				if (args.length > 0) {
					Player target = Bukkit.getPlayerExact(args[0]);
					if (target != null) {
						if (args.length > 1) {
							for (int i = 1; i < args.length; ++i) {
								reason = reason + args[i] + " ";
							}
							coreUtils.notifyStaff(ChatColor.RED + "Console", "kicked player " + Array.get(args, 0) + " with reason: " + ChatColor.translateAlternateColorCodes('&', reason));
							target.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
							//plugin.getLogger().info(ChatColor.GREEN + sender.getName() + ChatColor.YELLOW + " kicked player " + ChatColor.GOLD + target.getName() + ChatColor.GRAY + " with reason " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', reason));
						}
						if (args.length == 1) {
							reason = "You have been kicked from the Server.";
							coreUtils.notifyStaff("Console", "kicked player " + args[0] + " with reason: " + ChatColor.translateAlternateColorCodes('&', reason));
							target.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
							//plugin.getLogger().info(ChatColor.GREEN + sender.getName() + ChatColor.YELLOW + " kicked player " + ChatColor.GOLD + target.getName() + ChatColor.GRAY + " with reason " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', reason));
						}
					} else {
						coreUtils.playerNotFound((Player) sender);
					}
				} else coreUtils.printUsage((Player) sender, "kick", "<player> [reason]");
			}
		}
		return true;
	}
	
}