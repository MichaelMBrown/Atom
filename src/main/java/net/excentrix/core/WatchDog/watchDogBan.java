package net.excentrix.core.WatchDog;

import net.excentrix.core.utils.coreUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class watchDogBan implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (commandSender instanceof ConsoleCommandSender || coreUtils.getRankInteger(commandSender.getName()) >= 2) {
			String reason = "Cheating through the Unfair use of Modifications";
			if (strings.length == 1) {
				OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(strings[0]);
				OfflinePlayer targetPlayer;
				if (offlinePlayer.hasPlayedBefore()) {
					targetPlayer = Bukkit.getOfflinePlayer(offlinePlayer.getUniqueId());
				} else targetPlayer = null;
				if (targetPlayer != null) {
					targetPlayer.banPlayer(reason, null, commandSender.getName(), true);
					if (commandSender instanceof ConsoleCommandSender)
						coreUtils.notifyStaff("watchdog", "banned player " + targetPlayer.getName() + " for " + reason + ".");
					else
						coreUtils.notifyStaff("none", commandSender.getName() + " banned player " + targetPlayer.getName() + " for " + reason + ".");
				} else {
					coreUtils.playerDoesntExist((Player) commandSender);
				}
			} else coreUtils.printUsage((Player) commandSender, "ban", "<player>");
		} else coreUtils.errorMessage((Player) commandSender, "You must be a Mod or higher to use this command!");
		return true;
	}
}
