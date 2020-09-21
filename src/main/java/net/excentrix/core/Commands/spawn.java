package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(Core.spawn == null)) {
            if (!Core.spawn.isChunkLoaded())
                Core.spawn.getChunk().load();
            player.teleport(Core.spawn);
            staffUtils.informativeMessage(player, "You have been teleported to &eSpawn&a!");
        } else staffUtils.errorMessage(player, "Sorry, the location: &aspawn&c is undefined");
        return true;
    }
}
