package net.excentrix.core.events;

import net.excentrix.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class preventionMode implements Listener {
    @EventHandler
    public void preventBuild(BlockBreakEvent event) {
        if (Core.buildDenied.contains(event.getPlayer())) {
            event.setCancelled(true);
            if (event.getPlayer().hasPermission("clarke.command.build"))
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot break blocks as your build mode is disabled!"));
        }
    }

    @EventHandler
    public void preventPlace(BlockPlaceEvent event) {
        if (Core.buildDenied.contains(event.getPlayer())) {
            event.setCancelled(true);
            if (event.getPlayer().hasPermission("clarke.command.build"))
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot place blocks as your build mode is disabled!"));
        }
    }

    @EventHandler
    public void onWorld(PlayerChangedWorldEvent event) {
        if (event.getFrom().getName().equalsIgnoreCase("kitpvp")) {
            if (event.getPlayer().getWorld().getName().equalsIgnoreCase("plots")) {
                Core.buildDenied.remove(event.getPlayer());
            }
        } else if (event.getFrom().getName().equalsIgnoreCase("plots")) {
            Core.buildDenied.add(event.getPlayer());
        }
    }
}
