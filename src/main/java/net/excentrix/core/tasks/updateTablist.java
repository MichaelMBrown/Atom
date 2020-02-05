package net.excentrix.core.tasks;

import net.excentrix.core.Core;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class updateTablist extends BukkitRunnable {

    Core plugin;
    String group;

    public updateTablist(Core plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            LuckPerms api = LuckPermsProvider.get();
            for (Player p : Bukkit.getOnlinePlayers()) {
                group = api.getUserManager().getUser(p.getName()).getPrimaryGroup();
                if (api.getGroupManager().getGroup(group).getDisplayName() != null) {
                    switch (group) {
                        case "owner":
                            p.setPlayerListName(ChatColor.DARK_RED + p.getName());
                            break;
                        case "developer":
                            p.setPlayerListName(ChatColor.AQUA + "" + ChatColor.BOLD + p.getName());
                            break;
                        case "admin":
                            p.setPlayerListName(ChatColor.RED + p.getName());
                            break;
                        case "staff":
                            p.setPlayerListName(ChatColor.DARK_PURPLE + p.getName());
                            break;
                        default:
                            p.setPlayerListName(ChatColor.GRAY + p.getName());
                    }
                }
//            p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', api.getGroupManager().getGroup(group).));
            }
        }
    }
}
