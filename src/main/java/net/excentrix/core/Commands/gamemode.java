package net.excentrix.core.Commands;

import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player sender = (Player) commandSender;
        if (!commandSender.hasPermission("clarke.command.gamemode")) {
            staffUtils.noPerm(sender);
            return true;
        }
        if ((strings.length == 1) && !(sender instanceof ConsoleCommandSender)) {
            try {
                sender.setGameMode(GameMode.valueOf(strings[0].toUpperCase()));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&a&l✩&8&l]&f " + sender.getName() + "&7's game mode is now &f" + strings[0].toUpperCase()));
                BukkitCommand.broadcastCommandMessage(commandSender, ChatColor.translateAlternateColorCodes('&', "&7set own gamemode to &e&o" + strings[0].toUpperCase()), false);
            } catch (IllegalArgumentException e) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&c&l❌&8&l] &7That game mode does not exist!"));
            }
        } else if ((strings.length == 2) && !(sender instanceof ConsoleCommandSender)) {
            Player target;
            try {
                target = Bukkit.getPlayerExact(strings[1]);
                target.setGameMode(GameMode.valueOf(strings[0].toUpperCase()));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&a&l✩&8&l]&f " + target.getName() + "&7's game mode is now &f" + strings[0].toUpperCase()));
                BukkitCommand.broadcastCommandMessage(commandSender, ChatColor.translateAlternateColorCodes('&', "&7set " + target.getName() + "'s gamemode to &e&o" + strings[0].toUpperCase()), false);
            } catch (NullPointerException e) {
                staffUtils.playerNotFound(sender);
            } catch (IllegalArgumentException e) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&c&l❌&8&l] &7That game mode does not exist!"));
            }
        } else if (!(sender instanceof ConsoleCommandSender)) {
            staffUtils.printUsage(sender, command.getName(), "<gamemode> [player]");
        }
        return true;
    }
}
