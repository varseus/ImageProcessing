package imageprocessing.controller;

/**
 * Represents features for a swing app.
 */
public interface SwingAppFeatures {

  /**
   * Load the PPM image from the specified filePath and assign it
   * the given name. Overwrites the destination name if already taken.
   *
   * @param absolutePath the name to give the loaded image
   * @param showInputDialog  the file to load the image from
   */
  void loadImage(String absolutePath, String showInputDialog);

  /**
   * Create a greyscale using only the green component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the red component of
   * @param destName the name to give the new image
   */
  void greenComponent(String imageName, String destName);

  /**
   * Create a greyscale using only the blue component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the red component of
   * @param destName the name to give the new image
   */
  void blueComponent(String imageName, String destName);

  void valueComponent(String imageName, String destName);

  void intensityComponent(String imageName, String destName);

  void lumaComponent(String imageName, String destName);

  void flipHorizontally(String imageName, String destName);

  void flipVertically(String imageName, String destName);

  void blur(String imageName, String destName);

  void sepia(String imageName, String destName);

  void greyscale(String imageName, String destName);

  void sharpen(String imageName, String destName);

  void brighten(String imageName, String destName, int amount);

  void darken(String imageName, String destName, int amount);

  void displayImage(String filepath);

  void saveImage(String imageName, String filepath);

  /**
   * Create a greyscale using only the red component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the red component of
   * @param destName the name to give the new image
   */
  void redComponent(String imageName, String destName);
}
