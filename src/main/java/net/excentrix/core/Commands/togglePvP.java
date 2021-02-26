package net.excentrix.core.Commands;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static net.excentrix.core.CentralHandler.globalPVP;

public class togglePvP implements CommandExecutor {
	private static final Plugin plugin = CentralHandler.getPlugin(CentralHandler.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("atom.command.togglePvP")) {
			if (globalPVP) {
				coreUtils.broadcastServer("&c[Alert]&e PVP has been disabled by " + coreUtils.getPlayerColor((Player) sender) + sender.getName());
				globalPVP = false;
			} else {
				coreUtils.broadcastServer("&c[Alert]&e PVP has been enabled by " + coreUtils.getPlayerColor((Player) sender) + sender.getName());
				globalPVP = true;
			}
			plugin.getConfig().set("pvp-enabled", globalPVP);
			plugin.saveConfig();
			plugin.reloadConfig();
		} else coreUtils.noPerm((Player) sender);
		return true;
	}
}
