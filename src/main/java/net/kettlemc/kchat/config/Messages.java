package net.kettlemc.kchat.config;

import io.github.almightysatan.slams.Slams;
import io.github.almightysatan.slams.minimessage.AdventureMessage;
import io.github.almightysatan.slams.parser.JacksonParser;
import net.kettlemc.kcommon.java.FileUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Messages {

    private static final String DEFAULT_LANGUAGE = "de";

    private static final Path LANGUAGE_PATH = Paths.get("plugins", "kChat", "languages");
    private static final Slams LANGUAGE_MANAGER = Slams.create(DEFAULT_LANGUAGE);

    public static final AdventureMessage PREFIX = AdventureMessage.of("prefix", LANGUAGE_MANAGER);
    public static final AdventureMessage RELOADING = AdventureMessage.of("command.reload.reloading", LANGUAGE_MANAGER);
    public static final AdventureMessage RELOADED = AdventureMessage.of("command.reload.successful", LANGUAGE_MANAGER);
    public static final AdventureMessage RELOAD_FAILED = AdventureMessage.of("command.reloaded.failed", LANGUAGE_MANAGER);
    public static final AdventureMessage NO_PERMISSION = AdventureMessage.of("command.no-permission", LANGUAGE_MANAGER);
    public static final AdventureMessage USAGE = AdventureMessage.of("command.usage", LANGUAGE_MANAGER);

    private Messages() {
    }

    /**
     * Loads all messages from the language files.
     *
     * @return Whether the loading was successful.
     */
    public static boolean load() {
        if (!LANGUAGE_PATH.toFile().exists())
            LANGUAGE_PATH.toFile().mkdirs();
        try {
            FileUtil.saveResourceAsFile(Messages.class, "/lang/de.json", LANGUAGE_PATH.resolve("de.json"));
            FileUtil.saveResourceAsFile(Messages.class, "/lang/en.json", LANGUAGE_PATH.resolve("en.json"));
            loadFromFilesInDirectory(LANGUAGE_PATH.toFile());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Loads all json files in the given directory.
     *
     * @param directory The directory to load from.
     * @throws IOException If an error occurs while loading.
     */
    private static void loadFromFilesInDirectory(@NotNull File directory) throws IOException {
        if (!directory.isDirectory()) return;
        for (File file : Objects.requireNonNull(LANGUAGE_PATH.toFile().listFiles())) {
            if (file.isDirectory()) loadFromFilesInDirectory(file);
            else if (file.getName().endsWith(".json"))
                LANGUAGE_MANAGER.load(DEFAULT_LANGUAGE, JacksonParser.createJsonParser(file));
        }
    }

}
