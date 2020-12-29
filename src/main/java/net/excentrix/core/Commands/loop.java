package net.excentrix.core.Commands;

import net.excentrix.core.Central;
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
		Plugin plugin = Central.getPlugin(Central.class);
		if (coreUtils.getRankInteger(commandSender.getName())>=3){
			String command = "";
			if (strings.length > 1){
				try {
					int iterations = Integer.parseInt(strings[0]);
					for (int i = 1; i < strings.length; i++) {
						command = command + strings[i] + " ";
					}
					for (int i = 0; i < iterations; ++i){
						String finalCommand = command;
						Bukkit.getScheduler().runTaskLater(plugin, () -> {
							Bukkit.dispatchCommand(commandSender, finalCommand);
						}, 5L);
					}
				}catch (NumberFormatException exception){
					coreUtils.errorMessage((Player) commandSender,strings[0]+" is not a number.");
				}
			}else commandSender.sendMessage("/loop <iterations> <command>");
		}else coreUtils.errorMessage((Player) commandSender,"You must be Admin or higher to use this command!");
		return true;
	}
}
