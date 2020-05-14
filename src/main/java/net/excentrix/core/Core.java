package net.excentrix.core;

import net.excentrix.core.Commands.*;
import net.excentrix.core.Prison.Commands.rankup;
import net.excentrix.core.enchants.telekinesis;
import net.excentrix.core.enchants.trueDamage;
import net.excentrix.core.events.*;
import net.excentrix.core.internalCommands.announceToStaff;
import net.excentrix.core.tabCompletionServices.*;
import net.excentrix.core.tasks.updateTablist;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public final class Core extends JavaPlugin implements Listener, TabCompleter {
    public static ArrayList<Player> godList = new ArrayList();
    public static ArrayList<Player> freezeList = new ArrayList();
    public static ArrayList<Player> scMuted = new ArrayList();
    public static ArrayList<Player> pmToggled = new ArrayList();
    public static ArrayList<Player> nowSpying = new ArrayList();
    public static ArrayList<Player> buildDenied = new ArrayList<>();
    public static Boolean chatSilenced;
    public static Boolean enchantSupport = false;
    public static Boolean isPrison = false;
    public static Location spawn;
    // Setup the Economy
    private static Economy econ = null;

    public Core() {
    }

    public static Economy getEcon() {
        return econ;
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            econ = economyProvider.getProvider();
        }

        return (econ != null);
    }

    public void onEnable() {
        //Starting Plugin
        getLogger().info("Enabling Clarke v" + getDescription().getVersion() + " on server " + getConfig().getString("server-name").toLowerCase());
        //Setup the custom enchant support
        enchantSupport = getConfig().getString("server-name").equalsIgnoreCase("skyblock") || getConfig().getString("server-name").equalsIgnoreCase("prison");
        isPrison = getConfig().getString("server-name").equalsIgnoreCase("prison");


        // Setup Economy
        setupEconomy();
        getConfig().options().copyDefaults();
        saveDefaultConfig();


        // Register Commands
        // Clarke Command
        getCommand("clarke").setExecutor(new clarke());
        getCommand("clarke").setTabCompleter(new clarkeCompletion());
        // Kick Command
        getCommand("kick").setExecutor(new kick());
        // Report Command
        getCommand("report").setExecutor(new report());
        getCommand("report").setTabCompleter(new reportCompletion());
        // Helpop Command
        getCommand("helpop").setExecutor(new helpop());
        // Flight Command
        getCommand("fly").setExecutor(new flight());
        // SetServer Command
        getCommand("setserver").setExecutor(new setServer());
        // God Command
        getCommand("god").setExecutor(new God());
        // StaffChat Command
        //getCommand("sc").setExecutor(new staffchat());
        // Heal Command
        getCommand("heal").setExecutor(new heal());
        // Gamemode Command
        getCommand("gamemode").setExecutor(new gamemode());
        getCommand("gamemode").setTabCompleter(new gamemodeCompletion());
        // TP Command
        getCommand("tp").setExecutor(new teleport());
        // Kill Command
        getCommand("kill").setExecutor(new kill());
        // Weather Command
        getCommand("weather").setExecutor(new weather());
        getCommand("weather").setTabCompleter(new weatherCompletion());
        // Edit Command
        getCommand("edit").setExecutor(new edit());
        getCommand("edit").setTabCompleter(new editCompletion());
        // Freeze Command
        getCommand("freeze").setExecutor(new freeze());
        // Smite Command
        getCommand("smite").setExecutor(new smite());
        // Mutechat Commmand
        //getCommand("mutechat").setExecutor(new mutechat());
        // Enderchest Command
        getCommand("enderchest").setExecutor(new enderchest());
        // TPHere Command
        getCommand("tphere").setExecutor(new teleportHere());
        // Say Command
        getCommand("say").setExecutor(new say());
        // Grant Command
        //getCommand("grant").setExecutor(new grant());
        //getCommand("grant").setTabCompleter(new grantCompletion());
        // Grants Command
        //getCommand("grants").setExecutor(new grants());
        // Give Command
        getCommand("give").setExecutor(new give());
        // Toggle StaffChat Command
        //getCommand("togglesc").setExecutor(new toggleSC());
        // Whisper Command
        //getCommand("whisper").setExecutor(new whisper());
        // TogglePM Command
        //getCommand("togglePM").setExecutor(new togglePM());
        // SocialSpy Command
        //getCommand("socialspy").setExecutor(new socialspy());
        // Setspawn Command
        getCommand("setspawn").setExecutor(new setSpawn());
        // Spawn Command
        getCommand("spawn").setExecutor(new spawn());
        // Build Command
        getCommand("build").setExecutor(new buildMode());
        //Balance Command
        getCommand("balance").setExecutor(new balance());
        //Shrug Command
        getCommand("shrug").setExecutor(new shrug());
        //Rankup Command
        getCommand("rankup").setExecutor(new rankup());


        // Internals :)
        getCommand("announceToStaff").setExecutor(new announceToStaff());
        getServer().getPluginManager().registerEvents(new godEvent(), this);
        getServer().getPluginManager().registerEvents(new mobSpawn(), this);
        getServer().getPluginManager().registerEvents(new freezeEvent(), this);
        getServer().getPluginManager().registerEvents(new deathEvents(), this);
        getServer().getPluginManager().registerEvents(new authEvent(), this);
        getServer().getPluginManager().registerEvents(new playerChatEvents(), this);
        getServer().getPluginManager().registerEvents(new portalEvent(), this);
        getServer().getPluginManager().registerEvents(new preventionMode(), this);
        getServer().getPluginManager().registerEvents(new rightVersion(), this);
        getServer().getPluginManager().registerEvents(new trueDamage(), this);
        getServer().getPluginManager().registerEvents(new telekinesis(), this);

        // Setup Global Chat
        chatSilenced = getConfig().getBoolean("chat-silenced");
        if (chatSilenced) {
            getLogger().info(ChatColor.YELLOW + "The chat is " + ChatColor.RED + "disabled" + ChatColor.YELLOW + " as it was turned off, prior to reboot.");
        } else {
            getLogger().info(ChatColor.YELLOW + "The chat is " + ChatColor.GREEN + "enabled" + ChatColor.YELLOW + " as it was turned on, prior to reboot.");
        }
        // Starting the Scoreboard Task
        BukkitTask updateSB = (new updateTablist(this)).runTaskTimerAsynchronously(this, 0L, 60L);

        // Assigning the Spawn Values
        try {
            spawn = getServer().getWorld(getConfig().getString("world")).getSpawnLocation();
        } catch (NullPointerException e) {
            getServer().getLogger().warning("World " + getConfig().getString("world") + " has an invalid Spawn Point.");
        }
    }

    public void onDisable() {
        getLogger().info(ChatColor.YELLOW + "Saving chat state to disk.");
        getConfig().set("chat-silenced", chatSilenced);
        getLogger().info(ChatColor.GREEN + "Success!");
    }
}
