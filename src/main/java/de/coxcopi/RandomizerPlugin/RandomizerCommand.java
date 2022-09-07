package de.coxcopi.RandomizerPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandomizerCommand implements CommandExecutor {

    private final RandomizerPlugin randomizerPlugin;

    RandomizerCommand(RandomizerPlugin plugin) {
        randomizerPlugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            return false;
        }
        String arg = strings[0].toLowerCase();
        boolean isPlayer = commandSender instanceof Player;
        if (arg.equals("start")) {
            randomizerPlugin.startTimer();
            if (isPlayer) {
                Player player = (Player) commandSender;
                player.sendMessage("Randomizer started.");
            }
            return true;
        }
        if (arg.equals("stop")) {
            randomizerPlugin.cancelTimer();
            if (isPlayer) {
                Player player = (Player) commandSender;
                player.sendMessage("Randomizer stopped.");
            }
            return true;
        }
        return false;
    }
}
