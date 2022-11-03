package imageprocessing.model;

/**
 * This interface represents the operations that should
 * be offered by a processable image.
 */
interface Image {
  /**
   * Convert this image to ASCII PPM.
   *
   * @return a string containing the data for the PPM file.
   */
  StringBuilder convertToPPM();

  /**
   * Create a greyscale using only the red component of this image.
   *
   * @return the red component image
   */
  Image redComponent();

  /**
   * Create a greyscale using only the green component of this image.
   *
   * @return the green component image
   */
  Image greenComponent();

  /**
   * Create a greyscale using only the blue component of this image.
   *
   * @return the blue component image
   */
  Image blueComponent();

  /**
   * Create an image that is the horizontally flipped version
   * of this image.
   *
   * @return the horizontally flipped image
   */
  Image horizontalFlip();

  /**
   * Create an image that is the vertically flipped version
   * of this image.
   *
   * @return the vertically flipped image
   */
  Image verticalFlip();

  /**
   * Create a greyscale using only the value component of this image.
   *
   * @return the value component image
   */
  Image valueComponent();

  /**
   * Create a greyscale using only the intensity component of this image.
   *
   * @return the intensity component image
   */
  Image intensityComponent();

  /**
   * Create a greyscale using only the luma component of this image.
   *
   * @return the luma component image
   */
  Image lumaComponent();

  /**
   * Create an image that is brighter than this image by the specified amount if units (unless
   * already fully brightened).
   *
   * @param amount the amount to brighten this image by
   * @return the brightened image
   */
  Image brighten(int amount);

  /**
   * Create an image that is darker than this image by the specified amount of units (unless
   * already fully darkened).
   *
   * @param amount the name of the image to darken
   * @return the darkened image
   */
  Image darken(int amount);
}
