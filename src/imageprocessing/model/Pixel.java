package imageprocessing.model;

/**
 * This class represents operations that should be offered
 * by a pixel in an image which is processable.
 */
public interface Pixel {
  /**
   * Create a greyscale pixel using only the red component of this pixel.
   *
   * @return the red component pixel
   */
  Pixel redComponent();

  /**
   * Create a greyscale pixel using only the green component of this image.
   *
   * @return the green component pixel
   */
  Pixel greenComponent();

  /**
   * Create a greyscale pixel using only the blue component of this pixel.
   *
   * @return the blue component pixel
   */
  Pixel blueComponent();

  /**
   * Create a greyscale pixel using only the value component of this pixel.
   *
   * @return the value component pixel
   */
  Pixel valueComponent();

  /**
   * Create a greyscale pixel using only the intensity component of this pixel.
   *
   * @return the intensity component pixel
   */
  Pixel intensityComponent();

  /**
   * Create a greyscale pixel using only the luma component of this pixel.
   *
   * @return the luma component pixel
   */
  Pixel lumaComponent();

  /**
   * Create a pixel that is brighter than this image by the specified amount if units (unless
   * already fully brightened).
   *
   * @param amount the amount to brighten this pixel by
   * @return the brightened pixel
   */
  Pixel brighten(int amount);

  /**
   * Create a pixel that is darker than this pixel by the specified amount of units (unless
   * already fully darkened).
   *
   * @param amount the name of the pixel to darken
   * @return the darkened pixel
   */
  Pixel darken(int amount);

  /**
   * Creates a string representing this pixel's RGB.
   * Format as "[r] [g] [b]".
   * @return
   */
  String toString();

  /**
   * Determines the byte size of this pixel
   *
   * @return the byte size of this pixel
   */
  int byteSize();
}