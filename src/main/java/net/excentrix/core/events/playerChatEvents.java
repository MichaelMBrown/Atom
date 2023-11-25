package net.excentrix.core.events;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class playerChatEvents implements Listener {
	private static final Plugin plugin = CentralHandler.getPlugin(CentralHandler.class);
	static LuckPerms api = LuckPermsProvider.get();
	
	@EventHandler
	public void talkEvent(AsyncPlayerChatEvent event) {
		if (CentralHandler.chatSilenced && !event.getPlayer().hasPermission("atom.staff")) {
			event.setCancelled(true);
			coreUtils.errorMessage(event.getPlayer(), "You cannot right now, as global chat is currently muted.");
		}
	}
	
	@EventHandler
	public void staffTalk(AsyncPlayerChatEvent event) {
		if (event.getMessage().startsWith("# ") && coreUtils.getRankInteger(event.getPlayer().getName()) >= 1) {
			event.setCancelled(true);
			coreUtils.notifyStaff(event.getPlayer().getName(), event.getMessage().substring(2));
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
		if (coreUtils.getRankInteger(event.getPlayer().getName()) >= 1) {
			event.setFormat(ChatColor.translateAlternateColorCodes('&', metaData.getPrefix()) + " " + event.getPlayer().getName() + ChatColor.WHITE + ": " + ChatColor.translateAlternateColorCodes('&',event.getMessage()));
        } else
            event.setFormat(ChatColor.translateAlternateColorCodes('&', metaData.getPrefix()) + " " + event.getPlayer().getName() + ChatColor.WHITE + ": " + event.getMessage());
	}
}
