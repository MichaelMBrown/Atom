package net.excentrix.core.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.utils.staffUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class balance implements CommandExecutor {
    Economy econ = Core.getEcon();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                Player targetPlayer = Bukkit.getPlayerExact(args[0]);
                Player target = staffUtils.findPlayer((Player) sender, targetPlayer);
                if (target != null) {
                    staffUtils.informativeMessage((Player) sender, "&e" + target.getName() + "&7 has a balance of &a" + econ.format(econ.getBalance(target)));
                } else staffUtils.playerNotFound((Player) sender);
            } else {
                staffUtils.informativeMessage((Player) sender, "&eYou&7 have a balance of &a" + econ.format(econ.getBalance(player)));
            }
        }
        return true;
    }
}
