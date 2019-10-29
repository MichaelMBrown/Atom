package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class global implements CommandExecutor {
    Plugin plugin = Core.getPlugin(Core.class);
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Player player = (Player) sender;
        if(sender instanceof Player) {
                player.sendMessage(ChatColor.GREEN + "Set your channel to: " + ChatColor.GOLD + "GLOBAL");
                Bukkit.dispatchCommand(console, "ch set " + player.getName() + " global");
                if(player.hasPermission("excentrix.chat.staffchat")){
                    Bukkit.dispatchCommand(console, "ch set " + player.getName() + " staff read");
                }
        } else{
            System.out.println("You must be a player to execute this command!");
        }
        return true;
    }
}
