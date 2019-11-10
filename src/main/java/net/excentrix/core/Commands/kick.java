package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staff_utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Array;

public class kick implements CommandExecutor {
    Plugin plugin = Core.getPlugin(Core.class);
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String reason = "";
        if ((sender instanceof Player)) {
            if (sender.hasPermission("excentrix.command.kick")) {
                if (command.getName().equalsIgnoreCase("kick")) {
                    if (args.length > 0) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            if (args.length > 1) {
                                for (int i = 1; i < args.length; ++i) {
                                    reason = reason + args[i] + " ";
                                }
                                for (final Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.hasPermission("excentrix.chat.staffchat")) {
                                        //p.sendMessage(ChatColor.WHITE+ "[S] " + ChatColor.DARK_AQUA + "[" + getConfig().getString("server-name") + "] " + ChatColor.GRAY + sender.getName() + ChatColor.GRAY + ":" + ChatColor.WHITE+ " " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
                                        staff_utils.scNotif(ChatColor.YELLOW + ((Player) sender).getDisplayName(), p, "kicked player " + ChatColor.GOLD + Array.get(args, 0) + ChatColor.YELLOW + " with reason: " + ChatColor.WHITE + reason);
                                    }
                                }
                                target.kickPlayer(reason);
                            } else if (args.length == 1) {
                                reason = "You have been kicked by " + ((Player) sender).getDisplayName();
                                for (final Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.hasPermission("excentrix.chat.staffchat")) {
                                        //p.sendMessage(ChatColor.WHITE+ "[S] " + ChatColor.DARK_AQUA + "[" + getConfig().getString("server-name") + "] " + ChatColor.WHITE+ sender.getName() + ChatColor.GRAY + ":" + ChatColor.WHITE+ " " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
                                        staff_utils.scNotif(ChatColor.YELLOW + ((Player) sender).getDisplayName(), p, "kicked player " + ChatColor.GOLD + Array.get(args, 0) + ChatColor.YELLOW + " with reason: " + ChatColor.WHITE + reason);
                                    }
                                }
                                target.kickPlayer(reason);
                            }
                        } else
                            sender.sendMessage(ChatColor.RED + "There is no player by that name connected to this server!");
                    } else sender.sendMessage((ChatColor.RED + "Usage: /kick <user> [reason]"));
                }
            }
        } else if (sender instanceof ConsoleCommandSender) {
            if (command.getName().equalsIgnoreCase("kick")) {
                if (args.length > 0) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        if (args.length > 1) {
                            for (int i = 1; i < args.length; ++i) {
                                reason = reason + args[i] + " ";
                            }
                            for (final Player p : Bukkit.getOnlinePlayers()) {
                                if (p.hasPermission("excentrix.chat.staffchat")) {
                                    //p.sendMessage(ChatColor.WHITE+ "[S] " + ChatColor.DARK_AQUA + "[" + getConfig().getString("server-name") + "] " + ChatColor.WHITE+ sender.getName() + ChatColor.GRAY + ":" + ChatColor.WHITE+ " " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
                                    staff_utils.scNotif(ChatColor.RED + "Console", p, "kicked player " + ChatColor.GOLD + Array.get(args, 0) + ChatColor.YELLOW + " with reason: " + ChatColor.WHITE + reason);
                                }
                            }
                            target.kickPlayer(reason);
                        }
                        if (args.length == 1) {
                            reason = "You have been kicked by " + ChatColor.RED + "Console";
                            for (final Player p : Bukkit.getOnlinePlayers()) {
                                if (p.hasPermission("excentrix.chat.staffchat")) {
                                    //p.sendMessage(ChatColor.WHITE+ "[S] " + ChatColor.DARK_AQUA + "[" + getConfig().getString("server-name") + "] " + ChatColor.WHITE+ sender.getName() + ChatColor.GRAY + ":" + ChatColor.WHITE+ " " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
                                    staff_utils.scNotif("Console", p, "kicked player " + ChatColor.GOLD + Array.get(args, 0) + ChatColor.YELLOW + " with reason: " + ChatColor.WHITE + reason);
                                }
                            }
                            target.kickPlayer(reason);
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "There is no player by that name connected to this server!");
                    }
                } else sender.sendMessage((ChatColor.RED + "Usage: /kick <user> [reason]"));
            }
        }
        return true;
    }

}