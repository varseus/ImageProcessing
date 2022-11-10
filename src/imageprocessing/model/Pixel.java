package imageprocessing.model;

import java.util.ArrayList;

/**
 * This class represents operations that should be offered
 * by a pixel in an image which is processable.
 */
public interface Pixel {

  /**
   *
   * @param kernel
   * @param channel
   * @return
   */
  int filter(Double kernel, String channel);

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
   *
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
   * Determine the integer RGB value of this pixel.
   *
   * @return the int rgb value of this pixel
   */
  int intRGB();

  /**
   * Blur this pixel, given the surrounding pixels.
   *
   * @return the blurred pixel
   */
  Pixel blur(ArrayList<ArrayList<Pixel>> pixels, int x, int y);

  /**
   * Sharpen this pixel, given the surrounding pixels.
   *
   * @return the sharpened pixel
   */
  Pixel sharpen(ArrayList<ArrayList<Pixel>> pixels, int x, int y);

  /**
   * greyscale this pixel, given the surrounding pixels.
   * @return the greyscale pixel
   */
  GreyscalePixel greyscale();

  /**
   * sepia tone this pixel, given the surrounding pixels.
   * @return the sepia tone pixel
   */
  Pixel sepiaTone();
}

