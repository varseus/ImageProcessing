package imageprocessing.model;

import java.util.ArrayList;
import java.util.Map;

/**
 * The {@code Pixel} represents operations that should be offered by a pixel in an image which is
 * processable. Version 2 changes: added support for blur/sharpen/greyscale/sepiatone.
 *
 * @version 2
 */
public interface Pixel {

  /**
   * Filter this pixel's specified channel by the given kernel.
   *
   * @param kernel  to filter by
   * @param channel one of "R", "G", or "B"
   * @return the new channel component after filtering
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
   * Create a pixel that is darker than this pixel by the specified amount of units (unless already
   * fully darkened).
   *
   * @param amount the name of the pixel to darken
   * @return the darkened pixel
   */
  Pixel darken(int amount);

  /**
   * Creates a string representing this pixel's RGB. Format as "[r] [g] [b]".
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
   * Create a picture that is a blur of this pixel, given the surrounding pixels.
   *
   * @return the blurred pixel
   */
  Pixel blur(ArrayList<ArrayList<Pixel>> pixels, int x, int y);

  /**
   * Create a picture that is sharper than this pixel, given the surrounding pixels.
   *
   * @return the sharpened pixel
   */
  Pixel sharpen(ArrayList<ArrayList<Pixel>> pixels, int x, int y);

  /**
   * Create a picture that is a greyscale of this pixel.
   *
   * @return the greyscale pixel
   */
  GreyscalePixel greyscale();

  /**
   * Create a pixel that is the sepia tone this pixel.
   *
   * @return the sepia tone pixel
   */
  Pixel sepiaTone();

  /**
   * to add the num to the hash map.
   *
   * @param map  a hash map
   * @param type the type of R G B intensity
   * @return add 1 if there are R G B intensity
   */
  int addToHashmap(Map<Integer, Integer> map, String type);
}

