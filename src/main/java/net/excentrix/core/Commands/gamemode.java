package net.excentrix.core.Commands;

import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
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
                        if (args[0].equalsIgnoreCase("C") || args[0].equalsIgnoreCase("CREATIVE") || args[0].equals("1")) {
                            target.setGameMode(GameMode.CREATIVE);
                            sender.sendMessage(ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " is now in " + target.getGameMode() + ".");
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "Set " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + "'s gamemode to " + ChatColor.GOLD + target.getGameMode(), false);
                        } else if (args[0].equalsIgnoreCase("S") || args[0].equalsIgnoreCase("SURVIVAL") || args[0].equals("0")) {
                            target.setGameMode(GameMode.SURVIVAL);
                            sender.sendMessage(ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " is now in " + target.getGameMode() + ".");
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "Set " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + "'s gamemode to " + ChatColor.GOLD + target.getGameMode(), false);
                        } else if (args[0].equalsIgnoreCase("SP") || args[0].equalsIgnoreCase("SPECTATOR") || args[0].equals("3")) {
                            target.setGameMode(GameMode.SPECTATOR);
                            sender.sendMessage(ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " is now in " + target.getGameMode() + ".");
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "Set " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + "'s gamemode to " + ChatColor.GOLD + target.getGameMode(), false);
                        } else if (args[0].equalsIgnoreCase("A") || args[0].equalsIgnoreCase("ADVENTURE") || args[0].equals("2")) {
                            target.setGameMode(GameMode.ADVENTURE);
                            sender.sendMessage(ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " is now in " + target.getGameMode() + ".");
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "Set " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + "'s gamemode to " + ChatColor.GOLD + target.getGameMode(), false);
                        } else {
                            sender.sendMessage(ChatColor.RED + "There is no gamemode by the name of " + args[0].toUpperCase());
                        }
                    } else {
                        staffUtils.playerNotFound((Player) sender);
                    }
                } else if (args.length == 1 && !(sender instanceof ConsoleCommandSender)) {
                    if (args[0].equalsIgnoreCase("C") || args[0].equalsIgnoreCase("CREATIVE") || args[0].equals("1")) {
                        ((Player) sender).setGameMode(GameMode.CREATIVE);
                        sender.sendMessage(ChatColor.GREEN + "You are now in " + ((Player) sender).getGameMode() + ".");
                        BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "Set own gamemode to " + ChatColor.GOLD + ((Player) sender).getGameMode(), false);
                    } else if (args[0].equalsIgnoreCase("S") || args[0].equalsIgnoreCase("SURVIVAL") || args[0].equals("0")) {
                        ((Player) sender).setGameMode(GameMode.SURVIVAL);
                        sender.sendMessage(ChatColor.GREEN + "You are now in " + ((Player) sender).getGameMode() + ".");
                        BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "Set own gamemode to " + ChatColor.GOLD + ((Player) sender).getGameMode(), false);
                    } else if (args[0].equalsIgnoreCase("SP") || args[0].equalsIgnoreCase("SPECTATOR") || args[0].equals("3")) {
                        ((Player) sender).setGameMode(GameMode.SPECTATOR);
                        sender.sendMessage(ChatColor.GREEN + "You are now in " + ((Player) sender).getGameMode() + ".");
                        BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "Set own gamemode to " + ChatColor.GOLD + ((Player) sender).getGameMode(), false);
                    } else if (args[0].equalsIgnoreCase("A") || args[0].equalsIgnoreCase("ADVENTURE") || args[0].equals("2")) {
                        ((Player) sender).setGameMode(GameMode.ADVENTURE);
                        sender.sendMessage(ChatColor.GREEN + "You are now in " + ((Player) sender).getGameMode() + ".");
                        BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "Set own gamemode to " + ChatColor.GOLD + ((Player) sender).getGameMode(), false);
                    } else {
                        sender.sendMessage(ChatColor.RED + "There is no gamemode by the name of " + args[0].toUpperCase());
                    }
                } else {
                    staffUtils.printUsage((Player) sender, "gamemode", "<gamemode> [player]");
                }
            }
        }
        return true;
    }
}
