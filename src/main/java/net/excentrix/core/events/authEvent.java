package net.excentrix.core.events;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class authEvent implements Listener {

    private static Plugin plugin = Core.getPlugin(Core.class);

    @EventHandler
    public void authOnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            LuckPerms api = LuckPermsProvider.get();
            String group = api.getUserManager().getUser(player.getName()).getPrimaryGroup();
            String grant = api.getGroupManager().getGroup(group).getDisplayName();
            if (plugin.getConfig().getString("server-name").equalsIgnoreCase("hub"))
                staffUtils.informativeMessage(player, "Please hold while I verify your grants...");
            Core.freezeList.add(player);
            if (plugin.getConfig().getString("server-name").equalsIgnoreCase("hub"))
                if (grant == null) {
                    staffUtils.errorMessage(player, "WARN: Something went wrong in identifying your grants, playing it safe and locking you.");
                    staffUtils.scNotify("console", "&c&lWARN: &7" + player.getName() + "&c&l has an invalid grant setup, please notify the System Administrator immediately.");
                } else
                    staffUtils.informativeMessage(player, "&aVerified&7 Applying your " + grant + " &7grant to you now.");
            Core.freezeList.remove(player);
        } else {
            staffUtils.errorMessage(player, "WARN: Something went wrong in identifying your grants, playing it safe and locking you.");
            staffUtils.scNotify("console", "&c&lWARN: &7" + player.getName() + "&c&l has an invalid grant setup, please notify the System Administrator immediately.");
        }

        event.setJoinMessage("");
    }

    @EventHandler
    public void buildMode(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        switch (plugin.getConfig().getString("server-name").toLowerCase()) {
            case "skyblock":
                Core.buildDenied.remove(player);
                break;
            case "prison":
                Core.buildDenied.remove(player);
                break;
            default:
                Core.buildDenied.add(player);
        }
    }

    @EventHandler
    public void leaveEvent(PlayerQuitEvent event) {
        event.setQuitMessage("");
    }
}