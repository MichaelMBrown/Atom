package net.excentrix.core.Commands;

import net.excentrix.core.Central;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static net.excentrix.core.Central.chatSilenced;
import static net.excentrix.core.Central.playerColour;

public class mutelocalchat implements CommandExecutor {
	private static final Plugin plugin = Central.getPlugin(Central.class);
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (commandSender.hasPermission("atom.command.mutelocalchat")) {
			if (!chatSilenced) {
				coreUtils.broadcastServer("&c[Alert]&e Server chat has been disabled by " + playerColour + commandSender.getName());
				chatSilenced = true;
				plugin.getConfig().set("chat-silenced", chatSilenced);
				plugin.saveConfig();
			} else {
				coreUtils.broadcastServer("&c[Alert]&e Server chat has been enabled by " + playerColour + commandSender.getName());
				chatSilenced = false;
				plugin.getConfig().set("chat-silenced", chatSilenced);
				plugin.saveConfig();
			}
		} else coreUtils.noPerm((Player) commandSender);
		return true;
	}
}
