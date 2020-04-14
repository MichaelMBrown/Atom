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
            //Define Staff Colours
            ChatColor colourOwner = ChatColor.DARK_RED;
            ChatColor colourDeveloper = ChatColor.AQUA;
            ChatColor colourBold = ChatColor.BOLD;
            ChatColor colourAdmin = ChatColor.RED;
            ChatColor colourStaff = ChatColor.DARK_PURPLE;
            ChatColor colourBasic = ChatColor.GRAY;
            //Run Logic
            LuckPerms api = LuckPermsProvider.get();
            for (Player p : Bukkit.getOnlinePlayers()) {
                group = api.getUserManager().getUser(p.getName()).getPrimaryGroup();
                if (api.getGroupManager().getGroup(group).getDisplayName() != null) {
                    switch (group) {
                        case "owner":
                            p.setPlayerListName(colourOwner + p.getName());
                            p.setDisplayName(colourOwner + p.getName());
                            break;
                        case "developer":
                            p.setPlayerListName(colourDeveloper + "" + ChatColor.BOLD + p.getName());
                            p.setDisplayName(colourDeveloper + "" + colourBold + p.getName() + ChatColor.RESET);
                            break;
                        case "admin":
                            p.setPlayerListName(colourAdmin + p.getName());
                            p.setDisplayName(colourAdmin + p.getName() + ChatColor.RESET);
                            break;
                        case "head-mod":
                            p.setPlayerListName(colourStaff + "" + ChatColor.ITALIC + p.getName());
                            p.setDisplayName(colourStaff + "" + ChatColor.ITALIC + p.getName() + ChatColor.RESET);
                            break;
                        case "mod":
                            p.setPlayerListName(colourStaff + p.getName());
                            p.setDisplayName(colourStaff + p.getName() + ChatColor.RESET);
                            break;
                        case "helper":
                            p.setPlayerListName(ChatColor.YELLOW + p.getName());
                            p.setDisplayName(ChatColor.YELLOW + p.getName() + ChatColor.RESET);
                            break;
                        case "builder":
                            p.setPlayerListName(ChatColor.DARK_PURPLE + p.getName());
                            p.setDisplayName(ChatColor.DARK_PURPLE + p.getName() + ChatColor.RESET);
                            break;
                        case "goliath":
                            p.setPlayerListName(ChatColor.GOLD + "" + ChatColor.BOLD + p.getName());
                            p.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + p.getName() + ChatColor.RESET);
                            break;
                        case "mvp+":
                            p.setPlayerListName(ChatColor.AQUA + "" + ChatColor.ITALIC + p.getName());
                            p.setDisplayName(ChatColor.AQUA + "" + ChatColor.ITALIC + p.getName() + ChatColor.RESET);
                            break;
                        case "mvp":
                            p.setPlayerListName(ChatColor.AQUA + p.getName());
                            p.setDisplayName(ChatColor.AQUA + p.getName() + ChatColor.RESET);
                            break;
                        case "vip+":
                            p.setPlayerListName(ChatColor.GREEN + "" + ChatColor.ITALIC + p.getName());
                            p.setDisplayName(ChatColor.GREEN + "" + ChatColor.ITALIC + p.getName() + ChatColor.RESET);
                            break;
                        case "vip":
                            p.setPlayerListName(ChatColor.GREEN + "" + p.getName());
                            p.setDisplayName(ChatColor.GREEN + "" + p.getName() + ChatColor.RESET);
                            break;
                        default:
                            p.setPlayerListName(colourBasic + p.getName());
                            p.setDisplayName(colourBasic + p.getName() + ChatColor.RESET);
                            break;
                    }
                }
//            p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', api.getGroupManager().getGroup(group).));
            }
        }
    }
}
