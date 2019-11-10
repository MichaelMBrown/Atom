package net.excentrix.core.Commands;

import net.excentrix.core.utils.staff_utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class helpop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String message = "";
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("helpop")) {
                if (args.length > 0) {
                    for (int i = 0; i < args.length; ++i) {
                        message = message + args[i] + " ";
                    }
                    for (final Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("excentrix.chat.staffchat")) {
                            //p.sendMessage(ChatColor.AQUA + "[S] " + ChatColor.DARK_AQUA + "[" + getConfig().getString("server-name") + "] " + ChatColor.GRAY + sender.getName() + ChatColor.GRAY + ":" + ChatColor.AQUA + " " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
                            staff_utils.scNotif("Console", p, (ChatColor.YELLOW + ((Player) sender).getDisplayName() + ChatColor.WHITE + " messaged staff: " + ChatColor.GRAY + "" + ChatColor.ITALIC + message));
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Usage: /helpop <message>");
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You need to be a player to execute this command.");
        }
        return true;
    }
}
