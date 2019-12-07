package net.excentrix.core;

import net.excentrix.core.Commands.*;
import net.excentrix.core.events.godEvent;
import net.excentrix.core.events.mobSpawn;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public final class Core extends JavaPlugin implements Listener, TabCompleter {
    public static ArrayList<Player> godList = new ArrayList<>();
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
        getServer().getPluginManager().registerEvents(new godEvent(), this);
        getServer().getPluginManager().registerEvents(new mobSpawn(), this);

        //("staff").setExecutor(new staff());
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
