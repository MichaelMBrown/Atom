package net.excentrix.core.Commands;

import net.excentrix.core.utils.staff_utils;
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
                if (sender.hasPermission("clarke.command.tp")) {
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            Location targetLoc = target.getLocation();
                            ((Player) sender).teleport(targetLoc);
                            sender.sendMessage(ChatColor.GREEN + "Teleported to " + ChatColor.YELLOW + target.getDisplayName());
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "Teleported to " + ChatColor.GOLD + target.getDisplayName(), false);
                        } else {
                            staff_utils.playerNotFound((Player) sender);
                        }
                    } else if (args.length == 2) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        Player target2 = Bukkit.getPlayerExact(args[1]);
                        if (target != null && target2 != null) {
                            Location toTarget = target2.getLocation();
                            target.teleport(toTarget);
                            sender.sendMessage(ChatColor.GREEN + "Teleported " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + " to " + ChatColor.YELLOW + target2.getDisplayName());
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "Teleported " + ChatColor.GOLD + target.getDisplayName() + ChatColor.YELLOW + " to " + ChatColor.GOLD + target2.getDisplayName(), false);
                        } else {
                            staff_utils.playerNotFound((Player) sender);
                        }
                    } else {
                        staff_utils.printUsage((Player) sender, "tp", "<player> [player]");
                    }
                } else {
                    staff_utils.noPerm((Player) sender);
                }
            }
        }
        return true;
    }
}
