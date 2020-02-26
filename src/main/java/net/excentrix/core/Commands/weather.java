package net.excentrix.core.Commands;

import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class weather implements CommandExecutor {
    @SuppressWarnings({"DuplicatedCode", "ConstantConditions"})
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("weather")) {
                Player p = (Player) sender;
                if (sender.hasPermission("clarke.command.weather")) {
                    if (args.length == 0) {
                        staffUtils.printUsage((Player) sender, "weather", "<weather> [world]");
                    } else if (args.length == 1) {
                        String currentWorld = p.getWorld().getName();
                        if (args[0].equalsIgnoreCase("clear")) {
                            Bukkit.getWorld(currentWorld).setStorm(false);
                            sender.sendMessage(ChatColor.GREEN + "Set the weather in " + ChatColor.YELLOW + currentWorld + ChatColor.GREEN + " to " + ChatColor.YELLOW + args[0].toUpperCase());
                            staffUtils.scNotif(p.getName(), "Changing the weather in " + ChatColor.GOLD + currentWorld + ChatColor.GRAY + " to " + ChatColor.GOLD + args[0].toLowerCase() + ChatColor.YELLOW);
                        } else if (args[0].equalsIgnoreCase("storm") || args[0].equalsIgnoreCase("rain")) {
                            sender.sendMessage(ChatColor.GREEN + "Set the weather in " + ChatColor.YELLOW + currentWorld + ChatColor.GREEN + " to " + ChatColor.YELLOW + args[0].toUpperCase());
                            staffUtils.scNotif(p.getName(), "Changing the weather in " + ChatColor.GOLD + currentWorld + ChatColor.GRAY + " to " + ChatColor.GOLD + args[0].toLowerCase() + ChatColor.YELLOW);
                            Bukkit.getWorld(currentWorld).setStorm(true);
                        } else sender.sendMessage(ChatColor.RED + "Unknown weather type: " + args[0]);
                    } else if (args.length == 2) {
                        String targetWorld = args[1];
                        if (Bukkit.getWorld(targetWorld) != null) {
                            if (args[0].equalsIgnoreCase("clear")) {
                                Bukkit.getWorld(targetWorld).setStorm(false);
                                sender.sendMessage(ChatColor.GREEN + "Set the weather in " + ChatColor.YELLOW + targetWorld + ChatColor.GREEN + " to " + ChatColor.YELLOW + args[0].toUpperCase());
                                staffUtils.scNotif(p.getName(), "Changing the weather in " + ChatColor.GOLD + targetWorld + ChatColor.GRAY + " to " + ChatColor.GOLD + args[0].toLowerCase() + ChatColor.YELLOW);
                            } else if (args[0].equalsIgnoreCase("storm")) {
                                Bukkit.getWorld(targetWorld).setStorm(true);
                                sender.sendMessage(ChatColor.GREEN + "Set the weather in " + ChatColor.YELLOW + targetWorld + ChatColor.GREEN + " to " + ChatColor.YELLOW + args[0].toUpperCase());
                                staffUtils.scNotif(p.getName(), "Changing the weather in " + ChatColor.GOLD + targetWorld + ChatColor.GRAY + " to " + ChatColor.GOLD + args[0].toLowerCase() + ChatColor.YELLOW);
                            }

                        } else {
                            sender.sendMessage(ChatColor.RED + "That world does not exist! Unable to process any changes.");
                        }
                    } else
                        sender.sendMessage(ChatColor.RED + "To many arguments found. Expected [1-2] Found " + args.length);
                } else {
                    staffUtils.noPerm((Player) sender);
                }
            }
        }
        return true;
    }
}
