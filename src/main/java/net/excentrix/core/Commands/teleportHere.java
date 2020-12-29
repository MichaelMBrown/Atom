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

public class teleportHere implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		Player sender = (Player) commandSender;
		Location toLocation = sender.getLocation();
		if (!commandSender.hasPermission("atom.command.tp")) {
			coreUtils.noPerm(sender);
			return true;
		}
		if (strings.length == 1) {
			Player targetPlayer = Bukkit.getPlayerExact(strings[0]);
			Player target = coreUtils.playerLookup(sender, targetPlayer);
			if (target != null) {
				target.teleport(toLocation);
				coreUtils.informativeMessage(sender, "You have teleported " + coreUtils.getPlayerColor(target) + target.getName() + " &ato " + coreUtils.getPlayerColor(sender) + "You");
				BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "teleported to " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " to themself.", false);
			} else coreUtils.playerNotFound(sender);
		} else coreUtils.printUsage(sender, "tphere", "<player>");
		return true;
	}
}
