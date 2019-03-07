package de.joo.lwcinteractflag;

import de.exlll.configlib.annotation.Comment;
import de.exlll.configlib.configs.yaml.YamlConfiguration;
import org.bukkit.Material;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

/**
 * Created by Johannes on 26.11.2017.
 *
 */
public class LWCInteractConfig extends YamlConfiguration {

    protected LWCInteractConfig(Path configPath) {
        super(configPath);

        loadAndSave();
    }

    @Comment("Whitelisted Materials (e.g. EnderChest")
    public List<Material> whitelist = Collections.singletonList(Material.ENDER_CHEST);

    @Comment("Only public protection")
    public boolean onlyPublicProtection = true;
}
