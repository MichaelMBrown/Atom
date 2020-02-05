package net.excentrix.core.events;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class playerTalk implements Listener {
    private static Plugin plugin = Core.getPlugin(Core.class);

    public playerTalk() {
    }

    @EventHandler
    public void talkEvent(AsyncPlayerChatEvent event) {
        if (Core.chatSilenced && !event.getPlayer().hasPermission("clarke.staff")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&c&l❌&8&l]&7 You cannot talk, as global chat is currently muted."));
        }

    }

    @EventHandler
    public void staffTalk(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        if (message.startsWith("# ") && event.getPlayer().hasPermission("clarke.chat.staffchat")) {
            event.setCancelled(true);
            String newMessage = message.substring(2);
            staffUtils.scNotif(event.getPlayer().getName(), newMessage);
        }

    }
}
