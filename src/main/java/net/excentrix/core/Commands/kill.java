package net.excentrix.core.Commands;

import net.excentrix.core.Central;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kill implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("kill")) {
			if (sender.hasPermission("atom.command.kill")) {
				if (args.length == 1) {
					Player targetPlayer = Bukkit.getPlayerExact(args[0]);
					Player target = coreUtils.playerLookup((Player) sender, targetPlayer);
					if (target != null) {
						if (Central.godList.contains(target)) {
							coreUtils.actionForbidden((Player) sender);
						} else {
							target.setHealth(0);
							coreUtils.informativeMessage((Player) sender, "Killed " + coreUtils.getPlayerColor(target) + target.getName());
						}
					} else coreUtils.playerNotFound((Player) sender);
				} else {
					coreUtils.printUsage((Player) sender, "kill", "<player>");
				}
			} else {
				coreUtils.noPerm((Player) sender);
			}
		}
		return true;
	}
}
