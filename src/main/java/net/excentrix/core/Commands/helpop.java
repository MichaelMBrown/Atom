package net.excentrix.core.Commands;

import net.excentrix.core.utils.staffUtils;
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
                    staffUtils.informativeMessage((Player) sender, "Your message &f" + message + "&7was send to the staff!");
                    staffUtils.scNotify("Console", ChatColor.GOLD + (sender.getName() + ChatColor.YELLOW + " requested help: " + ChatColor.GRAY + "" + ChatColor.ITALIC + message));
                } else {
                    staffUtils.printUsage((Player) sender, "helpop", "<message>");
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You need to be a player to execute this command.");
        }
        return true;
    }
}
