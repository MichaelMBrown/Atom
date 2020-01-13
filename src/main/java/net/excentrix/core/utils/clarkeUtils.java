package net.excentrix.core.utils;

import net.excentrix.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class clarkeUtils {
    private static Plugin plugin = Core.getPlugin(Core.class);

    public static void clarkeCommand(Player player, String command, String usage) {
        if (player.hasPermission("clarke.command." + command)) {
            player.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/" + command + ChatColor.YELLOW + " -- " + ChatColor.WHITE + usage + ".");
        }
    }
}
