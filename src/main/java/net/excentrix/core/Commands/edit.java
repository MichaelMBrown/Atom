package net.excentrix.core.Commands;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class edit implements CommandExecutor {
	private static final Plugin plugin = CentralHandler.getPlugin(CentralHandler.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player theSender = (Player) sender;
		if (command.getName().equalsIgnoreCase("edit")) {
			if (sender.hasPermission("atom.command.edit")) {
				if (args.length == 3) {
					if (args[0].equalsIgnoreCase("boolean") || args[0].equalsIgnoreCase("bool")) {
						String value = args[2].toUpperCase();
						String configOption = args[1].toUpperCase();
						switch (configOption) {
							case "MOBAI":
								switch (value) {
									case "TRUE":
										coreUtils.notifyStaff(theSender.getName(), "Set the value of " + ChatColor.GOLD + args[1] + ChatColor.WHITE + " to " + ChatColor.YELLOW + args[2]);
										coreUtils.informativeMessage((Player) sender, "Updated the value of " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " to " + ChatColor.YELLOW + args[2]);
										plugin.getConfig().set("mobAI", true);
										plugin.saveConfig();
										plugin.reloadConfig();
										for (World world : Bukkit.getWorlds()) {
											for (Entity entity : world.getEntities()) {
												if (!(entity instanceof Player)) {
													if (entity instanceof Creature) {
														((Creature) entity).setAI(true);
													}
												}
											}
										}
										break;
									case "FALSE":
										coreUtils.notifyStaff(theSender.getName(), "Set the value of " + ChatColor.GOLD + args[1] + ChatColor.WHITE + " to " + ChatColor.YELLOW + args[2]);
										coreUtils.informativeMessage((Player) sender, "Updated the value of " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " to " + ChatColor.YELLOW + args[2]);
										plugin.getConfig().set("mobAI", false);
										plugin.saveConfig();
										plugin.reloadConfig();
										for (World world : Bukkit.getWorlds()) {
											for (Entity entity : world.getEntities()) {
												if (!(entity instanceof Player)) {
													if (entity instanceof Creature) {
														((Creature) entity).setAI(false);
													}
												}
											}
										}
										break;
									default:
										sender.sendMessage(ChatColor.RED + "Unknown data value of: " + args[2].toUpperCase());
										break;
								}
								break;
							case "DISABLEDROWNED":
								switch (value) {
									case "TRUE":
										coreUtils.notifyStaff(theSender.getName(), "Set the value of " + ChatColor.GOLD + args[1] + ChatColor.WHITE + " to " + ChatColor.YELLOW + args[2]);
										coreUtils.informativeMessage((Player) sender, "Updated the value of " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " to " + ChatColor.YELLOW + args[2]);
										plugin.getConfig().set("disableDrowned", true);
										plugin.saveConfig();
										plugin.reloadConfig();
										break;
									case "FALSE":
										coreUtils.notifyStaff(theSender.getName(), "Set the value of " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " to " + ChatColor.YELLOW + args[2]);
										coreUtils.informativeMessage((Player) sender, "Updated the value of " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " to " + ChatColor.YELLOW + args[2]);
										plugin.getConfig().set("disableDrowned", false);
										plugin.saveConfig();
										plugin.reloadConfig();
										break;
									default:
										sender.sendMessage(ChatColor.RED + "Unknown data value of: " + args[2].toUpperCase());
										break;
								}
								break;
							case "DOINSOMNIA":
								switch (value) {
									case "TRUE":
										coreUtils.notifyStaff(theSender.getName(), "Set the value of " + ChatColor.GOLD + args[1] + ChatColor.WHITE + " to " + ChatColor.YELLOW + args[2]);
										coreUtils.informativeMessage((Player) sender, "Updated the value of " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " to " + ChatColor.YELLOW + args[2]);
										plugin.getConfig().set("doInsomnia", true);
										plugin.saveConfig();
										plugin.reloadConfig();
										break;
									case "FALSE":
										coreUtils.notifyStaff(theSender.getName(), "Set the value of " + ChatColor.GOLD + args[1] + ChatColor.WHITE + " to " + ChatColor.YELLOW + args[2]);
										coreUtils.informativeMessage((Player) sender, "Updated the value of " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " to " + ChatColor.YELLOW + args[2]);
										plugin.getConfig().set("doInsomnia", false);
										plugin.saveConfig();
										plugin.reloadConfig();
										break;
									default:
										sender.sendMessage(ChatColor.RED + "Unknown data value of: " + args[2].toUpperCase());
										break;
								}
								break;
							case "DISABLEPORTALS":
								switch (value) {
									case "TRUE":
										coreUtils.notifyStaff(theSender.getName(), "Set the value of " + ChatColor.GOLD + args[1] + ChatColor.WHITE + " to " + ChatColor.YELLOW + args[2]);
										coreUtils.informativeMessage((Player) sender, "Updated the value of " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " to " + ChatColor.YELLOW + args[2]);
										plugin.getConfig().set("disable-portals", true);
										plugin.saveConfig();
										plugin.reloadConfig();
										break;
									case "FALSE":
										coreUtils.notifyStaff(theSender.getName(), "Set the value of " + ChatColor.GOLD + args[1] + ChatColor.WHITE + " to " + ChatColor.YELLOW + args[2]);
										coreUtils.informativeMessage((Player) sender, "Updated the value of " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " to " + ChatColor.YELLOW + args[2]);
										plugin.getConfig().set("disable-portals", false);
										plugin.saveConfig();
										plugin.reloadConfig();
										break;
									default:
										sender.sendMessage(ChatColor.RED + "Unknown data value of: " + args[2]);
										break;
								}
								break;
							default:
								coreUtils.printUsage((Player) sender, "edit", "<type> <data> <value>");
								break;
						}
					} else coreUtils.printUsage((Player) sender, "edit", "<type> <data> <value>");
				}
			}
		}
		return true;
	}
}
