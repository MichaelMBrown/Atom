package net.excentrix.core.messagingServices;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class messageUtils {
    private static final Plugin plugin = Core.getPlugin(Core.class);

    public static boolean messageEligibility(Player player) {
        return !Core.pmToggled.contains(player);
    }

    public static void messagePlayer(Player sender, Player receipt, String message) {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (Core.nowSpying.contains(p) && p != sender && p != receipt) {
                staffUtils.informativeMessage(p, "&9(" + staffUtils.retrievePlayerColour(sender) + sender.getName() + "&9 to " + staffUtils.retrievePlayerColour(receipt) + receipt.getName() + "&9) " + message);
            }
        }
        plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&c" + sender.getName() + " &f-> &c" + receipt.getName() + " &f" + message));
        staffUtils.informativeMessage(receipt, "&3(From " + staffUtils.retrievePlayerColour(sender) + sender.getName() + "&3) " + message);
        staffUtils.informativeMessage(receipt, "&3(To " + staffUtils.retrievePlayerColour(receipt) + receipt.getName() + "&3) " + message);
    }
}
