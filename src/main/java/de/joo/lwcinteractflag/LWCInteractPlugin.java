package de.joo.lwcinteractflag;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Paths;

/**
 * Created by Johannes on 22.11.2017.
 */
public class LWCInteractPlugin extends JavaPlugin {
    public static final Flag LWC_INTERACT_FLAG = new StateFlag("lwc-interact", true);
    private LWCInteractListener listener;
    private LWCInteractConfig config;

    @Override
    public void onEnable() {
        config = new LWCInteractConfig(Paths.get(getDataFolder().getPath(),"config.yml"));
        getLogger().info("Registered Listener.");
        listener = new LWCInteractListener(config);
        getServer().getPluginManager().registerEvents(listener, this);
    }

    @Override
    public void onLoad() {
        WorldGuardPlugin plugin = WGBukkit.getPlugin();
        if(plugin == null) {
            return;
        }
        FlagRegistry flagRegistry = plugin.getFlagRegistry();
        getLogger().info("Registered Flag.");
        try {
            flagRegistry.register(LWC_INTERACT_FLAG);
        } catch (FlagConflictException e) {
            getLogger().severe("Flag already registered.");
        }
    }
}
