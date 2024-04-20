package de.spigotutils.delta203.bungee.commands;

import de.spigotutils.delta203.bungee.SpigotUtilsBungee;
import de.spigotutils.delta203.core.SpigotUtils;
import java.util.ArrayList;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

public class TestCommand extends Command implements TabExecutor {

  public TestCommand(String name, String permission, String... aliases) {
    super(name, permission, aliases);
  }

  @Override
  public void execute(CommandSender sender, String[] args) {
    if (!(sender instanceof ProxiedPlayer p)) {
      sender.sendMessage(new TextComponent(SpigotUtils.prefix + "§cYou have to be a player!"));
      return;
    }
    p.sendMessage(new TextComponent(SpigotUtils.prefix + "Test! :)"));
    p.sendMessage(
        new TextComponent(SpigotUtils.prefix + SpigotUtilsBungee.config.getString("foo")));
    p.sendMessage(new TextComponent(SpigotUtils.prefix + SpigotUtils.config.get("foo")));
  }

  @Override
  public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
    if (args.length <= 1) {
      ArrayList<String> arguments = new ArrayList<>();
      arguments.add("foo");
      arguments.add("bar");
      arguments.add("alice");
      arguments.add("bob");
      return arguments;
    }
    return new ArrayList<>();
  }
}
