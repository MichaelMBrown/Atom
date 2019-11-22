package net.excentrix.core;

import net.excentrix.core.Commands.*;
import net.excentrix.core.utils.staff_utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public final class Core extends JavaPlugin implements Listener, TabCompleter {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("kick").setExecutor(new kick());
        getCommand("report").setExecutor(new report());
        getCommand("helpop").setExecutor(new helpop());
        getCommand("fly").setExecutor(new flight());
        getCommand("setserver").setExecutor(new setServer());
        getCommand("god").setExecutor(new God());

        //("staff").setExecutor(new staff());
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if((sender instanceof Player)) {
            if (sender.hasPermission("clarke.chat.staffchat")) {
                if (command.getName().equalsIgnoreCase("sc")) {
                    if (args.length > 0) {
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("clarke.chat.staffchat")) {
                                staff_utils.sendSC(((Player) sender).getDisplayName(), p, args);
                            }
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Usage: /sc <message>");
                    }
                }
            }else sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
        }else if((sender instanceof ConsoleCommandSender)){
            if(command.getName().equalsIgnoreCase("sc")){
                if(args.length > 0){
                    for (final Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("clarke.chat.staffchat")) {
                            staff_utils.sendSC_console(p, args);
                        }
                    }
                }else sender.sendMessage(ChatColor.RED + "Usage: /sc <message>");
            }
        }
        return true;
    }
}
