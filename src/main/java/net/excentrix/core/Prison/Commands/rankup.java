package net.excentrix.core.Prison.Commands;

import net.excentrix.core.Core;
import net.excentrix.core.Prison.prisonUtils;
import net.excentrix.core.utils.staffUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.MetaNode;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class rankup implements CommandExecutor {
    Economy econ = Core.getEcon();
    LuckPerms api = LuckPermsProvider.get();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (Core.isPrison) {
            if (prisonUtils.getPrisonRank(player).equals(ChatColor.RED + "D4")) {
                prisonUtils.printMessage(player, "&cYou've reached the max rankup. Congratulations!");
            } else {
                EconomyResponse canRank = econ.withdrawPlayer(player, prisonUtils.rankValue(player));
                double newRank;
                newRank = prisonUtils.getPrisonRankInt(player) + 1;
                String nextRanked = prisonUtils.getRankFromInt(player, newRank);
                if (canRank.transactionSuccess()) {
                    User user = api.getUserManager().getUser(player.getUniqueId());
                    user.data().remove(MetaNode.builder("prison_rank", prisonUtils.getRankFromInt(player, prisonUtils.getPrisonRankInt(player))).build());
                    user.data().add(MetaNode.builder("prison_rank", nextRanked).build());
                    api.getUserManager().saveUser(user);
                    //getServer().dispatchCommand(getServer().getConsoleSender(), "lp user " + player.getName() + " meta set prison_rank "+nextRanked);
                    prisonUtils.printMessage(player, "You ranked up to &f" + nextRanked + "&7 for &a" + econ.format(prisonUtils.rankValue(player)));
                } else {
                    prisonUtils.printMessage(player, "&cYou cannot afford to rankup to &f" + nextRanked);
                    prisonUtils.printMessage(player, "You still need &c" + econ.format(prisonUtils.rankValue(player) - econ.getBalance(player)));
                }
            }
        } else staffUtils.errorMessage(player, "This command is not available on this server!");
        return true;
    }
}
