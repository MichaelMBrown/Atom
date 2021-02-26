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

public class freeze implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (command.getName().equalsIgnoreCase("freeze")) {
			if (sender.hasPermission("atom.command.freeze")) {
				if (args.length == 1) {
					Player targetPlayer = Bukkit.getPlayerExact(args[0]);
					Player target = coreUtils.playerLookup((Player) sender, targetPlayer);
					if (target != null) {
						if (!CentralHandler.freezeList.contains(target)) {
							CentralHandler.freezeList.add(target);
							target.setInvulnerable(true);
							coreUtils.informativeMessage((Player) sender, "You froze " + coreUtils.getPlayerColor(target) + target.getName());
							BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "froze " + ChatColor.YELLOW + target.getName(), false);
							coreUtils.errorMessage(target, "You have been frozen.");
						} else {
							CentralHandler.freezeList.remove(target);
							target.setInvulnerable(false);
							coreUtils.informativeMessage((Player) sender, "You unfroze " + coreUtils.getPlayerColor(target) + target.getName());
							BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "unfroze " + ChatColor.YELLOW + target.getName(), false);
							coreUtils.informativeMessage(target, "You are no longer frozen.");
						}
					} else {
						coreUtils.playerNotFound((Player) sender);
					}
				} else coreUtils.printUsage((Player) sender, "freeze", "<player>");
			} else coreUtils.noPerm((Player) sender);
		}
		return true;
	}
}
