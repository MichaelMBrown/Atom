package net.excentrix.core.Commands;

import net.excentrix.core.Central;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawn implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (!(Central.spawn == null)) {
			if (!Central.spawn.isChunkLoaded())
				Central.spawn.getChunk().load();
			player.teleport(Central.spawn);
			coreUtils.informativeMessage(player, "You have been teleported to &eSpawn&a!");
		} else coreUtils.errorMessage(player, "Sorry, the location: &aspawn&c is undefined");
		return true;
	}
}
