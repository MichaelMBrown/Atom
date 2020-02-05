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

    public static void scNotif(String sender, String args) {
        plugin.getLogger().info(ChatColor.GOLD + sender + ChatColor.GRAY + ": " + ChatColor.WHITE + args);
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("clarke.chat.staffchat")) {
                if (!(Core.scMuted.contains(p))) {
                    if (sender.equalsIgnoreCase("Console")) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&6&l✩&8&l] &7[&f" + plugin.getConfig().getString("server-name") + "&7] &cConsole&7: ") + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&6&l✩&8&l] &7[&f" + plugin.getConfig().getString("server-name") + "&7] &a" + sender + "&7: ") + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
                        //p.sendMessage(ChatColor.AQUA + "[S] " + ChatColor.DARK_AQUA + "[" + plugin.getConfig().getString("server-name") + "] " + ChatColor.WHITE + sender + ChatColor.GRAY + ":" + ChatColor.YELLOW + " " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
                    }
                }
            }
        }

    }

    public static String getRank(String player) {
        String rank;
        rank = api.getUserManager().getUser(player).getPrimaryGroup();
        return rank;
    }

    public static void broadcastServer(String message) {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(message);
        }
    }

    public static void cannotPerform(Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&c&l❌&8&l] &cYou are forbidden perform this action."));
    }

    public static void noPerm(Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&c&l❌&8&l] &cYou do not have permission for this command!"));
    }

    public static void playerNotFound(Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&c&l❌&8&l] &cThere is no player by that name connected to this server!"));
    }

    public static void printUsage(Player player, String command, String args) {
//        player.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/" + command + " " + ChatColor.WHITE + args);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&a&l✩&8&l] &eUsage: &6/" + command + " &f" + args));
    }
}
