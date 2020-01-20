package net.excentrix.core.events;

import net.excentrix.core.Core;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class joinEvent implements Listener {
    LuckPerms api = LuckPermsProvider.get();
    private static Plugin plugin = Core.getPlugin(Core.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player;
        player = event.getPlayer();
        String group;
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            group = api.getUserManager().getUser(player.getName()).getPrimaryGroup();
            player.sendMessage(ChatColor.DARK_GRAY + "Validating account...");
            player.sendMessage(ChatColor.GREEN + "Done! Applying grant: " + ChatColor.translateAlternateColorCodes('&', api.getGroupManager().getGroup(group).getDisplayName()) + ChatColor.GREEN + " to You.");
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', api.getGroupManager().getGroup(group).getDisplayName()) + ChatColor.WHITE + " " + player.getName());
        }
        event.setJoinMessage("");
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage("");
    }

}
