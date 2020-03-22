package net.excentrix.core.events;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class playerChatEvents implements Listener {
    private static Plugin plugin = Core.getPlugin(Core.class);

    public playerChatEvents() {
    }

    @EventHandler
    public void talkEvent(AsyncPlayerChatEvent event) {
        if (Core.chatSilenced && !event.getPlayer().hasPermission("clarke.staff")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&c&l❌&8&l]&7 You cannot talk right now, as global chat is currently muted."));
        }

    }

    @EventHandler
    public void staffTalk(AsyncPlayerChatEvent event) {
        if (event.getMessage().startsWith("# ") && staffUtils.getRankInteger(event.getPlayer().getName()) >= 2) {
            event.setCancelled(true);
            staffUtils.scNotif(event.getPlayer().getName(), event.getMessage().substring(2));
        }
    }
}
