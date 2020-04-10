package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class setServer implements CommandExecutor {
    private static Plugin plugin = Core.getPlugin(Core.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("setserver") || command.getName().equalsIgnoreCase("set-server")) {
                if (sender.hasPermission("clarke.command.setserver")) {
                    if (args.length == 1) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "Processing request...");
                        plugin.getConfig().set("server-name", args[0]);
                        staffUtils.informativeMessage((Player) sender, "Done! You've set the server-name to " + ChatColor.YELLOW + args[0]);
                        plugin.saveConfig();
                        staffUtils.scNotify(sender.getName(), "Set the server name to " + ChatColor.RED + args[0]);
                    } else {
                        staffUtils.printUsage((Player) sender, "setserver", "<new server name>");
                    }
                } else staffUtils.noPerm((Player) sender);
            }
        }
        return true;
    }
}
