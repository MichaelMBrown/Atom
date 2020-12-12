package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class balance implements CommandExecutor {
	Economy econ = Core.getEcon();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			try {
				if (args.length == 1) {
					Player targetPlayer = Bukkit.getPlayerExact(args[0]);
					Player target = staffUtils.playerLookup((Player) sender, targetPlayer);
					if (target != null) {
						staffUtils.informativeMessage((Player) sender, staffUtils.getPlayerColor(target) + target.getName() + "&a has a balance of &e" + econ.format(econ.getBalance(target)));
					} else staffUtils.playerNotFound((Player) sender);
				} else {
					staffUtils.informativeMessage((Player) sender, "&aYou have a balance of &e" + econ.format(econ.getBalance(player)));
				}
			} catch (NullPointerException e) {
				staffUtils.errorMessage((Player) sender, "An error occurred whilst processing this command, please contact an administrator to resolve this issue.");
			}
		}
		return true;
	}
}
