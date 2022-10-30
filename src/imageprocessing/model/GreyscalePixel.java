package imageprocessing.model;

public class GreyscalePixel extends RGBPixel{
  /**
   * Instantiates this pixel with the given greyscale value.
   *
   * @param value the grey value, where 0 is black and maxValue is white
   * @param maxValue the maximum value of a pixel
   * @throws IllegalArgumentException if any component value is negative or
   *    *                             is greater than the maxValue
   */
  public GreyscalePixel(int value, int maxValue) throws IllegalArgumentException {
    super(value, value, value, maxValue);
  }
}
