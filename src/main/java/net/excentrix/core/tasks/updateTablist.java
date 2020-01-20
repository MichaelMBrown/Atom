package net.excentrix.core.tasks;

import net.excentrix.core.Core;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class updateTablist extends BukkitRunnable {
    LuckPerms api = LuckPermsProvider.get();
    Core plugin;
    String group;

    public updateTablist(Core plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            group = api.getUserManager().getUser(p.getName()).getPrimaryGroup();
            p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', api.getGroupManager().getGroup(group).getDisplayName()) + ChatColor.WHITE + " " + p.getName());
        }
    }
}
