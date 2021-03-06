package net.excentrix.core.Commands;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class setSpawn implements CommandExecutor {
	private static final Plugin plugin = CentralHandler.getPlugin(CentralHandler.class);
	
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
			coreUtils.informativeMessage((Player) sender, "Set the spawn of &f" + currentWorld + net.md_5.bungee.api.ChatColor.of("#2AFC0D") + " to &f" + spawnX + "&7,&f " + spawnY + "&7,&f " + spawnZ);
			CentralHandler.spawn = newSpawn;
			plugin.getConfig().set("world", newSpawn);
			plugin.saveConfig();
			plugin.reloadConfig();
		} else coreUtils.noPerm(player);
		return true;
	}
}
