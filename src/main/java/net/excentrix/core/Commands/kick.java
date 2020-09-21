package net.excentrix.core.Commands;


import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Array;

//TODO: Deprecate this command for later archival.

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
            if (sender.hasPermission("atom.command.kick")) {
                if (command.getName().equalsIgnoreCase("kick")) {
                    if (args.length > 0) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            if (args.length > 1) {
                                for (int i = 1; i < args.length; i++) {
                                    reason = reason + args[i] + " ";
                                }
                                staffUtils.scNotify(sender.getName(), "kicked player " + ChatColor.GOLD + Array.get(args, 0) + ChatColor.WHITE + " with reason: " + ChatColor.WHITE + reason);
                                target.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&cYou've been kicked for: \n&r" + reason));
                                //plugin.getLogger().info(ChatColor.GREEN + sender.getName() + ChatColor.YELLOW + " kicked player " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " with reason " + ChatColor.WHITE + reason);
                            } else if (args.length == 1) {
                                staffUtils.errorMessage((Player) sender, "You need to provide a reason for this kick.");
                                /*reason = "You have been kicked by " + sender.getName();
                                staffUtils.scNotif(sender.getName(), "kicked player " + ChatColor.GOLD + Array.get(args, 0) + ChatColor.GRAY + " with reason: " + ChatColor.WHITE + reason);
                                target.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
                                plugin.getLogger().info(ChatColor.GREEN + sender.getName() + ChatColor.YELLOW + " kicked player " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " with reason " + reason);*/
                            }
                        } else
                            staffUtils.playerNotFound((Player) sender);
                    } else staffUtils.printUsage((Player) sender, "kick", "<player> <reason>");
                }
            } else {
                staffUtils.noPerm((Player) sender);
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
                            staffUtils.scNotify(ChatColor.RED + "Console", "kicked player " + ChatColor.GOLD + Array.get(args, 0) + ChatColor.WHITE + " with reason: " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', reason));
                            target.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
                            //plugin.getLogger().info(ChatColor.GREEN + sender.getName() + ChatColor.YELLOW + " kicked player " + ChatColor.GOLD + target.getName() + ChatColor.GRAY + " with reason " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', reason));
                        }
                        if (args.length == 1) {
                            reason = "You have been kicked by " + ChatColor.RED + "Console";
                            staffUtils.scNotify("Console", "kicked player " + ChatColor.GOLD + Array.get(args, 0) + ChatColor.WHITE + " with reason: " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', reason));
                            target.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
                            //plugin.getLogger().info(ChatColor.GREEN + sender.getName() + ChatColor.YELLOW + " kicked player " + ChatColor.GOLD + target.getName() + ChatColor.GRAY + " with reason " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', reason));
                        }
                    } else {
                        staffUtils.playerNotFound((Player) sender);
                    }
                } else staffUtils.printUsage((Player) sender, "kick", "<player> [reason]");
            }
        }
        return true;
    }

}