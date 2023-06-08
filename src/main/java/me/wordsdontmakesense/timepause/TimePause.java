package me.wordsdontmakesense.timepause;

import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class TimePause extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("TimePause+: Enabled!");
        updateCycle(false);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("TimePause+: Disabled!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        updateCycle(true);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        if(!(getServer().getOnlinePlayers().size() > 0))
        {
            updateCycle(false);
        }
    }

    private void updateCycle(boolean value)
    {
        for(World world : getServer().getWorlds())
        {
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, value);
        }
        if(value)
        {
            getServer().getConsoleSender().sendMessage("Daylight Cycle Resumed");
        } else {
            getServer().getConsoleSender().sendMessage("Daylight Cycle Paused");
        }
    }
}
