package imageprocessing.view;

import imageprocessing.model.BasicImageProcessingModel;

import java.io.IOException;
import java.util.Objects;

/**
 * the {@code TextScriptImageProcessingView} represent the view method for
 * the image processing model.
 */
public class TextScriptImageProcessingView implements ImageProcessingView {
  private final Appendable appendable;

  /**
   * the Constructor for TextScriptImageProcessingView.
   *
   * @param appendable the appendable for the view
   * @throws NullPointerException if null args
   */
  public TextScriptImageProcessingView(Appendable appendable) throws NullPointerException {
    this.appendable = Objects.requireNonNull(appendable);
  }

  /**
   * The Constructor for TextScriptImageProcessingView.
   *
   * @param model the model for the image processing model
   */
  public TextScriptImageProcessingView(BasicImageProcessingModel model) {
    this(System.out);
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @return the string being rendered if successful
   * @throws IOException if transmission of the board to the provided data destination fails
   * @throws NullPointerException if null args
   */
  @Override
  public String renderMessage(String message) throws IOException, NullPointerException {
    this.appendable.append(Objects.requireNonNull(message));
    return message;
  }
}
