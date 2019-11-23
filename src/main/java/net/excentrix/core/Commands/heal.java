package net.excentrix.core.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("heal")) {
                if (sender.hasPermission("clarke.command.heal")) {
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            sender.sendMessage(ChatColor.GREEN + "You healed " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.GREEN + ".");
                            target.setHealth(20);
                            target.setFoodLevel(20);
                        } else {
                            sender.sendMessage(ChatColor.RED + "There is no player by that name connected to this server!");
                        }
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "You healed yourself.");
                        ((Player) sender).setHealth(20);
                        ((Player) sender).setFoodLevel(20);
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
                }
            }
        }
        return true;
    }
}
