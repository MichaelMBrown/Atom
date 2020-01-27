package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class staffchat implements CommandExecutor {
    private static Plugin plugin = Core.getPlugin(Core.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((sender instanceof Player)) {
            if (sender.hasPermission("clarke.chat.staffchat")) {
                if (command.getName().equalsIgnoreCase("sc")) {
                    if (args.length > 0) {
                        String message = String.join(" ", args);
                        plugin.getLogger().info(ChatColor.GOLD + ((Player) sender).getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + message);
                        staffUtils.scNotif(sender.getName(), message);
                    } else {
                        staffUtils.printUsage((Player) sender, "sc", "<message>");
                    }
                }
            } else staffUtils.noPerm((Player) sender);
        } else if ((sender instanceof ConsoleCommandSender)) {
            if (command.getName().equalsIgnoreCase("sc")) {
                if (args.length > 0) {
                    String message = String.join(" ", args);
                    staffUtils.scNotif("Console", message);
                } else
                    staffUtils.printUsage((Player) sender, "sc", "<message>");
            }
        }
        return true;
    }
}
