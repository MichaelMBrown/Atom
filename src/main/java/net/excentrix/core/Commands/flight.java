package net.excentrix.core.Commands;

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
            if (sender.hasPermission("excentrix.command.fly")) {
                if (command.getName().equalsIgnoreCase("fly")) {
                    if (args.length > 0 && args.length != 1) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            if (args[1].equalsIgnoreCase("on")) {
                                sender.sendMessage(ChatColor.GREEN + "You turned flight on for " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + "!");
                                if (!target.isOnGround() || !target.isSwimming()) {
                                    ((Player) sender).setAllowFlight(true);
                                    target.setFlying(true);
                                } else target.setAllowFlight(true);
                            } else if (args[1].equalsIgnoreCase("off")) {
                                sender.sendMessage(ChatColor.GREEN + "You turned flight off for " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + "!");
                                target.setFlying(false);
                                target.setAllowFlight(false);
                            }
                        }
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
                    } else sender.sendMessage(ChatColor.RED + "Usage /fly [player] <mode>");
                }
            } else
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
        } else {
            sender.sendMessage(ChatColor.RED + "You " + ChatColor.UNDERLINE + "MUST" + ChatColor.RESET + "" + ChatColor.RED + " be a player to execute this command!");
        }
        return true;
    }
}
