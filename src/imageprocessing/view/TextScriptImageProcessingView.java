package imageprocessing.view;

import imageprocessing.model.BasicImageProcessingModel;
import imageprocessing.model.ImageProcessingModel;

import java.io.IOException;
import java.util.Objects;

/**
 * the {@code TextScriptImageProcessingView} represent the view method for
 * the image processing model. Version 2 changes => added saveImageToFIle method.
 *
 * @version 2
 */
public class TextScriptImageProcessingView implements ImageProcessingView {
  private final Appendable appendable;
  private final ImageProcessingModel model;

  /**
   * the Constructor for TextScriptImageProcessingView.
   *
   * @param appendable the appendable for the view
   * @throws NullPointerException if null args
   */
  public TextScriptImageProcessingView(Appendable appendable, ImageProcessingModel model)
          throws NullPointerException {
    this.appendable = Objects.requireNonNull(appendable);
    this.model = Objects.requireNonNull(model);
  }

  /**
   * The Constructor for TextScriptImageProcessingView.
   *
   * @param model the model for the image processing model
   */
  public TextScriptImageProcessingView(BasicImageProcessingModel model) {
    this(System.out, model);
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @return the string being rendered if successful
   * @throws IOException          if transmission of the board to the provided
   *                              data destination fails
   * @throws NullPointerException if null args
   */
  @Override
  public String renderMessage(String message) throws IOException, NullPointerException {
    this.appendable.append(Objects.requireNonNull(message));
    return message;
  }

  /**
   * Export the given image as a ppm.
   *
   * @param imageName the name of the image to save
   * @param filepath  the file to save the image to
   * @return null for use as a function object
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void saveImageToFile(String imageName, String filepath)
          throws IllegalArgumentException, IOException {
    ImageWriteUtil.writePixelsToFile(this.model.pixels(imageName), filepath);
    return null;
  }
}
