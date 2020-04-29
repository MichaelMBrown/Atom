package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static net.excentrix.core.utils.clarkeUtils.clarkeCommand;

public class clarke implements CommandExecutor {
    private static Plugin plugin = Core.getPlugin(Core.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        double totalArgs = args.length - 1;
        if (sender.hasPermission("clarke.command.clarke")) {
            if (args.length > 0) {
                String option = args[0].toLowerCase();
                switch (option) {
                    case "help":
                        Player player = (Player) sender;
                        player.sendMessage(ChatColor.YELLOW + "==========" + ChatColor.GOLD + " Clarke Commands" + ChatColor.YELLOW + "==========");
                        player.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/helpop" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Requests Staff Assistance");
                        player.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/report" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Reports a Player");
                        player.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/w" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Message a Player");
                        player.sendMessage(ChatColor.YELLOW + "*" + " " + ChatColor.GOLD + "/togglepm" + ChatColor.YELLOW + " -- " + ChatColor.WHITE + "Toggles Receiving Messages.");
                        clarkeCommand(player, "fly", "Enables/Disables Flight for a Player");
                        clarkeCommand(player, "gamemode", "Sets a Players Gamemode");
                        clarkeCommand(player, "god", "Enables/Disables God for a Player");
                        clarkeCommand(player, "heal", "Heals a Player");
                        clarkeCommand(player, "kick", "Removes a player from the Network");
                        clarkeCommand(player, "kill", "Kills a Player");
                        clarkeCommand(player, "staffchat", "Communicates in StaffChat");
                        clarkeCommand(player, "toggleSC", "Toggles in-game staff chat");
                        clarkeCommand(player, "tp", "Teleport to Players");
                        clarkeCommand(player, "weather", "Changes the Weather");
                        clarkeCommand(player, "edit", "Edits in-game configs");
                        clarkeCommand(player, "freeze", "Halts a players actions");
                        clarkeCommand(player, "smite", "Summons a lightning-bolt");
                        clarkeCommand(player, "enderchest", "Opens the Enderchest");
                        clarkeCommand(player, "tphere", "Teleports a player to you");
                        clarkeCommand(player, "say", "Broadcast to the Server");
                        clarkeCommand(player, "give", "Give a player an Item");
                        clarkeCommand(player, "grant", "Grant a user a rank");
                        clarkeCommand(player, "grants", "Shows what a User has been Granted");
                        clarkeCommand(player, "build", "Toggles Buildmode.");
                        clarkeCommand(player, "balance", "Views your Balance");
                        clarkeCommand(player, "setserver", "Sets the internal server name");
                        break;
                    case "reload":
                        if (sender.hasPermission("clarke.command.clarke.reload")) {
                            plugin.reloadConfig();
                            sender.sendMessage(ChatColor.GREEN + "Reloaded " + ChatColor.YELLOW + "Clarke" + ChatColor.GREEN + "'s config.");
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "reloaded the Config.", false);
                        } else staffUtils.noPerm((Player) sender);
                        break;
                    case "debug":
                        sender.sendMessage(ChatColor.RED + "Temporarily disabled.");
                        break;
                    default:
                        staffUtils.informativeMessage((Player) sender, "This server is running &e&lClarke &fv" + plugin.getDescription().getVersion());
                        break;
                }
            } else
                sender.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/clarke " + ChatColor.WHITE + "<help/reload/version>");
        }
        return true;
    }

}
