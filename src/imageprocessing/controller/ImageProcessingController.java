package imageprocessing.controller;

import java.io.IOException;

/**
 * This interface represents operations that should be offered by a controller for the image
 * processor.
 */
public interface ImageProcessingController {

  /**
   * Starts an image processor. Input/output of the processor is specific to each implementation.
   *
   * @throws IllegalStateException if unable to successfully read input or transmit output
   */
  void startProcessor() throws IOException;
}
