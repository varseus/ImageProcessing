package imageprocessing.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.io.InputStreamReader;

import imageprocessing.ImageUtil;
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
  private final Map<String, Callable> commandMap;

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

    this.commandMap = new HashMap<String, Callable>();
    this.commandMap.put("load", (() -> (
            this.loadHelper(this.getFrom(), this.getTo()))));
    this.commandMap.put("save", (() -> (
            this.saveHelper(this.getFrom(), this.getTo()))));
    this.commandMap.put("red-component", (() -> (
            this.model.redComponent(this.getFrom(), this.getTo()))));
    this.commandMap.put("green-component", (() -> (
            this.model.greenComponent(this.getFrom(), this.getTo()))));
    this.commandMap.put("blue-component", (() -> (
            this.model.blueComponent(this.getFrom(), this.getTo()))));
    this.commandMap.put("value-component", (() -> (
            this.model.valueComponent(this.getFrom(), this.getTo()))));
    this.commandMap.put("intensity-component", (() -> (
            this.model.intensityComponent(this.getFrom(), this.getTo()))));
    this.commandMap.put("luma-component", (() -> (
            this.model.lumaComponent(this.getFrom(), this.getTo()))));
    this.commandMap.put("horizontal-flip", (() -> (
            this.model.horizontalFlip(this.getFrom(), this.getTo()))));
    this.commandMap.put("vertical-flip", (() -> (
            this.model.verticalFlip(this.getFrom(), this.getTo()))));
    this.commandMap.put("brighten", (() -> (
            this.model.brighten(this.getFrom(), this.getTo(), this.getNextIntToken()))));
    this.commandMap.put("darken", (() -> (
            this.model.darken(this.getFrom(), this.getTo(), this.getNextIntToken()))));
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
   * > q
   * > quit
   * > h
   * > help
   * </p>
   *
   * @throws IOException if unable to successfully read input or transmit output
   */
  @Override
  public void startProcessor() throws IOException {
    this.view.renderMessage("WELCOME TO IMAGE PROCESSOR\n" +
            "Enter 'q' to quite. Enter 'help' for a list of commands\n");

    this.process();
  }

  private void process() throws IOException {
    String nextToken = this.getNextToken().toLowerCase().trim();
    if (nextToken.equals("q") || nextToken.equals("quit")) {
      this.view.renderMessage("Bye!\n");
    } else if (nextToken.equals("h") || nextToken.equals("help")) {
      this.view.renderMessage("Commands to try:\n" +
              "   load IMAGE-PATH IMAGE-NAME\n" +
              "   save IMAGE-NAME IMAGE-PATH\n" +
              "   red-component IMAGE-NAME DEST-IMAGE-NAME\n" +
              "   blue-component IMAGE-NAME DEST-IMAGE-NAME\n" +
              "   green-component IMAGE-NAME DEST-IMAGE-NAME\n" +
              "   value-component IMAGE-NAME DEST-IMAGE-NAME\n" +
              "   intensity-component IMAGE-NAME DEST-IMAGE-NAME\n" +
              "   luma-component IMAGE-NAME DEST-IMAGE-NAME\n" +
              "   horizontal-flip IMAGE-NAME DEST-IMAGE-NAME\n" +
              "   vertical-flip IMAGE-NAME DEST-IMAGE-NAME\n" +
              "   brighten IMAGE-NAME DEST-IMAGE-NAME INCREMENT\n" +
              "   darken IMAGE-NAME DEST-IMAGE-NAME INCREMENT\n");
      this.process();
    } else {
      try {
        this.view.renderMessage("Attempting to do " + nextToken + ".\n");
        if (commandMap.containsKey(nextToken)) {
          commandMap.get(nextToken).call();
          this.view.renderMessage("Success!\n");
        } else {
          this.view.renderMessage("Command " + nextToken + " not identified.\n");
        }
        this.process();
      } catch (Exception e) {
        if (e instanceof IllegalArgumentException) {
          String error = e.toString();
          error = error.substring(error.indexOf("Exception:") + 10);
          this.view.renderMessage(error + "\n");
          this.process();
        } else {
          throw new IOException(e.toString());
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BasePPMImageProcessingModel model = new BasePPMImageProcessingModel();
    TextScriptImageProcessingView view = new TextScriptImageProcessingView(System.out);

    TextScriptedImageProcessingController controller = new TextScriptedImageProcessingController(
            model,
            view,
            new InputStreamReader(System.in));

    controller.startProcessor();
  }

  private String getNextToken() throws IOException {
    try {
      return this.userInput.next().trim();
    } catch (Exception E) {
      throw new IOException("No more inputs found.");
    }
  }

  private String getFrom() throws IOException {
    String nextToken = this.getNextToken().trim();
    this.view.renderMessage("... From: " + nextToken +" ...\n");
    return nextToken;
  }

  private String getTo() throws IOException {
    String nextToken = this.getNextToken().trim();
    this.view.renderMessage("... To: " + nextToken +" ...\n");
    return nextToken;
  }

  private int getNextIntToken() throws IllegalArgumentException, IOException {
    try {
      return this.userInput.nextInt();
    } catch (Exception e) {
      if (e instanceof IOException) {
        throw new IOException(e.toString());
      } else {
        throw new IllegalArgumentException("Last field for brighten/darken must be an integer.");
      }
    }
  }

  private Void loadHelper(String from, String to) throws IllegalArgumentException {
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath(from), to);
    return null;
  }

  private Void saveHelper(String from, String to) throws IllegalArgumentException, IOException {
    ImageUtil.writeToFile(this.model.saveImageToPPM(from), to);
    return null;
  }
}
