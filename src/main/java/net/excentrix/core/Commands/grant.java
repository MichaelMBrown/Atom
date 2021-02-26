package net.excentrix.core.Commands;

import net.excentrix.core.CentralHandler;
import net.excentrix.core.utils.coreUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class grant implements CommandExecutor {
	private static final Plugin plugin = CentralHandler.getPlugin(CentralHandler.class);
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
			LuckPerms api = LuckPermsProvider.get();
			if (sender instanceof ConsoleCommandSender) {
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target != null) {
					if (api.getGroupManager().getGroup(args[1]) != null) {
						getServer().dispatchCommand(getServer().getConsoleSender(), "lp user " + target.getName() + " parent set " + args[1]);
						getLogger().info(ChatColor.DARK_RED + "User " + target.getName() + " was granted " + ChatColor.translateAlternateColorCodes('&', api.getGroupManager().getGroup(args[1]).getDisplayName()) + ChatColor.DARK_RED + " by " + ChatColor.RED + "Console");
					} else {
						getLogger().info(ChatColor.RED + "You cannot grant " + args[1].toUpperCase() + " as it does not exist.");
					}
				}
				return true;
			}
			Player commandSender = (Player) sender;
			if (!(coreUtils.getRank(sender.getName()).equalsIgnoreCase("Manager")) && !(coreUtils.getRank(sender.getName()).equalsIgnoreCase("Owner")) && !(coreUtils.getRank(sender.getName()).equalsIgnoreCase("Admin")) && !(coreUtils.getRank(sender.getName())).equalsIgnoreCase("Developer")) {
				coreUtils.actionForbidden(commandSender);
				return true;
			} else if (args.length != 2) {
				coreUtils.printUsage(commandSender, "grant", "<user> <grant>");
			} else {
				Player targetPlayer = Bukkit.getPlayerExact(args[0]);
				Player target = coreUtils.playerLookup((Player) sender, targetPlayer);
				if (target != null) {
					if (api.getGroupManager().getGroup(args[1]) != null) {
						if (commandSender.hasPermission("group." + args[1])) {
							String grantName = api.getGroupManager().getGroup(args[1]).getDisplayName();
							getServer().dispatchCommand(getServer().getConsoleSender(), "lp user " + target.getName() + " parent set " + args[1]);
							if (grantName != null) {
								getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lUser &7" + target.getName() + "&6&l was granted: &f'" + api.getGroupManager().getGroup(args[1]).getDisplayName() + "&f'&6&l by &c&l" + sender.getName()));
								
								coreUtils.informativeMessage(commandSender, coreUtils.getPlayerColor(target) + target.getName() + "&a's grant was updated to " + grantName + "&a!");
								coreUtils.informativeMessage(target, "You are now " + grantName + "&a.");
							} else {
								coreUtils.informativeMessage(commandSender, coreUtils.getPlayerColor(target) + target.getName() + "&a's grant was updated to " + api.getGroupManager().getGroup(args[1]).getName() + "&a!");
								coreUtils.informativeMessage(target, "You are now " + api.getGroupManager().getGroup(args[1]).getName() + "&a.");
								getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lUser &7" + target.getName() + "&6&l was granted: &f'" + api.getGroupManager().getGroup(args[1]).getName() + "&f'&6&l by &c&l" + sender.getName()));
							}
						} else {
							coreUtils.actionForbidden(commandSender);
						}
					} else {
						coreUtils.errorMessage(commandSender, "You cannot grant " + args[1].toLowerCase() + "&c as it doesn't exist.");
					}
				} else coreUtils.playerNotFound(commandSender);
			}
		} else {
			coreUtils.errorMessage((Player) sender, "Cannot execute any grant commands because I'm missing the Dependency! (&aLuckPerms&c)");
		}
		return true;
	}
}
