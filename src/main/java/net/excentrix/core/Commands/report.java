package net.excentrix.core.Commands;

import net.excentrix.core.utils.coreUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// Deprecated

//TODO: Rewrite this command

public class report implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((sender instanceof Player)) {
			if (command.getName().equalsIgnoreCase("report")) {
				if ((args.length == 2)) {
					Player targetPlayer = Bukkit.getPlayerExact(args[0]);
					Player target = coreUtils.playerLookup((Player) sender, targetPlayer);
					if (target != null) {
						if (args[1].equalsIgnoreCase("KillAura") || args[1].equalsIgnoreCase("ka")) {
							sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " for " + ChatColor.RED + "KillAura" + ChatColor.GREEN + "!");
							notifyStaff((Player) sender, target, args);
						} else if (args[1].equalsIgnoreCase("aimbot")) {
							sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " for " + ChatColor.RED + "Aimbot" + ChatColor.GREEN + "!");
							notifyStaff((Player) sender, target, args);
						} else if (args[1].equalsIgnoreCase("Fly") || args[1].equalsIgnoreCase("flight")) {
							sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " for " + ChatColor.RED + "Flight" + ChatColor.GREEN + "!");
							notifyStaff((Player) sender, target, args);
						} else if (args[1].equalsIgnoreCase("antikb") || args[1].equalsIgnoreCase("antiknockback") || args[1].equalsIgnoreCase("knockback") || args[1].equalsIgnoreCase("velocity") || args[1].equalsIgnoreCase("anti-knockback")) {
							sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " for " + ChatColor.RED + "Anti-Knockback" + ChatColor.GREEN + "!");
							notifyStaff((Player) sender, target, args);
						} else if (args[1].equalsIgnoreCase("crits") || args[1].equalsIgnoreCase("criticals") || args[1].equalsIgnoreCase("crit")) {
							sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " for " + ChatColor.RED + "Criticals" + ChatColor.GREEN + "!");
							notifyStaff((Player) sender, target, args);
						} else if (args[1].equalsIgnoreCase("bhop") || args[1].equalsIgnoreCase("BunnyHop") || args[1].equalsIgnoreCase("B-Hop")) {
							sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " for " + ChatColor.RED + "BunnyHop" + ChatColor.GREEN + "!");
							notifyStaff((Player) sender, target, args);
						} else if (args[1].equalsIgnoreCase("speed") || args[1].equalsIgnoreCase("movement")) {
							sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " for " + ChatColor.RED + "Speed" + ChatColor.GREEN + "!");
							notifyStaff((Player) sender, target, args);
						} else if (args[1].equalsIgnoreCase("chat") || args[1].equalsIgnoreCase("spam") || args[1].equalsIgnoreCase("advertising") || args[1].equalsIgnoreCase("spamming")) {
							sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " for " + ChatColor.YELLOW + "Chat Violations" + ChatColor.GREEN + "!");
							notifyStaff((Player) sender, target, args);
						} else {
							sender.sendMessage(ChatColor.RED + "Valid Hacks: (KILLAURA, AIMBOT, FLIGHT, ANTIKNOCKBACK, CRITICALS, BHOP, SPEED, SPAM)");
						}
					} else {
						coreUtils.playerNotFound((Player) sender);
					}
				} else {
					coreUtils.printUsage((Player) sender, "report", "<player> <hacks>");
				}
			}
		}
		return true;
	}
	
	public void notifyStaff(Player sender, Player target, String[] args) {
		if (args[1].equalsIgnoreCase("KA")) {
			coreUtils.notifyStaff("watchdog", coreUtils.getPlayerColor(sender) + sender.getName() + ChatColor.YELLOW + " has reported " + coreUtils.getPlayerColor(target) + target.getName() + ChatColor.YELLOW + " for " + ChatColor.RED + "KillAura" + ChatColor.YELLOW + ".");
		} else
			coreUtils.notifyStaff("watchdog", coreUtils.getPlayerColor(sender) + sender.getName() + ChatColor.YELLOW + " has reported " + coreUtils.getPlayerColor(target) + target.getName() + ChatColor.YELLOW + " for " + ChatColor.RED + args[1].toLowerCase() + ChatColor.YELLOW + ".");
	}
}