package net.kettlemc.kchat;

import io.github.almightysatan.slams.minimessage.AdventureMessage;
import net.kettlemc.kchat.command.KChatCommand;
import net.kettlemc.kchat.config.Configuration;
import net.kettlemc.kchat.config.Messages;
import net.kettlemc.kchat.listener.ChatListener;
import net.kettlemc.kcommon.bukkit.ContentManager;
import net.kettlemc.klanguage.api.LanguageAPI;
import net.kettlemc.klanguage.bukkit.BukkitLanguageAPI;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class KChat extends JavaPlugin {

    private final ContentManager contentManager = new ContentManager(this);
    private static KChat instance;
    private BukkitAudiences adventure;

    public static final LanguageAPI<Player> LANGUAGE_API = BukkitLanguageAPI.of();

    public static KChat instance() {
        return instance;
    }

    public BukkitAudiences adventure() {
        if (this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    @Override
    public void onEnable() {

        instance = this;

        getLogger().info("Loading config for " + this.getName() + "...");
        if (!Configuration.load()) {
            getLogger().severe("Failed to load config!");
        }

        getLogger().info("Loading messages for " + this.getName() + "...");
        if (!Messages.load()) {
            getLogger().severe("Failed to load messages!");
        }

        getLogger().info("Loading adventure support...");
        this.adventure = BukkitAudiences.create(this);

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

    public void sendMessage(CommandSender sender, AdventureMessage message) {
        Audience audience = this.adventure().sender(sender);
        if (sender instanceof Player) {
            Player player = (Player) sender;
            audience.sendMessage(Messages.PREFIX.value().append(message.value(LANGUAGE_API.getEntity(player))));
            return;
        }
        audience.sendMessage(Messages.PREFIX.value().append(message.value()));
    }

}
