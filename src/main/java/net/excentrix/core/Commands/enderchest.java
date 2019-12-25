package net.excentrix.core.Commands;

import net.excentrix.core.utils.staff_utils;
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
                    player.sendMessage(ChatColor.GREEN + "Opening the Enderchest of " + ChatColor.YELLOW + commandSender.getName());
                } else if ((strings.length == 1)) {
                    if (player.hasPermission("clarke.command.enderchest.others")) {
                        Player target = Bukkit.getPlayerExact(strings[0]);
                        if (target != null) {
                            player.sendMessage(ChatColor.GREEN + "Opening the Enderchest of " + ChatColor.YELLOW + commandSender.getName());
                            player.openInventory(target.getEnderChest());
                        } else staff_utils.playerNotFound(player);
                    } else staff_utils.noPerm(player);
                } else staff_utils.printUsage(player, "enderchest", "[player]");
            } else staff_utils.noPerm(player);
        }
        return true;
    }
}
