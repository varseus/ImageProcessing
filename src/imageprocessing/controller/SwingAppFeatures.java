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
  Void loadImage(String absolutePath, String showInputDialog);

  /**
   * Displays the given image to the view.
   *
   * @param filepath of the image to display
   */
  Void displayImage(String filepath);

  /**
   * Saves the given image to a specified filepath.
   *
   * @param imageName image to save
   * @param filepath  location to save to
   */
  Void saveImage(String imageName, String filepath);

  /**
   * Process a command from the view.
   *
   * @param command the command to process
   */
  void processCommand(String command);
}
