package net.excentrix.core.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class clarke implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        double totalArgs = args.length - 1;
        if (command.getName().equalsIgnoreCase("clarke")) {
            if (sender.hasPermission("clarke.command.clarke")) {
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("help")) {
                        sender.sendMessage(ChatColor.YELLOW + "==========" + ChatColor.GOLD + " Clarke Commands" + ChatColor.YELLOW + "==========");
                        sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/fly" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Enables/Disables Flight for a Player");
                        sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/gm" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Sets a Players Gamemode");
                        sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/god" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Enables/Disables God for a Player");
                        sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/heal" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Heals a Player");
                        sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/helpop" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Requests Staff Assistance");
                        sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/kick" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Kicks a Player");
                        sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/kill" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Kills a Player");
                        sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/report" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Reports a Player");
                        sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/sc" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Communicate in StaffChat");
                        sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/tp" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Teleport to a Player");
                        if (args[(int) totalArgs].equalsIgnoreCase("-i")) {
                            sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/setserver" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Sets the server name." + ChatColor.RED + " [Internal]");
                        }
                    } else {
                        sender.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/clarke " + ChatColor.WHITE + "<help>");
                    }
                } else
                    sender.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/clarke " + ChatColor.WHITE + "<help>");
            }
        }
        return true;
    }
}
