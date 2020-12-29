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

public class watchDogUnban implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (commandSender instanceof ConsoleCommandSender || coreUtils.getRankInteger(commandSender.getName()) >= 3) {
			if (strings.length == 1) {
				OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(strings[0]);
				OfflinePlayer targetPlayer;
				if (offlinePlayer.hasPlayedBefore()) {
					targetPlayer = Bukkit.getOfflinePlayer(offlinePlayer.getUniqueId());
				} else targetPlayer = null;
				if (targetPlayer != null) {
					BanList banList = Bukkit.getBanList(BanList.Type.NAME);
					if (commandSender instanceof ConsoleCommandSender)
						coreUtils.notifyStaff("watchdog", "unbanned " + targetPlayer.getName() + ", they were originally banned by " + banList.getBanEntry(targetPlayer.getName()).getSource());
					else
						coreUtils.notifyStaff("none", commandSender.getName() + " unbanned " + targetPlayer.getName() + ", they were originally banned by " + banList.getBanEntry(targetPlayer.getName()).getSource());
					banList.pardon(targetPlayer.getName());
				} else coreUtils.errorMessage((Player) commandSender, "That ban does not exist in the System.");
			} else coreUtils.printUsage((Player) commandSender, "pardon", "<player>");
		} else coreUtils.errorMessage((Player) commandSender, "You must be Admin or higher to use this command!");
		return true;
	}
}
