package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class God implements CommandExecutor, Listener {
    Core plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("clarke.command.god")) {
                if (command.getName().equalsIgnoreCase("god")) {
                    if (args.length > 0 && args.length != 1) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("true")) {
                                staffUtils.informativeMessage((Player) sender, "You turned on God Mode for &f" + target.getName() + "&7!");
                                BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "enabled God mode for " + ChatColor.GOLD + target.getName(), false);
                                target.setInvulnerable(true);
                                Core.godList.add(target);
                            } else if (args[1].equalsIgnoreCase("off") || args[1].equalsIgnoreCase("false")) {
                                staffUtils.informativeMessage((Player) sender, "You turned off God Mode for &f" + target.getName() + "&7!");
                                BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "enabled God mode for " + ChatColor.GOLD + target.getName(), false);
                                target.setInvulnerable(false);
                                Core.godList.remove(target);
                            }
                        } else staffUtils.playerNotFound((Player) sender);
                    } else if (args.length == 0) {
                        if (Core.godList.contains(player)) {
                            staffUtils.informativeMessage((Player) sender, "You turned off God Mode for &f" + sender.getName() + "&7!");
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "disabled God Mode.", false);
                            ((Player) sender).setInvulnerable(false);
                            Core.godList.remove(player);
                        } else {
                            staffUtils.informativeMessage((Player) sender, "You turned on God Mode for &f" + sender.getName() + "&7!");
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "enabled God Mode.", false);
                            ((Player) sender).setInvulnerable(true);
                            Core.godList.add(player);
                        }
                    } else
                        staffUtils.printUsage((Player) sender, "god", "[player] <mode>");
                }
            } else
                staffUtils.noPerm((Player) sender);
        } else {
            sender.sendMessage(ChatColor.RED + "You " + ChatColor.UNDERLINE + "MUST" + ChatColor.RESET + "" + ChatColor.RED + " be a player to execute this command!");
        }
        return true;
    }
}
