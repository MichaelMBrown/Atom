package net.excentrix.core.Commands;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class God implements CommandExecutor, Listener {
	CentralHandler plugin;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (sender.hasPermission("atom.command.god")) {
				if (command.getName().equalsIgnoreCase("god")) {
					if (args.length > 0 && args.length != 1) {
						Player targetPlayer = Bukkit.getPlayerExact(args[0]);
						Player target = coreUtils.playerLookup((Player) sender, targetPlayer);
						if (target != null) {
							if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("true")) {
								coreUtils.informativeMessage((Player) sender, "You turned on God Mode for " + coreUtils.getPlayerColor(target) + target.getName() + "&a!");
								BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "enabled God mode for " + ChatColor.YELLOW + target.getName(), false);
								target.setInvulnerable(true);
								CentralHandler.godList.add(target);
							} else if (args[1].equalsIgnoreCase("off") || args[1].equalsIgnoreCase("false")) {
								coreUtils.informativeMessage((Player) sender, "You turned off God Mode for " + coreUtils.getPlayerColor(target) + target.getName() + "&a!");
								BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "enabled God mode for " + ChatColor.YELLOW + target.getName(), false);
								target.setInvulnerable(false);
								CentralHandler.godList.remove(target);
							}
						} else coreUtils.playerNotFound((Player) sender);
					} else if (args.length == 0) {
						if (CentralHandler.godList.contains(player)) {
							coreUtils.informativeMessage((Player) sender, "You turned off God Mode for " + coreUtils.getPlayerColor((Player) sender) + sender.getName() + "&a!");
							BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "disabled God Mode.", false);
							((Player) sender).setInvulnerable(false);
							CentralHandler.godList.remove(player);
						} else {
							coreUtils.informativeMessage((Player) sender, "You turned on God Mode for " + coreUtils.getPlayerColor((Player) sender) + sender.getName() + "&a!");
							BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "enabled God Mode.", false);
							((Player) sender).setInvulnerable(true);
							CentralHandler.godList.add(player);
						}
					} else
						coreUtils.printUsage((Player) sender, "god", "[player] <mode>");
				}
			} else
				coreUtils.noPerm((Player) sender);
		} else {
			sender.sendMessage(ChatColor.RED + "You " + ChatColor.UNDERLINE + "MUST" + ChatColor.RESET + "" + ChatColor.RED + " be a player to execute this command!");
		}
		return true;
	}
}
