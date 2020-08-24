package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class teleport implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("tp")) {
                if (sender.hasPermission("atom.command.tp")) {
                    if (args.length == 1) {
                        Player targetPlayer = Bukkit.getPlayerExact(args[0]);
                        Player target = staffUtils.findPlayer((Player) sender, targetPlayer);
                        if (target != null) {
                            Location targetLoc = target.getLocation();
                            ((Player) sender).teleport(targetLoc);
                            staffUtils.informativeMessage((Player) sender, "You have teleported to " + Core.playerColour + target.getName());
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "teleported to " + ChatColor.GOLD + target.getName(), false);
                        } else {
                            staffUtils.playerNotFound((Player) sender);
                        }
                    } else if (args.length == 2) {
                        Player targetLookup = Bukkit.getPlayerExact(args[0]);
                        Player target = staffUtils.findPlayer((Player) sender, targetLookup);
                        Player targetLookup2 = Bukkit.getPlayerExact(args[1]);
                        Player target2 = staffUtils.findPlayer((Player) sender, targetLookup2);
                        if (target != null && target2 != null) {
                            Location toTarget = target2.getLocation();
                            target.teleport(toTarget);
                            staffUtils.informativeMessage((Player) sender, "You have teleported to " + Core.playerColour + target.getName() + "&7 to " + Core.playerColour + target2.getName());
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "teleported " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " to " + ChatColor.GOLD + target2.getName(), false);
                        } else {
                            staffUtils.playerNotFound((Player) sender);
                        }
                    } else {
                        staffUtils.printUsage((Player) sender, "tp", "<player> [player]");
                    }
                } else {
                    staffUtils.noPerm((Player) sender);
                }
            }
        }
        return true;
    }
}
