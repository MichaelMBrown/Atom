package net.excentrix.core.messagingService;

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
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&a&l✩&8&l]&7 You will now receive private messages from players."));
        } else {
            Core.pmToggled.add(commandSender);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&c&l❌&8&l]&7 You will no longer receive private messages."));
        }

        return true;
    }
}
