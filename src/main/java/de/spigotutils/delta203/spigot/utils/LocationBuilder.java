package de.spigotutils.delta203.spigot.utils;

import de.spigotutils.delta203.spigot.SpigotUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Objects;

/**
 * <b>Location Builder</b><br>
 * This is content from Spigot Utils <small>(Useful classes for making a spigot plugin)</small>.
 *
 * @see <a href="https://github.com/Delta203/SpigotUtils">Spigot Utils</a>
 * @author Delta203
 * @version 1.0
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
    SpigotUtils.plugin
        .getConfig()
        .set("Locations." + name + ".world", Objects.requireNonNull(location.getWorld()).getName());
    SpigotUtils.plugin.getConfig().set("Locations." + name + ".x", location.getX());
    SpigotUtils.plugin.getConfig().set("Locations." + name + ".y", location.getY());
    SpigotUtils.plugin.getConfig().set("Locations." + name + ".z", location.getZ());
    SpigotUtils.plugin.getConfig().set("Locations." + name + ".yaw", location.getYaw());
    SpigotUtils.plugin.getConfig().set("Locations." + name + ".pitch", location.getPitch());
    SpigotUtils.plugin.saveConfig();
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
                SpigotUtils.plugin.getConfig().getString("Locations." + name + ".world"))),
        SpigotUtils.plugin.getConfig().getDouble("Locations." + name + ".x"),
        SpigotUtils.plugin.getConfig().getDouble("Locations." + name + ".y"),
        SpigotUtils.plugin.getConfig().getDouble("Locations." + name + ".z"),
        (float) SpigotUtils.plugin.getConfig().getDouble("Locations." + name + ".yaw"),
        (float) SpigotUtils.plugin.getConfig().getDouble("Locations." + name + ".pitch"));
  }
}
