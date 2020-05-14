package net.excentrix.core.Commands;

import net.excentrix.core.utils.staffUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class say implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String message = "";
        Player player = (Player) commandSender;
        if (!commandSender.hasPermission("clarke.command.say")) {
            staffUtils.noPerm(player);
            return true;
        }
        if (strings.length > 0) {
            for (int i = 0; i < strings.length; i++) {
                message = message + strings[i] + " ";
            }
            staffUtils.broadcastServer("\n" + ChatColor.LIGHT_PURPLE + "[" + commandSender.getName() + "] " + message + "\n " + ChatColor.WHITE);
        } else staffUtils.printUsage(player, "say", "<message>");
        return true;
    }
}
