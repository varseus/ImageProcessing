package imageprocessing.view;

import java.io.IOException;

/**
 * This class represents operations that should be offered by a text-based view of the image
 * processor. Version 2 changes => added saveImageToFile method.
 *
 * @version 2
 */
public interface ImageProcessingView {

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @return the string being rendered if successful
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  String renderMessage(String message) throws IOException;

  /**
   * Export the given image as a ppm.
   *
   * @param imageName the name of the image to save
   * @return StringBuilder containing the PPM data
   * @throws IllegalArgumentException if the image does not exist
   */
  Void saveImageToFile(String imageName, String filepath)
      throws IllegalArgumentException, IOException;
}
