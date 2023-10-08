package net.kettlemc.kchat.util;

import org.bukkit.Bukkit;

public class Util {

    public static boolean miniMessagesAvailable() {
        try {
            Class.forName("net.kyori.adventure.text.minimessage.MiniMessage");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean luckPermsInstalled() {
        return Bukkit.getPluginManager().getPlugin("LuckPerms") != null;
    }

}