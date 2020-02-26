package net.excentrix.core;

import net.excentrix.core.Commands.*;
import net.excentrix.core.events.*;
import net.excentrix.core.internalCommands.announceToStaff;
import net.excentrix.core.messagingService.socialspy;
import net.excentrix.core.messagingService.togglePM;
import net.excentrix.core.messagingService.whisper;
import net.excentrix.core.tabCompletionServices.*;
import net.excentrix.core.tasks.updateTablist;
import org.bukkit.ChatColor;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public final class Core extends JavaPlugin implements Listener, TabCompleter {
    public static ArrayList<Player> godList = new ArrayList();
    public static ArrayList<Player> freezeList = new ArrayList();
    public static ArrayList<Player> scMuted = new ArrayList();
    public static ArrayList<Player> pmToggled = new ArrayList();
    public static ArrayList<Player> nowSpying = new ArrayList();
    public static Boolean chatSilenced;

    public Core() {
    }

    public void onEnable() {
        // Clarke Command
        this.getCommand("clarke").setExecutor(new clarke());
        this.getCommand("clarke").setTabCompleter(new clarkeCompletion());
        // Kick Command
        this.getCommand("kick").setExecutor(new kick());
        // Report Command
        this.getCommand("report").setExecutor(new report());
        this.getCommand("report").setTabCompleter(new reportCompletion());
        // Helpop Command
        this.getCommand("helpop").setExecutor(new helpop());
        // Flight Command
        this.getCommand("fly").setExecutor(new flight());
        // SetServer Command
        this.getCommand("setserver").setExecutor(new setServer());
        // God Command
        this.getCommand("god").setExecutor(new God());
        // StaffChat Command
        this.getCommand("sc").setExecutor(new staffchat());
        // Heal Command
        this.getCommand("heal").setExecutor(new heal());
        // Gamemode Command
        this.getCommand("gamemode").setExecutor(new gamemode());
        this.getCommand("gamemode").setTabCompleter(new gamemodeCompletion());
        // TP Command
        this.getCommand("tp").setExecutor(new teleport());
        // Kill Command
        this.getCommand("kill").setExecutor(new kill());
        // Weather Command
        this.getCommand("weather").setExecutor(new weather());
        this.getCommand("weather").setTabCompleter(new weatherCompletion());
        // Edit Command
        this.getCommand("edit").setExecutor(new edit());
        this.getCommand("edit").setTabCompleter(new editCompletion());
        // Freeze Command
        this.getCommand("freeze").setExecutor(new freeze());
        // Smite Command
        this.getCommand("smite").setExecutor(new smite());
        // Mutechat Commmand
        this.getCommand("mutechat").setExecutor(new mutechat());
        // Enderchest Command
        this.getCommand("enderchest").setExecutor(new enderchest());
        // TPHere Command
        this.getCommand("tphere").setExecutor(new teleportHere());
        // Say Command
        this.getCommand("say").setExecutor(new say());
        // Grant Command
        this.getCommand("grant").setExecutor(new grant());
        this.getCommand("grant").setTabCompleter(new grantCompletion());
        // Grants Command
        this.getCommand("grants").setExecutor(new grants());
        // Give Command
        this.getCommand("give").setExecutor(new give());
        // Toggle StaffChat Command
        this.getCommand("togglesc").setExecutor(new toggleSC());
        // Whisper Command
        this.getCommand("whisper").setExecutor(new whisper());
        // TogglePM Command
        this.getCommand("togglePM").setExecutor(new togglePM());
        // SocialSpy Command
        this.getCommand("socialspy").setExecutor(new socialspy());
        // Internals :)
        this.getCommand("announceToStaff").setExecutor(new announceToStaff());
        this.getServer().getPluginManager().registerEvents(new godEvent(), this);
        this.getServer().getPluginManager().registerEvents(new mobSpawn(), this);
        this.getServer().getPluginManager().registerEvents(new freezeEvent(), this);
        this.getServer().getPluginManager().registerEvents(new deathEvents(), this);
        this.getServer().getPluginManager().registerEvents(new authEvent(), this);
        this.getServer().getPluginManager().registerEvents(new playerTalk(), this);
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        chatSilenced = this.getConfig().getBoolean("chat-silenced");
        if (chatSilenced) {
            this.getLogger().info(ChatColor.YELLOW + "The chat is " + ChatColor.RED + "disabled" + ChatColor.YELLOW + " as it was turned off, prior to reboot.");
        } else {
            this.getLogger().info(ChatColor.YELLOW + "The chat is " + ChatColor.GREEN + "enabled" + ChatColor.YELLOW + " as it was turned on, prior to reboot.");
        }

        BukkitTask updateSB = (new updateTablist(this)).runTaskTimerAsynchronously(this, 0L, 60L);
    }

    public void onDisable() {
        this.getLogger().info(ChatColor.YELLOW + "Saving chat state to disk.");
        this.getConfig().set("chat-silenced", chatSilenced);
        this.getLogger().info(ChatColor.GREEN + "Success!");
    }
}
