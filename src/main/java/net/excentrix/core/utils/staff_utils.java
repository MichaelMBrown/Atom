package net.excentrix.core.utils;

import net.excentrix.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class staff_utils {
    private static Plugin plugin = Core.getPlugin(Core.class);

    public static void sendSC_console(Player param, String[] args) {
        param.sendMessage(ChatColor.AQUA + "[S] " + ChatColor.DARK_AQUA + "[" + plugin.getConfig().getString("server-name") + "] " + ChatColor.RED + "Console" + ChatColor.GRAY + ":" + ChatColor.YELLOW + " " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
    }

    public static void sendSC(String player, Player param, String[] args) {
        param.sendMessage(ChatColor.AQUA + "[S] " + ChatColor.DARK_AQUA + "[" + plugin.getConfig().getString("server-name") + "] " + ChatColor.WHITE + player + ChatColor.GRAY + ":" + ChatColor.YELLOW + " " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
    }

    public static void scNotif(String sender, Player param, String args) {
        if (sender.equalsIgnoreCase("Console")) {
            param.sendMessage(ChatColor.AQUA + "[S] " + ChatColor.DARK_AQUA + "[" + plugin.getConfig().getString("server-name") + "] " + ChatColor.RED + "Console" + ChatColor.GRAY + ":" + ChatColor.YELLOW + " " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));

        } else {
            param.sendMessage(ChatColor.AQUA + "[S] " + ChatColor.DARK_AQUA + "[" + plugin.getConfig().getString("server-name") + "] " + ChatColor.WHITE + sender + ChatColor.GRAY + ":" + ChatColor.YELLOW + " " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));

        }
    }
}
