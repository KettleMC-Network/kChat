package net.kettlemc.kchat.config;

import io.github.almightysatan.jaskl.Config;
import io.github.almightysatan.jaskl.entries.BooleanConfigEntry;
import io.github.almightysatan.jaskl.entries.StringConfigEntry;
import io.github.almightysatan.jaskl.hocon.HoconConfig;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PluginConfig {

    private static final Path CONFIG_PATH = Paths.get("plugins", "Plugin", "config.conf");
    private static final Config CONFIG = HoconConfig.of(CONFIG_PATH.toFile(), "Config for example values");

    public static final StringConfigEntry CHAT_FORMAT = StringConfigEntry.of(CONFIG, "chat.format", "The chat format to use.", "%prefix% &8| &7%s &8» &r%s");
    public static final BooleanConfigEntry USE_MINIMESSAGES = BooleanConfigEntry.of(CONFIG, "chat.minimessages", "Whether or not to use MiniMessage in chat.", false);
    public static final StringConfigEntry DEFAULT_PREFIX = StringConfigEntry.of(CONFIG, "chat.default.prefix", "The default prefix to use", "&7Player");
    public static final StringConfigEntry DEFAULT_SUFFIX = StringConfigEntry.of(CONFIG, "chat.default.suffix", "The default suffix to use", "&7Player");


    private PluginConfig() {
    }

    public static boolean load() {
        try {
            CONFIG.load();
            CONFIG.write();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void unload() {
        CONFIG.close();
    }

    public static boolean write() {
        try {
            CONFIG.write();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
