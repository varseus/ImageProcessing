package imageprocessing.view;

import java.io.IOException;
import java.util.ArrayList;

import imageprocessing.controller.SwingAppFeatures;
import imageprocessing.model.Pixel;

/**
 * Represents a Swing View for an Image Processor.
 */
public interface ImageProcessingSwingView{
  /**
   * Sets the features/controller to be used by the view.
   * @param features the features to be used
   */
  void setFeatures(SwingAppFeatures features);

  /**
   * Saves the given image data to a file
   * @param filepath the file location
   * @param pixels the image data
   * @throws IllegalArgumentException if illegal filepath or image data
   * @throws IOException if unable to save
   */
  void saveImageToFile(String filepath, ArrayList<ArrayList<Pixel>> pixels) throws IllegalArgumentException, IOException;

  String renderMessage(String message);

  void addImage(String imageName);

  void displayImage(String filepath);

  void refresh();
}
