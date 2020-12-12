package net.excentrix.core.events;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.query.QueryOptions;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class playerChatEvents implements Listener {
	private static final Plugin plugin = Core.getPlugin(Core.class);
	static LuckPerms api = LuckPermsProvider.get();
	
	@EventHandler
	public void talkEvent(AsyncPlayerChatEvent event) {
		if (Core.chatSilenced && !event.getPlayer().hasPermission("atom.staff")) {
			event.setCancelled(true);
			staffUtils.errorMessage(event.getPlayer(), "You cannot right now, as global chat is currently muted.");
		}
	}
	
	@EventHandler
	public void staffTalk(AsyncPlayerChatEvent event) {
		if (event.getMessage().startsWith("# ") && staffUtils.getRankInteger(event.getPlayer().getName()) >= 1) {
			event.setCancelled(true);
			staffUtils.scNotify(event.getPlayer().getName(), event.getMessage().substring(2));
		}
	}
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		String group = null;
		try {
			group = api.getUserManager().getUser(event.getPlayer().getName()).getPrimaryGroup().toLowerCase();
		} catch (NullPointerException ignored) {
		}
		QueryOptions queryOptions = api.getContextManager().getQueryOptions(event.getPlayer());
		CachedMetaData metaData = api.getGroupManager().getGroup(group).getCachedData().getMetaData(queryOptions);
		event.setFormat(ChatColor.translateAlternateColorCodes('&', metaData.getPrefix()) + " " + event.getPlayer().getName() + ChatColor.WHITE + ": " + event.getMessage());
		if (staffUtils.getRankInteger(event.getPlayer().getName()) >= 1) {
			event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
		}
	}
}
