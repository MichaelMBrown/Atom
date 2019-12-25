package net.excentrix.core.events;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staff_utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class joinEvent implements Listener {
    private static Plugin plugin = Core.getPlugin(Core.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player;
        player = event.getPlayer();
        if (player.getUniqueId().equals(UUID.fromString("5077fab0-9749-4548-aacd-aff52565c55f"))) {
            player.sendMessage(ChatColor.DARK_GRAY + "Validating account...");
            player.sendMessage(ChatColor.GREEN + "Done! Applying rank: " + ChatColor.RED + "ADMIN " + ChatColor.GREEN + "to You.");
            player.setPlayerListName(ChatColor.RED + "" + ChatColor.BOLD + event.getPlayer().getName());
            staff_utils.scNotif("Console", ChatColor.RED + "" + ChatColor.BOLD + player.getName() + ChatColor.YELLOW + " has logged in.");
            event.setJoinMessage("");
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player;
        player = event.getPlayer();
        if (player.getUniqueId().equals(UUID.fromString("5077fab0-9749-4548-aacd-aff52565c55f"))) {
            event.setQuitMessage("");
            staff_utils.scNotif("Console", ChatColor.RED + "" + ChatColor.BOLD + player.getName() + ChatColor.YELLOW + " has logged out.");
        }
    }
}
