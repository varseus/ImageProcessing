package imageprocessing.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import imageprocessing.model.BasePPMImageProcessingModel;
import imageprocessing.model.ImageProcessingModel;
import imageprocessing.view.TextScriptImageProcessingView;

/**
 * The {@code TextScriptedImageProcessingController} represents
 * the operations offered b a controller for an image processor
 * operating through text commands.
 */
public class TextScriptedImageProcessingController implements ImageProcessingController {
  private final ImageProcessingModel model;
  private final TextScriptImageProcessingView view;
  private final Scanner userInput;

  /**
   * Instantiates this controller with the given model, view, and scanner.
   *
   * @param model     to represent the game state
   * @param view      to transmit output from the game
   * @param userInput to field user input
   */
  public TextScriptedImageProcessingController(
          ImageProcessingModel model,
          TextScriptImageProcessingView view,
          Readable userInput) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.userInput = new Scanner(Objects.requireNonNull(userInput));
  }

  /**
   * Starts this image processor, reading from this.in and transmitting to this.view.
   *
   * <p>
   * Accepts the following commands:
   * > load IMAGE-PATH IMAGE-NAME
   * > save IMAGE-NAME IMAGE-PATH
   * > red-component IMAGE-NAME DEST-IMAGE-NAME
   * > blue-component IMAGE-NAME DEST-IMAGE-NAME
   * > green-component IMAGE-NAME DEST-IMAGE-NAME
   * > value-component IMAGE-NAME DEST-IMAGE-NAME
   * > intensity-component IMAGE-NAME DEST-IMAGE-NAME
   * > luma-component IMAGE-NAME DEST-IMAGE-NAME
   * > horizontal-flip IMAGE-NAME DEST-IMAGE-NAME
   * > vertical-flip IMAGE-NAME DEST-IMAGE-NAME
   * > brighten IMAGE-NAME DEST-IMAGE-NAME INCREMENT
   * > darken IMAGE-NAME DEST-IMAGE-NAME INCREMENT
   * </p>
   *
   * @throws IOException if unable to successfully read input or transmit output
   */
  @Override
  public void startProcessor() throws IOException {
    Map<String, Callable> commandMap = new HashMap<String, Callable>();
    commandMap.put("load", (() -> (
            this.model.loadImageFromPPM(this.getNextToken(), this.getNextToken()))));
    commandMap.put("save", (() -> (
            this.model.saveImageToPPM(this.getNextToken(), this.getNextToken()))));
    commandMap.put("red-component", (() -> (
            this.model.redComponent(this.getNextToken(), this.getNextToken()))));
    commandMap.put("green-component", (() -> (
            this.model.greenComponent(this.getNextToken(), this.getNextToken()))));
    commandMap.put("blue-component", (() -> (
            this.model.blueComponent(this.getNextToken(), this.getNextToken()))));
    commandMap.put("value-component", (() -> (
            this.model.valueComponent(this.getNextToken(), this.getNextToken()))));
    commandMap.put("intensity-component", (() -> (
            this.model.intensityComponent(this.getNextToken(), this.getNextToken()))));
    commandMap.put("luma-component", (() -> (
            this.model.lumaComponent(this.getNextToken(), this.getNextToken()))));
    commandMap.put("horizontal-flip", (() -> (
            this.model.horizontalFlip(this.getNextToken(), this.getNextToken()))));
    commandMap.put("vertical-flip", (() -> (
            this.model.verticalFlip(this.getNextToken(), this.getNextToken()))));
    commandMap.put("brighten", (() -> (
            this.model.brighten(this.getNextToken(), this.getNextToken(), this.getNextIntToken()))));
    commandMap.put("darken", (() -> (
            this.model.darken(this.getNextToken(), this.getNextToken(), this.getNextIntToken()))));


    String nextToken = this.getNextToken().toLowerCase().trim();
    if (nextToken.equals("q") || nextToken.equals("quit")) {
      this.view.renderMessage("Bye!\n");
    } else {
      try {
        this.view.renderMessage("Attempting to do " + nextToken + ".\n");
        commandMap.get(nextToken).call();
        this.view.renderMessage("Success!\n");
        this.startProcessor();
      } catch (Exception e) {
        if (e instanceof IllegalArgumentException) {
          this.view.renderMessage("\n" + e.toString() + "\n");
        } else {
          throw new IOException("Unable to read from input/write to output.");
        }
      }
    }
  }

  public static void main(String[] args) {
    BasePPMImageProcessingModel model = new BasePPMImageProcessingModel();
    TextScriptImageProcessingView view = new TextScriptImageProcessingView(System.out);
    StringBuilder input = new StringBuilder("load res/Koala.ppm koala\nq\n");

    TextScriptedImageProcessingController controller = new TextScriptedImageProcessingController(
            model,
            view,
            new StringReader(input.toString())
    );

    try {
      controller.startProcessor();
    } catch (Exception e) {

    }
  }

  private String getNextToken() throws IOException {
    try {
      return this.userInput.next();
    } catch (Exception E) {
      throw new IOException("No more inputs found.");
    }
  }

  private int getNextIntToken() throws IllegalArgumentException {
    try {
      return this.userInput.nextInt();
    } catch (Exception E) {
      throw new IllegalArgumentException("\nLast field for brighten/darken must be an integer.");
    }
  }
}
