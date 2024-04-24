package de.spigotutils.delta203.spigot.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javax.imageio.ImageIO;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

/**
 * <b>Map Builder</b><br>
 * This is content from Spigot Utils <small>(Useful classes for making a spigot plugin)</small>.
 *
 * @see <a href="https://github.com/Delta203/SpigotUtils">Spigot Utils</a>
 * @author Delta203
 * @version 1.1.2
 */
public class MapBuilder extends MapRenderer {

  private final World world;
  private final String url;
  private final MapView mapView;

  private BufferedImage image;
  private boolean done;

  /**
   * Register a MapBuilder with the specified world and image url to create a custom map image.
   *
   * @param world the world where the map should be generated
   * @param url the image url
   */
  public MapBuilder(World world, String url) {
    this.world = world;
    this.url = url;
    this.image = null;
    mapView = Bukkit.createMap(world);
    mapView.getRenderers().clear();
    if (load()) {
      mapView.addRenderer(this);
      mapView.setScale(MapView.Scale.FARTHEST);
      mapView.setTrackingPosition(false);
    }
  }

  /**
   * Gets the map world.
   *
   * @return the world
   */
  public World getWorld() {
    return world;
  }

  /**
   * Gets the image url.
   *
   * @return the image url
   */
  public String getUrl() {
    return url;
  }

  /**
   * Gets the {@link MapView} of the map.
   *
   * @return the map view
   */
  public MapView getMapView() {
    return mapView;
  }

  /**
   * Gets the {@link MapRenderer} of the map.
   *
   * @return the map renderer
   */
  public MapRenderer getMapRenderer() {
    return this;
  }

  /**
   * Gets the modified map image as an {@link ItemStack}.
   *
   * @return the map item
   */
  public ItemStack getMapItem() {
    ItemStack map = new ItemStack(Material.FILLED_MAP);
    MapMeta meta = (MapMeta) map.getItemMeta();
    Objects.requireNonNull(meta).setMapView(mapView);
    map.setItemMeta(meta);
    return map;
  }

  /**
   * Loads the image from the url.
   *
   * @return the url is valid
   */
  private boolean load() {
    try {
      image = ImageIO.read(new URL(url));
      image = MapPalette.resizeImage(image);
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  /**
   * Draws the image on the map using map renderers. Make sure that the image is only loaded once
   * onto the {@link MapCanvas}. Otherwise, the server would constantly try to load the image onto
   * the map and this would lead to extreme lags.
   *
   * @param view the map view
   * @param canvas the map canvas
   * @param p the player
   */
  @Override
  public void render(MapView view, MapCanvas canvas, Player p) {
    if (done) return;
    canvas.drawImage(0, 0, image);
    done = true;
  }

  /*
   * To ensure that the maps are also shown after a server restart,
   * they must first be saved externally and then reloaded.

  @EventHandler
  public void onMapInit(MapInitializeEvent e) {
    MapView map = e.getMap();
    map.getRenderers().clear();
    map.addRenderer(getMapRenderer());
    map.setScale(MapView.Scale.FARTHEST);
    map.setTrackingPosition(false);
  }

  */
}
