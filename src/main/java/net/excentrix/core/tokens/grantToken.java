package net.excentrix.core.tokens;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class grantToken implements CommandExecutor {
	
	private static final Plugin plugin = Core.getPlugin(Core.class);
	LuckPerms api = LuckPermsProvider.get();
	
	//TODO: Add implementation that allows for the redeeming of these credit things.
	
	@Override
	public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
		Player sender = (Player) player;
		if (sender instanceof Player) {
			if (staffUtils.getRank(sender.getName()).equalsIgnoreCase("owner")) {
				if (args.length == 2) {
					Player target = Bukkit.getPlayerExact(args[0]);
					if (target != null) {
						if (api.getGroupManager().getGroup(args[1]) != null) {
							ItemStack rankToken = new ItemStack(Material.SUNFLOWER);
							ItemMeta rankMeta = rankToken.getItemMeta();
							List<String> rankLore = new ArrayList<>();
							rankMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lRANK TOKEN: " + staffUtils.getRankObject(args[1])));
							rankLore.add(ChatColor.GRAY + "Right-click this item to unlock");
							rankLore.add(ChatColor.GRAY + "the " + ChatColor.translateAlternateColorCodes('&', staffUtils.getRankObject(args[1])) + ChatColor.GRAY + " Rank on this server.");
							rankMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
							rankMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
							rankMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							rankMeta.addItemFlags((ItemFlag.HIDE_ATTRIBUTES));
							rankMeta.setLore(rankLore);
							rankToken.setItemMeta(rankMeta);
							target.getInventory().addItem(rankToken);
							plugin.getLogger().warning(ChatColor.translateAlternateColorCodes('&', "&cAtom has generated a Grant Token for the user " + staffUtils.getPlayerColor(target) + target.getName() + "&c for the rank " + staffUtils.getRankObject(args[1]) + "&c, it was created by " + staffUtils.getPlayerColor(sender) + sender.getName()));
						} else
							staffUtils.errorMessage(sender, "You have attempted to create an invalid Grant Token for the Grant: " + ChatColor.ITALIC + args[1] + ".");
					} else staffUtils.playerNotFound(sender);
				} else staffUtils.printUsage(sender, "granttoken", "<user> <grant>");
			}
		} else plugin.getLogger().info("You must be a player, as console execution is not supported yet.");
		return true;
	}
}
