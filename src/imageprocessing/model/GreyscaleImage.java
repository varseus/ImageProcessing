package imageprocessing.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The {@code GreyscaleImage} represents an image composed of only grey pixels. Version 2 changes:
 * refactored constructor to not take explicit maxValue for pixels.
 *
 * @version 2
 */
public class GreyscaleImage extends BasicImage {

  /**
   * Instantiates this GreyscaleImage with the given pixels and max value.
   *
   * @param pixels   the greyscale pixels in this image as a matrix
   * @param maxValue the maxValue of each pixel
   * @throws IllegalArgumentException if any pixel has the wrong maxValue, maxValue is negative, the
   *                                  matrix is not rectangular, or the image is empty;
   * @throws NullPointerException     if null args
   */
  public GreyscaleImage(ArrayList<ArrayList<GreyscalePixel>> pixels)
      throws IllegalArgumentException {
    super(// type cast the ArrayList of GreyscalePixels to an ArrayList of Pixels:
        new ArrayList<>(
            pixels.stream().map(row ->
                    new ArrayList<Pixel>(row.stream().collect(Collectors.toList())))
                .collect(Collectors.toList())));
  }
}
