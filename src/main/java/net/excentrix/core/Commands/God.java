package net.excentrix.core.Commands;

import net.excentrix.core.utils.staff_utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class God implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("clarke.command.god")) {
                if (command.getName().equalsIgnoreCase("god")) {
                    if (args.length > 0 && args.length != 1) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("true")) {
                                sender.sendMessage(ChatColor.GREEN + "You enabled God Mode for " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + ".");
                                staff_utils.scNotif(((Player) sender).getDisplayName(), "Enabled God Mode for " + ChatColor.GOLD + target.getDisplayName());
                                target.setInvulnerable(true);
                            } else if (args[1].equalsIgnoreCase("off") || args[1].equalsIgnoreCase("false")) {
                                sender.sendMessage(ChatColor.GREEN + "You disabled God Mode for " + ChatColor.GOLD + target.getDisplayName() + ChatColor.GREEN + ".");
                                staff_utils.scNotif(((Player) sender).getDisplayName(), "Disabled God Mode for " + ChatColor.WHITE + target.getDisplayName());
                                target.setInvulnerable(false);
                            }
                        }
                        if (args[0].equalsIgnoreCase("on")) {
                            sender.sendMessage(ChatColor.GREEN + "You turned on your God Mode.");
                            ((Player) sender).setInvulnerable(true);
                        } else if (args[0].equalsIgnoreCase("off")) {
                            sender.sendMessage(ChatColor.GREEN + "You turned off your God Mode.");
                            ((Player) sender).setInvulnerable(false);
                        }
                    } else if (args.length == 0) {
                        if (((Player) sender).isInvulnerable()) {
                            sender.sendMessage(ChatColor.GREEN + "You turned off your God Mode.");
                            ((Player) sender).setInvulnerable(false);
                        } else {
                            sender.sendMessage(ChatColor.GREEN + "You turned on your God Mode.");
                            ((Player) sender).setInvulnerable(true);
                        }
                    } else
                        sender.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/god " + ChatColor.WHITE + "[player] <mode>");
                }
            } else
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
        } else {
            sender.sendMessage(ChatColor.RED + "You " + ChatColor.UNDERLINE + "MUST" + ChatColor.RESET + "" + ChatColor.RED + " be a player to execute this command!");
        }
        return true;
    }
}
