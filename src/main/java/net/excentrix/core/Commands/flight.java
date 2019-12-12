package net.excentrix.core.Commands;

import net.excentrix.core.utils.staff_utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class flight implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("clarke.command.fly")) {
                if (command.getName().equalsIgnoreCase("fly")) {
                    if (args.length > 0 && args.length != 1) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            if (args[1].equalsIgnoreCase("on")) {
                                sender.sendMessage(ChatColor.GREEN + "You turned flight on for " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + "!");
                                if (!target.isOnGround() || !target.isSwimming()) {
                                    target.setAllowFlight(true);
                                    target.setFlying(true);
                                } else target.setAllowFlight(true);
                            } else if (args[1].equalsIgnoreCase("off")) {
                                sender.sendMessage(ChatColor.GREEN + "You turned flight off for " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + "!");
                                target.setFlying(false);
                                target.setAllowFlight(false);
                            }
                        } else staff_utils.playerNotFound((Player) sender);
                        if (args[0].equalsIgnoreCase("on")) {
                            sender.sendMessage(ChatColor.GREEN + "You turned flight on your flight!");
                            if (!((Player) sender).isOnGround() || !((Player) sender).isSwimming()) {
                                ((Player) sender).setAllowFlight(true);
                                ((Player) sender).setFlying(true);
                            }
                            ((Player) sender).setAllowFlight(true);
                        } else if (args[0].equalsIgnoreCase("off")) {
                            sender.sendMessage(ChatColor.GREEN + "You turned flight off your flight!");
                            ((Player) sender).setFlying(false);
                            ((Player) sender).setAllowFlight(false);
                        }
                    } else if (args.length == 0) {
                        if (((Player) sender).getAllowFlight()) {
                            sender.sendMessage(ChatColor.GREEN + "You turned flight off your flight!");
                            ((Player) sender).setAllowFlight(false);
                            ((Player) sender).setFlying(false);
                        } else {
                            if (!((Player) sender).isOnGround() || !((Player) sender).isSwimming()) {
                                ((Player) sender).setAllowFlight(true);
                                ((Player) sender).setFlying(true);
                            } else ((Player) sender).setAllowFlight(true);
                            sender.sendMessage(ChatColor.GREEN + "You turned flight on your flight!");
                        }
                    } else
                        staff_utils.printUsage((Player) sender, "fly", "[player] <mode>");
                }
            } else
                staff_utils.noPerm((Player) sender);
        } else {
            sender.sendMessage(ChatColor.RED + "You " + ChatColor.UNDERLINE + "MUST" + ChatColor.RESET + "" + ChatColor.RED + " be a player to execute this command!");
        }
        return true;
    }
}
