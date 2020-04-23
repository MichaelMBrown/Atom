package net.excentrix.core.events;

import net.excentrix.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class preventionMode implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void preventBuild(BlockBreakEvent event) {
        if (Core.buildDenied.contains(event.getPlayer())) {
            event.setCancelled(true);
            if (event.getPlayer().hasPermission("clarke.command.build"))
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot break blocks as your build mode is disabled!"));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void preventPlace(BlockPlaceEvent event) {
        if (Core.buildDenied.contains(event.getPlayer())) {
            event.setCancelled(true);
            if (event.getPlayer().hasPermission("clarke.command.build"))
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot place blocks as your build mode is disabled!"));
        }
    }

    @EventHandler
    public void onWorld(PlayerChangedWorldEvent event) {
        switch (event.getPlayer().getWorld().getName().toLowerCase()) {
            case "skyblock":
                Core.buildDenied.remove(event.getPlayer());
                break;
            case "hub":
                Core.buildDenied.add(event.getPlayer());
                break;
        }
    }
}

