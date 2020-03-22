package net.excentrix.core.events;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.plugin.Plugin;

public class portalEvent implements Listener {
    private static Plugin plugin = Core.getPlugin(Core.class);

    @EventHandler
    public void disablePortals(PortalCreateEvent event) {
        if (plugin.getConfig().getBoolean("disable-portals")) {
            event.setCancelled(true);
            if (event.getEntity() instanceof Player) {
                event.setCancelled(true);
                staffUtils.errorMessage((Player) event.getEntity(), "Sorry, the creation of Nether Portals have been disabled. :-(");
                staffUtils.scNotif("console", ChatColor.RED + event.getEntity().getName() + ChatColor.YELLOW + " tried to create a Nether Portal.");
            }
        }
    }
}
