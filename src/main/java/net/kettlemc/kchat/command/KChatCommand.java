package net.kettlemc.kchat.command;

import com.google.common.collect.ImmutableList;
import net.kettlemc.kchat.KChat;
import net.kettlemc.kchat.config.Configuration;
import net.kettlemc.kchat.config.Messages;
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
        if (sender.hasPermission("kchat.command")) {
            KChat.instance().sendMessage(sender, Messages.NO_PERMISSION);
            return true;
        }

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                KChat.instance().sendMessage(sender, Messages.RELOADING);
                Configuration.unload();
                if (Configuration.load()) {
                    KChat.instance().sendMessage(sender, Messages.RELOADED);
                } else {
                    KChat.instance().sendMessage(sender, Messages.RELOAD_FAILED);
                }
                return true;
            }
        }
        KChat.instance().sendMessage(sender, Messages.USAGE);
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
