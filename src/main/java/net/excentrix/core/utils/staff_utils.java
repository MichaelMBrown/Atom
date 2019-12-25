package net.excentrix.core.utils;

import net.excentrix.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

public class staff_utils {
    private static Plugin plugin = Core.getPlugin(Core.class);

    public static void sendSC_console(Player param, String[] args) {
        param.sendMessage(ChatColor.AQUA + "[S] " + ChatColor.DARK_AQUA + "[" + plugin.getConfig().getString("server-name") + "] " + ChatColor.RED + "Console" + ChatColor.GRAY + ":" + ChatColor.YELLOW + " " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
    }
    public static void sendSC(String player, Player param, String[] args) {
        param.sendMessage(ChatColor.AQUA + "[S] " + ChatColor.DARK_AQUA + "[" + plugin.getConfig().getString("server-name") + "] " + ChatColor.WHITE + player + ChatColor.GRAY + ":" + ChatColor.YELLOW + " " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
    }


    public static void scNotif(String sender, String args) {
        plugin.getLogger().info(ChatColor.GOLD + sender + ChatColor.GRAY + ": " + ChatColor.WHITE + args);
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("clarke.chat.staffchat")) {
                if (sender.equalsIgnoreCase("Console")) {
                    p.sendMessage(ChatColor.AQUA + "[S] " + ChatColor.DARK_AQUA + "[" + plugin.getConfig().getString("server-name") + "] " + ChatColor.RED + "Console" + ChatColor.GRAY + ":" + ChatColor.YELLOW + " " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));

                } else {
                    p.sendMessage(ChatColor.AQUA + "[S] " + ChatColor.DARK_AQUA + "[" + plugin.getConfig().getString("server-name") + "] " + ChatColor.WHITE + sender + ChatColor.GRAY + ":" + ChatColor.YELLOW + " " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));

                }
            }
        }
    }

    public static void noPerm(Player player) {
        player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
    }

    public static void playerNotFound(Player player) {
        player.sendMessage(ChatColor.RED + "There is no player by that name connected to this server!");
    }

    public static void printUsage(Player player, String command, String args) {
        player.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/" + command + " " + ChatColor.WHITE + args);
    }

    /*
     * Works from 1.0+.
     *
     * @param name new name of the player
     * @param player player to change the name of
     */
    @SuppressWarnings("unchecked")
    public static void changeName(String name, Player player) {
        try {
            Method getHandle = player.getClass().getMethod("getHandle");
            Object entityPlayer = getHandle.invoke(player);
            /*
             * These methods are no longer needed, as we can just access the
             * profile using handle.getProfile. Also, because we can just use
             * the method, which will not change, we don't have to do any
             * field-name look-ups.
             */
            boolean gameProfileExists = false;
            // Some 1.7 versions had the GameProfile class in a different package
            try {
                Class.forName("net.minecraft.util.com.mojang.authlib.GameProfile");
                gameProfileExists = true;
            } catch (ClassNotFoundException ignored) {

            }
            try {
                Class.forName("com.mojang.authlib.GameProfile");
                gameProfileExists = true;
            } catch (ClassNotFoundException ignored) {

            }
            if (!gameProfileExists) {
                /*
                 * Only 1.6 and lower servers will run this code.
                 *
                 * In these versions, the name wasn't stored in a GameProfile object,
                 * but as a String in the (final) name field of the EntityHuman class.
                 * Final (non-static) fields can actually be modified by using
                 * {@link java.lang.reflect.Field#setAccessible(boolean)}
                 */
                Field nameField = entityPlayer.getClass().getSuperclass().getDeclaredField("name");
                nameField.setAccessible(true);
                nameField.set(entityPlayer, name);
            } else {
                // Only 1.7+ servers will run this code
                Object profile = entityPlayer.getClass().getMethod("getProfile").invoke(entityPlayer);
                Field ff = profile.getClass().getDeclaredField("name");
                ff.setAccessible(true);
                ff.set(profile, name);
            }
            // In older versions, Bukkit.getOnlinePlayers() returned an Array instead of a Collection.
            if (Bukkit.class.getMethod("getOnlinePlayers").getReturnType() == Collection.class) {
                Collection<? extends Player> players = (Collection<? extends Player>) Bukkit.class.getMethod("getOnlinePlayers").invoke(null);
                for (Player p : players) {
                    p.hidePlayer(player);
                    p.showPlayer(player);
                }
            } else {
                Player[] players = ((Player[]) Bukkit.class.getMethod("getOnlinePlayers").invoke(null));
                for (Player p : players) {
                    p.hidePlayer(player);
                    p.showPlayer(player);
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException e) {
            /*
             * Merged all the exceptions. Less lines
             */
            e.printStackTrace();
        }
    }

}
