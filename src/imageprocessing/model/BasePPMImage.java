package imageprocessing.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

/**
 * the {@code BasePPMImage} represents operations offered by an image which
 * is processable and can be converted to ASCII PPM. Operations include:
 * get red/green/blue components, get value/intensity/luma components, bright, darken,
 * flip horizontally/vertically, and load/save image to and from PPM.
 */
public class BasePPMImage implements Image {
  private final ArrayList<ArrayList<Pixel>> pixels;
  private final int maxValue;

  /**
   * Instantiates this BasePPMImage with the given pixels and max value.
   *
   * @param pixels   the pixels in this image as a matrix
   * @param maxValue the maxValue of each pixel
   * @throws IllegalArgumentException if any pixel has the wrong maxValue, maxValue is negative,
   *                                  the matrix is not rectangular, or the image is empty;
   */
  public BasePPMImage(ArrayList<ArrayList<Pixel>> pixels, int maxValue)
          throws IllegalArgumentException {
    this.pixels = Objects.requireNonNull(pixels);
    this.maxValue = maxValue;

    if (pixels.size() == 0 || pixels.get(0).size() == 0) {
      throw new IllegalArgumentException("Cannot have empty image.");
    }

    Integer rowSize = null;
    for (ArrayList<Pixel> row : pixels) {
      if (rowSize == null) {
        rowSize = row.size();
      } else if (rowSize != row.size()) {
        throw new IllegalArgumentException("Image must be rectangular.");
      }

      for (Pixel pixel : row) {
        if (pixel.byteSize() != maxValue) {
          throw new IllegalArgumentException(
                  "All pixels in image must have the correct byte size.");
        }
      }
    }

    if (maxValue < 0) {
      throw new IllegalArgumentException("Cannot have negative maxValue");
    }
  }

  /**
   * Instantiates this BasePPMImage by loading from a PPM file at
   * the given destination.
   *
   * @param filePath the location of the PPM file to load from
   * @throws IllegalArgumentException if the file is not found or is invalid
   */
  public BasePPMImage(String filePath) throws IllegalArgumentException {
    this.pixels = new ArrayList<ArrayList<Pixel>>();
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filePath + " not found!");
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;
    token = sc.next();

    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    this.maxValue = sc.nextInt();

    for (int i = 0; i < height; i++) {
      ArrayList<Pixel> row = new ArrayList<Pixel>();
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        try {
        } catch (Exception e) {
          throw new IllegalArgumentException("The given file is missing pixels.");
        }
        row.add(new RGBPixel(r, g, b, this.maxValue));
      }

      this.pixels.add(row);
    }
  }

  /**
   * convert the image to PPM format.
   *
   * @return byte array containing the data for the PPM file.
   */

  @Override
  public byte[] convertToPPM() {
    String ppmString = String.format(
            "%s\n%d\n%d\n%d\n",
            "P3",
            this.pixels.get(0).size(),
            this.pixels.size(),
            this.maxValue);

    int i = 1;
    for (ArrayList<Pixel> row : this.pixels) {
      i++;
      for (Pixel pixel : row) {
        ppmString += pixel.toString() + "\n";
      }
    }
    return ppmString.getBytes();
  }

  /**
   * Maps an image to a new image by applying the given
   * function to each pixel.
   *
   * @param pixelFunction to apply to each pixel
   * @return the new image
   */
  private Image mapImagePixels(Function<Pixel, Pixel> pixelFunction) {
    return new BasePPMImage(
            new ArrayList(
                    Arrays.asList(
                            this.pixels.stream().map(
                                    row -> (row.stream().map(
                                            pixel -> pixelFunction.apply(pixel)))))),
            this.maxValue);
  }

  /**
   * Create a greyscale using only the red component of this image.
   *
   * @return the red component image
   */
  @Override
  public Image redComponent() {
    return this.mapImagePixels(pixel -> pixel.redComponent());
  }

  /**
   * Create a greyscale using only the green component of this image.
   *
   * @return the green component image
   */
  @Override
  public Image greenComponent() {
    return this.mapImagePixels(pixel -> pixel.greenComponent());
  }

  /**
   * Create a greyscale using only the blue component of this image.
   *
   * @return the blue component image
   */
  @Override
  public Image blueComponent() {
    return this.mapImagePixels(pixel -> pixel.blueComponent());
  }

  /**
   * Create an image that is the horizontally flipped version of this image.
   *
   * @return the horizontally flipped image
   */
  @Override
  public Image horizontalFlip() {
    ArrayList<ArrayList<Pixel>> newImagePixels = new ArrayList<ArrayList<Pixel>>();
    for (int i = 0; i < this.pixels.size(); i++) {
      ArrayList<Pixel> row = new ArrayList<Pixel>();
      for (int j = this.pixels.size() - 1; j >= 0; j--) {
        row.add(this.pixels.get(i).get(j));
      }

      newImagePixels.add(row);
    }

    return new BasePPMImage(newImagePixels, this.maxValue);
  }

  /**
   * Create an image that is the vertically flipped version
   * of this image.
   *
   * @return the vertically flipped image
   */
  @Override
  public Image verticalFlip() {
    ArrayList<ArrayList<Pixel>> newImagePixels = new ArrayList<ArrayList<Pixel>>();
    for (int i = this.pixels.size() - 1; i >= 0; i--) {
      newImagePixels.add(this.pixels.get(i));
    }

    return new BasePPMImage(newImagePixels, this.maxValue);
  }

  /**
   * Create a greyscale using only the value component of this image.
   *
   * @return the value component image
   */
  @Override
  public Image valueComponent() {
    return this.mapImagePixels(pixel -> pixel.valueComponent());
  }

  /**
   * Create a greyscale using only the intensity component of this image.
   *
   * @return the intensity component image
   */
  @Override
  public Image intensityComponent() {
    return this.mapImagePixels(pixel -> pixel.intensityComponent());
  }

  /**
   * Create a greyscale using only the luma component of this image.
   *
   * @return the luma component image
   */
  @Override
  public Image lumaComponent() {
    return this.mapImagePixels(pixel -> pixel.lumaComponent());
  }

  /**
   * Create an image that is brighter than this image by the specified amount if units (unless
   * already fully brightened).
   *
   * @param amount the amount to brighten this image by
   * @return the bright component image
   */
  @Override
  public Image brighten(int amount) {
    return this.mapImagePixels(pixel -> pixel.brighten(amount));
  }

  /**
   * Create an image that is darker than this image by the specified amount if units (unless
   * * already fully darkened).
   *
   * @param amount the name of the image to darken
   * @return the dark component image
   */
  @Override
  public Image darken(int amount) {
    return this.mapImagePixels(pixel -> pixel.darken(amount));
  }
}
