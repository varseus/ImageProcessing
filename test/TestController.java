import imageprocessing.controller.SwingAppFeatures;
import imageprocessing.controller.SwingController;
import imageprocessing.controller.TextScriptedImageProcessingController;
import imageprocessing.view.ImageProcessingSwingView;
import imageprocessing.view.MockSwingView;
import imageprocessing.view.TextScriptImageProcessingView;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import imageprocessing.model.BasicImageProcessingModel;
import imageprocessing.model.ImageProcessingModel;
import imageprocessing.model.MockImageProcessingModel;

import static org.junit.Assert.assertEquals;

/**
 * The {@code TestController} to test the controller methods. Version 2 changes: added tests for new
 * functionality of controller. Switched tests to measure equality by comparing image pixels as
 * strings.
 *
 * @version 2
 */
public class TestController {

  private StringBuilder mockOutput;
  private StringReader mockInput;
  private ImageProcessingModel model;
  private TextScriptImageProcessingView view;
  private TextScriptedImageProcessingController controller;

  /**
   * Setup the controller for testing.
   *
   * @param mockInput for the controller to read
   */
  public void setUp(String mockInput) {
    this.mockOutput = new StringBuilder();
    this.mockInput = new StringReader(mockInput);

    this.model = new BasicImageProcessingModel();
    this.view = new TextScriptImageProcessingView(this.mockOutput, this.model);
    this.controller = new TextScriptedImageProcessingController(
        this.model,
        this.view,
        this.mockInput);
  }

  /**
   * Test that creating a controller with a null model throws an error.
   */
  @Test(expected = NullPointerException.class)
  public void testConstructorNull1() {
    this.setUp("");
    this.controller = new TextScriptedImageProcessingController(
        null,
        this.view,
        this.mockInput
    );
  }

  /**
   * Test that creating a controller with a null view throws an error.
   */
  @Test(expected = NullPointerException.class)
  public void testConstructorNull2() {
    this.setUp("");
    this.controller = new TextScriptedImageProcessingController(
        this.model,
        null,
        this.mockInput
    );
  }

  /**
   * Test that creating a controller with a null input throws an error.
   */
  @Test(expected = NullPointerException.class)
  public void testConstructorNull3() {
    this.setUp("");
    this.controller = new TextScriptedImageProcessingController(
        this.model,
        this.view,
        null
    );
  }

  /**
   * Test that the quit command in the controller startProcessor method produces the correct view
   * output.
   */
  @Test
  public void testQuit() throws IOException {
    this.setUp("q\n");

    assertEquals("",
        this.mockOutput.toString());

    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
            "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
            "Bye!\n",
        this.mockOutput.toString());
  }

  /**
   * Test that the quit command in the controller startProcessor method produces the correct view
   * output.
   */
  @Test
  public void testQuit2() throws IOException {
    this.setUp("qUiT\n");

    assertEquals("",
        this.mockOutput.toString());

    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
            "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
            "Bye!\n",
        this.mockOutput.toString());
  }

  /**
   * Test that the quit command in the controller startProcessor method produces the correct view
   * output.
   */
  @Test
  public void testQuit3() throws IOException {
    this.setUp("Q\n");

    assertEquals("",
        this.mockOutput.toString());

    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
            "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
            "Bye!\n",
        this.mockOutput.toString());
  }

  /**
   * Test that the quit command in the controller startProcessor method produces the correct view
   * output.
   */
  @Test
  public void testHelp() throws IOException {
    this.setUp("h H hElP q\n");

    assertEquals("",
        this.mockOutput.toString());

    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
            "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
            "Commands to try:\n" +
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
            "   darken IMAGE-NAME DEST-IMAGE-NAME INCREMENT\n" +
            "   blur IMAGE-NAME DEST-IMAGE-NAME\n" +
            "   sharpen IMAGE-NAME DEST-IMAGE-NAME\n" +
            "   greyscale IMAGE-NAME DEST-IMAGE-NAME\n" +
            "   sepia IMAGE-NAME DEST-IMAGE-NAME\n" +
            "Commands to try:\n" +
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
            "   darken IMAGE-NAME DEST-IMAGE-NAME INCREMENT\n" +
            "   blur IMAGE-NAME DEST-IMAGE-NAME\n" +
            "   sharpen IMAGE-NAME DEST-IMAGE-NAME\n" +
            "   greyscale IMAGE-NAME DEST-IMAGE-NAME\n" +
            "   sepia IMAGE-NAME DEST-IMAGE-NAME\n" +
            "Commands to try:\n" +
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
            "   darken IMAGE-NAME DEST-IMAGE-NAME INCREMENT\n" +
            "   blur IMAGE-NAME DEST-IMAGE-NAME\n" +
            "   sharpen IMAGE-NAME DEST-IMAGE-NAME\n" +
            "   greyscale IMAGE-NAME DEST-IMAGE-NAME\n" +
            "   sepia IMAGE-NAME DEST-IMAGE-NAME\n" +
            "Bye!\n",
        this.mockOutput.toString());
  }

  /**
   * Test that the load command in the controller startProcessor method produces the correct view
   * output.
   */
  @Test
  public void testLoadAndSave() throws IOException {
    this.setUp("load res/square.ppm square save square res/square.ppm q\n");
    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
            "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
            "Attempting to do load.\n" +
            "... From: res/square.ppm ...\n" +
            "... To: square ...\n" +
            "Success!\n" +
            "Attempting to do save.\n" +
            "... From: square ...\n" +
            "... To: res/square.ppm ...\n" +
            "Success!\n" +
            "Bye!\n",
        this.mockOutput.toString());
  }

  /**
   * Test that the load command in the controller startProcessor method produces the correct view
   * output.
   */
  @Test
  public void testLoadBadSource() throws IOException {
    this.setUp("load BAD square q\n");
    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
            "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
            "Attempting to do load.\n" +
            "... From: BAD ...\n" +
            "... To: square ...\n" +
            " Invalid filepath, BAD, filepath must end in one of: " +
            ".gif, .bmp, .wbmp, .jpg, .tif, .ppm, .jpeg, .tiff, .png.\n" +
            "Bye!\n",
        this.mockOutput.toString());
  }

  /**
   * Test that the save command in the controller startProcessor method produces the correct view
   * output.
   */
  @Test
  public void testSaveBadSource() throws IOException {
    this.setUp("load res/square.ppm square save BAD res/square.ppm q\n");
    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
            "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
            "Attempting to do load.\n" +
            "... From: res/square.ppm ...\n" +
            "... To: square ...\n" +
            "Success!\n" +
            "Attempting to do save.\n" +
            "... From: BAD ...\n" +
            "... To: res/square.ppm ...\n" +
            " Given image name does not exist in this processor.\n" +
            "Bye!\n",
        this.mockOutput.toString());
  }

  /**
   * Test that the save command in the controller startProcessor method produces the correct view
   * output.
   */
  @Test
  public void testSaveBadDestination() throws IOException {
    this.setUp("load res/square.ppm square save square res/square.bad q\n");
    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
            "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
            "Attempting to do load.\n" +
            "... From: res/square.ppm ...\n" +
            "... To: square ...\n" +
            "Success!\n" +
            "Attempting to do save.\n" +
            "... From: square ...\n" +
            "... To: res/square.bad ...\n" +
            " Unrecognized file suffix in filepath: res/square.bad. File path must " +
            "end in one of: ppm, tif, jpg, tiff, bmp, gif, png, jpeg, wbmp.\n" +
            "Bye!\n",
        this.mockOutput.toString());
  }

  /**
   * Test that the save command in the controller startProcessor method produces the correct view
   * output.
   */
  @Test
  public void testSaveBadDestination2() throws IOException {
    this.setUp("load res/square.ppm square save square bad q\n");
    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
            "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
            "Attempting to do load.\n" +
            "... From: res/square.ppm ...\n" +
            "... To: square ...\n" +
            "Success!\n" +
            "Attempting to do save.\n" +
            "... From: square ...\n" +
            "... To: bad ...\n" +
            " Unrecognized file suffix in filepath: bad. File path must end in" +
            " one of: ppm, tif, jpg, tiff, bmp, gif, png, jpeg, wbmp.\n" +
            "Bye!\n",
        this.mockOutput.toString());
  }

  /**
   * Test that the load command in the controller startProcessor method produces the correct view
   * output.
   */
  @Test
  public void testLoadComponents() throws IOException {
    this.setUp("load res/square.ppm square " +
        "red-component square squareRed " +
        "green-component square squareGreen " +
        "blue-component square squareBlue " +
        "value-component square squareValue " +
        "luma-component square squareLuma " +
        "intensity-component square squareIntensity " +
        "brighten square squareBrighter 50 " +
        "darken square squareDarker 50 " +
        "horizontal-flip square squareH " +
        "vertical-flip square squareV " +
        "blur square squareVBlur " +
        "sharpen square squareSharpen " +
        "greyscale square squareGreyscale " +
        "sepia square squareSepia q\n");
    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
            "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
            "Attempting to do load.\n" +
            "... From: res/square.ppm ...\n" +
            "... To: square ...\n" +
            "Success!\n" +
            "Attempting to do red-component.\n" +
            "... From: square ...\n" +
            "... To: squareRed ...\n" +
            "Success!\n" +
            "Attempting to do green-component.\n" +
            "... From: square ...\n" +
            "... To: squareGreen ...\n" +
            "Success!\n" +
            "Attempting to do blue-component.\n" +
            "... From: square ...\n" +
            "... To: squareBlue ...\n" +
            "Success!\n" +
            "Attempting to do value-component.\n" +
            "... From: square ...\n" +
            "... To: squareValue ...\n" +
            "Success!\n" +
            "Attempting to do luma-component.\n" +
            "... From: square ...\n" +
            "... To: squareLuma ...\n" +
            "Success!\n" +
            "Attempting to do intensity-component.\n" +
            "... From: square ...\n" +
            "... To: squareIntensity ...\n" +
            "Success!\n" +
            "Attempting to do brighten.\n" +
            "... From: square ...\n" +
            "... To: squareBrighter ...\n" +
            "Success!\n" +
            "Attempting to do darken.\n" +
            "... From: square ...\n" +
            "... To: squareDarker ...\n" +
            "Success!\n" +
            "Attempting to do horizontal-flip.\n" +
            "... From: square ...\n" +
            "... To: squareH ...\n" +
            "Success!\n" +
            "Attempting to do vertical-flip.\n" +
            "... From: square ...\n" +
            "... To: squareV ...\n" +
            "Success!\n" +
            "Attempting to do blur.\n" +
            "... From: square ...\n" +
            "... To: squareVBlur ...\n" +
            "Success!\n" +
            "Attempting to do sharpen.\n" +
            "... From: square ...\n" +
            "... To: squareSharpen ...\n" +
            "Success!\n" +
            "Attempting to do greyscale.\n" +
            "... From: square ...\n" +
            "... To: squareGreyscale ...\n" +
            "Success!\n" +
            "Attempting to do sepia.\n" +
            "... From: square ...\n" +
            "... To: squareSepia ...\n" +
            "Success!\n" +
            "Bye!\n",
        this.mockOutput.toString());
  }

  /**
   * Test that the load command in the controller startProcessor method produces the correct view
   * output.
   */
  @Test
  public void testLoadComponentsDifferentInputs() throws IOException {
    this.setUp("load res/square.ppm square " +
        "rEd-cOmponent square squareRed " +
        "green-COMPONENT square squareGreen " +
        "BLUE-COMPONENT square squareBlue " +
        "value-comPonent square squareValue " +
        "luma-componenT square squareLuma " +
        "intensity-componeNt square squareIntensity " +
        "brigHten square squareBrighter 50 " +
        "DARKEN square squareDarker 50 " +
        "horizontal-FLIP square squareH " +
        "vertical-Flip square squareV " +
        "bLur square squareBlur " +
        "ShaRpeN square squareSharpen " +
        "greyScale square squareGreyscale " +
        "sEPIa square squareSepia q\n");
    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
            "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
            "Attempting to do load.\n" +
            "... From: res/square.ppm ...\n" +
            "... To: square ...\n" +
            "Success!\n" +
            "Attempting to do red-component.\n" +
            "... From: square ...\n" +
            "... To: squareRed ...\n" +
            "Success!\n" +
            "Attempting to do green-component.\n" +
            "... From: square ...\n" +
            "... To: squareGreen ...\n" +
            "Success!\n" +
            "Attempting to do blue-component.\n" +
            "... From: square ...\n" +
            "... To: squareBlue ...\n" +
            "Success!\n" +
            "Attempting to do value-component.\n" +
            "... From: square ...\n" +
            "... To: squareValue ...\n" +
            "Success!\n" +
            "Attempting to do luma-component.\n" +
            "... From: square ...\n" +
            "... To: squareLuma ...\n" +
            "Success!\n" +
            "Attempting to do intensity-component.\n" +
            "... From: square ...\n" +
            "... To: squareIntensity ...\n" +
            "Success!\n" +
            "Attempting to do brighten.\n" +
            "... From: square ...\n" +
            "... To: squareBrighter ...\n" +
            "Success!\n" +
            "Attempting to do darken.\n" +
            "... From: square ...\n" +
            "... To: squareDarker ...\n" +
            "Success!\n" +
            "Attempting to do horizontal-flip.\n" +
            "... From: square ...\n" +
            "... To: squareH ...\n" +
            "Success!\n" +
            "Attempting to do vertical-flip.\n" +
            "... From: square ...\n" +
            "... To: squareV ...\n" +
            "Success!\n" +
            "Attempting to do blur.\n" +
            "... From: square ...\n" +
            "... To: squareBlur ...\n" +
            "Success!\n" +
            "Attempting to do sharpen.\n" +
            "... From: square ...\n" +
            "... To: squareSharpen ...\n" +
            "Success!\n" +
            "Attempting to do greyscale.\n" +
            "... From: square ...\n" +
            "... To: squareGreyscale ...\n" +
            "Success!\n" +
            "Attempting to do sepia.\n" +
            "... From: square ...\n" +
            "... To: squareSepia ...\n" +
            "Success!\n" +
            "Bye!\n",
        this.mockOutput.toString());
  }

  /**
   * Test that various features in the swing controller produces the correct view output.
   */
  @Test
  public void testLoadComponentsBadInputsSwing() throws IOException {
    Appendable log = new StringBuilder();
    this.model = new MockImageProcessingModel(log);
    ImageProcessingSwingView view = new MockSwingView(model);
    SwingAppFeatures controller = new SwingController(model, view);
    view.setFeatures(controller);

    controller.loadImage("", "");
    controller.saveImage("", "");
    controller.processCommand("Red Component");
    controller.processCommand("Value Component");
    controller.processCommand("Brighten");

    assertEquals("loading file to .\n" +
                    "getting pixels from image\n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "getting pixels from image\n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "getting pixels from \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "getting pixels from \n" +
                    "Doing commandgetting pixels from image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "getting pixels from image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Doing commandgetting pixels from image\n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "getting pixels from image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "getting pixels from image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Doing commandgetting pixels from image\n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "Making histogram for \n" +
                    "getting pixels from image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "getting pixels from image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n" +
                    "Making histogram for image\n",
        log.toString());
  }
//
//  /**
//   * Test that the load command in the controller startProcessor method produces the correct view
//   * output.
//   */
//  @Test
//  public void testLoadInvalidFromImage() throws IOException {
//    this.setUp("red-component square squareRed " +
//        "green-component square squareGreen " +
//        "blue-component square squareBlue " +
//        "value-component square squareValue " +
//        "luma-component square squareLuma " +
//        "intensity-component square squareIntensity " +
//        "brighten square squareBrighter 50 " +
//        "darken square squareDarker 50 " +
//        "horizontal-flip square squareH " +
//        "vertical-flip square squareV " +
//        "blur square squareVBlur " +
//        "sharpen square squareSharpen " +
//        "greyscale square squareGreyscale " +
//        "sepia square squareSepia q\n");
//    this.controller.startProcessor();
//
//    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
//            "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
//            "Attempting to do red-component.\n" +
//            "... From: square ...\n" +
//            "... To: squareRed ...\n" +
//            " Given image name does not exist in this processor.\n" +
//            "Attempting to do green-component.\n" +
//            "... From: square ...\n" +
//            "... To: squareGreen ...\n" +
//            " Given image name does not exist in this processor.\n" +
//            "Attempting to do blue-component.\n" +
//            "... From: square ...\n" +
//            "... To: squareBlue ...\n" +
//            " Given image name does not exist in this processor.\n" +
//            "Attempting to do value-component.\n" +
//            "... From: square ...\n" +
//            "... To: squareValue ...\n" +
//            " Given image name does not exist in this processor.\n" +
//            "Attempting to do luma-component.\n" +
//            "... From: square ...\n" +
//            "... To: squareLuma ...\n" +
//            " Given image name does not exist in this processor.\n" +
//            "Attempting to do intensity-component.\n" +
//            "... From: square ...\n" +
//            "... To: squareIntensity ...\n" +
//            " Given image name does not exist in this processor.\n" +
//            "Attempting to do brighten.\n" +
//            "... From: square ...\n" +
//            "... To: squareBrighter ...\n" +
//            " Given image name does not exist in this processor.\n" +
//            "Attempting to do darken.\n" +
//            "... From: square ...\n" +
//            "... To: squareDarker ...\n" +
//            " Given image name does not exist in this processor.\n" +
//            "Attempting to do horizontal-flip.\n" +
//            "... From: square ...\n" +
//            "... To: squareH ...\n" +
//            " Given image name does not exist in this processor.\n" +
//            "Attempting to do vertical-flip.\n" +
//            "... From: square ...\n" +
//            "... To: squareV ...\n" +
//            " Given image name does not exist in this processor.\n" +
//            "Attempting to do blur.\n" +
//            "... From: square ...\n" +
//            "... To: squareVBlur ...\n" +
//            " Given image name does not exist in this processor.\n" +
//            "Attempting to do sharpen.\n" +
//            "... From: square ...\n" +
//            "... To: squareSharpen ...\n" +
//            " Given image name does not exist in this processor.\n" +
//            "Attempting to do greyscale.\n" +
//            "... From: square ...\n" +
//            "... To: squareGreyscale ...\n" +
//            " Given image name does not exist in this processor.\n" +
//            "Attempting to do sepia.\n" +
//            "... From: square ...\n" +
//            "... To: squareSepia ...\n" +
//            " Given image name does not exist in this processor.\n" +
//            "Bye!\n",
//        this.mockOutput.toString());
//  }
//
//  /**
//   * Test that the load command in the controller startProcessor method produces the correct view
//   * output.
//   */
//  @Test
//  public void testControllerToModel() throws IOException {
//    this.mockInput = new StringReader("load res/square.ppm square " +
//        "red-component square squareRed " +
//        "green-component square squareGreen " +
//        "blue-component square squareBlue " +
//        "value-component square squareValue " +
//        "luma-component square squareLuma " +
//        "intensity-component square squareIntensity " +
//        "brighten square squareBrighter 50 " +
//        "darken square squareDarker 50 " +
//        "horizontal-flip square squareH " +
//        "vertical-flip square squareV " +
//        "blur square squareVBlur " +
//        "sharpen square squareSharpen " +
//        "greyscale square squareGreyscale " +
//        "sepia square squareSepia q\n");
//
//    Appendable modelLog = new StringBuilder();
//    this.model = new MockImageProcessingModel(modelLog);
//    this.controller = new TextScriptedImageProcessingController(this.model,
//        new TextScriptImageProcessingView(new StringBuilder(), this.model),
//        this.mockInput);
//    this.controller.startProcessor();
//
//    assertEquals("loading file to square.\n" +
//            "red square to squareRed\n" +
//            "green square to squareGreen\n" +
//            "blue square to squareBlue\n" +
//            "value square to squareValue\n" +
//            "luma square to squareLuma\n" +
//            "intensity square to squareIntensity\n" +
//            "brighten square to squareBrighter 50\n" +
//            "darken square to squareDarker 50\n" +
//            "horizontal square to squareH\n" +
//            "vertical square to squareV\n" +
//            "blur square to squareVBlur\n" +
//            "sharpen square to squareSharpen\n" +
//            "greyscale square to squareGreyscale\n" +
//            "sepiaTone square to squareSepia\n",
//        modelLog.toString());
//  }
//
//  /**
//   * Tests that the controller throws an error if it does not find inputs.
//   */
//  @Test(expected = IOException.class)
//  public void testNoMoreInputs() throws IOException {
//    this.setUp("");
//    this.controller.startProcessor();
//  }
}
