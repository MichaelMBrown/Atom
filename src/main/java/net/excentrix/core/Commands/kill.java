package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kill implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kill")) {
            if (sender.hasPermission("atom.command.kill")) {
                if (args.length == 1) {
                    Player targetPlayer = Bukkit.getPlayerExact(args[0]);
                    Player target = staffUtils.playerLookup((Player) sender, targetPlayer);
                    if (target != null) {
                        if (Core.godList.contains(target)) {
                            staffUtils.actionForbidden((Player) sender);
                        } else {
                            target.setHealth(0);
                            staffUtils.informativeMessage((Player) sender, "Killed " + staffUtils.retrievePlayerColour(target) + target.getName());
//                            staffUtils.scNotif(sender.getName(), "Killed player " + ChatColor.GOLD + target.getName());
                        }
                    } else staffUtils.playerNotFound((Player) sender);
                } else {
                    staffUtils.printUsage((Player) sender, "kill", "<player>");
                }
            } else {
                staffUtils.noPerm((Player) sender);
            }
        }
        return true;
    }
}
