package de.joo.lwcinteractflag;

import com.griefcraft.lwc.LWC;
import com.griefcraft.model.Protection;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.*;
import com.sk89q.worldguard.bukkit.event.block.UseBlockEvent;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * Created by Johannes on 22.11.2017.
 */
public class LWCInteractListener implements Listener {
    private LWCInteractConfig config;

    public LWCInteractListener(LWCInteractConfig config) {
        this.config = config;
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onUseBlockEvent(UseBlockEvent event) {
        Player player = event.getCause().getFirstPlayer();
        if(player == null) return;
        final LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        Block block = event.getBlocks().stream().findFirst().orElse(null);
        if(block == null) return;
        RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
        StateFlag.State state = query.queryState
                (BukkitAdapter.adapt(block.getLocation()), localPlayer, LWCInteractPlugin.LWC_INTERACT_FLAG);
        if(state != StateFlag.State.ALLOW) return;

        if(LWCInteractPlugin.whitelist.contains(block.getType())) event.setResult(Event.Result.ALLOW);
        if(!LWC.getInstance().isProtectable(block)) return;
        Protection protection = LWC.getInstance().findProtection(block);
        if(protection == null) return;
        if(protection.getType() != Protection.Type.PUBLIC && config.onlyPublicProtection) return;
        if(LWC.getInstance().canAccessProtection(player, protection)) event.setResult(Event.Result.ALLOW);
    }
}
