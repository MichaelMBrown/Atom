package net.excentrix.core.events;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.MetaNode;
import net.luckperms.api.query.QueryOptions;
import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class authEvent implements Listener {
	
	private static final Plugin plugin = CentralHandler.getPlugin(CentralHandler.class);
	LuckPerms api = LuckPermsProvider.get();
	
	@EventHandler
	public void authOnJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
			LuckPerms api = LuckPermsProvider.get();
			String group = api.getUserManager().getUser(player.getName()).getPrimaryGroup();
			String grant = api.getGroupManager().getGroup(group).getDisplayName();
			if (plugin.getConfig().getString("server-name").equalsIgnoreCase("hub"))
				coreUtils.informativeMessage(player, "Please hold while I verify your grants...");
			CentralHandler.freezeList.add(player);
			if (plugin.getConfig().getString("server-name").equalsIgnoreCase("hub"))
				if (grant == null) {
					coreUtils.errorMessage(player, "WARN: Something went wrong in identifying your grants, playing it safe and locking you.");
					coreUtils.notifyStaff("console", "&c&lWARN: &7" + player.getName() + "&c&l has an invalid grant setup, please notify the System Administrator immediately.");
					CentralHandler.freezeList.add(player);
				} else
					coreUtils.informativeMessage(player, "&aVerified&7 Applying your " + grant + " &7grant to you now.");
			CentralHandler.freezeList.remove(player);
		} else {
			coreUtils.errorMessage(player, "WARN: Something went wrong in identifying your grants, playing it safe and locking you.");
			coreUtils.notifyStaff("console", "&c&lWARN: &7" + player.getName() + "&c&l has an invalid grant setup, please notify the System Administrator immediately.");
			CentralHandler.freezeList.add(player);
		}
		QueryOptions queryOptions = api.getContextManager().getQueryOptions(player);
		CachedMetaData metaData = api.getUserManager().getUser(player.getName()).getCachedData().getMetaData(queryOptions);
		if (metaData.getMetaValue("prison_rank") == null || metaData.getMetaValue("prison_rank").equals("A")) {
			try {
				if (metaData.getMetaValue("prison_rank").equals("A")) {
					User user = api.getUserManager().getUser(player.getUniqueId());
					user.data().remove(MetaNode.builder("prison_rank", "A").build());
				}
			} catch (NullArgumentException | NullPointerException ignored) {
			}
			User user = api.getUserManager().getUser(player.getUniqueId());
			user.data().add(MetaNode.builder("prison_rank", "A1").build());
			api.getUserManager().saveUser(user);
			
		}
	}
	
	@EventHandler()
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (coreUtils.getRankInteger(event.getPlayer().getName()) >= 1) {
			event.setJoinMessage("");
		} else event.setJoinMessage(ChatColor.DARK_GRAY + "Join> " + ChatColor.GRAY + event.getPlayer().getName());
		event.getPlayer().setCollidable(false);
	}
	
	@EventHandler
	public void buildMode(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		switch (plugin.getConfig().getString("server-name").toLowerCase()) {
			case "skyblock":
			case "creative":
			case "prison":
				CentralHandler.buildDenied.remove(player);
				break;
			default:
				CentralHandler.buildDenied.add(player);
		}
	}
	
	@EventHandler
	public void leaveEvent(PlayerQuitEvent event) {
		if (coreUtils.getRankInteger(event.getPlayer().getName()) >= 1) {
			event.setQuitMessage("");
		} else event.setQuitMessage(ChatColor.DARK_GRAY + "Quit> " + ChatColor.GRAY + event.getPlayer().getName());
	}
}