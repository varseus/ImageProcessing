package imageprocessing.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * the {@code RGBPixel} represent operations that should be offered
 * * by a pixel in an image which is processable.
 */
class RGBPixel implements Pixel {
  protected final int R;
  protected final int G;
  protected final int B;
  protected final int maxValue;

  /**
   * Instantiate this pixel with the given rgb values.
   *
   * @param r        the red component of this pixel, where 0 is black and maxValue is red
   * @param g        the green component of this pixel, where 0 is black and maxValue is green
   * @param b        the blue component of this pixel, where 0 is black and maxValue is blue
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
    return new GreyscalePixel(
            Math.min((int) (0.2126 * this.R + 0.7152 * this.G + 0.0722 * this.B), this.maxValue),
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
            Math.max(Math.min(this.R + amount, this.maxValue), 0),
            Math.max(Math.min(this.G + amount, this.maxValue), 0),
            Math.max(Math.min(this.B + amount, this.maxValue), 0),
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

  @Override
  public int intRGB() {
    return (((this.R << 8) + this.G) << 8) + this.B;
  }

  //------------------------------//

  /**
   * Assumes pixels and kernel are the same shape.
   */
  private int filterChannel(ArrayList<ArrayList<Pixel>> pixels, ArrayList<ArrayList<Double>> kernel, String channel) {
    HashMap<String, Function<Pixel, Integer>> channelMap = new HashMap<>();
    channelMap.put("R", pixel -> Integer.parseInt(pixel.toString().substring(0, pixel.toString().indexOf(" "))));
    channelMap.put("G", pixel -> Integer.parseInt(pixel.toString().substring(pixel.toString().indexOf(" "),pixel.toString().lastIndexOf(" "))));
    channelMap.put("B", pixel -> Integer.parseInt(pixel.toString().substring(pixel.toString().lastIndexOf(" "))));

    ArrayList<ArrayList<Integer>> pixelVals = new ArrayList<>(
            pixels.stream().map(row ->
                    new ArrayList<>(row.stream().map(channelMap.get(channel)
                    ).collect(Collectors.toList()))).collect(Collectors.toList()));

    double pixelVal = 0;
    for (int i = 0; i < pixelVals.size(); i++) {
      for (int j = 0; j < pixelVals.get(0).size(); j++) {
        pixelVal += pixelVals.get(i).get(j) * kernel.get(i).get(j);
      }
    }
    return Math.min((int) pixelVal, this.maxValue);
  }

  @Override
  public Pixel blur(ArrayList<ArrayList<Pixel>> pixels) {
    ArrayList<ArrayList<Double>> kernel = new ArrayList<ArrayList<Double>>(
            Arrays.asList(
                    new ArrayList<Double>(Arrays.asList(1.0 / 16, 1.0 / 8, 1.0 / 16)),
                    new ArrayList<Double>(Arrays.asList(1.0 / 8, 1.0 / 4, 1.0 / 8)),
                    new ArrayList<Double>(Arrays.asList(1.0 / 16, 1.0 / 8, 1.0 / 16))
            ));
    return new RGBPixel(
            this.filterChannel(pixels, kernel, "R"),
            this.filterChannel(pixels, kernel, "G"),
            this.filterChannel(pixels, kernel, "B"),
            this.maxValue
    );
  }

  @Override
  public Pixel sharpen(ArrayList<ArrayList<Pixel>> pixels) {
    ArrayList<ArrayList<Double>> kernel = new ArrayList<ArrayList<Double>>(
            Arrays.asList(
                    new ArrayList<Double>(Arrays.asList(-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8)),
                    new ArrayList<Double>(Arrays.asList(-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8)),
                    new ArrayList<Double>(Arrays.asList(-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8)),
                    new ArrayList<Double>(Arrays.asList(-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8)),
                    new ArrayList<Double>(Arrays.asList(-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8))
            ));
    return new RGBPixel(
            this.filterChannel(pixels, kernel, "R"),
            this.filterChannel(pixels, kernel, "G"),
            this.filterChannel(pixels, kernel, "B"),
            this.maxValue
    );
  }

  private Double dotProduct(ArrayList<Integer> l1, ArrayList<Double> l2) {
    Double output = 0.0;
    for (int i = 0; i < l1.size(); i++) {
      output += l1.get(i) * l2.get(i);
    }

    return output;
  }

  private Pixel colorTransformation(ArrayList<Integer> rgb, ArrayList<ArrayList<Double>> kernel) {
    int red = Math.min(this.dotProduct(rgb, kernel.get(0)).intValue(), this.maxValue);
    int green = Math.min(this.dotProduct(rgb, kernel.get(1)).intValue(), this.maxValue);
    int blue = Math.min(this.dotProduct(rgb, kernel.get(2)).intValue(), this.maxValue);

    return new RGBPixel(red, green, blue, this.maxValue);
  }

  @Override
  public Pixel sepiaTone() {
    return this.colorTransformation(
            new ArrayList<>(Arrays.asList(this.R, this.G, this.B)),
            new ArrayList<ArrayList<Double>>(
                    Arrays.asList(
                            new ArrayList<Double>(Arrays.asList(0.393, 0.769, 0.189)),
                            new ArrayList<Double>(Arrays.asList(0.349, 0.686, 0.168)),
                            new ArrayList<Double>(Arrays.asList(0.272, 0.534, 0.131))
                    ))
    );
  }


  @Override
  public GreyscalePixel greyscale() {
    // no need to use a transformation here since the greyscale operation is so straightforward
    return new GreyscalePixel((int) (0.2126 * this.R + 0.7152 * this.G + 0.0722 * this.B),
            this.maxValue);
  }
}

















