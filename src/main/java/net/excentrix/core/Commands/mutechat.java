package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static net.excentrix.core.Core.chatSilenced;

public class mutechat implements CommandExecutor {
    private static Plugin plugin = Core.getPlugin(Core.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("clarke.command.mutechat")) {
            if (!chatSilenced) {
                staffUtils.broadcastServer(ChatColor.translateAlternateColorCodes('&', "&e&lClarke&f Global chat has been disabled by " + commandSender.getName()));
                chatSilenced = true;
                plugin.getConfig().set("chat-silenced", chatSilenced);
                plugin.saveConfig();
                staffUtils.scNotify("console", ChatColor.YELLOW + commandSender.getName() + ChatColor.GRAY + " has muted the chat.");
            } else {
                staffUtils.broadcastServer(ChatColor.translateAlternateColorCodes('&', "&e&lClarke&f Global chat has been enabled by " + commandSender.getName()));
                chatSilenced = false;
                plugin.getConfig().set("chat-silenced", chatSilenced);
                plugin.saveConfig();
                staffUtils.scNotify("console", ChatColor.YELLOW + commandSender.getName() + ChatColor.GRAY + " has unmuted the chat.");
            }
        } else staffUtils.noPerm((Player) commandSender);
        return true;
    }
}
