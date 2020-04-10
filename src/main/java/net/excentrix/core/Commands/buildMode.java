package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class buildMode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(sender.hasPermission("clarke.command.build")))
            return true;
        if (Core.buildDenied.contains(player)) {
            staffUtils.informativeMessage(player, "&aYour build mode has been turned on.");
            Core.buildDenied.remove(player);
        } else {
            staffUtils.informativeMessage(player, "&cYour build mode has been turned off.");
            Core.buildDenied.add(player);
        }
        return true;
    }
}
