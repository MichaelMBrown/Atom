package net.excentrix.core;

import net.excentrix.core.Commands.helpop;
import net.excentrix.core.Commands.kick;
import net.excentrix.core.Commands.report;
import net.excentrix.core.utils.staff_utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class Core extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("kick").setExecutor(new kick());
        getCommand("report").setExecutor(new report());
        getCommand("helpop").setExecutor(new helpop());
        //("staff").setExecutor(new staff()); Deprecated
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        //player.sendMessage("Welcome back");
        //player.setOp(true);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if((sender instanceof Player)) {
            if (sender.hasPermission("excentrix.chat.staffchat")) {
                if (command.getName().equalsIgnoreCase("sc")) {
                    if (args.length > 0) {
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("excentrix.chat.staffchat")) {
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
                        if (p.hasPermission("excentrix.chat.staffchat")) {
                            staff_utils.sendSC_console(p, args);
                        }
                    }
                }else sender.sendMessage(ChatColor.RED + "Usage: /sc <message>");
            }
        }
        return true;
    }
}
