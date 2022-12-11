package imageprocessing.model;

import java.util.ArrayList;
import java.util.Map;

import imageprocessing.model.Commands.Command;

/**
 * The {@code ImageProcessingModel} interface represents operations that should be offered by a
 * model for an image processor. One object of the model represents one image processor. Version 2
 * changes: added support for blur/sharpen/greyscale/sepiatone; save method moved to view; added
 * pixels method to retrieve an image's pixels.
 *
 * @version 2
 */
public interface ImageProcessingModel {

  Void doCommand(Command command, String imageName, String destName);

  /**
   * Load the PPM image from the specified filePath and assign it the given name. Overwrites the
   * destination name if already taken.
   *
   * @param filepath  the location to load the image from
   * @param imageName the name to load the image to
   * @return null for use in Callable<> lambda expression
   * @throws IllegalArgumentException if the filePath is invalid
   */
  Void loadImageFromFile(String filepath, String imageName) throws IllegalArgumentException;

  /**
   * Gets the image object for an image.
   *
   * @param imageName the name of the image
   * @return image object
   * @throws IllegalArgumentException if null or invalid
   */
  Image image(String imageName)
      throws IllegalArgumentException;

  /**
   * to make a histogram map.
   *
   * @param type                the type if R G B intensity
   * @param normalizationFactor the normalizer factor
   * @return a new histogram map
   * @throws IllegalArgumentException if given image does not exist
   */
  public Map<Integer, Integer> makeHistogramHashmap(
      String imageName, String type,
      int normalizationFactor)
      throws IllegalArgumentException;
}
