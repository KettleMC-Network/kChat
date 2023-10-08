package net.kettlemc.kchat.command;

import com.google.common.collect.ImmutableList;
import net.kettlemc.kchat.config.Configuration;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class KChatCommand implements TabExecutor {

    private static final List<String> SUBCOMMANDS = ImmutableList.of("reload");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Reloading config..."));
                Configuration.unload();
                if (Configuration.load()) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReloaded config..."));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cFailed to reload config!"));
                }
                return true;
            }
        }

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/kchat reload"));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], SUBCOMMANDS, new ArrayList<>());
        }
        return ImmutableList.of();
    }
}
