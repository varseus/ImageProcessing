package imageprocessing.view;
import java.io.IOException;

/**
 * This class represents operations that should be
 * offered by a text-based view of the image processor.
 */
public interface ImageProcessingView {
  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   * @return the string being rendered if successful
   */
  String renderMessage(String message) throws IOException;
}
