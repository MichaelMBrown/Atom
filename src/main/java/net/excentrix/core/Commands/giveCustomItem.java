package net.excentrix.core.Commands;

import net.excentrix.core.utils.coreUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class giveCustomItem implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (coreUtils.getRankInteger(commandSender.getName()) >= 3) {
			if (strings.length != 1) {
				coreUtils.printUsage((Player) commandSender, "givecustomitem", "<ITEM_NAME>");
			} else {
				switch (strings[0].toUpperCase()) {
					case "SWORD_OF_THE_UNIVERSE":
						ItemStack sword_of_the_universe = new ItemStack(Material.GOLDEN_SWORD, 1);
						ItemMeta sword_of_the_universe_ItemMeta = sword_of_the_universe.getItemMeta();
						sword_of_the_universe_ItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lS&c&lw&6&lo&e&lr&a&ld &b&lo&3&lf &9&lt&d&lh&9&le &3&lU&b&ln&a&li&e&lv&6&le&c&lr&4&ls&c&le"));
						sword_of_the_universe_ItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//						sword_of_the_universe_ItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
						sword_of_the_universe_ItemMeta.setUnbreakable(true);
						ArrayList<String> sword_of_the_universe_ItemLore = new ArrayList<>();
						sword_of_the_universe_ItemLore.add(ChatColor.translateAlternateColorCodes('&',"&7Damage: &d+∞"));
						sword_of_the_universe_ItemLore.add(ChatColor.translateAlternateColorCodes('&',"&7Skill: &d-∞"));
						sword_of_the_universe_ItemLore.add(ChatColor.translateAlternateColorCodes('&',"&7Clout: &d+100"));
						sword_of_the_universe_ItemLore.add("");
						sword_of_the_universe_ItemLore.add(ChatColor.translateAlternateColorCodes('&',"&9Sharpness L"));
						sword_of_the_universe_ItemLore.add(ChatColor.translateAlternateColorCodes('&',"&7Increases melee damage dealt by &a250%."));
						sword_of_the_universe_ItemLore.add("");
						sword_of_the_universe_ItemLore.add(ChatColor.translateAlternateColorCodes('&',"&eOi you! Yes you. What are you"));
						sword_of_the_universe_ItemLore.add(ChatColor.translateAlternateColorCodes('&',"&elooking at? Yes this sword has ∞"));
						sword_of_the_universe_ItemLore.add(ChatColor.translateAlternateColorCodes('&',"&edamage. Kinda overkill? I'm lazy"));
						sword_of_the_universe_ItemLore.add(ChatColor.translateAlternateColorCodes('&',"&eok. &c(╯°□°)╯&7︵ ┻━┻"));
						sword_of_the_universe_ItemLore.add("");
						sword_of_the_universe_ItemLore.add(ChatColor.translateAlternateColorCodes('&',"&c&lSPECIAL SWORD"));
						sword_of_the_universe.setItemMeta(sword_of_the_universe_ItemMeta);
						sword_of_the_universe.setLore(sword_of_the_universe_ItemLore);
						Player player = (Player) commandSender;
						player.getInventory().addItem(sword_of_the_universe);
						coreUtils.informativeMessage(player,"Poof!");
						break;
				}
			}
		} else coreUtils.noPerm((Player) commandSender);
		return true;
	}
}
