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

@SuppressWarnings("DuplicatedCode")
public class kick implements CommandExecutor {
    Plugin plugin = Core.getPlugin(Core.class);
    private CommandSender sender;
    private Command command;
    private String[] args;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Plugin plugin = Core.getPlugin(Core.class);
        String reason = "";
        if ((sender instanceof Player)) {
            if (sender.hasPermission("clarke.command.kick")) {
                if (command.getName().equalsIgnoreCase("kick")) {
                    if (args.length > 0) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            if (args.length > 1) {
                                for (int i = 1; i < args.length; i++) {
                                    reason = reason + args[i] + " ";
                                }
                                staff_utils.scNotif(sender.getName(), "kicked player " + ChatColor.GOLD + Array.get(args, 0) + ChatColor.YELLOW + " with reason: " + ChatColor.WHITE + reason);
                                target.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
                                plugin.getLogger().info(ChatColor.GREEN + sender.getName() + ChatColor.YELLOW + " kicked player " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " with reason " + ChatColor.WHITE + reason);
                            } else if (args.length == 1) {
                                reason = "You have been kicked by " + sender.getName();
                                staff_utils.scNotif(sender.getName(), "kicked player " + ChatColor.GOLD + Array.get(args, 0) + ChatColor.YELLOW + " with reason: " + ChatColor.WHITE + reason);
                                target.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
                                plugin.getLogger().info(ChatColor.GREEN + sender.getName() + ChatColor.YELLOW + " kicked player " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " with reason " + reason);
                            }
                        } else
                            staff_utils.playerNotFound((Player) sender);
                    } else staff_utils.printUsage((Player) sender, "kick", "[player] <reason>");
                }
            } else {
                staff_utils.noPerm((Player) sender);
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
                            staff_utils.scNotif(ChatColor.RED + "Console", "kicked player " + ChatColor.GOLD + Array.get(args, 0) + ChatColor.YELLOW + " with reason: " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', reason));
                            target.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
                            plugin.getLogger().info(ChatColor.GREEN + sender.getName() + ChatColor.YELLOW + " kicked player " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " with reason " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', reason));
                        }
                        if (args.length == 1) {
                            reason = "You have been kicked by " + ChatColor.RED + "Console";
                            staff_utils.scNotif("Console", "kicked player " + ChatColor.GOLD + Array.get(args, 0) + ChatColor.YELLOW + " with reason: " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', reason));
                            target.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
                            plugin.getLogger().info(ChatColor.GREEN + sender.getName() + ChatColor.YELLOW + " kicked player " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " with reason " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', reason));
                        }
                    } else {
                        staff_utils.playerNotFound((Player) sender);
                    }
                } else staff_utils.printUsage((Player) sender, "kick", "[player] <reason>");
            }
        }
        return true;
    }

}