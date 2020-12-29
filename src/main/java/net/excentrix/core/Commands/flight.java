package net.excentrix.core.Commands;

import net.excentrix.core.utils.coreUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class flight implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (sender.hasPermission("atom.command.fly")) {
				if (command.getName().equalsIgnoreCase("fly")) {
					if (args.length > 0 && args.length != 1) {
						Player targetPlayer = Bukkit.getPlayerExact(args[0]);
						Player target = coreUtils.playerLookup((Player) sender, targetPlayer);
						if (target != null) {
							if (args[1].equalsIgnoreCase("on")) {
//                                sender.sendMessage(ChatColor.GREEN + "You turned flight on for " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + "!");
								coreUtils.informativeMessage((Player) sender, "You turned flight on for " + coreUtils.getPlayerColor(target) + target.getName() + "&a!");
								if (!target.isOnGround() || !target.isSwimming()) {
									target.setAllowFlight(true);
									target.setFlying(true);
								} else target.setAllowFlight(true);
							} else if (args[1].equalsIgnoreCase("off")) {
								coreUtils.informativeMessage((Player) sender, "You turned flight off for " + coreUtils.getPlayerColor(target) + target.getName() + "&a!");
								target.setFlying(false);
								target.setAllowFlight(false);
							}
						} else coreUtils.playerNotFound((Player) sender);
					} else if (args.length == 0) {
						if (((Player) sender).getAllowFlight()) {
							coreUtils.informativeMessage((Player) sender, "You turned flight off for " + coreUtils.getPlayerColor((Player) sender) + sender.getName() + "&a!");
							((Player) sender).setAllowFlight(false);
							((Player) sender).setFlying(false);
						} else {
							if (!((Player) sender).isOnGround() || !((Player) sender).isSwimming()) {
								((Player) sender).setAllowFlight(true);
								((Player) sender).setFlying(true);
							} else ((Player) sender).setAllowFlight(true);
							coreUtils.informativeMessage((Player) sender, "You turned flight on for " + coreUtils.getPlayerColor((Player) sender) + sender.getName() + "&a!");
						}
					} else
						coreUtils.printUsage((Player) sender, "fly", "[player] <mode>");
				}
			} else
				coreUtils.noPerm((Player) sender);
		} else {
			sender.sendMessage(ChatColor.RED + "You " + ChatColor.UNDERLINE + "MUST" + ChatColor.RESET + "" + ChatColor.RED + " be a player to execute this command!");
		}
		return true;
	}
}
