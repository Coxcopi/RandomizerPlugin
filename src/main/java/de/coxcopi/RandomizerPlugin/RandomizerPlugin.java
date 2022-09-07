package de.coxcopi.RandomizerPlugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class RandomizerPlugin extends JavaPlugin {

    private Timer timer;
    private TimerTask task;
    private int timerDelay = 15;

    @Override
    public void onEnable() {

        this.getCommand("randomizer").setExecutor(new RandomizerCommand(this));
        this.getCommand("randomizer").setTabCompleter(new CommandCompleter());

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                randomize();
            }
        };
    }

    @Override
    public void onDisable() {
        cancelTimer();
    }

    private void randomize() {
        for (Player player : getServer().getOnlinePlayers()) {
            Material[] materials = Material.values();
            int randomInt = ThreadLocalRandom.current().nextInt(0, materials.length);
            player.getInventory().addItem(new ItemStack(materials[randomInt]));
        }
    }

    public void startTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                randomize();
            }
        };
        timer.scheduleAtFixedRate(task, timerDelay * 1000, timerDelay * 1000);
    }

    public void cancelTimer() {
        timer.cancel();
        task.cancel();
    }

    public void setTimerDelay(int newDelay) {
        timerDelay = newDelay;
    }

}
