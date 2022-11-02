package imageprocessing.view;

import imageprocessing.model.BasePPMImageProcessingModel;
import java.io.IOException;
import java.util.Objects;

/**
 * the {@code TextScriptImageProcessingView} represent the view method for the image processing model.
 */
public class TextScriptImageProcessingView implements ImageProcessingView{
  private final Appendable appendable;

  /**
   * the Constructor for TextScriptImageProcessingView.
   * @param model the model for the image processing model
   * @param appendable the appendable for the view
   */
  public TextScriptImageProcessingView(Appendable appendable){
    this.appendable = Objects.requireNonNull(appendable);
  }

  /**
   * the Constructor for TextScriptImageProcessingView.
   * @param model the model for the image processing model
   */
  public TextScriptImageProcessingView(BasePPMImageProcessingModel model) {
    this(System.out);
  }
  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }
}
