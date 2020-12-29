package net.excentrix.core.tasks;

import net.excentrix.core.Central;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class updateTablist extends BukkitRunnable {
	
	Central plugin;
	String group;
	
	public updateTablist(Central plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
			//Run Logic
			LuckPerms api = LuckPermsProvider.get();
			for (Player p : Bukkit.getOnlinePlayers()) {
				try {
					group = api.getUserManager().getUser(p.getName()).getPrimaryGroup().toLowerCase();
				} catch (NullPointerException ignored) {
				}
				if (api.getGroupManager().getGroup(group).getDisplayName() != null) {
					QueryOptions queryOptions = api.getContextManager().getQueryOptions(p);
					CachedMetaData metaData = api.getGroupManager().getGroup(group).getCachedData().getMetaData(queryOptions);
					switch (group) {
						case "owner":
							p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', metaData.getPrefix() + " " + p.getName()));
							p.setDisplayName(ChatColor.RED + p.getName());
							break;
						case "developer":
							p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', metaData.getPrefix() + " " + p.getName()));
							p.setDisplayName(ChatColor.AQUA + p.getName());
							break;
						case "admin":
							p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', metaData.getPrefix() + " " + p.getName()));
							p.setDisplayName(ChatColor.RED + p.getName() + ChatColor.RESET);
							break;
						case "senior-mod":
						case "mod":
							p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', metaData.getPrefix() + " " + p.getName()));
							p.setDisplayName(ChatColor.DARK_GREEN + p.getName() + ChatColor.RESET);
							break;
						case "helper":
							p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', metaData.getPrefix() + " " + p.getName()));
							p.setDisplayName(ChatColor.BLUE + p.getName() + ChatColor.RESET);
							break;
						case "buildteam":
							p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', metaData.getPrefix() + " " + p.getName()));
							p.setDisplayName(ChatColor.DARK_AQUA + p.getName() + ChatColor.RESET);
							break;
						case "goliath":
							p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', metaData.getPrefix() + " " + p.getName()));
							p.setDisplayName(ChatColor.GOLD + p.getName() + ChatColor.RESET);
							break;
						case "mvp+":
						case "mvp":
							p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', metaData.getPrefix() + " " + p.getName()));
							p.setDisplayName(ChatColor.AQUA + p.getName() + ChatColor.RESET);
							break;
						case "vip+":
						case "vip":
							p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', metaData.getPrefix() + " " + p.getName()));
							p.setDisplayName(ChatColor.GREEN + p.getName() + ChatColor.RESET);
							break;
						default:
							p.setPlayerListName(ChatColor.GRAY + p.getName());
							p.setDisplayName(ChatColor.GRAY + p.getName() + ChatColor.RESET);
							break;
					}
				}
			}
		}
	}
}
