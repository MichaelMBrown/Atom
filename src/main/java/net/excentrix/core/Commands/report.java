package net.excentrix.core.Commands;

import net.excentrix.core.utils.staff_utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class report implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((sender instanceof Player)) {
            if (command.getName().equalsIgnoreCase("report")) {
                    if ((args.length == 2)) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            if (args[1].equalsIgnoreCase("KillAura") || args[1].equalsIgnoreCase("ka")) {
                                sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + " for " + ChatColor.RED + "KillAura" + ChatColor.GREEN + "!");
                                notifyStaff(((Player) sender).getDisplayName(), target.getDisplayName(), args);
                            } else if (args[1].equalsIgnoreCase("aimbot")) {
                                sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + " for " + ChatColor.RED + "Aimbot" + ChatColor.GREEN + "!");
                                notifyStaff(((Player) sender).getDisplayName(), target.getDisplayName(), args);
                            } else if (args[1].equalsIgnoreCase("Fly") || args[1].equalsIgnoreCase("flight")) {
                                sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + " for " + ChatColor.RED + "Flight" + ChatColor.GREEN + "!");
                                notifyStaff(((Player) sender).getDisplayName(), target.getDisplayName(), args);
                            } else if (args[1].equalsIgnoreCase("antikb") || args[1].equalsIgnoreCase("antiknockback") || args[1].equalsIgnoreCase("knockback") || args[1].equalsIgnoreCase("velocity") || args[1].equalsIgnoreCase("anti-knockback")) {
                                sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + " for " + ChatColor.RED + "Anti-Knockback" + ChatColor.GREEN + "!");
                                notifyStaff(((Player) sender).getDisplayName(), target.getDisplayName(), args);
                            } else if (args[1].equalsIgnoreCase("crits") || args[1].equalsIgnoreCase("criticals") || args[1].equalsIgnoreCase("crit")) {
                                sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + " for " + ChatColor.RED + "Criticals" + ChatColor.GREEN + "!");
                                notifyStaff(((Player) sender).getDisplayName(), target.getDisplayName(), args);
                            } else if (args[1].equalsIgnoreCase("bhop") || args[1].equalsIgnoreCase("BunnyHop") || args[1].equalsIgnoreCase("B-Hop")) {
                                sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + " for " + ChatColor.RED + "BunnyHop" + ChatColor.GREEN + "!");
                                notifyStaff(((Player) sender).getDisplayName(), target.getDisplayName(), args);
                            } else if (args[1].equalsIgnoreCase("speed") || args[1].equalsIgnoreCase("movement")) {
                                sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + " for " + ChatColor.RED + "Speed" + ChatColor.GREEN + "!");
                                notifyStaff(((Player) sender).getDisplayName(), target.getDisplayName(), args);
                            } else if (args[1].equalsIgnoreCase("chat") || args[1].equalsIgnoreCase("spam") || args[1].equalsIgnoreCase("advertising") || args[1].equalsIgnoreCase("spamming")) {
                                sender.sendMessage(ChatColor.GREEN + "Thanks for reporting " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + " for " + ChatColor.YELLOW + "Chat Violations" + ChatColor.GREEN + "!");
                                notifyStaff(((Player) sender).getDisplayName(), target.getDisplayName(), args);
                            } else {
                                sender.sendMessage(ChatColor.RED + "Valid Hacks: (KILLAURA, AIMBOT, FLIGHT, ANTIKNOCKBACK, CRITICALS, BHOP, SPEED, SPAM)");
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "There is no player by that name connected to this server!");
                        }
                    } else {
                        sender.sendMessage((ChatColor.RED + "Usage: /report <user> <hacks>"));
                    }
                }
            }
        return true;
    }

    public void notifyStaff(String sender, String target, String[] args) {
        if (args[1].equalsIgnoreCase("KA")) {
            staff_utils.scNotif("Console", ChatColor.YELLOW + sender + ChatColor.WHITE + " has reported " + ChatColor.RED + target + ChatColor.WHITE + " for " + ChatColor.RED + "KillAura");
        } else
            staff_utils.scNotif("Console", ChatColor.YELLOW + sender + ChatColor.WHITE + " has reported " + ChatColor.RED + target + ChatColor.WHITE + " for " + ChatColor.RED + args[1].toUpperCase() + ChatColor.AQUA + ".");
    }
}