package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staff_utils;
import org.bukkit.Bukkit;
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
                        plugin.getLogger().info(ChatColor.GOLD + ((Player) sender).getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + String.join(" ", args));
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("clarke.chat.staffchat")) {
                                staff_utils.sendSC(sender.getName(), p, args);
                            }
                        }
                    } else {
                        staff_utils.printUsage((Player) sender, "sc", "<message>");
                    }
                }
            } else staff_utils.noPerm((Player) sender);
        } else if ((sender instanceof ConsoleCommandSender)) {
            if (command.getName().equalsIgnoreCase("sc")) {
                if (args.length > 0) {
                    for (final Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("clarke.chat.staffchat")) {
                            staff_utils.sendSC_console(p, args);
                        }
                    }
                } else
                    staff_utils.printUsage((Player) sender, "sc", "<message>");
            }
        }
        return true;
    }
}
