package net.excentrix.core.Prison;

import net.excentrix.core.Core;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.query.QueryOptions;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class prisonUtils {
    static LuckPerms api = LuckPermsProvider.get();
    Economy econ = Core.getEcon();

    public static String getPrisonRank(Player player) {
        QueryOptions queryOptions = api.getContextManager().getQueryOptions(player);
        CachedMetaData metaData = api.getUserManager().getUser(player.getName()).getCachedData().getMetaData(queryOptions);
        String prisonRank = "null";
        switch (metaData.getMetaValue("prison_rank")) {
            case "A1":
                prisonRank = ChatColor.GREEN + "A1";
                break;
            case "A2":
                prisonRank = ChatColor.GREEN + "A2";
                break;
            case "A3":
                prisonRank = ChatColor.GREEN + "A3";
                break;
            case "A4":
                prisonRank = ChatColor.GREEN + "A4";
                break;
            case "B1":
                prisonRank = ChatColor.AQUA + "B1";
                break;
            case "B2":
                prisonRank = ChatColor.AQUA + "B2";
                break;
            case "B3":
                prisonRank = ChatColor.AQUA + "B3";
                break;
            case "B4":
                prisonRank = ChatColor.AQUA + "B4";
                break;
            case "C1":
                prisonRank = ChatColor.YELLOW + "C1";
                break;
            case "C2":
                prisonRank = ChatColor.YELLOW + "C2";
                break;
            case "C3":
                prisonRank = ChatColor.YELLOW + "C3";
                break;
            case "C4":
                prisonRank = ChatColor.YELLOW + "C4";
                break;
            case "D1":
                prisonRank = ChatColor.RED + "D1";
                break;
            case "D2":
                prisonRank = ChatColor.RED + "D2";
                break;
            case "D3":
                prisonRank = ChatColor.RED + "D3";
                break;
            case "D4":
                prisonRank = ChatColor.RED + "D4";
                break;
        }
        return prisonRank;
    }

    public static double rankValue(Player player) {
        double rankCost = 0.00;
        QueryOptions queryOptions = api.getContextManager().getQueryOptions(player);
        CachedMetaData metaData = api.getUserManager().getUser(player.getName()).getCachedData().getMetaData(queryOptions);
        switch (metaData.getMetaValue("prison_rank")) {
            case "A1":
                rankCost = 1200000;
                break;
            case "A2":
                rankCost = 3450000;
                break;
            case "A3":
                rankCost = 6750000;
                break;
            case "A4":
                rankCost = 10500000;
                break;
            case "B1":
                rankCost = 140250000;
                break;
            case "B2":
                rankCost = 20500000;
                break;
            case "B3":
                rankCost = 27000000;
                break;
            case "B4":
                rankCost = 35750000;
                break;
            case "C1":
                rankCost = 45950000;
                break;
            case "C2":
                rankCost = 50000000;
                break;
            case "C3":
                rankCost = 87500000;
                break;
            case "C4":
                rankCost = 153125000;
                break;
            case "D1":
                rankCost = 267968750;
                break;
            case "D2":
                rankCost = 468945312;
                break;
            case "D3":
                rankCost = 820654296;
                break;
            case "D4":
                rankCost = 0;
                break;
        }
        return rankCost;
    }

    public static double getPrisonRankInt(Player player) {
        {
            QueryOptions queryOptions = api.getContextManager().getQueryOptions(player);
            CachedMetaData metaData = api.getUserManager().getUser(player.getName()).getCachedData().getMetaData(queryOptions);
            int prisonRankInt = 0;
            switch (metaData.getMetaValue("prison_rank")) {
                case "A1":
                    prisonRankInt = 1;
                    break;
                case "A2":
                    prisonRankInt = 2;
                    break;
                case "A3":
                    prisonRankInt = 3;
                    break;
                case "A4":
                    prisonRankInt = 4;
                    break;
                case "B1":
                    prisonRankInt = 5;
                    break;
                case "B2":
                    prisonRankInt = 6;
                    break;
                case "B3":
                    prisonRankInt = 7;
                    break;
                case "B4":
                    prisonRankInt = 8;
                    break;
                case "C1":
                    prisonRankInt = 9;
                    break;
                case "C2":
                    prisonRankInt = 10;
                    break;
                case "C3":
                    prisonRankInt = 11;
                    break;
                case "C4":
                    prisonRankInt = 12;
                    break;
                case "D1":
                    prisonRankInt = 13;
                    break;
                case "D2":
                    prisonRankInt = 14;
                    break;
                case "D3":
                    prisonRankInt = 15;
                    break;
                case "D4":
                    prisonRankInt = 16;
                    break;
            }

            return prisonRankInt;
        }
    }

    public static String getRankFromInt(Player player, double rankInt) {
        {
            QueryOptions queryOptions = api.getContextManager().getQueryOptions(player);
            CachedMetaData metaData = api.getUserManager().getUser(player.getName()).getCachedData().getMetaData(queryOptions);
            String prisonRanked = null;
            switch ((int) rankInt) {
                case 1:
                    prisonRanked = "A1";
                    break;
                case 2:
                    prisonRanked = "A2";
                    break;
                case 3:
                    prisonRanked = "A3";
                    break;
                case 4:
                    prisonRanked = "A4";
                    break;
                case 5:
                    prisonRanked = "B1";
                    break;
                case 6:
                    prisonRanked = "B2";
                    break;
                case 7:
                    prisonRanked = "B3";
                    break;
                case 8:
                    prisonRanked = "B4";
                    break;
                case 9:
                    prisonRanked = "C1";
                    break;
                case 10:
                    prisonRanked = "C2";
                    break;
                case 11:
                    prisonRanked = "C3";
                    break;
                case 12:
                    prisonRanked = "C4";
                    break;
                case 13:
                    prisonRanked = "D1";
                    break;
                case 14:
                    prisonRanked = "D2";
                    break;
                case 15:
                    prisonRanked = "D3";
                    break;
                case 16:
                    prisonRanked = "D4";
                    break;
            }
            return prisonRanked;
        }
    }

    public static void printMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lPrison &7» " + message));
    }

}
