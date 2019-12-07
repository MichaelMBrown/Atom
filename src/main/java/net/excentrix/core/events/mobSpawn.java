package net.excentrix.core.events;

import net.excentrix.core.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.Plugin;

public class mobSpawn implements Listener {
    private static Plugin plugin = Core.getPlugin(Core.class);

    @EventHandler
    public void disableAI(CreatureSpawnEvent event) {
        if (plugin.getConfig().getBoolean("mobs-disabled")) {
            event.getEntity().setAI(false);
        } else {
            event.getEntity().setAI(true);
        }
    }
}
