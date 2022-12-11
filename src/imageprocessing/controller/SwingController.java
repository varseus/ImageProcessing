package imageprocessing.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

import imageprocessing.model.Commands.BlueComponent;
import imageprocessing.model.Commands.BlurFilter;
import imageprocessing.model.Commands.BrightenComponent;
import imageprocessing.model.Commands.Command;
import imageprocessing.model.Commands.DarkenComponent;
import imageprocessing.model.Commands.GreenComponent;
import imageprocessing.model.Commands.GreyscaleComponent;
import imageprocessing.model.Commands.HorizontalFlipCommand;
import imageprocessing.model.Commands.IntensityComponent;
import imageprocessing.model.Commands.LumaComponent;
import imageprocessing.model.Commands.RedComponent;
import imageprocessing.model.Commands.SharpenFilter;
import imageprocessing.model.Commands.ValueComponent;
import imageprocessing.model.Commands.VerticalFlipCommand;
import imageprocessing.model.ImageProcessingModel;
import imageprocessing.view.ImageProcessingSwingView;

/**
 * Represents a controller for a swing app.
 */
public class SwingController implements SwingAppFeatures {

  private final ImageProcessingModel model;
  private final ImageProcessingSwingView view;
  private final Map<String, Callable> commandMap;

  /**
   * Instantiate this SwingController with the given model and view.
   *
   * @param model model to run the processor
   * @param view  view to field user input from and output to
   */
  public SwingController(ImageProcessingModel model, ImageProcessingSwingView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);

    this.commandMap = new HashMap<>();
    this.commandMap.put("Load Image", (() -> (
            this.loadImage(this.view.getFilepath(true), this.view.getTo()))));
    this.commandMap.put("Save Image", (() -> (
            this.saveImage("", this.view.getFilepath(false)))));
    this.commandMap.put("Red Component", (() -> (
            this.doCommand(new RedComponent()))));
    //this.model.redComponent("", ""))));
    this.commandMap.put("Green Component", (() -> (
            this.doCommand(new GreenComponent()))));
    this.commandMap.put("Blue Component", (() -> (
            this.doCommand(new BlueComponent()))));
    this.commandMap.put("Value Component", (() -> (
            this.doCommand(new ValueComponent()))));
    this.commandMap.put("Intensity Component", (() -> (
            this.doCommand(new IntensityComponent()))));
    this.commandMap.put("Luma Component", (() -> (
            this.doCommand(new LumaComponent()))));
    this.commandMap.put("Flip Horizontally", (() -> (
            this.doCommand(new HorizontalFlipCommand()))));
    this.commandMap.put("Flip Vertically", (() -> (
            this.doCommand(new VerticalFlipCommand()))));
    this.commandMap.put("Brighten", (() -> (
            this.doCommand(new BrightenComponent(this.view.getNextIntToken())))));
    this.commandMap.put("Darken", (() -> (
            this.doCommand(new DarkenComponent(this.view.getNextIntToken())))));
    this.commandMap.put("Blur", (() -> (
            this.doCommand(new BlurFilter()))));
    this.commandMap.put("Sharpen", (() -> (
            this.doCommand(new SharpenFilter()))));
    this.commandMap.put("Greyscale", (() -> (
            this.doCommand(new GreyscaleComponent()))));
    this.commandMap.put("Sepia", (() -> (
            this.doCommand(new GreyscaleComponent()))));
    this.commandMap.put("Change Image", (() -> (
            this.displayImage(this.view.getFrom()))));
  }

  @Override
  public Void loadImage(String absolutePath, String imageName) {
    try {
      this.model.loadImageFromFile(absolutePath, imageName);
      this.view.addImage(imageName);
      this.displayImage(imageName);
    } catch (Exception e) {
      String error = e.toString();
      this.view.renderMessage(error.substring(error.indexOf("Exception:") + 10) + "\n");
    }

    return null;
  }


  /**
   * Use the given callable to do an action on the model.
   *
   * @param destName name to store the resulting image from the action
   * @param toDo     function object to do on the model
   */
  private Void doCommand(Command toDo) {
    try {
      String from = this.view.getFrom();
      String to = this.view.getTo();
      System.out.println("doing");
      this.model.doCommand(toDo, from, to);
      System.out.println("viewing");
      this.view.addImage(to);
      System.out.println("displaying");
      this.displayImage(to);
      System.out.println("done");
    } catch (Exception e) {
      String error = e.toString();
      this.view.renderMessage(error.substring(error.indexOf("Exception:") + 10) + "\n");
    }

    return null;
  }

  public void processCommand(String command) {
    if(this.commandMap.containsKey(command)){
      System.out.println("calling");
      try {
        this.commandMap.get(command).call();
      } catch (Exception e) {
        this.view.renderMessage(e.getMessage());
      }
    } else {
      this.view.renderMessage("Error. Command not found.");
    }
  }



  @Override
  public Void displayImage(String imageName) {
    this.view.displayImage(this.model.image(imageName));
    this.view.refresh();

    return null;
  }

  /**
   * Save the image from the model at the specified imageName to the. Render an error message to the
   * view if something goes wrong. specified absolutePath;
   *
   * @param imageName    image to save
   * @param absolutePath filepath to save image to
   */
  @Override
  public Void saveImage(String imageName, String absolutePath) {
    try {
      this.view.saveImageToFile(this.model.image(imageName), absolutePath);
    } catch (Exception e) {
      String error = e.toString();
      this.view.renderMessage(error.substring(error.indexOf("Exception:") + 10) + "\n");
    }

    return null;
  }
}
