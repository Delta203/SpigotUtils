package de.spigotutils.delta203.spigot.customitem;

import de.spigotutils.delta203.spigot.SpigotUtilsSpigot;
import de.spigotutils.delta203.spigot.utils.ItemBuilder;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * <b>Custom Item</b><br>
 * This is content from Spigot Utils <small>(Useful classes for making a spigot plugin)</small>.
 *
 * @see <a href="https://github.com/Delta203/SpigotUtils">Spigot Utils</a>
 * @author Delta203
 * @version 1.1.1
 */
public class RubySword {

  private final ItemStack item;
  private final int modelData;

  public RubySword() {
    // initialise item
    item =
        new ItemBuilder(new ItemStack(Material.NETHERITE_SWORD), "§cRuby Sword", "§9Custom Item")
            .getItem();
    modelData = 1234;
  }

  public ItemStack getItem() {
    return item;
  }

  public int getModelData() {
    return modelData;
  }

  public void register() {
    ItemMeta meta = item.getItemMeta();
    // set model data id to item
    Objects.requireNonNull(meta).setCustomModelData(modelData);
    item.setItemMeta(meta);
    getRecipe();
  }

  private void getRecipe() {
    // create a custom crafting recipe for item
    NamespacedKey key = new NamespacedKey(SpigotUtilsSpigot.plugin, "ruby_sword");
    ShapedRecipe recipe = new ShapedRecipe(key, item);
    recipe.shape(" R ", " R ", " S ");
    recipe.setIngredient('R', Material.REDSTONE);
    recipe.setIngredient('S', Material.STICK);
    Bukkit.addRecipe(recipe);
  }
}
