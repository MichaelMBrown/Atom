package net.excentrix.core.Commands;

import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class enderchest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player;
        player = (Player) commandSender;
        if (commandSender.hasPermission("atom.command.enderchest")) {
            if (strings.length == 0) {
                player.openInventory(player.getEnderChest());
                staffUtils.informativeMessage(player, "Opening the Enderchest of " + staffUtils.retrievePlayerColour((Player) commandSender) + commandSender.getName());
            } else if ((strings.length == 1)) {
                if (player.hasPermission("atom.command.enderchest.others")) {
                    Player targetPlayer = Bukkit.getPlayerExact(strings[0]);
                    Player target = staffUtils.playerLookup((Player) commandSender, targetPlayer);
                    if (target != null) {
                        staffUtils.informativeMessage(player, "Opening the Enderchest of " + staffUtils.retrievePlayerColour(target) + target.getName());
                        player.openInventory(target.getEnderChest());
                    } else staffUtils.playerNotFound(player);
                } else staffUtils.noPerm(player);
            } else staffUtils.printUsage(player, "enderchest", "[player]");
        } else staffUtils.noPerm(player);
        return true;
    }
}
