package imageprocessing.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * the {@code BasePPMImage} represents operations offered by an image which
 * is processable and can be converted to ASCII PPM. Operations include:
 * get red/green/blue components, get value/intensity/luma components, bright, darken,
 * flip horizontally/vertically, blur/sharpen, greyscale/sepiatone, and load image to and from PPM.
 * Version 2 changes: added support for blur/sharpen/greyscale/sepiatone; save method moved to
 * view. Added pixels() method to retrieve pixels from this image.
 *
 * @version 2
 */
class BasicImage implements Image {
  private final ArrayList<ArrayList<Pixel>> pixels;

  /**
   * Instantiates this BasePPMImage with the given pixels and max value.
   *
   * @param pixels the pixels in this image as a matrix
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
    this(ImageReadUtil.readFile(filepath));
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

  private <T> int indexOf(ArrayList<ArrayList<T>> matrix, T t) {
    for (int i = 0; i < matrix.size(); i++) {
      if (matrix.get(i).indexOf(t) >= 0) {
        return i;
      }
    }
    return -1;
  }

  /**
   * create an image that is blur.
   *
   * @return the image to blur
   */
  @Override
  public Image blur() {
    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<>(this.pixels);
    ArrayList<ArrayList<Pixel>> blurredPixels = new ArrayList<>();
    for (int i = 0; i < this.pixels.size(); i++) {
      ArrayList<Pixel> blurredRow = new ArrayList<>();
      for (int j = 0; j < this.pixels.get(0).size(); j++) {
        blurredRow.add(this.pixels.get(i).get(j).blur(pixels, i, j));
      }
      blurredPixels.add(blurredRow);
    }
    return new BasicImage(blurredPixels);
  }


  /**
   * Create an image that is sharpened.
   *
   * @return the sharpened image
   */
  @Override
  public Image sharpen() {
    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<>(this.pixels);
    ArrayList<ArrayList<Pixel>> sharpenedPixels = new ArrayList<>();
    for (int i = 0; i < this.pixels.size(); i++) {
      ArrayList<Pixel> sharpenedRow = new ArrayList<>();
      for (int j = 0; j < this.pixels.get(0).size(); j++) {
        sharpenedRow.add(this.pixels.get(i).get(j).sharpen(pixels, i, j));
      }
      sharpenedPixels.add(sharpenedRow);
    }
    return new BasicImage(sharpenedPixels);
  }

  /**
   * create an image that is greyscale.
   *
   * @return the image to greyscale
   */
  @Override
  public Image greyscale() {
    return this.mapImagePixelsGreyscale(pixel -> pixel.greyscale());
  }

  /**
   * create an image that is sepia tone.
   *
   * @return the image to sepia tone
   */
  @Override
  public Image sepiaTone() {
    return this.mapImagePixels(pixel -> pixel.sepiaTone());
  }

  /**
   * create a list of pixels.
   *
   * @return a list of pixels
   * @throws IllegalArgumentException if it's null
   */
  @Override
  public ArrayList<ArrayList<Pixel>> pixels() throws IllegalArgumentException {
    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<>();
    for (int i = 0; i < this.pixels.size(); i++) {
      pixels.add(new ArrayList<>(this.pixels.get(i)));
    }
    return pixels;
  }

  /**
   * to make a histogram map.
   * @param type the type if R G B intensity
   * @param normalizationFactor the normalizer factor
   * @return a new histogram map
   * @throws IllegalArgumentException if it's null
   */
  @Override
  public Map<Integer, Integer> makeHistogramHashmap(String type, int normalizationFactor)
          throws IllegalArgumentException {
    int largest = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    for(ArrayList<Pixel> row : pixels) {
      for (Pixel pixel : row) {
        int value = pixel.addToHashmap(map, type);
        if (value > largest) {
          largest = value;
        }
      }
    }

    for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
      map.put(entry.getKey(), entry.getValue() * normalizationFactor / largest);
    }
    return map;
  }

  /**
   * the main method.
   *
   * @param args main
   */
  public static void main(String[] args) {
    BasicImage image = new BasicImage("res/Koala.ppm");
    image.sharpen();
  }
}

