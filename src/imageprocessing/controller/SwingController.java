package imageprocessing.controller;

import java.util.Objects;
import java.util.concurrent.Callable;

import imageprocessing.model.ImageProcessingModel;
import imageprocessing.view.ImageProcessingSwingView;

/**
 * Represents a controller for a swing app.
 */
public class SwingController implements SwingAppFeatures {
  private final ImageProcessingModel model;
  private final ImageProcessingSwingView view;

  /**
   * Instantiate this SwingController with the given model and view.
   * @param model model to run the processor
   * @param view view to field user input from and output to
   */
  public SwingController(ImageProcessingModel model, ImageProcessingSwingView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
  }

  @Override
  public void loadImage(String absolutePath, String imageName) {
    try {
      this.model.loadImageFromFile(absolutePath, imageName);
      this.view.saveImageToFile("res/displayedImages/" + imageName.hashCode() + ".png", this.model.pixels(imageName));
      this.view.addImage(imageName);
      this.displayImage("res/displayedImages/" + imageName.hashCode() + ".png");
    } catch (Exception e) {
      String error = e.toString();
      this.view.renderMessage(error.substring(error.indexOf("Exception:") + 10) + "\n");
    }
  }

  @Override
  public void redComponent(String imageName, String destName) {
    this.doComponent(destName, () -> (this.model.redComponent(imageName, destName)));
  }

  @Override
  public void greenComponent(String imageName, String destName) {
    this.doComponent(destName, () -> (this.model.greenComponent(imageName, destName)));
  }

  @Override
  public void blueComponent(String imageName, String destName) {
    this.doComponent(destName, () -> (this.model.blueComponent(imageName, destName)));
  }

  @Override
  public void valueComponent(String imageName, String destName) {
    this.doComponent(destName, () -> (this.model.valueComponent(imageName, destName)));
  }

  @Override
  public void intensityComponent(String imageName, String destName) {
    this.doComponent(destName, () -> (this.model.intensityComponent(imageName, destName)));
  }

  @Override
  public void lumaComponent(String imageName, String destName) {
    this.doComponent(destName, () -> (this.model.lumaComponent(imageName, destName)));
  }

  @Override
  public void flipHorizontally(String imageName, String destName) {
    this.doComponent(destName, () -> (this.model.horizontalFlip(imageName, destName)));
  }

  @Override
  public void flipVertically(String imageName, String destName) {
    this.doComponent(destName, () -> (this.model.verticalFlip(imageName, destName)));
  }

  @Override
  public void blur(String imageName, String destName) {
    this.doComponent(destName, () -> (this.model.blur(imageName, destName)));
  }

  @Override
  public void sepia(String imageName, String destName) {
    this.doComponent(destName, () -> (this.model.sepiaTone(imageName, destName)));
  }

  @Override
  public void greyscale(String imageName, String destName) {
    this.doComponent(destName, () -> (this.model.greyscale(imageName, destName)));
  }

  @Override
  public void sharpen(String imageName, String destName) {
    this.doComponent(destName, () -> (this.model.sharpen(imageName, destName)));
  }

  @Override
  public void brighten(String imageName, String destName, int amount) {
    this.doComponent(destName, () -> (this.model.brighten(imageName, destName, amount)));
  }

  @Override
  public void darken(String imageName, String destName, int amount) {
    this.doComponent(destName, () -> (this.model.darken(imageName, destName, amount)));
  }


  /**
   * Use the given callable to do an action on the model.
   *
   * @param destName name to store the resulting image from the action
   * @param toDo     function object to do on the model
   */
  private void doComponent(String destName, Callable toDo) {
    try {
      toDo.call();
      this.view.saveImageToFile("res/displayedImages/" + destName.hashCode() + ".png", this.model.pixels(destName));
      this.view.addImage(destName);
      this.displayImage("res/displayedImages/" + destName.hashCode() + ".png");
    } catch (Exception e) {
      String error = e.toString();
      this.view.renderMessage(error.substring(error.indexOf("Exception:") + 10) + "\n");
    }
  }

  @Override
  public void displayImage(String filepath) {
    this.view.displayImage(filepath);
    this.view.refresh();
  }

  /**
   * Save the image from the model at the specified imageName to the. Render an
   * error message to the view if something goes wrong.
   * specified absolutePath;
   *
   * @param imageName    image to save
   * @param absolutePath filepath to save image to
   */
  @Override
  public void saveImage(String imageName, String absolutePath) {
    try {
      this.view.saveImageToFile(absolutePath, this.model.pixels(imageName));
    } catch (Exception e) {
      String error = e.toString();
      this.view.renderMessage(error.substring(error.indexOf("Exception:") + 10) + "\n");
    }
  }
}
