package net.excentrix.core.messaging_service;

import net.excentrix.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class messageUtils {
    private static Plugin plugin = Core.getPlugin(Core.class);

    public static boolean messageEligibility(Player player) {
        return !Core.pmToggled.contains(player);
    }

    public static void messagePlayer(Player sender, Player receipt, String message) {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (Core.nowSpying.contains(p) && p != sender && p != receipt) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Messaging> &e" + sender.getName() + " -> " + receipt.getName() + " &7" + message));
            }
        }
        plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&e" + sender.getName() + " - > " + receipt.getName() + " " + message));
        receipt.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Messaging> &e" + sender.getName() + " -> Me&7 " + message));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Messaging> &eMe -> " + receipt.getName() + " &7" + message));
    }
}
