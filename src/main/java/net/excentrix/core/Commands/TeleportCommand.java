package net.excentrix.core.Commands;

import net.excentrix.core.utils.coreUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (command.getName().equalsIgnoreCase("tp")) {
				if (sender.hasPermission("atom.command.tp")) {
					if (args.length == 1) {
						Player targetPlayer = Bukkit.getPlayerExact(args[0]);
						Player target = coreUtils.playerLookup((Player) sender, targetPlayer);
						if (target != null) {
							Location targetLoc = target.getLocation();
							((Player) sender).teleport(targetLoc);
							coreUtils.informativeMessage((Player) sender, "You have teleported to " + coreUtils.getPlayerColor(target) + target.getName());
							BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "teleported to " + ChatColor.GOLD + target.getName(), false);
						} else {
							coreUtils.playerNotFound((Player) sender);
						}
					} else if (args.length == 2) {
						Player targetLookup = Bukkit.getPlayerExact(args[0]);
						Player target = coreUtils.playerLookup((Player) sender, targetLookup);
						Player targetLookup2 = Bukkit.getPlayerExact(args[1]);
						Player target2 = coreUtils.playerLookup((Player) sender, targetLookup2);
						if (target != null && target2 != null) {
							Location toTarget = target2.getLocation();
							target.teleport(toTarget);
							coreUtils.informativeMessage((Player) sender, "You have teleported to " + coreUtils.getPlayerColor(target) + target.getName() + "&a to " + coreUtils.getPlayerColor(target2) + target2.getName());
							BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "teleported " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " to " + ChatColor.GOLD + target2.getName(), false);
						} else {
							coreUtils.playerNotFound((Player) sender);
						}
					} else if (args.length == 3) {
						double x, y, z;
						try {
							x = Integer.parseInt(args[0]) + .5;
							y = Integer.parseInt(args[1]);
							z = Integer.parseInt(args[2]) + .5;
							Location targetLoc = new Location(((Player) sender).getWorld(), x, y, z, ((Player) sender).getLocation().getYaw(), ((Player) sender).getLocation().getPitch());
							((Player) sender).teleport(targetLoc);
							coreUtils.informativeMessage((Player) sender, "You have teleported to coordinates: &e" + x + "&a, &e" + y + "&a, &e" + z);
						} catch (NumberFormatException exception) {
							coreUtils.errorMessage((Player) sender, "Invalid coordinates provided.");
						}
					} else {
						coreUtils.printUsage((Player) sender, "tp", "<player> [player] OR <x> <y> <z>");
					}
				} else {
					coreUtils.noPerm((Player) sender);
				}
			}
		}
		return true;
	}
}

