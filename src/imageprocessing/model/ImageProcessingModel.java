package imageprocessing.model;

import java.io.IOException;

/**
 * This interface represents operations that should be offered by a model for an image processor.
 * One object of the model represents one image processor.
 */
public interface ImageProcessingModel {
  /**
   * Load the PPM image from the specified filePath and assign it
   * the given name. Overwrites the destination name if already taken.
   *
   * @param imageName the name to give the loaded image
   * @param filepath  the location to load the image from
   * @throws IllegalArgumentException if the filePath is invalid
   */
  Void loadImageFromPPM(String imageName, String filepath) throws IllegalArgumentException;

  /**
   * Save the specified image as a PPM at the specified file path.
   *
   * @param imageName the name of the image to save
   * @param filepath  the location to save the image to
   * @throws IllegalArgumentException if the image does not exist or filePath is invalid
   * @throws IOException              if unable to write the file
   */
  Void saveImageToPPM(String imageName, String filepath)
          throws IllegalArgumentException, IOException;

  /**
   * Create a greyscale using only the red component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the red component of
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  Void redComponent(String imageName, String destImageName) throws IllegalArgumentException;

  /**
   * Create greyscale using only the green component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the green component of
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  Void greenComponent(String imageName, String destImageName)
          throws IllegalArgumentException;

  /**
   * Create a greyscale using only the blue component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the blue component of
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  Void blueComponent(String imageName, String destImageName)
          throws IllegalArgumentException;

  /**
   * Create an image that is the horizontally flipped version
   * of the specified image, and load it with the given name.
   * Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to horizontally flip
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  Void horizontalFlip(String imageName, String destImageName)
          throws IllegalArgumentException;

  /**
   * Create an image that is the vertically flipped version
   * of the specified image, and load it with the given name.
   * Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to vertically flip
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  Void verticalFlip(String imageName, String destImageName) throws IllegalArgumentException;

  /**
   * Create a greyscale using only the value component of a given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to get the value component of
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  Void valueComponent(String imageName, String destImageName)
          throws IllegalArgumentException;

  /**
   * Create a greyscale using only the intensity component of a given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to get the intensity component of
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  Void intensityComponent(String imageName, String destImageName)
          throws IllegalArgumentException;

  /**
   * Create a greyscale using only the luma component of a given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to get the luma component of
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  Void lumaComponent(String imageName, String destImageName)
          throws IllegalArgumentException;

  /**
   * Create an image that is brighter than the given image by 10 units (unless
   * already fully brightened), and load it with the given name. Overwrites
   * the destination name if already taken.
   *
   * @param imageName     the name of the image to brighten
   * @param destImageName the name to give the new image
   * @param amount        the amount to brighten by
   * @throws IllegalArgumentException if the image does not exist
   */
  Void brighten(String imageName, String destImageName, int amount)
          throws IllegalArgumentException;

  /**
   * Create an image that is darker than the given image by 10 units (unless
   * already fully darkened), and load it with the given name.
   * Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to darken
   * @param destImageName the name to give the new image
   * @param amount        the amount to darken by
   * @throws IllegalArgumentException if the image does not exist
   */
  Void darken(String imageName, String destImageName, int amount)
          throws IllegalArgumentException;
}
