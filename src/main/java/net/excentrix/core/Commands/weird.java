package net.excentrix.core.Commands;

import net.excentrix.core.utils.coreUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class weird implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (coreUtils.getRankInteger(commandSender.getName()) >= 3) {
			if (strings.length != 2) {
				coreUtils.printUsage((Player) commandSender, "weird", "<player> <packet>");
			} else {
				coreUtils.errorMessage((Player) commandSender,"This command is disabled due to compatibility issues.");
//				Player targetPlayer = Bukkit.getPlayerExact(strings[0]);
//				Player target = coreUtils.playerLookup((Player) commandSender, targetPlayer);
//				String args = strings[1];
//				String[] packets = args.split(",");
//
//				commandSender.sendMessage((packets[0]) + " + " + (packets[1]));
//				BukkitCommand.broadcastCommandMessage(commandSender, ChatColor.translateAlternateColorCodes('&', "&esent the packets &f" + packets[0] + "," + packets[1] + "&e to " + target.getName()), false);
//				coreUtils.informativeMessage((Player) commandSender, "Weirded " + coreUtils.getPlayerColor(targetPlayer) + target.getName() + "&a with " + Arrays.toString(packets));
			}
		} else coreUtils.errorMessage((Player) commandSender, "You must be Admin or higher to use this command!");
		return true;
	}
}
