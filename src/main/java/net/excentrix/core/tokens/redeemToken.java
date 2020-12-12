package net.excentrix.core.tokens;

import net.excentrix.core.utils.staffUtils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class redeemToken implements Listener {
	@EventHandler(ignoreCancelled = true)
	public void onBlockPlace(BlockPlaceEvent event) {
		try {
			String itemName = ChatColor.stripColor(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName());
			if (itemName.contains("RANK TOKEN")) {
				event.setCancelled(true);
				String pattern = "(?<=: ).*";
				Pattern p = Pattern.compile(pattern);
				Matcher matcher = p.matcher(itemName);
				while (matcher.find()) {
					for (int i = 1; i <= matcher.groupCount(); i++) {
						staffUtils.informativeMessage(event.getPlayer(), "It worked, I think: " + matcher.group(0));
					}
				}
			}
		} catch (NullPointerException | IllegalStateException ignored) {
		}
	}
}
