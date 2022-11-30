package imageprocessing.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.DoubleStream;

/**
 * The {@code RGBPixel} represent operations that should be offered by a pixel in an image which is
 * processable. Version 2 changes: added support for various filters / color transformations.
 *
 * @version 2
 */
class RGBPixel implements Pixel {

  protected final int R;
  protected final int G;
  protected final int B;
  protected final int maxValue;
  private final ArrayList<ArrayList<Double>> GAUSSIAN_BLUR_KERNEL =
      new ArrayList<ArrayList<Double>>(Arrays.asList(new ArrayList<Double>(
              Arrays.asList(1.0 / 16, 1.0 / 8, 1.0 / 16)),
          new ArrayList<Double>(Arrays.asList(1.0 / 8, 1.0 / 4, 1.0 / 8)),
          new ArrayList<Double>(Arrays.asList(1.0 / 16, 1.0 / 8, 1.0 / 16))));
  private final ArrayList<ArrayList<Double>> SHARPEN_KERNEL =
      new ArrayList<ArrayList<Double>>(Arrays.asList(new ArrayList<Double>(
              Arrays.asList(-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8)),
          new ArrayList<Double>(Arrays.asList(
              -1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8)),
          new ArrayList<Double>(Arrays.asList(
              -1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8)),
          new ArrayList<Double>(Arrays.asList(
              -1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8)),
          new ArrayList<Double>(Arrays.asList(
              -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8))));

  private final ArrayList<ArrayList<Double>> GREYSCALE_KERNEL =
      new ArrayList<>(Arrays.asList(new ArrayList<Double>(
              Arrays.asList(0.2126, 0.7152, 0.0722)),
          new ArrayList<Double>(Arrays.asList(0.2126, 0.7152, 0.0722)),
          new ArrayList<Double>(Arrays.asList(0.2126, 0.7152, 0.0722))));

  private final ArrayList<ArrayList<Double>> SEPIA_KERNEL =
      new ArrayList<>(Arrays.asList(new ArrayList<Double>(
              Arrays.asList(0.393, 0.769, 0.189)),
          new ArrayList<Double>(Arrays.asList(0.349, 0.686, 0.168)),
          new ArrayList<Double>(Arrays.asList(0.272, 0.534, 0.131))));

  /**
   * Instantiate this pixel with the given rgb values.
   *
   * @param r        the red component of this pixel, where 0 is black and maxValue is red
   * @param g        the green component of this pixel, where 0 is black and maxValue is green
   * @param b        the blue component of this pixel, where 0 is black and maxValue is blue
   * @param maxValue the maximum value of a pixel
   * @throws IllegalArgumentException if any value is negative or any component value is greater
   *                                  than the maxValue
   */
  public RGBPixel(int r, int g, int b, int maxValue) throws IllegalArgumentException {
    this.R = r;
    this.G = g;
    this.B = b;
    this.maxValue = maxValue;

    if (r > maxValue || g > maxValue || b > maxValue) {
      throw new IllegalArgumentException("Pixel components cannot be larger than the max value.");
    }
    if (r < 0 || g < 0 || b < 0 || maxValue < 0) {
      throw new IllegalArgumentException("Pixel components cannot be negative.");
    }
  }

  /**
   * Create a greyscale pixel using only the red component of this pixel.
   *
   * @return the red component pixel
   */
  @Override
  public GreyscalePixel redComponent() {
    return new GreyscalePixel(this.R, this.maxValue);
  }

  /**
   * Create a greyscale pixel using only the green component of this pixel.
   *
   * @return the green component pixel
   */
  @Override
  public GreyscalePixel greenComponent() {
    return new GreyscalePixel(this.G, this.maxValue);
  }

  /**
   * Create a greyscale pixel using only the blue component of this pixel.
   *
   * @return the blue component pixel
   */
  @Override
  public GreyscalePixel blueComponent() {
    return new GreyscalePixel(this.B, this.maxValue);
  }

  /**
   * Create a greyscale pixel using only the value component of this pixel.
   *
   * @return the value component pixel
   */
  @Override
  public GreyscalePixel valueComponent() {
    return new GreyscalePixel(Math.max(this.R, Math.max(this.G, this.B)), this.maxValue);
  }

  /**
   * Create a greyscale pixel using only the intensity component of this pixel.
   *
   * @return the intensity component pixel
   */
  @Override
  public GreyscalePixel intensityComponent() {
    return new GreyscalePixel((this.R + this.G + this.B) / 3, this.maxValue);
  }

  /**
   * Create a greyscale pixel using only the luma component of this pixel.
   *
   * @return the luma component pixel
   */
  @Override
  public GreyscalePixel lumaComponent() {
    return new GreyscalePixel(Math.min((int) (0.2126 * this.R + 0.7152 * this.G + 0.0722 * this.B),
        this.maxValue), this.maxValue);
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
    return new RGBPixel(Math.max(Math.min(this.R + amount, this.maxValue), 0),
        Math.max(Math.min(this.G + amount, this.maxValue), 0),
        Math.max(Math.min(this.B + amount, this.maxValue), 0),
        this.maxValue);
  }

  /**
   * Create a pixel that is darker than this pixel by the specified amount of units (unless already
   * fully darkened).
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
   *
   * @return the String of this pixel.
   */
  @Override
  public String toString() {
    return String.format("%d %d %d", this.R, this.G, this.B);
  }

  /**
   * Determines the byte size of this pixel.
   *
   * @return the byte size of this pixel, represented by this.maxValue
   */
  @Override
  public int byteSize() {
    return this.maxValue;
  }

  /**
   * Determine the integer RGB value of this pixel.
   *
   * @return the int rgb value of this pixel
   */
  @Override
  public int intRGB() {
    return (((this.R << 8) + this.G) << 8) + this.B;
  }

  /**
   * the filter for the pixel.
   *
   * @param kernel  each point
   * @param channel the color for each channel
   * @return
   */
  public int filter(Double kernel, String channel) {
    int channelValue;

    switch (channel) {
      case "R":
        channelValue = this.R;
        break;
      case "G":
        channelValue = this.G;
        break;
      default:
        channelValue = this.B;
        break;
    }

    return (int) (channelValue * kernel);
  }

  /**
   * make a filter's channel to assume kernel is square.
   *
   * @param pixels  pixels as a matrix
   * @param kernel  a square
   * @param channel cahnnel for pixels
   * @param row     pixels' row
   * @param col     pixels' col
   * @return the channel
   */
  private int filterChannel(ArrayList<ArrayList<Pixel>> pixels,
      ArrayList<ArrayList<Double>> kernel,
      String channel,
      int row, int col) {

    DoubleStream.Builder pixelVal = DoubleStream.builder();
    int kernelSize = kernel.size();
    for (int i = row - kernelSize / 2; i <= row + kernelSize / 2; i++) {
      for (int j = col - kernelSize / 2; j <= col + kernelSize / 2; j++) {
        if (i >= 0 && i < pixels.size() && j >= 0 && j < pixels.get(0).size()) {
          pixelVal.add(pixels.get(i).get(j).filter(
              kernel.get(i - (row - kernelSize / 2))
                  .get(j - (col - kernelSize / 2)), channel));
        }
      }
    }
    return Math.max(Math.min((int) pixelVal.build().sum(), this.maxValue), 0);
  }

  /**
   * Blur this pixel, given the surrounding pixels.
   *
   * @return the blurred pixel
   */
  @Override
  public Pixel blur(ArrayList<ArrayList<Pixel>> pixels, int x, int y) {
    return new RGBPixel(this.filterChannel(pixels, GAUSSIAN_BLUR_KERNEL, "R", x, y),
        this.filterChannel(pixels, GAUSSIAN_BLUR_KERNEL, "G", x, y),
        this.filterChannel(pixels, GAUSSIAN_BLUR_KERNEL, "B", x, y),
        this.maxValue);
  }

  /**
   * Sharpen this pixel, given the surrounding pixels.
   *
   * @return the sharpened pixel
   */
  @Override
  public Pixel sharpen(ArrayList<ArrayList<Pixel>> pixels, int x, int y) {
    return new RGBPixel(this.filterChannel(pixels, SHARPEN_KERNEL, "R", x, y),
        this.filterChannel(pixels, SHARPEN_KERNEL, "G", x, y),
        this.filterChannel(pixels, SHARPEN_KERNEL, "B", x, y),
        this.maxValue);
  }

  /**
   * Does the dot product of two vectors.
   *
   * @param l1 the first vector
   * @param l2 the second vector
   * @return a number for result
   */
  private Double dotProduct(ArrayList<Integer> l1, ArrayList<Double> l2) {
    DoubleStream.Builder output = DoubleStream.builder();
    for (int i = 0; i < l1.size(); i++) {
      output.add(l1.get(i) * l2.get(i));
    }

    return output.build().sum();
  }

  /**
   * the color Transformation method to make transmate color.
   *
   * @param kernel the matrix of kernel
   * @return a pixel
   */
  private Pixel colorTransformation(ArrayList<ArrayList<Double>> kernel) {
    ArrayList<Integer> rgb = new ArrayList<>(Arrays.asList(this.R, this.G, this.B));

    int red = Math.min(this.dotProduct(rgb, kernel.get(0)).intValue(), this.maxValue);
    int green = Math.min(this.dotProduct(rgb, kernel.get(1)).intValue(), this.maxValue);
    int blue = Math.min(this.dotProduct(rgb, kernel.get(2)).intValue(), this.maxValue);

    return new RGBPixel(red, green, blue, this.maxValue);
  }

  /**
   * the color Transformation for Greyscale.
   *
   * @param kernel a matrix of kernel
   * @return a Greyscale Pixel
   */
  private GreyscalePixel colorTransformationGreyscale(ArrayList<ArrayList<Double>> kernel) {
    ArrayList<Integer> rgb = new ArrayList<>(Arrays.asList(this.R, this.G, this.B));

    int value = Math.min(this.dotProduct(rgb, kernel.get(0)).intValue(), this.maxValue);

    return new GreyscalePixel(value, this.maxValue);
  }

  /**
   * sepia tone this pixel, given the surrounding pixels.
   *
   * @return the sepia tone pixel
   */
  @Override
  public Pixel sepiaTone() {
    return this.colorTransformation(this.SEPIA_KERNEL);
  }

  /**
   * greyscale this pixel, given the surrounding pixels.
   *
   * @return the greyscale pixel
   */
  @Override
  public GreyscalePixel greyscale() {
    return this.colorTransformationGreyscale(this.GREYSCALE_KERNEL);
  }

  /**
   * to add the num to the hash map.
   *
   * @param map  a hash map
   * @param type the type of R G B intensity
   * @return add 1 if there are R G B intensity
   */
  public int addToHashmap(Map<Integer, Integer> map, String type) {
    int value = 0;
    switch (type) {
      case "R":
        value = this.redComponent().R;
        break;
      case "G":
        value = this.greenComponent().G;
        break;
      case "B":
        value = this.blueComponent().B;
        break;
      case "intensity":
        value = this.intensityComponent().R;
        break;
      default:
        break;
    }
    if (map.containsKey(value)) {
      return map.put(value, map.get(value) + 1) + 1;
    } else {
      map.put(value, 1);
      return 1;
    }
  }
}