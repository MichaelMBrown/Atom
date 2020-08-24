package net.excentrix.core.events;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class playerChatEvents implements Listener {
    private static final Plugin plugin = Core.getPlugin(Core.class);

    public playerChatEvents() {
    }

    @EventHandler
    public void talkEvent(AsyncPlayerChatEvent event) {
        if (Core.chatSilenced && !event.getPlayer().hasPermission("atom.staff")) {
            event.setCancelled(true);
            staffUtils.errorMessage(event.getPlayer(), "&7You cannot right now, as global chat is currently muted.");
        }
    }

    @EventHandler
    public void staffTalk(AsyncPlayerChatEvent event) {
        if (event.getMessage().startsWith("# ") && staffUtils.getRankInteger(event.getPlayer().getName()) >= 1) {
            event.setCancelled(true);
            staffUtils.scNotify(event.getPlayer().getName(), event.getMessage().substring(2));
        }
    }
}
