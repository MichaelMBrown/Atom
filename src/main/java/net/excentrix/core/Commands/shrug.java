package net.excentrix.core.Commands;

import net.excentrix.core.utils.coreUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class shrug implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (coreUtils.getRank(sender.getName()).equalsIgnoreCase("owner")) {
			Player player = (Player) sender;
			player.chat(ChatColor.translateAlternateColorCodes('&', "&6¯\\_(ツ)_/¯"));
		}
		return true;
	}
}
