package imageprocessing.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The {@code MockImageProcessingModel} is a mock of an ImageProcessingModel, which logs
 * all calls made to it to a log.
 */
public class MockImageProcessingModel implements ImageProcessingModel {
  private final Appendable log;

  public MockImageProcessingModel(Appendable log) {
    this.log = log;
  }

  @Override
  public Void loadImageFromPPM(Readable file, String imageName)
          throws IllegalArgumentException {
    try {
      this.log.append("loading file to " + imageName + ".\n");
    } catch (Exception e) {
      System.out.println(e);
    }

    return null;
  }

  @Override
  public StringBuilder saveImageToPPM(String imageName)
          throws IllegalArgumentException {
    try {
      this.log.append("saving " + imageName + "\n");
    } catch (Exception e) {
      System.out.println(e);
    }

    return null;
  }

  /**
   * Create a greyscale using only the red component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the red component of
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void redComponent(String imageName, String destImageName) throws IllegalArgumentException {
    try {
      this.log.append("red " + imageName + " to " + destImageName + "\n");
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  /**
   * Create greyscale using only the green component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the green component of
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void greenComponent(String imageName, String destImageName) throws IllegalArgumentException {
    try {
      this.log.append("green " + imageName + " to " + destImageName + "\n");
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  /**
   * Create a greyscale using only the blue component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the blue component of
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void blueComponent(String imageName, String destImageName) throws IllegalArgumentException {
    try {
      this.log.append("blue " + imageName + " to " + destImageName + "\n");
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  /**
   * Create an image that is the horizontally flipped version
   * of the specified image, and load it with the given name.
   * Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to horizontally flip
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void horizontalFlip(String imageName, String destImageName) throws IllegalArgumentException {
    try {
      this.log.append("horizontal " + imageName + " to " + destImageName + "\n");
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  /**
   * Create an image that is the vertically flipped version
   * of the specified image, and load it with the given name.
   * Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to vertically flip
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void verticalFlip(String imageName, String destImageName) throws IllegalArgumentException {
    try {
      this.log.append("vertical " + imageName + " to " + destImageName + "\n");
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  /**
   * Create a greyscale using only the value component of a given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to get the value component of
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void valueComponent(String imageName, String destImageName) throws IllegalArgumentException {
    try {
      this.log.append("value " + imageName + " to " + destImageName + "\n");
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  /**
   * Create a greyscale using only the intensity component of a given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to get the intensity component of
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void intensityComponent(String imageName, String destImageName) throws IllegalArgumentException {
    try {
      this.log.append("intensity " + imageName + " to " + destImageName + "\n");
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  /**
   * Create a greyscale using only the luma component of a given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to get the luma component of
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void lumaComponent(String imageName, String destImageName) throws IllegalArgumentException {
    try {
      this.log.append("luma " + imageName + " to " + destImageName + "\n");
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  /**
   * Create an image that is brighter than the given image by 10 units (unless
   * already fully brightened), and load it with the given name. Overwrites
   * the destination name if already taken.
   *
   * @param imageName     the name of the image to brighten
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void brighten(String imageName, String destImageName, int amount) throws IllegalArgumentException {
    try {
      this.log.append("brighten " + imageName + " to " + destImageName + " " + amount + "\n");
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  /**
   * Create an image that is darker than the given image by 10 units (unless
   * already fully darkened), and load it with the given name.
   * Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to darken
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void darken(String imageName, String destImageName, int amount) throws IllegalArgumentException {
    try {
      this.log.append("darken " + imageName + " to " + destImageName + " " + amount + "\n");
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }
}
