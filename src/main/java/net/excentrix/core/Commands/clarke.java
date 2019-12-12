package net.excentrix.core.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class clarke implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        double totalArgs = args.length - 1;
            if (sender.hasPermission("clarke.command.clarke")) {
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("help")) {
                        sender.sendMessage(ChatColor.YELLOW + "==========" + ChatColor.GOLD + " Clarke Commands" + ChatColor.YELLOW + "==========");
                        if (sender.hasPermission("clarke.command.fly"))
                            sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/fly" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Enables/Disables Flight for a Player");
                        if (sender.hasPermission("clarke.command.gamemode"))
                            sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/gm" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Sets a Players Gamemode");
                        if (sender.hasPermission("clarke.command.god"))
                            sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/god" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Enables/Disables God for a Player");
                        if (sender.hasPermission("clarke.command.heal"))
                            sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/heal" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Heals a Player");
                        sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/helpop" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Requests Staff Assistance");
                        if (sender.hasPermission("clarke.command.kick"))
                            sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/kick" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Kicks a Player");
                        if (sender.hasPermission("clarke.command.kill"))
                            sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/kill" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Kills a Player");
                        sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/report" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Reports a Player");
                        if (sender.hasPermission("clarke.command.staffchat"))
                            sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/sc" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Communicate in StaffChat");
                        if (sender.hasPermission("clarke.command.tp"))
                            sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/tp" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Teleport to a Player");
                        if (sender.hasPermission("clarke.command.weather"))
                            sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/weather" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Changes the weather");
                        if (sender.hasPermission("clarke.command.edit"))
                            sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/edit" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Changes things");
                        if (sender.hasPermission("clarke.command.freeze"))
                            sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/freeze" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Halts a players movements");
                        if (sender.hasPermission("clarke.command.smite"))
                            sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/smite" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Summons a lightning bolt");
                        if (args[(int) totalArgs].equalsIgnoreCase("-i"))
                            sender.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/setserver" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Sets the server name." + ChatColor.RED + " [Internal]");
                    } else {
                        sender.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/clarke " + ChatColor.WHITE + "<help>");
                    }
                } else
                    sender.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/clarke " + ChatColor.WHITE + "<help>");
        }
        return true;
    }
}
