package net.kettlemc.kchat;

import net.kettlemc.kchat.command.KChatCommand;
import net.kettlemc.kchat.config.Configuration;
import net.kettlemc.kchat.listener.ChatListener;
import net.kettlemc.kcommon.bukkit.ContentManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class KChat extends JavaPlugin {

    private final ContentManager contentManager = new ContentManager(this);

    @Override
    public void onEnable() {
        getLogger().info("Loading config for " + this.getName() + "...");
        if (!Configuration.load()) {
            getLogger().severe("Failed to load config!");
        }

        getLogger().info("Registering listeners...");
        contentManager.registerListener(new ChatListener());

        getLogger().info("Registering commands...");
        contentManager.registerCommand("kchat", new KChatCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling " + this.getName() + " and closing config...");
        Configuration.unload();
    }

}
