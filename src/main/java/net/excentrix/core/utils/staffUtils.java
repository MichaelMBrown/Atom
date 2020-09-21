package net.excentrix.core.utils;

import de.myzelyam.api.vanish.VanishAPI;
import net.excentrix.core.Core;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class staffUtils {
    static LuckPerms api = LuckPermsProvider.get();
    private static final Plugin plugin = Core.getPlugin(Core.class);

    public static void scNotify(String sender, String args) {
        plugin.getLogger().info(ChatColor.GOLD + sender + ChatColor.GRAY + ": " + ChatColor.WHITE + args);
        String group = null;
        Player scSender = Bukkit.getPlayer(sender);
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("atom.chat.staffchat")) {
                if (!(Core.scMuted.contains(p))) {
                    if (sender.equalsIgnoreCase("Console")) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[SYSTEM]&7: ") + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
                    } else {
                        try {
                            group = api.getUserManager().getUser(scSender.getName()).getPrimaryGroup().toLowerCase();
                        } catch (NullPointerException ignored) {
                        }
                        QueryOptions queryOptions = api.getContextManager().getQueryOptions(scSender);
                        CachedMetaData metaData = api.getGroupManager().getGroup(group).getCachedData().getMetaData(queryOptions);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[STAFF] &a" + ChatColor.translateAlternateColorCodes('&', metaData.getPrefix()) + " " + sender + "&f: ") + String.join(" ", args));
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
        } else if (getRank(player.getName()).contains("mod") || getRank(player.getName()).contains("staff") || getRank(player.getName()).contains("helper")) {
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
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    public static void informativeMessage(Player sender, String message) {
        if (plugin.getServer().getVersion().contains("1.16")) {
            sender.sendMessage(net.md_5.bungee.api.ChatColor.of("#2AFC0D") + net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message));
        } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + message));
    }

    public static void errorMessage(Player sender, String message) {
        if (plugin.getServer().getVersion().contains("1.16")) {
            sender.sendMessage(net.md_5.bungee.api.ChatColor.of("#DF0B0B") + message);
        } else
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message)));
    }

    public static void actionForbidden(Player player) {
        if (plugin.getServer().getVersion().contains("1.16")) {
            player.sendMessage(net.md_5.bungee.api.ChatColor.of("#DF0B0B") + "You are forbidden to perform this action.");
        } else
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are forbidden to perform this action."));
    }

    public static void noPerm(Player player) {
        if (plugin.getServer().getVersion().contains("1.16")) {
            player.sendMessage(net.md_5.bungee.api.ChatColor.of("#FC0E0E") + "You do not have permission for this command!");
        } else
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have permission for this command!"));
    }

    public static void playerNotFound(Player player) {
        if (plugin.getServer().getVersion().contains("1.16")) {
            player.sendMessage(net.md_5.bungee.api.ChatColor.of("#DF0B0B") + "There is no player by that name connected to this server!");
        } else
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThere is no player by that name connected to this server!"));
    }

    public static void printUsage(Player player, String command, String args) {
//        player.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/" + command + " " + ChatColor.WHITE + args);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eCommand Usage: &6/" + command + " &f&7" + args));
    }

    public static ChatColor retrievePlayerColour(Player player) {
        switch (getRank(player.getName()).toLowerCase()) {
            case "mod":
                return ChatColor.DARK_GREEN;
            case "admin":
            case "owner":
                return ChatColor.RED;
            default:
                return ChatColor.GRAY;
        }
    }

    public static Player playerLookup(Player sender, Player target) {
        try {
            if (VanishAPI.canSee(sender, target)) {
                return target;
            } else return null;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
