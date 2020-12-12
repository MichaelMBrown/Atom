package net.excentrix.core.tabCompletionServices;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class reportCompletion implements TabCompleter {
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 2) {
			List<String> reportableTypes = new ArrayList<>();
			reportableTypes.add("KillAura");
			reportableTypes.add("Flight");
			reportableTypes.add("AntiKnockback");
			reportableTypes.add("Criticals");
			reportableTypes.add("BunnyHop");
			reportableTypes.add("Speed");
			reportableTypes.add("Spamming");
			return reportableTypes;
		}
		
		return null;
	}
}
