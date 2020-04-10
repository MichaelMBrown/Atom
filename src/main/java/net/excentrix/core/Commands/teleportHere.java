package net.excentrix.core.Commands;

import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class teleportHere implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player sender = (Player) commandSender;
        Location toLocation = sender.getLocation();
        if (!commandSender.hasPermission("clarke.command.tphere")) {
            staffUtils.noPerm(sender);
            return true;
        }
        if (strings.length == 1) {
            Player target = Bukkit.getPlayerExact(strings[0]);
            if (target != null) {
                target.teleport(toLocation);
                staffUtils.informativeMessage(sender, "You have teleported &6" + target.getName() + " &7to You");
                BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "teleported to " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " to themself.", false);
            } else staffUtils.playerNotFound(sender);
        } else staffUtils.printUsage(sender, "tphere", "<player>");
        return true;
    }
}
