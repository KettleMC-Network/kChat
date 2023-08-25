package net.kettlemc.kchat.listener;

import net.kettlemc.kchat.config.PluginConfig;
import net.kettlemc.kchat.util.LuckPermsUtil;
import net.kettlemc.kchat.util.MiniMessageUtil;
import net.kettlemc.kchat.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.isCancelled()) return;

        String prefix = Util.luckPermsInstalled() ? LuckPermsUtil.getLuckPermsPrefix(event.getPlayer()) : null;
        String suffix = Util.luckPermsInstalled() ? LuckPermsUtil.getLuckPermsSuffix(event.getPlayer()) : null;

        String format = PluginConfig.CHAT_FORMAT.getValue()
                .replace("%prefix%", prefix == null ? PluginConfig.DEFAULT_PREFIX.getValue() : prefix)
                .replace("%suffix%", suffix == null ? PluginConfig.DEFAULT_SUFFIX.getValue() : suffix);

        if (PluginConfig.USE_MINI_MESSAGES.getValue() && Util.miniMessagesAvailable()) {
            format = MiniMessageUtil.miniMessageToLegacy(format);
        } else {
            format = ChatColor.translateAlternateColorCodes('&', format);
        }

        event.setFormat(format);
    }

}
