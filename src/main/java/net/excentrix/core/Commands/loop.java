package net.excentrix.core.Commands;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class loop implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender commandSender, Command sentCommand, String s, String[] strings) {
		Plugin plugin = CentralHandler.getPlugin(CentralHandler.class);
		if (coreUtils.getRankInteger(commandSender.getName()) >= 3) {
			String command = "";
			if (strings.length > 1) {
				try {
					int iterations = Integer.parseInt(strings[0]);
					for (int i = 2; i < strings.length; i++) {
						if (i == strings.length - 1) command = command + strings[i];
						else
							command = command + strings[i] + " ";
					}
					long delay = Long.parseLong(strings[1]);
					coreUtils.informativeMessage((Player) commandSender, "Looping '/" + command + "' " + iterations + " times.");

					// Run the first command instantly
					Bukkit.dispatchCommand(commandSender, command);

					// Run the remaining commands after the delay
					for (int i = 1; i < iterations; ++i) {
						String finalCommand = command;
						Bukkit.getScheduler().runTaskLater(plugin, () -> {
							Bukkit.dispatchCommand(commandSender, finalCommand);
						}, delay * (i + 1)); // Delay increases for each iteration
					}
				} catch (NumberFormatException exception) {
					coreUtils.errorMessage((Player) commandSender, "Cannot process that request, retry that command again.");
				}
			} else commandSender.sendMessage("/loop <iterations> <delay> <command>");
		} else coreUtils.errorMessage((Player) commandSender, "You must be Admin or higher to use this command!");
		return true;
	}

}
