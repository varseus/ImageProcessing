package imageprocessing.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import imageprocessing.model.Commands.BlurFilter;
import imageprocessing.model.Commands.SharpenFilter;

/**
 * the {@code BasePPMImage} represents operations offered by an image which is processable and can
 * be converted to ASCII PPM. Operations include: get red/green/blue components, get
 * value/intensity/luma components, bright, darken, flip horizontally/vertically, blur/sharpen,
 * greyscale/sepiatone, and load image to and from PPM. Version 2 changes: added support for
 * blur/sharpen/greyscale/sepiatone; save method moved to view. Added pixels() method to retrieve
 * pixels from this image.
 *
 * @version 2
 */
public class BasicImage implements Image {

  private final ArrayList<ArrayList<Pixel>> pixels;

  /**
   * Instantiates this BasePPMImage with the given pixels and max value.
   *
   * @param pixels the pixels in this image as a matrix
   * @throws IllegalArgumentException if any pixel has the wrong maxValue, the matrix is not
   *                                  rectangular, or the image is empty;
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
   * Instantiates this BasePPMImage by loading from a PPM file at the given destination.
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
   *
   * @param type                the type if R G B intensity
   * @param normalizationFactor the normalizer factor
   * @return a new histogram map
   * @throws IllegalArgumentException if it's null
   */
  @Override
  public Map<Integer, Integer> makeHistogramHashmap(String type, int normalizationFactor)
      throws IllegalArgumentException {
    int largest = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    for (ArrayList<Pixel> row : pixels) {
      for (Pixel pixel : row) {
        int value = pixel.addToHashmap(map, type);
        if (value > largest) {
          largest = value;
        }
      }
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
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
    BasicImage image = new BasicImage("res/koala-vertical.png");
    new BlurFilter().execute(image);
  }


}

