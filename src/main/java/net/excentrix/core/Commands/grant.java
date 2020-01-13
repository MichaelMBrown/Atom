package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class grant implements CommandExecutor {
    private static Plugin plugin = Core.getPlugin(Core.class);
    LuckPerms api = LuckPermsProvider.get();

    public User loadUser(Player player) {
        // assert that the player is online
        if (!player.isOnline()) {
            throw new IllegalStateException("Player is offline");
        }

        return api.getUserManager().getUser(player.getUniqueId());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            Player commandSender = (Player) sender;
            Player target;
            if (!(staffUtils.getRank(sender.getName()).equalsIgnoreCase("Manager")) && !(staffUtils.getRank(sender.getName()).equalsIgnoreCase("Owner")) && !(staffUtils.getRank(sender.getName()).equalsIgnoreCase("Admin"))) {
                sender.sendMessage(ChatColor.RED + "You must be granted " + ChatColor.BOLD + "Admin" + ChatColor.RED + " to execute this command!");
                return true;
            } else if (args.length != 2) {
                staffUtils.printUsage(commandSender, "grant", "<user> <grant>");
            } else {
                target = Bukkit.getPlayerExact(args[0]);
                if (target != null) {
                    if (api.getGroupManager().getGroup(args[1]) != null) {
                        if (commandSender.hasPermission("group." + args[1])) {
                            getServer().dispatchCommand(getServer().getConsoleSender(), "lp user " + target.getName() + " parent set " + args[1]);
                            getLogger().info(ChatColor.RED + "User " + target.getName() + " was granted " + api.getGroupManager().getGroup(args[1]).getDisplayName() + " by " + sender.getName());
                            User user = loadUser(target);
                            api.getUserManager().getUser(target.getName()).setPrimaryGroup(args[1]);
                            api.getUserManager().saveUser(user);
                            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Grant Manager> &7" + target.getName() + "'s grant was updated to " + api.getGroupManager().getGroup(args[1]).getDisplayName() + "&7!"));
                            staffUtils.scNotif(commandSender.getName(), ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " has been granted " + api.getGroupManager().getGroup(args[1]).getDisplayName() + ChatColor.YELLOW + " by " + ChatColor.GOLD + commandSender.getName());
                        } else {
                            staffUtils.cannotPerform(commandSender);
                        }

                    } else {
                        sender.sendMessage(ChatColor.RED + "You cannot grant " + args[1].toUpperCase() + " as it does not exist.");
                    }
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Cannot perform any actions using grant, as the dependency is not found!");
        }
        return true;
    }
}
