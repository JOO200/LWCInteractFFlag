package de.joo.lwcinteractflag;

import de.exlll.configlib.annotation.Comment;
import de.exlll.configlib.configs.yaml.YamlConfiguration;

import java.nio.file.Path;

/**
 * Created by Johannes on 26.11.2017.
 *
 */
public class LWCInteractConfig extends YamlConfiguration {

    protected LWCInteractConfig(Path configPath) {
        super(configPath);

        loadAndSave();
    }

    @Comment("Only public protection")
    public boolean onlyPublicProtection = true;
}
