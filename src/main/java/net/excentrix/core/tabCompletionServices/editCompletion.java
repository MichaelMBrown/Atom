package net.excentrix.core.tabCompletionServices;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class editCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> dataTypes = new ArrayList<>();
        List<String> configOptions = new ArrayList<>();
        List<String> dataValues = new ArrayList<>();
        if (sender.hasPermission("clarke.command.edit")) {
            if (args.length == 1) {
                dataTypes.add("boolean");
                return dataTypes;
            }
            if (args.length == 2) {
                configOptions.add("mobAI");
                configOptions.add("disableDrowned");
                configOptions.add("doInsomnia");
                return configOptions;
            }
            if (args.length == 3) {
                dataValues.add("true");
                dataValues.add("false");
                return dataValues;
            }
        }
        return null;
    }
}
