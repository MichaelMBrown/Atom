package net.excentrix.core.Commands;

import net.excentrix.core.utils.staff_utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class staffchat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((sender instanceof Player)) {
            if (sender.hasPermission("clarke.chat.staffchat")) {
                if (command.getName().equalsIgnoreCase("sc")) {
                    if (args.length > 0) {
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("clarke.chat.staffchat")) {
                                staff_utils.sendSC(((Player) sender).getDisplayName(), p, args);
                            }
                        }
                    } else {
                        sender.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/sc " + ChatColor.WHITE + "<message>");
                    }
                }
            } else sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
        } else if ((sender instanceof ConsoleCommandSender)) {
            if (command.getName().equalsIgnoreCase("sc")) {
                if (args.length > 0) {
                    for (final Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("clarke.chat.staffchat")) {
                            staff_utils.sendSC_console(p, args);
                        }
                    }
                } else
                    sender.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/sc" + ChatColor.WHITE + "<message>");
            }
        }
        return true;
    }
}
