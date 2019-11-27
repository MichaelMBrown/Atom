package net.excentrix.core.Commands;

import net.excentrix.core.utils.staff_utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kill implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kill")) {
            if (sender.hasPermission("clarke.command.kill")) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        target.setHealth(0);
                        sender.sendMessage(ChatColor.GREEN + "Killed player " + ChatColor.YELLOW + target.getDisplayName());
                        staff_utils.scNotif(sender.getName(), "Killed player " + ChatColor.GOLD + target.getDisplayName());
                    }
                } else {
                    sender.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/kill " + ChatColor.WHITE + "<player>");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
            }
        }
        return true;
    }
}
