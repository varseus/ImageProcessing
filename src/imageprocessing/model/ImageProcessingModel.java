package imageprocessing.model;


import java.io.IOException;
import java.util.ArrayList;

/**
 * This interface represents operations that should be offered by a model for an image processor.
 * One object of the model represents one image processor.
 */
public interface ImageProcessingModel {
  /**
   * Load the PPM image from the specified filePath and assign it
   * the given name. Overwrites the destination name if already taken.
   *
   * @param filepath  the location to load the image from
   * @param imageName the name to load the image to
   * @return null for use in Callable<> lambda expression
   * @throws IllegalArgumentException if the filePath is invalid
   */
  Void loadImageFromFile(String filepath, String imageName) throws IllegalArgumentException;

  /**
   * x
   * Create a greyscale using only the red component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the red component of
   * @param destImageName the name to give the new image
   * @return null for use in Callable<> lambda expression
   * @throws IllegalArgumentException if the image does not exist
   */
  Void redComponent(String imageName, String destImageName) throws IllegalArgumentException;

  /**
   * Create greyscale using only the green component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the green component of
   * @param destImageName the name to give the new image
   * @return null for use in Callable<> lambda expression
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
   * @return null for use in Callable<> lambda expression
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
   * @return null for use in Callable<> lambda expression
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
   * @return null for use in Callable<> lambda expression
   * @throws IllegalArgumentException if the image does not exist
   */
  Void verticalFlip(String imageName, String destImageName) throws IllegalArgumentException;

  /**
   * Create a greyscale using only the value component of a given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to get the value component of
   * @param destImageName the name to give the new image
   * @return null for use in Callable<> lambda expression
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
   * @return null for use in Callable<> lambda expression
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
   * @return null for use in Callable<> lambda expression
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
   * @return null for use in Callable<> lambda expression
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
   * @return null for use in Callable<> lambda expression
   * @throws IllegalArgumentException if the image does not exist
   */
  Void darken(String imageName, String destImageName, int amount)
          throws IllegalArgumentException;

  /**
   * create an image that is blur to the given image, and load it with the given name.
   *
   * @param imageName     the name of the image to blur
   * @param destImageName the name to give the new image
   * @return null for use in Callable<> lambda expression
   * @throws IllegalArgumentException if the image does not exist
   */
  Void blur(String imageName, String destImageName)
          throws IllegalArgumentException;

  /**
   * create an image that is sharpening to the given image, and load it with the given name.
   *
   * @param imageName     the name of the image to sharpening
   * @param destImageName the name to give the new image
   * @return null for use in Callable<> lambda expression
   * @throws IllegalArgumentException if the image does not exist
   */
  Void sharpen(String imageName, String destImageName)
          throws IllegalArgumentException;

  /**
   * create an image that is greyscale with the given image, and load it with the given name.
   *
   * @param imageName     the name of the image to greyscale
   * @param destImageName the name to give the new image
   * @return null for use in Callable<> lambda expression
   * @throws IllegalArgumentException if the image does not exist
   */
  Void greyscale(String imageName, String destImageName)
          throws IllegalArgumentException;

  /**
   * create an image that is sepia tone with the given image, and load it with the given name.
   *
   * @param imageName     the name of the image to sepia tone
   * @param destImageName the name to give the new image
   * @return null for use in Callable<> lambda expression
   * @throws IllegalArgumentException if the image does not exist
   */
  Void sepiaTone(String imageName, String destImageName)
          throws IllegalArgumentException;

  /**
   * the list of pixels.
   * @param imageName the name of the image
   * @return a list of pixels of this image
   * @throws IllegalArgumentException if it's null
   */
  ArrayList<ArrayList<Pixel>> pixels(String imageName)
          throws IllegalArgumentException;
}
