package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class toggleSC implements CommandExecutor {
    private static Plugin plugin = Core.getPlugin(Core.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player sender = (Player) commandSender;
        if (!(commandSender.hasPermission("clarke.chat.staffchat"))) {
            staffUtils.noPerm((Player) commandSender);
            return true;
        } else {
            if (Core.scMuted.contains(sender)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Chat Manager> &7You enabled the Staff Chat!"));
                Core.scMuted.remove(sender);
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Chat Manager> &7You disabled the Staff Chat!"));
                Core.scMuted.add(sender);
            }
        }
        return true;
    }
}
