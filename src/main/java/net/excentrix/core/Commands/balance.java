package net.excentrix.core.Commands;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class balance implements CommandExecutor {
	Economy econ = CentralHandler.getEcon();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			try {
				if (args.length == 1) {
					Player targetPlayer = Bukkit.getPlayerExact(args[0]);
					Player target = coreUtils.playerLookup((Player) sender, targetPlayer);
					if (target != null) {
						coreUtils.informativeMessage((Player) sender, coreUtils.getPlayerColor(target) + target.getName() + "&a has a balance of &e" + econ.format(econ.getBalance(target)));
					} else coreUtils.playerNotFound((Player) sender);
				} else {
					coreUtils.informativeMessage((Player) sender, "&aYou have a balance of &e" + econ.format(econ.getBalance(player)));
				}
			} catch (NullPointerException e) {
                coreUtils.errorMessage((Player) sender, "An error occurred whilst processing this command, likely a Economy Plugin is not installed, and thus no economy is present.");
			}
		}
		return true;
	}
}
