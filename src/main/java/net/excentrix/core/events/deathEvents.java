package net.excentrix.core.events;

import net.excentrix.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class deathEvents implements Listener {
    @EventHandler
    public void playerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (!Core.godList.contains(event.getEntity())) {
            if (event.getEntity().getKiller() instanceof PlayerDeathEvent) {
                event.setDeathMessage(ChatColor.DARK_RED + "» " + ChatColor.RED + player.getName() + ChatColor.GRAY + " was killed by " + ChatColor.RED + event.getEntity().getKiller());
            } else
                event.setDeathMessage("");
        }
    }
}
