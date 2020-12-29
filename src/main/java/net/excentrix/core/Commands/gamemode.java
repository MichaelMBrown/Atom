package net.excentrix.core.Commands;

import net.excentrix.core.utils.coreUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class gamemode implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		Player sender = (Player) commandSender;
		if (!commandSender.hasPermission("atom.command.gamemode")) {
			coreUtils.noPerm(sender);
			return true;
		}
		if ((strings.length == 1) && !(sender instanceof ConsoleCommandSender)) {
			try {
				sender.setGameMode(GameMode.valueOf(strings[0].toUpperCase()));
				coreUtils.informativeMessage(sender, coreUtils.getPlayerColor(sender) + sender.getName() + "&a's game mode is now &e" + strings[0]);
				BukkitCommand.broadcastCommandMessage(commandSender, ChatColor.translateAlternateColorCodes('&', "&7&oset own gamemode to &e&o" + strings[0]), false);
			} catch (IllegalArgumentException e) {
				coreUtils.errorMessage(sender, "That gamemode does not exist!");
			}
		} else if ((strings.length == 2) && !(sender instanceof ConsoleCommandSender)) {
			if (!(sender.hasPermission("atom.command.gamemode.others"))) {
				coreUtils.noPerm(sender);
			}
			
			try {
				Player targetPlayer = Bukkit.getPlayerExact(strings[1]);
				Player target = coreUtils.playerLookup(sender, targetPlayer);
				target.setGameMode(GameMode.valueOf(strings[0].toUpperCase()));
				coreUtils.informativeMessage(sender, coreUtils.getPlayerColor(target) + target.getName() + "&a's game mode is now &e" + strings[0]);
				BukkitCommand.broadcastCommandMessage(commandSender, ChatColor.translateAlternateColorCodes('&', "&7&oset " + target.getName() + "'s gamemode to &e&o" + strings[0]), false);
			} catch (NullPointerException e) {
				coreUtils.playerNotFound(sender);
			} catch (IllegalArgumentException e) {
				coreUtils.errorMessage(sender, "That gamemode does not exist!");
			}
		} else if (!(sender instanceof ConsoleCommandSender)) {
			coreUtils.printUsage(sender, command.getName(), "<gamemode> [player]");
		}
		return true;
	}
}
