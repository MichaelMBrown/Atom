package net.excentrix.core.Commands;

import net.excentrix.core.utils.staff_utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class smite implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("smite")) {
            if (commandSender.hasPermission("clarke.command.smite")) {
                Player sender = (Player) commandSender;
                if (strings.length == 0) {
                    Block block = sender.getTargetBlock(120);
                    assert block != null;
                    Location loc = block.getLocation();
                    World world = sender.getWorld();
                    world.strikeLightning(loc);
                } else if (strings.length == 1) {
                    Player target = Bukkit.getPlayerExact(strings[0]);
                    if (target != null) {
                        commandSender.sendMessage(ChatColor.GREEN + "You smited " + ChatColor.YELLOW + target.getName());
                        BukkitCommand.broadcastCommandMessage(commandSender, ChatColor.YELLOW + "smited " + ChatColor.GOLD + target.getName(), false);
                        World world = target.getWorld();
                        world.strikeLightning(target.getLocation());
                    } else staff_utils.playerNotFound((Player) commandSender);
                } else staff_utils.printUsage((Player) commandSender, "smite", "[player]");
            }
        }
        return true;
    }
}
