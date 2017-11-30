package de.joo.lwcinteractflag;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import org.bukkit.Material;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

/**
 * Created by Johannes on 26.11.2017.
 */
public class LWCInteractConfig extends Configuration {

    protected LWCInteractConfig(Path configPath) {
        super(configPath);

        try {
            loadAndSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Comment("Whitelisted Materials (e.g. EnderChest")
    public List<Material> whitelist = Collections.singletonList(Material.ENDER_CHEST);

    @Comment("Only public protection")
    public boolean onlyPublicProtection = true;
}
