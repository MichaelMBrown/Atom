package net.excentrix.core.WatchDog;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static net.excentrix.core.utils.coreUtils.notifyStaff;

public class watchDogNotification implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public void watchDogCheckBanned(PlayerLoginEvent event) throws ParseException {
		if (event.getResult().equals(PlayerLoginEvent.Result.KICK_BANNED)) {
			try {
				BanList bannedPlayers = Bukkit.getBanList(BanList.Type.NAME);
				bannedPlayers.getBanEntry(event.getPlayer().getName()).getExpiration();
				Date currentTime = new Date();
				Date expiration = bannedPlayers.getBanEntry(event.getPlayer().getName()).getExpiration();
				long difference = expiration.getTime() - currentTime.getTime();
				//Prepare for time conversion!
				long diffDays = difference / (24 * 60 * 60 * 1000);
				long diffHours = difference / (60 * 60 * 1000) % 24;
				long diffMinutes = difference / (60 * 1000) % 60;
				long diffSeconds = difference / 1000 % 60;
				String expiryTime = diffDays + "d" + diffHours + "h" + diffMinutes + "m" + diffSeconds + "s";
				notifyStaff("watchdog", event.getPlayer().getName() + " " + "tried to login, but is currently banned, expires in: " + expiryTime);
			}catch (NullPointerException nullPointerException){
				notifyStaff("watchdog", event.getPlayer().getName() + " " + "tried to login, but is currently banned, expires in: never");
			}
			
		}
	}
}

