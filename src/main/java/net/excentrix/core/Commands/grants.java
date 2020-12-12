package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
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
	private static final Plugin plugin = Core.getPlugin(Core.class);
	
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		Player player;
		player = (Player) commandSender;
		if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
			LuckPerms api = LuckPermsProvider.get();
			if (!(commandSender.hasPermission("atom.command.grants"))) {
				staffUtils.noPerm((Player) commandSender);
				return true;
			} else if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
				if (strings.length != 1) {
					staffUtils.printUsage(player, "grants", "<player>");
				} else {
					Player targetPlayer = Bukkit.getPlayerExact(strings[0]);
					Player target = staffUtils.playerLookup((Player) commandSender, targetPlayer);
					try {
						String group = staffUtils.getRank(target.getName());
						if (target != null) {
							String grantName = api.getGroupManager().getGroup(group).getDisplayName();
							if (grantName == null) {
								staffUtils.informativeMessage(player, staffUtils.getPlayerColor(target) + target.getName() + "&a has the grant: " + api.getGroupManager().getGroup(group).getName() + "&a.");
							} else {
								staffUtils.informativeMessage(player, staffUtils.getPlayerColor(target) + target.getName() + "&a has the grant: " + api.getGroupManager().getGroup(group).getDisplayName() + "&a.");
							}
						}
					} catch (NullPointerException e) {
						staffUtils.playerNotFound(player);
					}
				}
			}
		} else
			commandSender.sendMessage(ChatColor.RED + "Cannot perform any actions using grant, as the dependency is not found!");
		return true;
	}
}
