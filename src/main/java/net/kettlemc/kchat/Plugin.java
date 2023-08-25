package net.kettlemc.kchat;

import net.kettlemc.kchat.config.PluginConfig;
import net.kettlemc.kchat.listener.ChatListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {

    private final ContentManager contentManager = new ContentManager(this);

    @Override
    public void onEnable() {
        getLogger().info("Loading config for " + this.getName() + "...");
        if (!PluginConfig.load()) {
            getLogger().severe("Failed to load config!");
        }
        getLogger().info("Registering listeners...");
        contentManager.registerListener(new ChatListener());
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling " + this.getName() + " and closing config...");
        PluginConfig.unload();
    }

}
