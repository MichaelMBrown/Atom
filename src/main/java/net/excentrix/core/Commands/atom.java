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
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- /&fhelpop &6&m  &f&r Requests Staff Assistance"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- /&freport &6&m  &f&r Reports a Player"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- /&fw &6&m  &r&f Message a Player"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- /&ftogglepm &6&m  &f&r Toggles Receiving Messages"));
						atomShowCommand(player, "fly", "Enables/Disables Flight for a Player");
						atomShowCommand(player, "gamemode", "Sets a Players Gamemode");
						atomShowCommand(player, "god", "Enables/Disables God for a Player");
						atomShowCommand(player, "heal", "Heals a Player");
						atomShowCommand(player, "kick", "Removes a player from the Network");
						atomShowCommand(player, "kill", "Kills a Player");
						atomShowCommand(player, "staffchat", "Communicates in StaffChat");
						atomShowCommand(player, "toggleSC", "Toggles in-game staff chat");
						atomShowCommand(player, "tp", "Teleport to Players");
						atomShowCommand(player, "weather", "Changes the Weather");
						atomShowCommand(player, "edit", "Edits in-game configs");
						atomShowCommand(player, "freeze", "Halts a players actions");
						atomShowCommand(player, "smite", "Summons a lightning-bolt");
						atomShowCommand(player, "enderchest", "Opens the Enderchest");
						atomShowCommand(player, "tphere", "Teleports a player to you");
						atomShowCommand(player, "say", "Broadcast to the Server");
						atomShowCommand(player, "give", "Give a player an Item");
						atomShowCommand(player, "grant", "Grant a user a rank");
						atomShowCommand(player, "grants", "Shows what a User has been Granted");
						atomShowCommand(player, "build", "Toggles Buildmode.");
						atomShowCommand(player, "balance", "Views your Balance");
						atomShowCommand(player, "setserver", "Sets the internal server name");
						atomShowCommand(player, "togglePvP", "Toggles PvP Globally");
						atomShowCommand(player, "mutelocalchat", "Mutes Server Chat Locally");
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
						if (((Player) sender).getUniqueId().toString().equalsIgnoreCase("5077fab0-9749-4548-aacd-aff52565c55f")) {
							staffUtils.informativeMessage((Player) sender, "Rank: " + staffUtils.getRankObject(staffUtils.getRank(sender.getName())));
							staffUtils.informativeMessage((Player) sender, "Prison Rank: " + prisonUtils.getPrisonRank((Player) sender));
							staffUtils.informativeMessage((Player) sender, "Prison Rank (raw): " + prisonUtils.getRankFromInt((Player) sender, prisonUtils.getPrisonRankInt((Player) sender)));
							staffUtils.informativeMessage((Player) sender, "Rank Integer: " + staffUtils.getRankInteger(sender.getName()));
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
