package imageprocessing.controller;

/**
 * Represents features for a swing app.
 */
public interface SwingAppFeatures {

  /**
   * Load the PPM image from the specified filePath and assign it the given name. Overwrites the
   * destination name if already taken.
   *
   * @param absolutePath    the name to give the loaded image
   * @param showInputDialog the file to load the image from
   */
  void loadImage(String absolutePath, String showInputDialog);

  /**
   * Create a greyscale using only the green component of the given image, and load it with the
   * given name. Overwrites the destination name if already taken.
   *
   * @param imageName the name of the image to take the red component of
   * @param destName  the name to give the new image
   */
  void greenComponent(String imageName, String destName);

  /**
   * Create a greyscale using only the blue component of the given image, and load it with the given
   * name. Overwrites the destination name if already taken.
   *
   * @param imageName the name of the image to take the red component of
   * @param destName  the name to give the new image
   */
  void blueComponent(String imageName, String destName);

  /**
   * Create a greyscale using only the value component of the given image, and load it with the given
   * name. Overwrites the destination name if already taken.
   *
   * @param imageName the name of the image to take the red component of
   * @param destName  the name to give the new image
   */
  void valueComponent(String imageName, String destName);

  /**
   * Create a greyscale using only the intensity component of the given image, and load it with the given
   * name. Overwrites the destination name if already taken. Displays the image in the view.
   *
   * @param imageName the name of the image to take the red component of
   * @param destName  the name to give the new image
   */
  void intensityComponent(String imageName, String destName);

  /**
   * Create a greyscale using only the luma component of the given image, and load it with the given
   * name. Overwrites the destination name if already taken. Displays the image in the view.
   *
   * @param imageName the name of the image to take the red component of
   * @param destName  the name to give the new image
   */
  void lumaComponent(String imageName, String destName);

  /**
   * Horizontally flips the given image, and loads it with the given
   * name. Overwrites the destination name if already taken. Displays the new image in the view.
   *
   * @param imageName the name of the image to take the red component of
   * @param destName  the name to give the new image
   */
  void flipHorizontally(String imageName, String destName);

  /**
   * Vertically flips the given image, and loads it with the given
   * name. Overwrites the destination name if already taken. Displays the new image in the view.
   *
   * @param imageName the name of the image to take the red component of
   * @param destName  the name to give the new image
   */
  void flipVertically(String imageName, String destName);

  /**
   * Blurs the given image, and loads it with the given
   * name. Overwrites the destination name if already taken. Displays the new image in the view.
   *
   * @param imageName the name of the image to take the red component of
   * @param destName  the name to give the new image
   */
  void blur(String imageName, String destName);

  /**
   * Applies sepia tone to the given image, and loads it with the given
   * name. Overwrites the destination name if already taken. Displays the new image in the view.
   *
   * @param imageName the name of the image to take the red component of
   * @param destName  the name to give the new image
   */
  void sepia(String imageName, String destName);

  /**
   * Takes the greyscale of the given image, and loads it with the given
   * name. Overwrites the destination name if already taken. Displays the new image in the view.
   *
   * @param imageName the name of the image to take the red component of
   * @param destName  the name to give the new image
   */
  void greyscale(String imageName, String destName);

  /**
   * Sharpens the given image, and loads it with the given
   * name. Overwrites the destination name if already taken. Displays the new image in the view.
   *
   * @param imageName the name of the image to take the red component of
   * @param destName  the name to give the new image
   */
  void sharpen(String imageName, String destName);

  /**
   * Brightens the given image, and loads it with the given
   * name. Overwrites the destination name if already taken. Displays the new image in the view.
   *
   * @param imageName the name of the image to take the red component of
   * @param destName  the name to give the new image
   * @param amount    the amount to brighten by
   */
  void brighten(String imageName, String destName, int amount);

  /**
   * Darkens given image, and loads it with the given
   * name. Overwrites the destination name if already taken. Displays the new image in the view.
   *
   * @param imageName the name of the image to take the red component of
   * @param destName  the name to give the new image
   * @param amount    the amount to darken by
   */
  void darken(String imageName, String destName, int amount);

  /**
   * Displays the given image to the view.
   *
   * @param filepath of the image to display
   */
  void displayImage(String filepath);

  /**
   * Saves the given image to a specified filepath.
   *
   * @param imageName image to save
   * @param filepath  location to save to
   */
  void saveImage(String imageName, String filepath);

  /**
   * Create a greyscale using only the red component of the given image, and load it with the given
   * name. Overwrites the destination name if already taken.
   *
   * @param imageName the name of the image to take the red component of
   * @param destName  the name to give the new image
   */
  void redComponent(String imageName, String destName);
}
