package net.excentrix.core.Commands;

import net.excentrix.core.utils.staff_utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("DuplicatedCode")
public class gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("gamemode") || command.getName().equalsIgnoreCase("gm")) {
            if (sender.hasPermission("clarke.command.gamemode")) {
                if (args.length == 2) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (target != null) {
                        if (args[0].equalsIgnoreCase("C") || args[0].equalsIgnoreCase("CREATIVE")) {
                            target.setGameMode(GameMode.CREATIVE);
                            sender.sendMessage(ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + " is now in " + target.getGameMode() + ".");
                            staff_utils.scNotif(((Player) sender).getDisplayName(), "Set " + ChatColor.GOLD + target.getDisplayName() + ChatColor.YELLOW + "'s gamemode to " + ChatColor.GOLD + target.getGameMode());
                        } else if (args[0].equalsIgnoreCase("S") || args[0].equalsIgnoreCase("SURVIVAL")) {
                            target.setGameMode(GameMode.SURVIVAL);
                            sender.sendMessage(ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + " is now in " + target.getGameMode() + ".");
                            staff_utils.scNotif(((Player) sender).getDisplayName(), "Set " + ChatColor.GOLD + target.getDisplayName() + ChatColor.YELLOW + "'s gamemode to " + ChatColor.GOLD + target.getGameMode());
                        } else if (args[0].equalsIgnoreCase("SP") || args[0].equalsIgnoreCase("SPECTATOR")) {
                            target.setGameMode(GameMode.SPECTATOR);
                            sender.sendMessage(ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + " is now in " + target.getGameMode() + ".");
                            staff_utils.scNotif(((Player) sender).getDisplayName(), "Set " + ChatColor.GOLD + target.getDisplayName() + ChatColor.YELLOW + "'s gamemode to " + ChatColor.GOLD + target.getGameMode());
                        } else if (args[0].equalsIgnoreCase("A") || args[0].equalsIgnoreCase("ADVENTURE")) {
                            target.setGameMode(GameMode.ADVENTURE);
                            sender.sendMessage(ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + " is now in " + target.getGameMode() + ".");
                            staff_utils.scNotif(((Player) sender).getDisplayName(), "Set " + ChatColor.GOLD + target.getDisplayName() + ChatColor.YELLOW + "'s gamemode to " + ChatColor.GOLD + target.getGameMode());
                        } else {
                            sender.sendMessage(ChatColor.RED + "There is no gamemode by the name of " + args[0].toUpperCase());
                        }
                    } else {
                        staff_utils.playerNotFound((Player) sender);
                    }
                } else if (args.length == 1 && !(sender instanceof ConsoleCommandSender)) {
                    if (args[0].equalsIgnoreCase("C") || args[0].equalsIgnoreCase("CREATIVE")) {
                        ((Player) sender).setGameMode(GameMode.CREATIVE);
                        sender.sendMessage(ChatColor.GREEN + "You are now in " + ((Player) sender).getGameMode() + ".");
                        staff_utils.scNotif(((Player) sender).getDisplayName(), "Set own gamemode to " + ChatColor.GOLD + ((Player) sender).getGameMode());
                    } else if (args[0].equalsIgnoreCase("S") || args[0].equalsIgnoreCase("SURVIVAL")) {
                        ((Player) sender).setGameMode(GameMode.SURVIVAL);
                        sender.sendMessage(ChatColor.GREEN + "You are now in " + ((Player) sender).getGameMode() + ".");
                        staff_utils.scNotif(((Player) sender).getDisplayName(), "Set own gamemode to " + ChatColor.GOLD + ((Player) sender).getGameMode());
                    } else if (args[0].equalsIgnoreCase("SP") || args[0].equalsIgnoreCase("SPECTATOR")) {
                        ((Player) sender).setGameMode(GameMode.SPECTATOR);
                        sender.sendMessage(ChatColor.GREEN + "You are now in " + ((Player) sender).getGameMode() + ".");
                        staff_utils.scNotif(((Player) sender).getDisplayName(), "Set own gamemode to " + ChatColor.GOLD + ((Player) sender).getGameMode());
                    } else if (args[0].equalsIgnoreCase("A") || args[0].equalsIgnoreCase("ADVENTURE")) {
                        ((Player) sender).setGameMode(GameMode.ADVENTURE);
                        sender.sendMessage(ChatColor.GREEN + "You are now in " + ((Player) sender).getGameMode() + ".");
                        staff_utils.scNotif(((Player) sender).getDisplayName(), "Set own gamemode to " + ChatColor.GOLD + ((Player) sender).getGameMode());
                    } else {
                        sender.sendMessage(ChatColor.RED + "There is no gamemode by the name of " + args[0].toUpperCase());
                    }
                } else {
                    staff_utils.printUsage((Player) sender, "gamemode", "<gamemode> [player]");
                }
            }
        }
        return true;
    }
}
