package net.excentrix.core.events;

import net.excentrix.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class godEvent implements Listener {
    Core plugin;


    @EventHandler
    public void godCheck(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (Core.godList.contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void neverDie(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (Core.godList.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void instaKill(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            Entity entity = event.getEntity();
            if (Core.godList.contains(player) && !(entity instanceof Player)) {
                event.setCancelled(true);
                event.setDamage(2500);
                player.sendMessage(ChatColor.RED + "Since you're in God Mode, you insta-killed this " + event.getEntity().getName());
                entity.remove();
            } else if (Core.godList.contains(player) && entity instanceof Player) {
                event.setCancelled(true);
                ((Player) entity).setHealth(0);
                player.sendMessage(ChatColor.RED + "Since you're in God Mode, you insta-killed the player " + event.getEntity().getName());
            }
        }
    }
}
