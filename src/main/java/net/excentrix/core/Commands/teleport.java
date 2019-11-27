package net.excentrix.core.Commands;

import net.excentrix.core.utils.staff_utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
                            staff_utils.scNotif(sender.getName(), "Teleported to " + ChatColor.GOLD + target.getDisplayName());
                        } else {
                            sender.sendMessage(ChatColor.RED + "There is no player by that name connected to this server!");
                        }
                    } else if (args.length == 2) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        Player target2 = Bukkit.getPlayerExact(args[1]);
                        if (target != null && target2 != null) {
                            Location toTarget = target2.getLocation();
                            target.teleport(toTarget);
                            staff_utils.scNotif(((Player) sender).getDisplayName(), "Teleported " + ChatColor.GOLD + target.getDisplayName() + ChatColor.YELLOW + " to " + ChatColor.GOLD + target2.getDisplayName());
                        } else {
                            sender.sendMessage(ChatColor.RED + "There is no player by that name connected to this server!");
                        }
                    } else {
                        sender.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/tp" + ChatColor.WHITE + "<player> [player]");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
                }
            }
        }
        return true;
    }
}
