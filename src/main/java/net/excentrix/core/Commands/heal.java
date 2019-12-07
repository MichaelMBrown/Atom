package net.excentrix.core.Commands;

import net.excentrix.core.utils.staff_utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
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
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "healed " + target.getDisplayName(), false);
                            target.setHealth(20);
                            target.setFireTicks(0);
                            target.setFoodLevel(20);
                        } else {
                            staff_utils.playerNotFound((Player) sender);
                        }
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "You healed yourself.");
                        BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "healed " + ((Player) sender).getDisplayName(), false);
                        ((Player) sender).setHealth(20);
                        ((Player) sender).setFireTicks(0);
                        ((Player) sender).setFoodLevel(20);
                    }
                } else {
                    staff_utils.noPerm((Player) sender);
                }
            }
        }
        return true;
    }
}
