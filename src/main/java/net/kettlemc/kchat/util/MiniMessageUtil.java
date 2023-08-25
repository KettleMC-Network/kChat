package net.kettlemc.kchat.util;

import net.kyori.adventure.platform.bukkit.BukkitComponentSerializer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MiniMessageUtil {


    /**
     * Convert a MiniMessage string to a legacy string.
     *
     * @param miniMessage The MiniMessage string to convert.
     * @return The legacy string.
     */
    public static String miniMessageToLegacy(String miniMessage) {
        Component message = MiniMessage.miniMessage().deserialize(miniMessage);
        return BukkitComponentSerializer.legacy().serialize(message);
    }

}
