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
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class grant implements CommandExecutor {
    private static Plugin plugin = Core.getPlugin(Core.class);


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            LuckPerms api = LuckPermsProvider.get();
            if (sender instanceof ConsoleCommandSender) {
                Player targetPlayer = Bukkit.getPlayerExact(args[0]);
                Player target = staffUtils.findPlayer((Player) sender, targetPlayer);
                if (target != null) {
                    if (api.getGroupManager().getGroup(args[1]) != null) {
                        getServer().dispatchCommand(getServer().getConsoleSender(), "lp user " + target.getName() + " parent set " + args[1]);
                        getLogger().info(ChatColor.DARK_RED + "User " + target.getName() + " was granted " + ChatColor.translateAlternateColorCodes('&', api.getGroupManager().getGroup(args[1]).getDisplayName()) + ChatColor.DARK_RED + " by " + sender.getName());
                        //staffUtils.scNotif(commandSender.getName(), ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " has been granted " + api.getGroupManager().getGroup(args[1]).getDisplayName() + ChatColor.YELLOW + " by " + ChatColor.GOLD + commandSender.getName());
                    } else {
                        sender.sendMessage(ChatColor.RED + "You cannot grant " + args[1].toUpperCase() + " as it does not exist.");
                    }
                }
                return true;
            }
            Player commandSender = (Player) sender;
            if (!(staffUtils.getRank(sender.getName()).equalsIgnoreCase("Manager")) && !(staffUtils.getRank(sender.getName()).equalsIgnoreCase("Owner")) && !(staffUtils.getRank(sender.getName()).equalsIgnoreCase("Admin")) && !(staffUtils.getRank(sender.getName())).equalsIgnoreCase("Developer")) {
                staffUtils.actionForbidden(commandSender);
                return true;
            } else if (args.length != 2) {
                staffUtils.printUsage(commandSender, "grant", "<user> <grant>");
            } else {
                Player targetPlayer = Bukkit.getPlayerExact(args[0]);
                Player target = staffUtils.findPlayer((Player) sender, targetPlayer);
                if (target != null) {
                    if (api.getGroupManager().getGroup(args[1]) != null) {
                        if (commandSender.hasPermission("group." + args[1])) {
                            String grantName = api.getGroupManager().getGroup(args[1]).getDisplayName();
                            getServer().dispatchCommand(getServer().getConsoleSender(), "lp user " + target.getName() + " parent set " + args[1]);
                            if (grantName != null) {
                                getLogger().info(ChatColor.DARK_RED + "User " + target.getName() + " was granted " + ChatColor.translateAlternateColorCodes('&', api.getGroupManager().getGroup(args[1]).getDisplayName()) + ChatColor.DARK_RED + " by " + sender.getName());
                                staffUtils.informativeMessage(commandSender, target.getName() + "&7's grant was updated to " + grantName + "&7!");
                                staffUtils.informativeMessage(target, "You are now granted " + grantName + "&7.");
                            } else {
                                staffUtils.informativeMessage(commandSender, target.getName() + "&7's grant was updated to " + api.getGroupManager().getGroup(args[1]).getName() + "&7!");
                                staffUtils.informativeMessage(target, "You are now granted " + api.getGroupManager().getGroup(args[1]).getName() + "&7.");
                                getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lUser &7" + target.getName() + "&6&l was granted: &f'" + api.getGroupManager().getGroup(args[1]).getName() + "&f'&6&l by &c&l" + sender.getName()));
                            }
                        } else {
                            staffUtils.actionForbidden(commandSender);
                        }
                    } else {
                        staffUtils.errorMessage(commandSender, "You cannot grant " + args[1].toLowerCase() + "&c as it doesn't exist.");
                    }
                } else staffUtils.playerNotFound(commandSender);
            }
        } else {
            staffUtils.errorMessage((Player) sender, "Cannot execute any grant commands because I'm missing the Dependency! (&aLuckPerms&c)");
        }
        return true;
    }
}
