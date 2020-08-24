package net.excentrix.core.tabCompletionServices;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class weatherCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> weatherTypes = new ArrayList<>();
        List<String> worldList = new ArrayList<>();
        if (sender.hasPermission("atom.command.weather")) {
            if (args.length == 1) {
                weatherTypes.add("storm");
                weatherTypes.add("clear");
                return weatherTypes;
            }
            if (args.length == 2) {
                for (final World world : Bukkit.getWorlds()) {
                    worldList.add(world.getName());
                }
                return worldList;
            }
        }
        return null;
    }
}
