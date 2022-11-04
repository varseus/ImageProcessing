package imageprocessing.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * the {@code BasePPMImage} represents operations offered by an image which
 * is processable and can be converted to ASCII PPM. Operations include:
 * get red/green/blue components, get value/intensity/luma components, bright, darken,
 * flip horizontally/vertically, and load/save image to and from PPM.
 */
class BasePPMImage implements Image {
  private final ArrayList<ArrayList<Pixel>> pixels;
  private final int maxValue;

  /**
   * Instantiates this BasePPMImage with the given pixels and max value.
   *
   * @param pixels   the pixels in this image as a matrix
   * @param maxValue the maxValue of each pixel
   * @throws IllegalArgumentException if any pixel has the wrong maxValue,
   *                                  the matrix is not rectangular, or the image is empty;
   * @throws NullPointerException if null args
   */
  public BasePPMImage(ArrayList<ArrayList<Pixel>> pixels, int maxValue)
          throws IllegalArgumentException, NullPointerException {
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
   * @param file the file of the PPM file to load from
   * @throws IllegalArgumentException if the file is not found or is invalid
   */
  public BasePPMImage(Readable file) throws IllegalArgumentException, NullPointerException {
    this.pixels = new ArrayList<ArrayList<Pixel>>();
    Scanner sc;
    sc = new Scanner(file);

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.length() > 0 && s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc.close();
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    if(sc.hasNext()) {
      token = sc.next();
    } else {
      throw new IllegalArgumentException("Cannot read empty file.");
    }


    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width;
    int height;
    if (sc.hasNext())
      width = sc.nextInt();
    else
      throw new IllegalArgumentException("Cannot load from invalid file type.");
    if (sc.hasNext())
      height = sc.nextInt();
    else
      throw new IllegalArgumentException("Cannot load from invalid file type.");
    if (sc.hasNext())
      this.maxValue = sc.nextInt();
    else
      throw new IllegalArgumentException("Cannot load from invalid file type");

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
   * Convert the image to PPM format.
   *
   * @return byte array containing the data for the PPM file.
   */
  @Override
  public StringBuilder convertToPPM() {
    StringBuilder ppmString = new StringBuilder(
            String.format(
                    "%s\n%d\n%d\n%d\n",
                    "P3",
                    this.pixels.get(0).size(),
                    this.pixels.size(),
                    this.maxValue));

    int i = 1;
    for (ArrayList<Pixel> row : this.pixels) {
      i++;
      for (Pixel pixel : row) {
        ppmString.append(pixel.toString() + "\n");
      }
    }
    return ppmString;
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
            new ArrayList<ArrayList<Pixel>>(
                    this.pixels.stream().map(
                            row -> new ArrayList<Pixel>((row.stream().map(
                                    pixel -> pixelFunction.apply(pixel))).collect(Collectors.toList()))).collect(Collectors.toList())),
            this.maxValue);
  }

  /**
   * Maps an Image to a new GreyScaleImage by applying the given
   * function to each pixel.
   *
   * @param pixelFunction to apply to each pixel
   * @return the new greyscale image
   */
  private GreyscaleImage mapImagePixelsGreyscale(Function<Pixel, GreyscalePixel> pixelFunction) {
    return new GreyscaleImage(
            new ArrayList<ArrayList<GreyscalePixel>>(
                    this.pixels.stream().map(
                            row -> new ArrayList<GreyscalePixel>((row.stream().map(
                                    pixel -> pixelFunction.apply(pixel))).collect(Collectors.toList()))).collect(Collectors.toList())),
            this.maxValue);
  }

  /**
   * Create a greyscale using only the red component of this image.
   *
   * @return the red component image
   */
  @Override
  public GreyscaleImage redComponent() {
    return this.mapImagePixelsGreyscale(pixel -> pixel.redComponent());
  }

  /**
   * Create a greyscale using only the green component of this image.
   *
   * @return the green component image
   */
  @Override
  public GreyscaleImage greenComponent() {
    return this.mapImagePixelsGreyscale(pixel -> pixel.greenComponent());
  }

  /**
   * Create a greyscale using only the blue component of this image.
   *
   * @return the blue component image
   */
  @Override
  public GreyscaleImage blueComponent() {
    return this.mapImagePixelsGreyscale(pixel -> pixel.blueComponent());
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
      for (int j = this.pixels.get(0).size() - 1; j >= 0; j--) {
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
  public GreyscaleImage valueComponent() {
    return this.mapImagePixelsGreyscale(pixel -> pixel.valueComponent());
  }

  /**
   * Create a greyscale using only the intensity component of this image.
   *
   * @return the intensity component image
   */
  @Override
  public GreyscaleImage intensityComponent() {
    return this.mapImagePixelsGreyscale(pixel -> pixel.intensityComponent());
  }

  /**
   * Create a greyscale using only the luma component of this image.
   *
   * @return the luma component image
   */
  @Override
  public GreyscaleImage lumaComponent() {
    return this.mapImagePixelsGreyscale(pixel -> pixel.lumaComponent());
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
