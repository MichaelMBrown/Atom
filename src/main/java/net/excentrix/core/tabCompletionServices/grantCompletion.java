package net.excentrix.core.tabCompletionServices;

import net.excentrix.core.utils.staffUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class grantCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> canGrant = new ArrayList<>();
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            if (!(staffUtils.getRank(sender.getName()).equalsIgnoreCase("Manager")) && !(staffUtils.getRank(sender.getName()).equalsIgnoreCase("Owner")) && !(staffUtils.getRank(sender.getName()).equalsIgnoreCase("Admin")) && !(staffUtils.getRank(sender.getName())).equalsIgnoreCase("Developer")) {
            } else {
                if (args.length == 2) {
                    LuckPerms lpAPI = LuckPermsProvider.get();
                    for (Group e : lpAPI.getGroupManager().getLoadedGroups()) {
                        if (sender.hasPermission("group." + e.getName())) ;
                        {
                            canGrant.add(e.getName());
                        }
                    }
                    Collections.sort(canGrant, Comparator.naturalOrder());
                    return canGrant;
                }
            }
        }
        return null;
    }
}
