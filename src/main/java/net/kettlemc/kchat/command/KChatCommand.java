package net.kettlemc.kchat.command;

import com.google.common.collect.ImmutableList;
import net.kettlemc.kchat.config.PluginConfig;
import net.kettlemc.kchat.util.Util;
import org.bukkit.command.*;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class KChatCommand implements TabExecutor {

    private static final List<String> SUBCOMMANDS = ImmutableList.of("reload");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                sender.sendMessage(Util.miniMessageToLegacy("<gray>Reloading config...</gray>"));
                PluginConfig.unload();
                PluginConfig.load();
                return true;
            }
        }

        sender.sendMessage(Util.miniMessageToLegacy("<gray>/kchat reload</gray>"));
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
