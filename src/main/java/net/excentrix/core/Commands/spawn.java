package net.excentrix.core.Commands;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawn implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (!(CentralHandler.spawn == null)) {
			if (!CentralHandler.spawn.isChunkLoaded())
				CentralHandler.spawn.getChunk().load();
			player.teleport(CentralHandler.spawn);
			coreUtils.informativeMessage(player, "You have been teleported to &eSpawn&a!");
		} else coreUtils.errorMessage(player, "Sorry, the location:&a spawn&c is undefined");
		return true;
	}
}
