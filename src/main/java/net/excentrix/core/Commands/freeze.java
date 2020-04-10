package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class freeze implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("freeze")) {
            if (sender.hasPermission("clarke.command.freeze")) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        if (!Core.freezeList.contains(target)) {
                            Core.freezeList.add(target);
                            target.setInvulnerable(true);
                            staffUtils.informativeMessage((Player) sender, "You froze " + target.getName());
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "froze " + ChatColor.GOLD + target.getName(), false);
                            staffUtils.errorMessage(target, "You have been frozen.");
                        } else {
                            Core.freezeList.remove(target);
                            target.setInvulnerable(false);
                            staffUtils.informativeMessage((Player) sender, "You unfroze " + target.getName());
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "unfroze " + ChatColor.GOLD + target.getName(), false);
                            staffUtils.informativeMessage(target, "You are no longer frozen.");
                        }
                    } else {
                        staffUtils.playerNotFound((Player) sender);
                    }
                } else staffUtils.printUsage((Player) sender, "freeze", "<player>");
            } else staffUtils.noPerm((Player) sender);
        }
        return true;
    }
}
