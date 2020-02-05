package net.excentrix.core;

import net.excentrix.core.Commands.*;
import net.excentrix.core.events.*;
import net.excentrix.core.messagingService.socialspy;
import net.excentrix.core.messagingService.togglePM;
import net.excentrix.core.messagingService.whisper;
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
        this.getCommand("kick").setExecutor(new kick());
        this.getCommand("report").setExecutor(new report());
        this.getCommand("helpop").setExecutor(new helpop());
        this.getCommand("fly").setExecutor(new flight());
        this.getCommand("setserver").setExecutor(new setServer());
        this.getCommand("god").setExecutor(new God());
        this.getCommand("sc").setExecutor(new staffchat());
        this.getCommand("heal").setExecutor(new heal());
        this.getCommand("gamemode").setExecutor(new gamemode());
        this.getCommand("tp").setExecutor(new teleport());
        this.getCommand("kill").setExecutor(new kill());
        this.getCommand("clarke").setExecutor(new clarke());
        this.getCommand("weather").setExecutor(new weather());
        this.getCommand("edit").setExecutor(new edit());
        this.getCommand("freeze").setExecutor(new freeze());
        this.getCommand("smite").setExecutor(new smite());
        this.getCommand("mutechat").setExecutor(new mutechat());
        this.getCommand("enderchest").setExecutor(new enderchest());
        this.getCommand("tphere").setExecutor(new teleportHere());
        this.getCommand("say").setExecutor(new say());
        this.getCommand("grant").setExecutor(new grant());
        this.getCommand("grants").setExecutor(new grants());
        this.getCommand("give").setExecutor(new give());
        this.getCommand("togglesc").setExecutor(new toggleSC());
        this.getCommand("whisper").setExecutor(new whisper());
        this.getCommand("togglePM").setExecutor(new togglePM());
        this.getCommand("socialspy").setExecutor(new socialspy());
        this.getServer().getPluginManager().registerEvents(new godEvent(), this);
        this.getServer().getPluginManager().registerEvents(new mobSpawn(), this);
        this.getServer().getPluginManager().registerEvents(new freezeEvent(), this);
        this.getServer().getPluginManager().registerEvents(new deathEvents(), this);
        this.getServer().getPluginManager().registerEvents(new joinEvent(), this);
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
