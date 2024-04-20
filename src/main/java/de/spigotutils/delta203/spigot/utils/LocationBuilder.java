package de.spigotutils.delta203.spigot.utils;

import de.spigotutils.delta203.spigot.SpigotUtilsSpigot;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * <b>Location Builder</b><br>
 * This is content from Spigot Utils <small>(Useful classes for making a spigot plugin)</small>.
 *
 * @see <a href="https://github.com/Delta203/SpigotUtils">Spigot Utils</a>
 * @author Delta203
 * @version 1.1
 */
public class LocationBuilder {

  private Location location;
  private final String name;

  /**
   * Register a LocationBuilder with the specified location and name to save and load a location
   * from the config file.
   *
   * @param location the initial location.
   * @param name the name of the location.
   */
  public LocationBuilder(Location location, String name) {
    this.location = location;
    this.name = name;
  }

  /**
   * Register a LocationBuilder with the specified name to load a location from the config file.
   *
   * @param name the name of the location:
   */
  public LocationBuilder(String name) {
    this.name = name;
  }

  /** Creates a location entry in the plugins configuration file. */
  public void create() {
    SpigotUtilsSpigot.plugin
        .getConfig()
        .set("Locations." + name + ".world", Objects.requireNonNull(location.getWorld()).getName());
    SpigotUtilsSpigot.plugin.getConfig().set("Locations." + name + ".x", location.getX());
    SpigotUtilsSpigot.plugin.getConfig().set("Locations." + name + ".y", location.getY());
    SpigotUtilsSpigot.plugin.getConfig().set("Locations." + name + ".z", location.getZ());
    SpigotUtilsSpigot.plugin.getConfig().set("Locations." + name + ".yaw", location.getYaw());
    SpigotUtilsSpigot.plugin.getConfig().set("Locations." + name + ".pitch", location.getPitch());
    SpigotUtilsSpigot.plugin.saveConfig();
  }

  /**
   * Loads the location stored in the plugins configuration file.
   *
   * @return the loaded location
   */
  public Location getLocation() {
    return new Location(
        Bukkit.getWorld(
            Objects.requireNonNull(
                SpigotUtilsSpigot.plugin.getConfig().getString("Locations." + name + ".world"))),
        SpigotUtilsSpigot.plugin.getConfig().getDouble("Locations." + name + ".x"),
        SpigotUtilsSpigot.plugin.getConfig().getDouble("Locations." + name + ".y"),
        SpigotUtilsSpigot.plugin.getConfig().getDouble("Locations." + name + ".z"),
        (float) SpigotUtilsSpigot.plugin.getConfig().getDouble("Locations." + name + ".yaw"),
        (float) SpigotUtilsSpigot.plugin.getConfig().getDouble("Locations." + name + ".pitch"));
  }
}
