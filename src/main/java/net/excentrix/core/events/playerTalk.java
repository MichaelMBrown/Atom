package net.excentrix.core.events;

import net.excentrix.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import static net.excentrix.core.Core.chatSilenced;

public class playerTalk implements Listener {
    private static Plugin plugin = Core.getPlugin(Core.class);

    @EventHandler
    public void talkEvent(AsyncPlayerChatEvent event) {
        if (chatSilenced) {
            if (!(event.getPlayer().hasPermission("clarke.staff"))) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Chat> &7You cannot talk, as global chat is currently muted."));
            }
        }
    }
}
