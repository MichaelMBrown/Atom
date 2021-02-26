package net.excentrix.core.Commands;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class toggleSC implements CommandExecutor {
	private static final Plugin plugin = CentralHandler.getPlugin(CentralHandler.class);
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		Player sender = (Player) commandSender;
		if (!(commandSender.hasPermission("atom.chat.staffchat"))) {
			coreUtils.noPerm((Player) commandSender);
			return true;
		} else {
			if (CentralHandler.scMuted.contains(sender)) {
				coreUtils.informativeMessage(sender, "&aYou've enabled staff messages.");
				CentralHandler.scMuted.remove(sender);
			} else {
				coreUtils.informativeMessage(sender, "&cYou've disabled staff messages.");
				CentralHandler.scMuted.add(sender);
			}
		}
		return true;
	}
}
