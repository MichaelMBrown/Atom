package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class give implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player sender = (Player) commandSender;
        if (!(commandSender.hasPermission("atom.command.give"))) {
            staffUtils.noPerm(sender);
            return true;
        }
        if (strings.length > 0) {
            int amount;
            Player targetPlayer = Bukkit.getPlayerExact(strings[0]);
            Player target = staffUtils.playerLookup(sender, targetPlayer);
            if (strings[0].equalsIgnoreCase("all")) {
                if (strings.length > 1) {
                    try {
                        try {
                            amount = Integer.parseInt(strings[2]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            amount = 1;
                        }
                    } catch (NumberFormatException e) {
                        amount = 1;
                    }
                    try {
                        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(strings[1].toUpperCase())), amount);
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.getInventory().addItem(item);
                        }
                        staffUtils.informativeMessage(sender, "You gave &e" + amount + " " + item.getI18NDisplayName() + "&a to " + Core.playerColour + strings[0].toUpperCase());
                    } catch (NullPointerException e) {
                        staffUtils.actionForbidden(sender);
                    }
                } else staffUtils.printUsage(sender, "give", "<player> <item> [count]");
            } else if (target != null) {
                if (strings.length > 1) {
                    try {
                        try {
                            amount = Integer.parseInt(strings[2]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            amount = 1;
                        }
                    } catch (NumberFormatException e) {
                        amount = 1;
                    }
                    try {
                        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(strings[1].toUpperCase())), amount);
                        target.getInventory().addItem(item);
                        staffUtils.informativeMessage(sender, "You gave &e" + amount + " " + item.getI18NDisplayName() + "&a to " + staffUtils.retrievePlayerColour(target) + strings[0]);
                    } catch (NullPointerException e) {
                        staffUtils.actionForbidden(sender);
                    }
                } else staffUtils.printUsage(sender, "give", "<player> <item> [count]");
            } else staffUtils.playerNotFound(sender);
        } else staffUtils.printUsage(sender, "give", "<player> <item> [count]");
        return true;
    }
}
