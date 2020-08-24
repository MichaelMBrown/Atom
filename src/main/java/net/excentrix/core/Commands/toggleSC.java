package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class toggleSC implements CommandExecutor {
    private static final Plugin plugin = Core.getPlugin(Core.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player sender = (Player) commandSender;
        if (!(commandSender.hasPermission("atom.chat.staffchat"))) {
            staffUtils.noPerm((Player) commandSender);
            return true;
        } else {
            if (Core.scMuted.contains(sender)) {
                staffUtils.informativeMessage(sender, "&aYou've enabled staff messages.");
                Core.scMuted.remove(sender);
            } else {
                staffUtils.informativeMessage(sender, "&cYou've disabled staff messages.");
                Core.scMuted.add(sender);
            }
        }
        return true;
    }
}
