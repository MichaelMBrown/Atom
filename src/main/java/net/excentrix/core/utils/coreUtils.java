package net.excentrix.core.utils;

import de.myzelyam.api.vanish.VanishAPI;
import net.excentrix.core.CentralHandler;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class coreUtils {
	private static final Plugin plugin = CentralHandler.getPlugin(CentralHandler.class);
	static LuckPerms api = LuckPermsProvider.get();
	
	public static void notifyStaff(String sender, String args) {
		plugin.getLogger().info(ChatColor.GOLD + sender + ChatColor.GRAY + ": " + ChatColor.WHITE + args);
		String group = null;
		Player scSender = Bukkit.getPlayer(sender);
		for (final Player p : Bukkit.getOnlinePlayers()) {
			if (p.hasPermission("atom.chat.staffchat")) {
				if (!(CentralHandler.scMuted.contains(p))) {
					switch (sender.toLowerCase()) {
						case "console":
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o(" + plugin.getConfig().getString("server-name") + "&7&o) " + "&b[STAFF] &4[SYSTEM]&7: &f") + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
							break;
						case "watchdog":
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o(" + plugin.getConfig().getString("server-name") + "&7&o) " + "&b[STAFF] &4[WATCHDOG]&7: &7") + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
							break;
						case "none":
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o(" + plugin.getConfig().getString("server-name") + "&7&o) " + "&b[STAFF] &f") + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
							break;
						default:
							try {
								group = api.getUserManager().getUser(scSender.getName()).getPrimaryGroup().toLowerCase();
							} catch (NullPointerException ignored) {
							}
							QueryOptions queryOptions = api.getContextManager().getQueryOptions(scSender);
							CachedMetaData metaData = api.getGroupManager().getGroup(group).getCachedData().getMetaData(queryOptions);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o(" + plugin.getConfig().getString("server-name") + "&7&o) " + "&b[STAFF] &a" + ChatColor.translateAlternateColorCodes('&', metaData.getPrefix()) + " " + sender + "&f: ") + String.join(" ", args));
							break;
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
	
	public static Integer getRankInteger(String PlayerName) {
		int rankValue = 0;
		if (!(Bukkit.getPluginManager().isPluginEnabled("LuckPerms"))) {
			return rankValue;
		}
		Player player = Bukkit.getPlayerExact(PlayerName);
		assert player != null;
		switch (getRank(PlayerName).toLowerCase()) {
			default:
				rankValue = 0;
				break;
			case "helper":
				rankValue = 1;
				break;
			case "mod":
			case "staff":
				rankValue = 2;
				break;
			case "admin":
			case "administrator":
				rankValue = 3;
				break;
			case "developer":
			case "dev":
				rankValue = 4;
				break;
			case "owner":
				rankValue = 5;
				break;
		}
		return rankValue;
	}
	
	public static void broadcastServer(String message) {
		plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public static void informativeMessage(Player sender, String message) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + message));
	}
	
	public static void errorMessage(Player sender, String message) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + ChatColor.translateAlternateColorCodes('&', message)));
	}
	
	public static String getRankObject(String rank) {
		String rankObject = null;
		String internal = rank.toLowerCase();
		internal = net.md_5.bungee.api.ChatColor.stripColor(internal);
		LuckPerms api = LuckPermsProvider.get();
		if (api.getGroupManager().getGroup(internal) != null) {
			if (api.getGroupManager().getGroup(internal).getDisplayName() != null) {
				rankObject = api.getGroupManager().getGroup(internal).getDisplayName();
			} else rankObject = api.getGroupManager().getGroup(internal).getName();
		}
		return rankObject;
	}
	
	
	public static void actionForbidden(Player player) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are forbidden to perform this action."));
	}
	
	public static void noPerm(Player player) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have permission for this command!"));
	}
	
	public static void playerNotFound(Player player) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThere is no player by that name connected to this server!"));
	}
	public static void playerDoesntExist(Player player) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player does not exist!"));
	}
	
	public static void printUsage(Player player, String command, String args) {
//        player.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/" + command + " " + ChatColor.WHITE + args);
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eCommand Usage: &6/" + command + " &f&7" + args));
	}
	
	public static ChatColor getPlayerColor(Player player) {
		switch (getRank(player.getName()).toLowerCase()) {
			case "mod":
				return ChatColor.DARK_GREEN;
			case "dev":
			case "developer":
				return ChatColor.AQUA;
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
