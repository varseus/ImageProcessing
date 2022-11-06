package imageprocessing.model;

/**
 * This class represents operations that should be offered
 * by a pixel in an image which is processable.
 */
interface Pixel {
  /**
   * Create a greyscale pixel using only the red component of this pixel.
   *
   * @return the red component pixel
   */
  GreyscalePixel redComponent();

  /**
   * Create a greyscale pixel using only the green component of this image.
   *
   * @return the green component pixel
   */
  GreyscalePixel greenComponent();

  /**
   * Create a greyscale pixel using only the blue component of this pixel.
   *
   * @return the blue component pixel
   */
  GreyscalePixel blueComponent();

  /**
   * Create a greyscale pixel using only the value component of this pixel.
   *
   * @return the value component pixel
   */
  GreyscalePixel valueComponent();

  /**
   * Create a greyscale pixel using only the intensity component of this pixel.
   *
   * @return the intensity component pixel
   */
  GreyscalePixel intensityComponent();

  /**
   * Create a greyscale pixel using only the luma component of this pixel.
   *
   * @return the luma component pixel
   */
  GreyscalePixel lumaComponent();

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
   * Determines the byte size of this pixel.
   *
   * @return the byte size of this pixel
   */
  int byteSize();
  /**
   * applied to the pixel in edge place to blur.
   *
   * @return the blur pixel
   */
  Pixel blurEdge();
  /**
   * applied to the pixel in center place to blur.
   *
   * @return the blur pixel
   */
  Pixel blurCenter();
  /**
   * applied to the pixel in conner place to blur.
   *
   * @return the blur pixel
   */
  Pixel blurCorner();
  /**
   * make the first row, last row, first col, last col's pixels to sharpening.
   *
   * @return the sharpe pixel
   */
  Pixel sharpeningOutsideEdge();
  /**
   * make the second row, second to last row, second col, second to last col's pixels to sharpening.
   *
   * @return the sharpe pixel
   */
  Pixel sharpeningInsideEdge();
  /**
   * make the center pixel to sharpening.
   *
   * @return the sharpe pixel
   */
  Pixel sharpeningCenter();

  Pixel greyscale();

  Pixel sepiaTone();
}

// model should not need file info
// greyscale image

