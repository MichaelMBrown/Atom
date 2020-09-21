package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.Prison.prisonUtils;
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

import static net.excentrix.core.utils.atomUtils.atomCommand;

public class atom implements CommandExecutor {
    private static final Plugin plugin = Core.getPlugin(Core.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("atom.command.clarke")) {
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
                        atomCommand(player, "fly", "Enables/Disables Flight for a Player");
                        atomCommand(player, "gamemode", "Sets a Players Gamemode");
                        atomCommand(player, "god", "Enables/Disables God for a Player");
                        atomCommand(player, "heal", "Heals a Player");
                        atomCommand(player, "kick", "Removes a player from the Network");
                        atomCommand(player, "kill", "Kills a Player");
                        atomCommand(player, "staffchat", "Communicates in StaffChat");
                        atomCommand(player, "toggleSC", "Toggles in-game staff chat");
                        atomCommand(player, "tp", "Teleport to Players");
                        atomCommand(player, "weather", "Changes the Weather");
                        atomCommand(player, "edit", "Edits in-game configs");
                        atomCommand(player, "freeze", "Halts a players actions");
                        atomCommand(player, "smite", "Summons a lightning-bolt");
                        atomCommand(player, "enderchest", "Opens the Enderchest");
                        atomCommand(player, "tphere", "Teleports a player to you");
                        atomCommand(player, "say", "Broadcast to the Server");
                        atomCommand(player, "give", "Give a player an Item");
                        atomCommand(player, "grant", "Grant a user a rank");
                        atomCommand(player, "grants", "Shows what a User has been Granted");
                        atomCommand(player, "build", "Toggles Buildmode.");
                        atomCommand(player, "balance", "Views your Balance");
                        atomCommand(player, "setserver", "Sets the internal server name");
                        atomCommand(player, "togglepvp", "Toggles PvP Globally");
                        atomCommand(player, "mutelocalchat", "Mutes Server Chat Locally");
                        break;
                    case "reload":
                        if (sender.hasPermission("atom.command.atom.reload")) {
                            plugin.reloadConfig();
                            staffUtils.informativeMessage((Player) sender, "Reloaded the config.");
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "reloaded the Config.", false);
                        } else staffUtils.noPerm((Player) sender);
                        break;
                    case "debug":
                        if (sender.getName().equals("qr0"))
                            staffUtils.informativeMessage((Player) sender, "Your prison debug rank is " + prisonUtils.getPrisonRank((Player) sender));
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
