package net.excentrix.core.tabCompletionServices;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class gamemodeCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> possibleGamemodes = new ArrayList<>();
        if (sender.hasPermission("clarke.command.gamemode")) {
            if (args.length == 1) {
                for (GameMode c : GameMode.values()) {
                    possibleGamemodes.add(c.toString().toLowerCase());
                }
                return possibleGamemodes;
            }
        }
        return null;
    }
}
