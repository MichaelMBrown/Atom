package net.excentrix.core.Commands;

import net.excentrix.core.utils.coreUtils;
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
				if (sender.hasPermission("atom.command.heal")) {
					if (args.length == 1) {
						Player targetPlayer = Bukkit.getPlayerExact(args[0]);
						Player target = coreUtils.playerLookup((Player) sender, targetPlayer);
						if (target != null) {
							coreUtils.informativeMessage((Player) sender, "You healed " + coreUtils.getPlayerColor(target) + target.getName());
							BukkitCommand.broadcastCommandMessage(sender, ChatColor.YELLOW + "healed " + target.getName(), false);
							target.setHealth(20);
							target.setFireTicks(0);
							target.setFoodLevel(20);
							target.setSaturation(20);
							for (PotionEffect effect : target.getActivePotionEffects()) {
								target.removePotionEffect(effect.getType());
							}
						} else {
							coreUtils.playerNotFound((Player) sender);
						}
					} else {
						coreUtils.informativeMessage((Player) sender, "You healed " + coreUtils.getPlayerColor((Player) sender) + sender.getName());
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
					coreUtils.noPerm((Player) sender);
				}
			}
		}
		return true;
	}
}
