package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class edit implements CommandExecutor {
    private static Plugin plugin = Core.getPlugin(Core.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player theSender = (Player) sender;
        if (command.getName().equalsIgnoreCase("edit")) {
            if (sender.hasPermission("clarke.command.edit")) {
                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("boolean") || args[0].equalsIgnoreCase("bool")) {
                        Boolean newBool = null;
                        if (args[1].equalsIgnoreCase("mobAI")) {
                            if (args[2].equalsIgnoreCase("true")) {
                                staffUtils.scNotif(theSender.getName(), "Set the value of " + ChatColor.GOLD + args[1].toUpperCase() + ChatColor.GRAY + " to " + ChatColor.GOLD + args[2]);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&a&l✩&8&l] &7Updated the value of " + ChatColor.YELLOW + args[1].toUpperCase() + ChatColor.GRAY + " to " + ChatColor.YELLOW + args[2]));
                                plugin.getConfig().set("mobAI", true);
                                plugin.saveConfig();
                                plugin.reloadConfig();
                                for (World world : Bukkit.getWorlds()) {
                                    for (Entity entity : world.getEntities()) {
                                        if (!(entity instanceof Player)) {
                                            if (entity instanceof Creature) {
                                                ((Creature) entity).setAI(true);
                                            }
                                        }
                                    }
                                }
                            } else if (args[2].equalsIgnoreCase("false")) {
                                staffUtils.scNotif(theSender.getName(), "Set the value of " + ChatColor.GOLD + args[1].toUpperCase() + ChatColor.GRAY + " to " + ChatColor.GOLD + args[2]);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&a&l✩&8&l] &7Updated the value of " + ChatColor.YELLOW + args[1].toUpperCase() + ChatColor.GRAY + " to " + ChatColor.YELLOW + args[2]));
                                plugin.getConfig().set("mobAI", false);
                                plugin.saveConfig();
                                plugin.reloadConfig();
                                for (World world : Bukkit.getWorlds()) {
                                    for (Entity entity : world.getEntities()) {
                                        if (!(entity instanceof Player)) {
                                            if (entity instanceof Creature) {
                                                ((Creature) entity).setAI(false);
                                            }
                                        }
                                    }
                                }
                            } else
                                sender.sendMessage(ChatColor.RED + "Unknown data value of: " + args[2].toUpperCase());
                        } else if (args[1].equalsIgnoreCase("disableDrowned")) {
                            if (args[2].equalsIgnoreCase("true")) {
                                staffUtils.scNotif(theSender.getName(), "Set the value of " + ChatColor.GOLD + args[1].toUpperCase() + ChatColor.GRAY + " to " + ChatColor.GOLD + args[2]);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&a&l✩&8&l] &7Updated the value of " + ChatColor.YELLOW + args[1].toUpperCase() + ChatColor.GRAY + " to " + ChatColor.YELLOW + args[2]));
                                plugin.getConfig().set("disableDrowned", true);
                                plugin.saveConfig();
                                plugin.reloadConfig();
                            } else if (args[2].equalsIgnoreCase("false")) {
                                staffUtils.scNotif(theSender.getName(), "Set the value of " + ChatColor.GOLD + args[1].toUpperCase() + ChatColor.GRAY + " to " + ChatColor.GOLD + args[2]);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&a&l✩&8&l] &7Updated the value of " + ChatColor.YELLOW + args[1].toUpperCase() + ChatColor.GRAY + " to " + ChatColor.YELLOW + args[2]));
                                plugin.getConfig().set("disableDrowned", false);
                                plugin.saveConfig();
                                plugin.reloadConfig();
                            } else
                                sender.sendMessage(ChatColor.RED + "Unknown data value of: " + args[2].toUpperCase());
                        } else staffUtils.printUsage((Player) sender, "edit", "<type> <data> <value>");
                    } else staffUtils.printUsage((Player) sender, "edit", "<type> <data> <value>");
                }
            }
        }
        return true;
    }
}
