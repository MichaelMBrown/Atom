package net.excentrix.core.utils;

import net.excentrix.core.Core;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class staffUtils {
    static LuckPerms api = LuckPermsProvider.get();
    private static Plugin plugin = Core.getPlugin(Core.class);

    public static void scNotify(String sender, String args) {
        plugin.getLogger().info(ChatColor.GOLD + sender + ChatColor.GRAY + ": " + ChatColor.WHITE + args);
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("clarke.chat.staffchat")) {
                if (!(Core.scMuted.contains(p))) {
                    if (sender.equalsIgnoreCase("Console")) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&f" + plugin.getConfig().getString("server-name") + "&7] &4[SYSTEM]&7: ") + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&f" + plugin.getConfig().getString("server-name") + "&7] &a" + sender + "&7: ") + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
                    }
                }
            }
        }
    }

    public static String getRank(String player) {
        String rank;
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            rank = api.getUserManager().getUser(player).getPrimaryGroup();
        } else
            rank = "N/A";
        return rank;
    }

    public static Integer getRankInteger(String str_Player) {
        int rankValue = 0;
        if (!(Bukkit.getPluginManager().isPluginEnabled("LuckPerms"))) {
            return rankValue;
        }
        Player player = Bukkit.getPlayerExact(str_Player);
        assert player != null;
        if (getRank(player.getName()).equalsIgnoreCase("default")) {
            rankValue = 0;
        } else if (getRank(player.getName()).contains("mod") || getRank(player.getName()).contains("staff")) {
            rankValue = 1;
        } else if (getRank(player.getName()).contains("admin")) {
            rankValue = 2;
        } else if (getRank(player.getName()).contains("developer")) {
            rankValue = 3;
        } else if (getRank(player.getName()).contains("owner")) {
            rankValue = 4;
        } else rankValue = 0;
        return rankValue;
    }

    public static void broadcastServer(String message) {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(message);
        }
    }

    public static void informativeMessage(Player sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lClarke &7" + message));
    }

    public static void errorMessage(Player sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lClarke &c" + message));
    }

    public static void actionForbidden(Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lClarke &cYou are forbidden perform this action."));
    }

    public static void noPerm(Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lClarke &cYou do not have permission for this command!"));
    }

    public static void playerNotFound(Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lClarke &cThere is no player by that name connected to this server!"));
    }

    public static void printUsage(Player player, String command, String args) {
//        player.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/" + command + " " + ChatColor.WHITE + args);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUsage: &6/" + command + " &f" + args));
    }
}
