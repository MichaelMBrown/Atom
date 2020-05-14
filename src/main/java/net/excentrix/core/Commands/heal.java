package net.excentrix.core.Commands;

import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("heal")) {
                if (sender.hasPermission("clarke.command.heal")) {
                    if (args.length == 1) {
                        Player targetPlayer = Bukkit.getPlayerExact(args[0]);
                        Player target = staffUtils.findPlayer((Player) sender, targetPlayer);
                        if (target != null) {
                            staffUtils.informativeMessage((Player) sender, "You healed &e" + target.getName());
                            BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "healed " + target.getName(), false);
                            target.setHealth(20);
                            target.setFireTicks(0);
                            target.setFoodLevel(20);
                            target.setSaturation(20);
                            for (PotionEffect effect : target.getActivePotionEffects()) {
                                target.removePotionEffect(effect.getType());
                            }
                        } else {
                            staffUtils.playerNotFound((Player) sender);
                        }
                    } else {
                        staffUtils.informativeMessage((Player) sender, "You healed &eyourself&7.");
                        BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "healed " + sender.getName(), false);
                        Player commandSender = (Player) sender;
                        commandSender.setHealth(20);
                        commandSender.setFireTicks(0);
                        commandSender.setFoodLevel(20);
                        commandSender.setSaturation(20);
                        for (PotionEffect effect : commandSender.getActivePotionEffects()) {
                            commandSender.removePotionEffect(effect.getType());
                        }
                    }
                } else {
                    staffUtils.noPerm((Player) sender);
                }
            }
        }
        return true;
    }
}
