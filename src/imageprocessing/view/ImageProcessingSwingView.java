package imageprocessing.view;

import java.io.IOException;
import java.util.ArrayList;

import imageprocessing.controller.SwingAppFeatures;
import imageprocessing.model.Image;
import imageprocessing.model.Pixel;

/**
 * Represents a Swing View for an Image Processor.
 */
public interface ImageProcessingSwingView extends ImageProcessingView{

  /**
   * Sets the features/controller to be used by the view.
   *
   * @param features the features to be used
   */
  void setFeatures(SwingAppFeatures features);

  /**
   * Saves the given image data to a file.
   *
   * @param filepath the file location
   * @param image   the image data
   * @throws IllegalArgumentException if illegal filepath or image data
   * @throws IOException              if unable to save
   */
  Void saveImageToFile(Image image, String filepath)
          throws IllegalArgumentException, IOException;

  /**
   * Renders the given message as a visual/popup.
   *
   * @param message to render
   * @return the message
   */
  String renderMessage(String message);

  /**
   * Adds the given image to the list of images available to this view
   * and displays it.
   *
   * @param imageName name of image to add
   */
  void addImage(String imageName);

  /**
   * Displays the given image to this view.
   *
   * @param image the image to display
   */
  void displayImage(Image image);

  /**
   * Refresh the view.
   */
  void refresh();

  /**
   * Get the from image for an operation.
   * @return from image as a string
   */
  String getFrom();

  /**
   * Get the to image for an operation.
   * @return to image as a string
   */
  String getTo();

  /**
   * Get the filepath for an image for an operation.
   * @return filepath as a string
   */
  String getFilepath(boolean image);

  /**
   * Get the to image for an operation.
   * @param label the string to ask the user
   * @return to image as a string
   */
  Integer getNextIntToken(String label);
}
