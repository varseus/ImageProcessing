package imageprocessing.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import imageprocessing.model.Commands.Command;


/**
 * The {@code BasePPMImageProcessingModel} represents the operations and state of an image
 * processor, intended to process a set of PPM images. Operations include: get red/green/blue
 * components, get value/intensity/luma components, bright, darken, flip horizontally/vertically,
 * and load image to and from PPM. Version 2 changes: added support for
 * blur/sharpen/greyscale/sepiatone; save method moved to view; added pixels method to retrieve an
 * image's pixels.
 *
 * @version 2
 */
public class BasicImageProcessingModel implements ImageProcessingModel {

  private final Map<String, Image> images;

  public BasicImageProcessingModel() {
    this.images = new HashMap<String, Image>();
  }

  @Override
  public Void doCommand(Command command, String imageName, String destName)
          throws IllegalArgumentException {
    if(!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("Given image name does not exist in this processor.");
    }
    try {
      this.images.put(destName, command.execute(this.images.get(imageName)));
    } catch (Exception e) {
      throw e;
    }
    return null;
  }

  /**
   * Load the PPM image from the specified filePath and assign it the given name. Overwrites the
   * destination name if already taken.
   *
   * @param imageName the name to give the loaded image
   * @param filepath  the file to load the image from
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the file is invalid
   */
  @Override
  public Void loadImageFromFile(String filepath, String imageName)
          throws IllegalArgumentException {
    images.put(imageName, new BasicImage(filepath));
    return null;
  }

  /**
   * Get the list of pixels from an image.
   *
   * @param imageName the name of the image to get the pixels from
   * @return a list of pixels of the image
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Image image(String imageName) throws IllegalArgumentException {
    try {
      return images.get(imageName);
    } catch (Exception e) {
      throw new IllegalArgumentException("Given image name does not exist in this processor.");
    }
  }

  /**
   * to make a histogram map.
   *
   * @param type                the type if R G B intensity
   * @param normalizationFactor the normalizer factor
   * @return a new histogram map
   * @throws IllegalArgumentException if given image does not exist
   */
  @Override
  public Map<Integer, Integer> makeHistogramHashmap(String imageName, String type,
                                                    int normalizationFactor) throws IllegalArgumentException {
    try {
      return images.get(imageName).makeHistogramHashmap(type, normalizationFactor);
    } catch (Exception e) {
      throw new IllegalArgumentException("Given image name does not exist in this processor.");
    }
  }
}
