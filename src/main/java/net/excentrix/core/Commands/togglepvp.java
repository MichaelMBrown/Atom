package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static net.excentrix.core.Core.globalPVP;

public class togglepvp implements CommandExecutor {
    private static final Plugin plugin = Core.getPlugin(Core.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("atom.command.togglepvp")) {
            if (globalPVP) {
                staffUtils.broadcastServer("Global PVP has been disabled by " + Core.playerColour + sender.getName());
                globalPVP = false;
                plugin.getConfig().set("pvp-enabled", globalPVP);
                plugin.saveConfig();
                plugin.reloadConfig();
                //staffUtils.scNotify("console", ChatColor.YELLOW + commandSender.getName() + ChatColor.GRAY + " has muted the chat.");
            } else {
                staffUtils.broadcastServer("Global PVP has been enabled by " + Core.playerColour + sender.getName());
                globalPVP = true;
                plugin.getConfig().set("pvp-enabled", globalPVP);
                plugin.saveConfig();
                plugin.reloadConfig();
                //staffUtils.scNotify("console", ChatColor.YELLOW + commandSender.getName() + ChatColor.GRAY + " has unmuted the chat.");
            }
        } else staffUtils.noPerm((Player) sender);
        return true;
    }
}
