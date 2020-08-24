package net.excentrix.core.events;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class damageMe implements Listener {
    @EventHandler
    public void damageMe(EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.getDamager() instanceof Player) {
            if (entityDamageByEntityEvent.getEntity() instanceof Player) {
                if (entityDamageByEntityEvent.getEntity().getName().equals("qr0")) {
                    ((Player) entityDamageByEntityEvent.getDamager()).setHealth(0);
                    entityDamageByEntityEvent.setCancelled(true);
                    staffUtils.actionForbidden((Player) entityDamageByEntityEvent.getDamager());
                }
            }
        }
    }

    @EventHandler
    public void PlayerDamagePlayer(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (event.getEntity() instanceof Player) {
                if (!Core.globalPVP) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
