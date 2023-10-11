package net.kettlemc.kchat.listener;

import net.kettlemc.kchat.config.Configuration;
import net.kettlemc.kchat.util.Util;
import net.kettlemc.kcommon.adventure.AdventureUtil;
import net.kettlemc.kcommon.luckperms.LuckPermsUtil;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.isCancelled()) return;

        String prefix = Util.luckPermsInstalled() ? LuckPermsUtil.getLuckPermsPrefix(Util.luckPerms(), event.getPlayer()) : null;
        String suffix = Util.luckPermsInstalled() ? LuckPermsUtil.getLuckPermsSuffix(Util.luckPerms(), event.getPlayer()) : null;

        String format = Configuration.CHAT_FORMAT.getValue()
                .replace("%prefix%", prefix == null ? Configuration.DEFAULT_PREFIX.getValue() : prefix)
                .replace("%suffix%", suffix == null ? Configuration.DEFAULT_SUFFIX.getValue() : suffix);

        if (Configuration.USE_MINI_MESSAGES.getValue() && Util.miniMessagesAvailable()) {
            format = AdventureUtil.miniMessageToLegacy(format);
        } else {
            format = ChatColor.translateAlternateColorCodes('&', format);
        }

        event.setFormat(format);
    }

}
