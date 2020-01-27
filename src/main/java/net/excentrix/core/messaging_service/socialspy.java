package net.excentrix.core.messaging_service;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class socialspy implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player commandSender = (Player) sender;
        if (!sender.hasPermission("clarke.command.socialspy")) {
            staffUtils.noPerm(commandSender);
            return true;
        } else {
            if (Core.nowSpying.contains(commandSender)) {
                Core.nowSpying.remove(commandSender);
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Staff> &7You have disabled admin spying"));
            } else {
                Core.nowSpying.add(commandSender);
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Staff> &7You have enabled admin spying"));
            }

            return true;
        }
    }
}
