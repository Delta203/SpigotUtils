package de.spigotutils.delta203.spigot;

import de.spigotutils.delta203.spigot.commands.Test;
import de.spigotutils.delta203.spigot.files.FileManager;
import de.spigotutils.delta203.spigot.mysql.MySQlManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class SpigotUtils extends JavaPlugin {

  public static SpigotUtils plugin;
  public static String prefix = "§f[§eSpigot§6Utils§f] §7";
  public static Configuration config;
  public static MySQlManager mysql;

  @Override
  public void onEnable() {
    plugin = this;

    // load config file
    FileManager configYml = new FileManager("config.yml");
    configYml.create();
    configYml.load();
    config = configYml.get();

    // create mysql connection
    mysql = new MySQlManager("localhost", 3306, "test", "root", "");
    mysql.connect();
    mysql.createTable();

    // register commands and listeners
    Objects.requireNonNull(getCommand("test")).setExecutor(new Test());

    Bukkit.getConsoleSender().sendMessage(prefix + "§aPlugin successfully loaded.");
  }

  @Override
  public void onDisable() {
    Bukkit.getConsoleSender().sendMessage(prefix + "§cPlugin successfully disabled.");
  }
}
