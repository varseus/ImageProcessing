package imageprocessing.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * the {@code BasePPMImage} represents operations offered by an image which
 * is processable and can be converted to ASCII PPM. Operations include:
 * get red/green/blue components, get value/intensity/luma components, bright, darken,
 * flip horizontally/vertically, and load/save image to and from PPM.
 */
class BasicImage implements Image {
  private final ArrayList<ArrayList<Pixel>> pixels;

  /**
   * Instantiates this BasePPMImage with the given pixels and max value.
   *
   * @param pixels   the pixels in this image as a matrix
   * @throws IllegalArgumentException if any pixel has the wrong maxValue,
   *                                  the matrix is not rectangular, or the image is empty;
   * @throws NullPointerException     if null args
   */
  public BasicImage(ArrayList<ArrayList<Pixel>> pixels)
          throws IllegalArgumentException, NullPointerException {
    this.pixels = Objects.requireNonNull(pixels);

    // check for empty image:
    if (pixels.size() == 0 || pixels.get(0).size() == 0) {
      throw new IllegalArgumentException("Cannot have empty image.");
    }

    // determine max value for a pixel in this image
    int maxValue = pixels.get(0).get(0).byteSize();

    // make sure that pixel matrix is rectangular
    Integer rowSize = null;
    for (ArrayList<Pixel> row : pixels) {
      if (rowSize == null) {
        rowSize = row.size();
      } else if (rowSize != row.size()) {
        throw new IllegalArgumentException("Image must be rectangular.");
      }

      // make sure that all pixels have same max value
      for (Pixel pixel : row) {
        if (pixel.byteSize() != maxValue) {
          throw new IllegalArgumentException(
                  "All pixels in image must have the correct byte size.");
        }
      }
    }
  }

  /**
   * Instantiates this BasePPMImage by loading from a PPM file at
   * the given destination.
   *
   * @param filepath the file location   of the PPM file to load from
   * @throws IllegalArgumentException if the file is not found or is invalid
   * @throws NullPointerException     if null args
   */
  public BasicImage(String filepath) throws
          IllegalArgumentException,
          NullPointerException {
    this(ImageUtil.readFile(filepath));
  }

  /**
   * Save the image to the specified file
   *
   * @return StringBuilder containing the data for the PPM file.
   */
  @Override
  public void saveToFile(String filepath)
          throws IOException, IllegalArgumentException, NullPointerException {
    ImageUtil.writePixelsToFile(this.pixels, filepath);
  }

  /**
   * Maps an image to a new image by applying the given
   * function to each pixel.
   *
   * @param pixelFunction to apply to each pixel
   * @return the new image
   */
  private Image mapImagePixels(Function<Pixel, Pixel> pixelFunction) {
    return new BasicImage(
            new ArrayList<>(this.pixels.stream().map(row ->
                            new ArrayList<Pixel>((row.stream().map(pixel ->
                                    pixelFunction.apply(pixel)))
                                    .collect(Collectors.toList())))
                    .collect(Collectors.toList())));
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
            new ArrayList<ArrayList<GreyscalePixel>>(this.pixels.stream().map(row ->
                            new ArrayList<GreyscalePixel>((row.stream().map(pixel ->
                                    pixelFunction.apply(pixel)))
                                    .collect(Collectors.toList())))
                    .collect(Collectors.toList())));
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

    return new BasicImage(newImagePixels);
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

    return new BasicImage(newImagePixels);
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

  /**
   * create an image that is blur.
   *
   * @return the image to blur
   */
  @Override
  public Image blur() {
    // create a padded copy of the pixels to use for blurring
    ArrayList<ArrayList<Pixel>> pixelsCopy = new ArrayList<ArrayList<Pixel>>(this.pixels);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (Math.abs(i - j) == 1) {
          newImagePixels.get(i).get(j) =     // edge
        } else if ((i == 0 && j == 0) || (i == 0 && i == 2) || (i == 2 && i == 0) || (i == 2 && i == 2)) {
          newImagePixels.get(i).get(j) = //corner
        }
        this.mapImagePixels(pixel -> pixel.blurCenter());   // center
      }
    }
    return new BasicImage(newImagePixels);
  }

//  /**
//   * Create an image that is sharpened.
//   *
//   * @return the sharpened image
//   */
//  @Override
//  public Image sharpening() {
//    ArrayList<ArrayList<Pixel>> newImagePixels = new ArrayList<ArrayList<Pixel>>();
//    for (int i = 0; i < 4; i++) {
//      for (int j = 0; j < 4; j++) {
//        if (i == 0 || i == 4 || j == 0 || j == 4) {
//          // outside
//        } else if (i == 2 && j == 2) {
//          // center
//        }
//        // inside
//      }
//    }
//    return null;
//  }
//
//  /**
//   * create an image that is greyscale.
//   *
//   * @return the image to greyscale
//   */
//  @Override
//  public Image greyscale() {
//    return this.mapImagePixels(pixel -> pixel.greyscale());
//  }
//
//  /**
//   * create an image that is sepia tone.
//   *
//   * @return the image to sepia tone
//   */
//  @Override
//  public Image sepiaTone() {
//    return this.mapImagePixels(pixel -> pixel.sepiaTone());
//  }
}
