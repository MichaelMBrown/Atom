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
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class grants implements CommandExecutor {
	private static final Plugin plugin = CentralHandler.getPlugin(CentralHandler.class);
	
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		Player player;
		player = (Player) commandSender;
		if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
			LuckPerms api = LuckPermsProvider.get();
			if (!(commandSender.hasPermission("atom.command.grants"))) {
				coreUtils.noPerm((Player) commandSender);
				return true;
			} else if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
				if (strings.length != 1) {
					coreUtils.printUsage(player, "grants", "<player>");
				} else {
					Player targetPlayer = Bukkit.getPlayerExact(strings[0]);
					Player target = coreUtils.playerLookup((Player) commandSender, targetPlayer);
					try {
						String group = coreUtils.getRank(target.getName());
						if (target != null) {
							String grantName = api.getGroupManager().getGroup(group).getDisplayName();
							if (grantName == null) {
								coreUtils.informativeMessage(player, coreUtils.getPlayerColor(target) + target.getName() + "&a has the grant: " + api.getGroupManager().getGroup(group).getName() + "&a.");
							} else {
								coreUtils.informativeMessage(player, coreUtils.getPlayerColor(target) + target.getName() + "&a has the grant: " + api.getGroupManager().getGroup(group).getDisplayName() + "&a.");
							}
						}
					} catch (NullPointerException e) {
						coreUtils.playerNotFound(player);
					}
				}
			}
		} else
			commandSender.sendMessage(ChatColor.RED + "Cannot perform any actions using grant, as the dependency is not found!");
		return true;
	}
}
