package net.excentrix.core.Commands;

import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class enderchest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player;
        player = (Player) commandSender;
        if (command.getName().equalsIgnoreCase("enderchest")) {
            if (commandSender.hasPermission("clarke.command.enderchest")) {
                if (strings.length == 0) {
                    player.openInventory(player.getEnderChest());
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&a&l✩&8&l] &7Opening the Enderchest of &e" + commandSender.getName()));
                } else if ((strings.length == 1)) {
                    if (player.hasPermission("clarke.command.enderchest.others")) {
                        Player target = Bukkit.getPlayerExact(strings[0]);
                        if (target != null) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&a&l✩&8&l] &7Opening the Enderchest of &e" + target.getName()));
                            player.openInventory(target.getEnderChest());
                        } else staffUtils.playerNotFound(player);
                    } else staffUtils.noPerm(player);
                } else staffUtils.printUsage(player, "enderchest", "[player]");
            } else staffUtils.noPerm(player);
        }
        return true;
    }
}
