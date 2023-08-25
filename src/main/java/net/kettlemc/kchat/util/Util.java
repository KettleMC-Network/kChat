package net.kettlemc.kchat.util;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;

public class Util {

    private static final LuckPerms luckperms = LuckPermsProvider.get();

    /**
     * Get the LuckPerms prefix of a player.
     *
     * @param player The player to get the prefix of.
     * @return The LuckPerms prefix of the player or null if the player is not found.
     */
    public static String getLuckPermsPrefix(Player player) {
        User user = luckperms.getUserManager().getUser(player.getUniqueId());
        if (user == null) {
            return null;
        }
        return user.getCachedData().getMetaData().getPrefix();
    }

    /**
     * Get the LuckPerms suffix of a player.
     *
     * @param player The player to get the suffix of.
     * @return The LuckPerms suffix of the player or null if the player is not found.
     */
    public static String getLuckPermsSuffix(Player player) {
        User user = luckperms.getUserManager().getUser(player.getUniqueId());
        if (user == null) {
            return null;
        }
        return user.getCachedData().getMetaData().getSuffix();
    }
}
