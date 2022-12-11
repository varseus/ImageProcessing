package imageprocessing.model;

import java.util.ArrayList;
import java.util.Map;

/**
 * The {@code Image} interface represents the operations that should be offered by a processable
 * image. Version 2 changes: added support for blur/sharpen/greyscale/sepiatone; save method moved
 * to view; added pixels() method to retrieve pixels from this image.
 *
 * @version 2
 */
public interface Image {
  /**
   * create a list of pixels.
   *
   * @return a list of pixels
   * @throws IllegalArgumentException if it's null
   */
  ArrayList<ArrayList<Pixel>> pixels()
      throws IllegalArgumentException;

  /**
   * To make a histogram map.
   *
   * @param type                the type that we want to use
   * @param normalizationFactor the normalizer factor
   * @return a new histogram map
   * @throws IllegalArgumentException if it's null
   */
  Map<Integer, Integer> makeHistogramHashmap(String type,
      int normalizationFactor)
      throws IllegalArgumentException;
}
