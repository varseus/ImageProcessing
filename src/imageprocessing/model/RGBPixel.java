package imageprocessing.model;

/**
 * the {@code RGBPixel} represent operations that should be offered
 *  * by a pixel in an image which is processable.
 */
public class RGBPixel implements Pixel{
  final int R;
  final int G;
  final int B;
  final int maxValue;

  /**
   * Instantiate this pixel with the given rgb values.
   * @param r the red component of this pixel, where 0 is black and maxValue is red
   * @param g the green component of this pixel, where 0 is black and maxValue is green
   * @param b the blue component of this pixel, where 0 is black and maxValue is blue
   * @param maxValue the maximum value of a pixel
   * @throws IllegalArgumentException if any value is negative or
   *                                  any component value is greater than the maxValue
   */
  public RGBPixel(int r, int g, int b, int maxValue) throws IllegalArgumentException {
    this.R = r;
    this.G = g;
    this.B = b;
    this.maxValue = maxValue;

    if (r > maxValue || g > maxValue || b > maxValue) {
      throw new IllegalArgumentException("Pixel components cannot be larger than the max value.");
    }
    if(r < 0 || g < 0 || b < 0 || maxValue < 0) {
      throw new IllegalArgumentException("Pixel components cannot be negative.");
    }
  }

  /**
   * Create a greyscale pixel using only the red component of this pixel.
   *
   * @return the red component pixel
   */
  @Override
  public Pixel redComponent() {
    return new GreyscalePixel(this.R, this.maxValue);
  }
  /**
   * Create a greyscale pixel using only the green component of this pixel.
   *
   * @return the green component pixel
   */
  @Override
  public Pixel greenComponent() {
    return new GreyscalePixel(this.G, this.maxValue);
  }
  /**
   * Create a greyscale pixel using only the blue component of this pixel.
   *
   * @return the blue component pixel
   */
  @Override
  public Pixel blueComponent() {
    return new GreyscalePixel(this.B, this.maxValue);
  }
  /**
   * Create a greyscale pixel using only the value component of this pixel.
   *
   * @return the value component pixel
   */
  @Override
  public Pixel valueComponent() {
    return new GreyscalePixel(Math.max(this.R, Math.max(this.G, this.B)), this.maxValue);
  }
  /**
   * Create a greyscale pixel using only the intensity component of this pixel.
   *
   * @return the intensity component pixel
   */
  @Override
  public Pixel intensityComponent() {
    return new GreyscalePixel((this.R + this.G + this.B)/3, this.maxValue);
  }
  /**
   * Create a greyscale pixel using only the luma component of this pixel.
   *
   * @return the luma component pixel
   */
  @Override
  public Pixel lumaComponent() {
    return new GreyscalePixel(
            Math.min((int)(0.2126 * this.R + 0.7152 * this.G + 0.0722 * this.B), this.maxValue),
            this.maxValue);
  }
  /**
   * Create a pixel that is brighter than this pixel by the specified amount of units (unless
   * already fully brightened).
   *
   * @param amount the name of the pixel to brighten
   * @return the brightened pixel
   */
  @Override
  public Pixel brighten(int amount) {
    return new RGBPixel(
            Math.min(this.R + amount, this.maxValue),
            Math.min(this.G + amount, this.maxValue),
            Math.min(this.B + amount, this.maxValue),
            this.maxValue);
  }
  /**
   * Create a pixel that is darker than this pixel by the specified amount of units (unless
   * already fully darkened).
   *
   * @param amount the name of the pixel to darken
   * @return the darkened pixel
   */
  @Override
  public Pixel darken(int amount) {
    return this.brighten(amount * -1);
  }

  /**
   * to convert each color of pixel to string format.
   * @return the String of this pixel.
   */
  @Override
  public String toString() {
    return String.format("%d %d %d", this.R, this.G, this.B);
  }

  /**
   * Determines the byte size of this pixel
   *
   * @return the byte size of this pixel, represented by this.maxValue
   */
  @Override
  public int byteSize() {
    return this.maxValue;
  }
}

















