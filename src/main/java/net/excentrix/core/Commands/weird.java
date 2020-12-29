package net.excentrix.core.Commands;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class weird implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (coreUtils.getRank(commandSender.getName()).equalsIgnoreCase("admin") || coreUtils.getRank(commandSender.getName()).equalsIgnoreCase("owner")) {
			if (strings.length != 2) {
				coreUtils.printUsage((Player) commandSender, "weird", "<player> <packet>");
			} else {
				Player targetPlayer = Bukkit.getPlayerExact(strings[0]);
				Player target = coreUtils.playerLookup((Player) commandSender, targetPlayer);
				ProtocolManager manager = ProtocolLibrary.getProtocolManager();
				PacketContainer container = manager.createPacket(PacketType.Play.Server.GAME_STATE_CHANGE);
				String args = strings[1];
				String[] packets = args.split(",");
				commandSender.sendMessage(Integer.parseInt(packets[0]) + " + " + Float.valueOf(packets[1]));
				container.getFloat().write(Integer.parseInt(packets[1]), Float.parseFloat(packets[0]));
				try {
					manager.sendServerPacket(target, container);
					BukkitCommand.broadcastCommandMessage(commandSender, ChatColor.translateAlternateColorCodes('&', "&esent the packets &f" + packets[0] + "," + packets[1] + "&e to " + target.getName()), false);
					coreUtils.informativeMessage((Player) commandSender, "Weirded " + coreUtils.getPlayerColor(targetPlayer) + target.getName() + "&a with " + Arrays.toString(packets));
				} catch (InvocationTargetException e) {
					coreUtils.errorMessage((Player) commandSender, "Could not send packets.");
				}
			}
		} else coreUtils.errorMessage((Player) commandSender, "You must be Admin or higher to use this command!");
		return true;
	}
}
