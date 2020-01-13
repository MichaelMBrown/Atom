package net.excentrix.core;

import net.excentrix.core.Commands.*;
import net.excentrix.core.events.*;
import org.bukkit.ChatColor;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public final class Core extends JavaPlugin implements Listener, TabCompleter {
    public static ArrayList<Player> godList = new ArrayList<>();
    public static ArrayList<Player> freezeList = new ArrayList<>();
    public static Boolean chatSilenced;

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("kick").setExecutor(new kick());
        getCommand("report").setExecutor(new report());
        getCommand("helpop").setExecutor(new helpop());
        getCommand("fly").setExecutor(new flight());
        getCommand("setserver").setExecutor(new setServer());
        getCommand("god").setExecutor(new God());
        getCommand("sc").setExecutor(new staffchat());
        getCommand("heal").setExecutor(new heal());
        getCommand("gamemode").setExecutor(new gamemode());
        getCommand("tp").setExecutor(new teleport());
        getCommand("kill").setExecutor(new kill());
        getCommand("clarke").setExecutor(new clarke());
        getCommand("weather").setExecutor(new weather());
        getCommand("edit").setExecutor(new edit());
        getCommand("freeze").setExecutor(new freeze());
        getCommand("smite").setExecutor(new smite());
        getCommand("mutechat").setExecutor(new mutechat());
        getCommand("enderchest").setExecutor(new enderchest());
        getCommand("tphere").setExecutor(new teleportHere());
        getCommand("say").setExecutor(new say());
        getCommand("grant").setExecutor(new grant());
        getCommand("give").setExecutor(new give());
        getServer().getPluginManager().registerEvents(new godEvent(), this);
        getServer().getPluginManager().registerEvents(new mobSpawn(), this);
        getServer().getPluginManager().registerEvents(new freezeEvent(), this);
        getServer().getPluginManager().registerEvents(new deathEvents(), this);
        getServer().getPluginManager().registerEvents(new joinEvent(), this);
        getServer().getPluginManager().registerEvents(new playerTalk(), this);
        chatSilenced = getConfig().getBoolean("chat-silenced");
        if (chatSilenced) {
            getLogger().info(ChatColor.YELLOW + "The chat is " + ChatColor.RED + "disabled" + ChatColor.YELLOW + " as it was turned off, prior to reboot.");
        } else
            getLogger().info(ChatColor.YELLOW + "The chat is " + ChatColor.GREEN + "enabled" + ChatColor.YELLOW + " as it was turned on, prior to reboot.");

        //("staff").setExecutor(new staff());
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info(ChatColor.YELLOW + "Saving chat state to disk.");
        getConfig().set("chat-silenced", chatSilenced);
        getLogger().info(ChatColor.GREEN + "Success!");
    }
}
