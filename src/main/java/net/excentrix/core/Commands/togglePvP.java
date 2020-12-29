package net.excentrix.core.Commands;

import net.excentrix.core.Central;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static net.excentrix.core.Central.globalPVP;

public class togglePvP implements CommandExecutor {
	private static final Plugin plugin = Central.getPlugin(Central.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("atom.command.togglePvP")) {
			if (globalPVP) {
				coreUtils.broadcastServer("&c[Alert]&e PVP has been disabled by " + Central.playerColour + sender.getName());
				globalPVP = false;
				plugin.getConfig().set("pvp-enabled", globalPVP);
				plugin.saveConfig();
				plugin.reloadConfig();
				//coreUtils.scNotify("console", ChatColor.YELLOW + commandSender.getName() + ChatColor.GRAY + " has muted the chat.");
			} else {
				coreUtils.broadcastServer("&c[Alert]&e PVP has been enabled by " + Central.playerColour + sender.getName());
				globalPVP = true;
				plugin.getConfig().set("pvp-enabled", globalPVP);
				plugin.saveConfig();
				plugin.reloadConfig();
				//coreUtils.scNotify("console", ChatColor.YELLOW + commandSender.getName() + ChatColor.GRAY + " has unmuted the chat.");
			}
		} else coreUtils.noPerm((Player) sender);
		return true;
	}
}
