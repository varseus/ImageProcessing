package imageprocessing.model;

import java.util.ArrayList;
import java.util.Map;

import imageprocessing.model.Commands.Command;

/**
 * The {@code MockImageProcessingModel} is a mock of an ImageProcessingModel, which logs all calls
 * made to it to a log. Version 2 changes: added support for blur/sharpen/greyscale/sepiatone; save
 * method moved to view.
 *
 * @version 2
 */
public class MockImageProcessingModel implements ImageProcessingModel {

  private final Appendable log;

  public MockImageProcessingModel(Appendable log) {
    this.log = log;
  }


  @Override
  public Void doCommand(Command command, String imageName, String destName) {
    try {
      this.log.append("Doing command");
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  /**
   * Load the PPM image from the specified filePath and assign it the given name. Overwrites the
   * destination name if already taken.
   *
   * @param filepath  the location to load the image from
   * @param imageName the name to load the image to
   * @return null for use in Callable<> lambda expression
   * @throws IllegalArgumentException if the filePath is invalid
   */
  @Override
  public Void loadImageFromFile(String filepath, String imageName) throws IllegalArgumentException {
    try {
      this.log.append("loading file to " + imageName + ".\n");
    } catch (Exception e) {
      System.out.println(e);
    }

    return null;
  }

  /**
   * the list of pixels.
   *
   * @param imageName the name of the image
   * @return a list of pixels of this image with string
   * @throws IllegalArgumentException if it's null
   */
  @Override
  public Image image(String imageName) throws IllegalArgumentException {
    try {
      this.log.append("getting pixels from " + imageName + "\n");
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  @Override
  public Map<Integer, Integer> makeHistogramHashmap(String imageName, String type,
                                                    int normalizationFactor) throws IllegalArgumentException {
    try {
      this.log.append("Making histogram for " + imageName + "\n");
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }
}
