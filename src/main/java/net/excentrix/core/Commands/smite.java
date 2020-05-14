package net.excentrix.core.Commands;

import net.excentrix.core.utils.staffUtils;
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
                    Player targetPlayer = Bukkit.getPlayerExact(strings[0]);
                    Player target = staffUtils.findPlayer(sender, targetPlayer);
                    if (target != null) {
                        staffUtils.informativeMessage((Player) commandSender, "You smited &e" + target.getName());
                        BukkitCommand.broadcastCommandMessage(commandSender, ChatColor.YELLOW + "smited " + ChatColor.GOLD + target.getName(), false);
                        World world = target.getWorld();
                        world.strikeLightning(target.getLocation());
                    } else staffUtils.playerNotFound((Player) commandSender);
                } else staffUtils.printUsage((Player) commandSender, "smite", "[player]");
            }
        }
        return true;
    }
}
