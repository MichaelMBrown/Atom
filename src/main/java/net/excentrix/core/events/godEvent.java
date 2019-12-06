package net.excentrix.core.events;

import net.excentrix.core.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class godEvent implements Listener {
    Core plugin;


    @EventHandler
    public void godCheck(EntityDamageEvent event){
        if (event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if (plugin.godList.contains(player)){
                event.setCancelled(true);
            }
        }
    }
}
