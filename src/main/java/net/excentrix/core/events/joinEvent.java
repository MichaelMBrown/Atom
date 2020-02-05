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

    private static Plugin plugin = Core.getPlugin(Core.class);

    public joinEvent() {
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            LuckPerms api = LuckPermsProvider.get();
            String group = api.getUserManager().getUser(player.getName()).getPrimaryGroup();
            String grant = api.getGroupManager().getGroup(group).getDisplayName();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&a&l✩&8&l] &8Please hold while we check your grants..."));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&a&l✩&8&l] &7Done! Applying " + grant + "&7 to you!"));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&c&l❌&8&l]&c WARN: I could not verify you grants, playing it safe and locking you."));
            Core.freezeList.add(player);
        }

        event.setJoinMessage("");
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage("");
    }
}
