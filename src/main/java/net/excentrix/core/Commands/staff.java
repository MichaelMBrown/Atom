package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Method;

public class staff implements CommandExecutor {
    Plugin plugin = Core.getPlugin(Core.class);
    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(sender instanceof Player){
            if (player.hasPermission("excentrix.chat.staffchat")) {
                player.sendMessage(ChatColor.GREEN + "Set your channel to: " + ChatColor.GOLD + "STAFF");
                resetChannel(sender);
            } else {
                player.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
            }

        }
        return true;
    }
    private void resetChannel(CommandSender sender){
        Player player = (Player) sender;
        Bukkit.dispatchCommand(console, "ch set " + player.getName() + " staff");
        Bukkit.dispatchCommand(console, "ch set " + player.getName() + " global");
        Bukkit.dispatchCommand(console, "ch set " + player.getName() + " staff write");
        Bukkit.dispatchCommand(console, "ch set " + player.getName() + " global read");
    }

}
