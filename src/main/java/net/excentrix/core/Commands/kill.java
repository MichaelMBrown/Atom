package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staff_utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kill implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kill")) {
            if (sender.hasPermission("clarke.command.kill")) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        if (Core.godList.contains(target)) {
                            sender.sendMessage(ChatColor.RED + "That player cannot be damaged, as they are in God Mode.");
                        } else {
                            target.setHealth(0);
                            sender.sendMessage(ChatColor.GREEN + "Killed player " + ChatColor.YELLOW + target.getName());
                            staff_utils.scNotif(sender.getName(), "Killed player " + ChatColor.GOLD + target.getName());
                        }
                    } else staff_utils.playerNotFound((Player) sender);
                } else {
                    staff_utils.printUsage((Player) sender, "kill", "<player>");
                }
            } else {
                staff_utils.noPerm((Player) sender);
            }
        }
        return true;
    }
}
