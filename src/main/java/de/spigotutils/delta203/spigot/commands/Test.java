package de.spigotutils.delta203.spigot.commands;

import de.spigotutils.delta203.spigot.SpigotUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!(sender instanceof Player p)) {
      sender.sendMessage(SpigotUtils.prefix + "§cYou have to be a player!");
      return false;
    }
    p.sendMessage(SpigotUtils.prefix + "Test! :)");
    return false;
  }
}
