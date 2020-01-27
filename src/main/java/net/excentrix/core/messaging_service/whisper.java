package net.excentrix.core.messaging_service;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class whisper implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player commandSender = (Player) sender;
        String message = "";
        if (Core.freezeList.contains(sender)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Permissions> &7Sorry, you can't do this. :("));
            return true;
        } else {
            if (args.length == 0) {
                staffUtils.printUsage(commandSender, command.getName(), "<player> <message>");
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target != null) {
                    if (messageUtils.messageEligibility(target)) {
                        for (int i = 1; i < args.length; ++i) {
                            message = message + args[i] + " ";
                        }

                        messageUtils.messagePlayer(commandSender, target, message);
                    } else {
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Chat> &7Sorry, you cannot message this player."));
                    }
                } else {
                    staffUtils.playerNotFound(commandSender);
                }
            }

            return true;
        }
    }
}
