package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.Prison.prisonUtils;
import net.excentrix.core.utils.atomUtils;
import net.excentrix.core.utils.staffUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static net.excentrix.core.utils.atomUtils.atomShowCommand;

public class atom implements CommandExecutor {
	private static final Plugin plugin = Core.getPlugin(Core.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("atom.command.help")) {
			LuckPerms api = LuckPermsProvider.get();
			Player player = (Player) sender;
			
			if (args.length > 0) {
				String option = args[0].toLowerCase();
				switch (option) {
					case "help":
						player.sendMessage(ChatColor.GOLD + "---------- " + "Commands for: " + ChatColor.YELLOW + "Atom" + ChatColor.GOLD + " ----------");
						atomShowCommand(player, "balance", "Views your Balance", "none");
						atomShowCommand(player, "helpop", "Requests Staff Assistance", "none");
						atomShowCommand(player, "report", "Reports a Player to Staff", "none");
						atomShowCommand(player, "msg", "Message a Player", "none");
						atomShowCommand(player, "spawn", "Teleports to Spawn", "none");
						atomShowCommand(player, "tsm", "Toggles Receiving Messages", "none");
						atomShowCommand(player, "fly", "Enables/Disables Flight for a Player", "fly");
						atomShowCommand(player, "gamemode", "Sets a Players Gamemode", "gamemode");
						atomShowCommand(player, "god", "Enables/Disables God for a Player", "god");
						atomShowCommand(player, "heal", "Heals a Player", "heal");
						atomShowCommand(player, "kick", "Removes a player from the Network", "kick");
						atomShowCommand(player, "kill", "Kills a Player", "kill");
						atomShowCommand(player, "sc", "Communicates in StaffChat", "staffchat");
						atomShowCommand(player, "tsc", "Toggles in-game staff chat", "staffchat");
						atomShowCommand(player, "tp", "Teleport to Players", "tp");
						atomShowCommand(player, "weather", "Changes the Weather", "weather");
						atomShowCommand(player, "edit", "Edits in-game configs", "edit");
						atomShowCommand(player, "freeze", "Halts a players actions", "freeze");
						atomShowCommand(player, "smite", "Summons a lightning-bolt", "smite");
						atomShowCommand(player, "ec | enderchest", "Opens the Enderchest", "enderchest");
						atomShowCommand(player, "tphere", "Teleports a player to you", "tp");
						atomShowCommand(player, "say", "Broadcast to the Server", "say");
						atomShowCommand(player, "give", "Give a player an Item", "give");
						atomShowCommand(player, "grant", "Grant a user a rank", "grant");
						atomShowCommand(player, "grants", "Shows what a User has been Granted", "grants");
						atomShowCommand(player, "build", "Toggles buildmode, allowing you to place anywhere", "build");
						atomShowCommand(player, "setserver", "Sets the internal server name", "setserver");
						atomShowCommand(player, "setspawn", "Sets the Server's Spawn", "spawn.set");
						atomShowCommand(player, "togglepvp", "Toggles PvP Globally", "togglepvp");
						atomShowCommand(player, "togglechat", "Mutes Server Chat Locally", "mutelocalchat");
						break;
					case "reload":
						if (sender.hasPermission("atom.command.atom.reload")) {
							plugin.reloadConfig();
							atomUtils.pluginSetup();
							staffUtils.informativeMessage((Player) sender, "Reloaded the config.");
							BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "reloaded the Config.", false);
						} else staffUtils.noPerm((Player) sender);
						break;
					case "debug":
						if (((Player) sender).getUniqueId().toString().equalsIgnoreCase("5077fab0-9749-4548-aacd-aff52565c55f") || ((Player) sender).getUniqueId().toString().equalsIgnoreCase("6c7d3c44-fd94-4150-8045-dbc5da6ce872")) {
							staffUtils.informativeMessage((Player) sender, "Rank: " + staffUtils.getRankObject(staffUtils.getRank(sender.getName())));
							staffUtils.informativeMessage((Player) sender, "Prison Rank: " + prisonUtils.getPrisonRank((Player) sender));
							staffUtils.informativeMessage((Player) sender, "Prison Rank (raw): " + prisonUtils.getRankFromInt((Player) sender, prisonUtils.getPrisonRankInt((Player) sender)));
							staffUtils.informativeMessage((Player) sender, "Rank Integer: " + staffUtils.getRankInteger(sender.getName()));
							staffUtils.informativeMessage((Player) sender, "Regular Rank Prefix: " + staffUtils.getRankObject(staffUtils.getRank(sender.getName())) + ".");
							staffUtils.informativeMessage((Player) sender, "Stripped Rank: " + ChatColor.stripColor(ChatColor.RED+"red") + ".");
						}
						break;
					default:
						staffUtils.informativeMessage((Player) sender, "This server is running &b&lAtom &fv" + plugin.getDescription().getVersion());
						staffUtils.informativeMessage((Player) sender, "Developed by &b&lqr0");
						break;
				}
			} else
				staffUtils.printUsage((Player) sender, "atom", "<help/reload/version>");
		}
		return true;
	}
	
}
