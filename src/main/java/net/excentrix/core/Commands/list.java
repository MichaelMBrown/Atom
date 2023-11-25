package net.excentrix.core.Commands;

import net.excentrix.core.utils.coreUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class list implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Check if user has permission to run command (atom.command.list)
        // If they do, run the command
        if (commandSender.hasPermission("atom.command.list")) {
            List<String> staffList = new ArrayList<String>();
            List<String> playerList = new ArrayList<String>();
            coreUtils.informativeMessage((Player) commandSender, "&8Checking on that...");
            // for each player in the server, check their rank and add them to the list
            // if they are a staff member, add them to the staff list
            // if they are a player, add them to the player list
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (coreUtils.getRankInteger(player.getName()) >= 1) {
                    // Can the player see the staff member?
                    if (coreUtils.playerCanSee((Player) commandSender, player)) {
                        if (coreUtils.isVanished(player)) {
                            staffList.add("&7[V] " + coreUtils.getPlayerColor(player) + player.getName());
                        } else {
                            staffList.add(coreUtils.getPlayerColor(player) + player.getName());
                        }
                    }
                } else {
                    // if the player is a player, add them to the player list
                    playerList.add(coreUtils.getPlayerColor(player) + player.getName());
                }
            }
            // send the player the staff list
            // if list of staff is empty, don't send it
            if (!staffList.isEmpty()) {
                coreUtils.informativeMessage((Player) commandSender, "&aStaff Online: \n&f" + String.join(", ", staffList));
            }
            // send the player list
            coreUtils.informativeMessage((Player) commandSender, "&aPlayers Online: \n&f" + String.join(", ", playerList));
        } else {
            coreUtils.noPerm((Player) commandSender);
        }
        return false;
    }
}
