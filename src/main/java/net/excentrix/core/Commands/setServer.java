package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
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
                        sender.sendMessage(ChatColor.GREEN + "Done! You've set the server-name to " + ChatColor.YELLOW + args[0]);
                        plugin.saveConfig();
                        staffUtils.scNotif(sender.getName(), "Set the server name to " + ChatColor.RED + args[0]);
                        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
                            LuckPerms api = LuckPermsProvider.get();

                        }
                    } else {
                        staffUtils.printUsage((Player) sender, "setserver", "<new server name>");
                    }
                } else staffUtils.noPerm((Player) sender);
            }
        }
        return true;
    }
}
