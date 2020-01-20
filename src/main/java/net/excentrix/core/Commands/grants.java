package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class grants implements CommandExecutor {
    private static Plugin plugin = Core.getPlugin(Core.class);
    LuckPerms api = LuckPermsProvider.get();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player;
        Player target;
        player = (Player) commandSender;
        if (!(commandSender.hasPermission("clarke.command.grants"))) {
            staffUtils.noPerm((Player) commandSender);
            return true;
        } else {
            if (strings.length != 1) {
                staffUtils.printUsage(player, "grants", "<player>");
            } else {
                target = Bukkit.getPlayerExact(strings[0]);
                try {
                    String group = staffUtils.getRank(target.getName());
                    if (target != null) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Grant Manager> &e" + target.getName() + "&7 belongs to: " + api.getGroupManager().getGroup(group).getDisplayName() + "&7."));
                    }
                } catch (NullPointerException e) {
                    staffUtils.playerNotFound(player);
                }
            }
        }
        return true;
    }
}
