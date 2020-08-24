package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (sender.hasPermission("atom.command." + command.getName() + ".set")) {
            Location newSpawn = player.getLocation();
            String currentWorld = player.getWorld().getName();
            Bukkit.getWorld(currentWorld).setSpawnLocation(newSpawn);
            int spawnX = newSpawn.getBlockX();
            int spawnY = newSpawn.getBlockY();
            int spawnZ = newSpawn.getBlockZ();
            staffUtils.informativeMessage((Player) sender, "Set the spawn of &f" + currentWorld + "&7 to &f" + spawnX + "&7,&f " + spawnY + "&7,&f " + spawnZ);
            Core.spawn = newSpawn;
        } else staffUtils.noPerm(player);
        return true;
    }
}
