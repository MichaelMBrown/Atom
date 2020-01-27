package net.excentrix.core.messaging_service;

import net.excentrix.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class togglePM implements CommandExecutor {
    public togglePM() {
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player commandSender = (Player) sender;
        if (Core.pmToggled.contains(commandSender)) {
            Core.pmToggled.remove(commandSender);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Chat> &7You will now receive private messages from players."));
        } else {
            Core.pmToggled.add(commandSender);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Chat> &7You will no longer receive private messages."));
        }

        return true;
    }
}
