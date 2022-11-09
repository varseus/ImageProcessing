import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import imageprocessing.controller.TextScriptedImageProcessingController;
import imageprocessing.model.BasicImageProcessingModel;
import imageprocessing.model.ImageProcessingModel;
import imageprocessing.model.MockImageProcessingModel;
import imageprocessing.view.TextScriptImageProcessingView;

import static org.junit.Assert.assertEquals;

/**
 * The {@code TestController} to test the controller methods.
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
    this.view = new TextScriptImageProcessingView(this.mockOutput);
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
   * Test that the quit command in the controller startProcessor method
   * produces the correct view output.
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
   * Test that the quit command in the controller startProcessor method
   * produces the correct view output.
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
   * Test that the quit command in the controller startProcessor method
   * produces the correct view output.
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
   * Test that the quit command in the controller startProcessor method
   * produces the correct view output.
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
                    "Bye!\n",
            this.mockOutput.toString());
  }

  /**
   * Test that the load command in the controller startProcessor method produces the correct
   * view output.
   */
  @Test
  public void testLoadAndSave() throws IOException {
    this.setUp("load res/Koala.ppm koala save koala res/Koala.ppm q\n");
    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
                    "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
                    "Attempting to do load.\n" +
                    "... From: res/Koala.ppm ...\n" +
                    "... To: koala ...\n" +
                    "Success!\n" +
                    "Attempting to do save.\n" +
                    "... From: koala ...\n" +
                    "... To: res/Koala.ppm ...\n" +
                    "Success!\n" +
                    "Bye!\n",
            this.mockOutput.toString());
  }

  /**
   * Test that the load command in the controller startProcessor method produces the correct
   * view output.
   */
  @Test
  public void testLoadBadSource() throws IOException {
    this.setUp("load BAD koala q\n");
    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
                    "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
                    "Attempting to do load.\n" +
                    "... From: BAD ...\n" +
                    "... To: koala ...\n" +
                    " Invalid filepath, BAD, filepath must end in .ppm\n" +
                    "Bye!\n",
            this.mockOutput.toString());
  }

  /**
   * Test that the save command in the controller startProcessor method produces the correct
   * view output.
   */
  @Test
  public void testSaveBadSource() throws IOException {
    this.setUp("load res/Koala.ppm koala save BAD res/Koala.ppm q\n");
    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
                    "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
                    "Attempting to do load.\n" +
                    "... From: res/Koala.ppm ...\n" +
                    "... To: koala ...\n" +
                    "Success!\n" +
                    "Attempting to do save.\n" +
                    "... From: BAD ...\n" +
                    "... To: res/Koala.ppm ...\n" +
                    " Image BAD does not exist.\n" +
                    "Bye!\n",
            this.mockOutput.toString());
  }

  /**
   * Test that the load command in the controller startProcessor method produces the correct
   * view output.
   */
  @Test
  public void testLoadComponents() throws IOException {
    this.setUp("load res/Koala.ppm koala " +
            "red-component koala koalaRed " +
            "green-component koala koalaGreen " +
            "blue-component koala koalaBlue " +
            "value-component koala koalaValue " +
            "luma-component koala koalaLuma " +
            "intensity-component koala koalaIntensity " +
            "brighten koala koalaBrighter 50 " +
            "darken koala koalaDarker 50 " +
            "horizontal-flip koala koalaH " +
            "vertical-flip koala koalaV q\n");
    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
                    "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
                    "Attempting to do load.\n" +
                    "... From: res/Koala.ppm ...\n" +
                    "... To: koala ...\n" +
                    "Success!\n" +
                    "Attempting to do red-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaRed ...\n" +
                    "Success!\n" +
                    "Attempting to do green-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaGreen ...\n" +
                    "Success!\n" +
                    "Attempting to do blue-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaBlue ...\n" +
                    "Success!\n" +
                    "Attempting to do value-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaValue ...\n" +
                    "Success!\n" +
                    "Attempting to do luma-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaLuma ...\n" +
                    "Success!\n" +
                    "Attempting to do intensity-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaIntensity ...\n" +
                    "Success!\n" +
                    "Attempting to do brighten.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaBrighter ...\n" +
                    "Success!\n" +
                    "Attempting to do darken.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaDarker ...\n" +
                    "Success!\n" +
                    "Attempting to do horizontal-flip.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaH ...\n" +
                    "Success!\n" +
                    "Attempting to do vertical-flip.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaV ...\n" +
                    "Success!\n" +
                    "Bye!\n",
            this.mockOutput.toString());
  }

  /**
   * Test that the load command in the controller startProcessor method produces the correct
   * view output.
   */
  @Test
  public void testLoadComponentsDifferentInputs() throws IOException {
    this.setUp("load res/Koala.ppm koala " +
            "rEd-cOmponent koala koalaRed " +
            "green-COMPONENT koala koalaGreen " +
            "BLUE-COMPONENT koala koalaBlue " +
            "value-comPonent koala koalaValue " +
            "luma-componenT koala koalaLuma " +
            "intensity-componeNt koala koalaIntensity " +
            "brigHten koala koalaBrighter 50 " +
            "DARKEN koala koalaDarker 50 " +
            "horizontal-FLIP koala koalaH " +
            "vertical-Flip koala koalaV q\n");
    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
                    "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
                    "Attempting to do load.\n" +
                    "... From: res/Koala.ppm ...\n" +
                    "... To: koala ...\n" +
                    "Success!\n" +
                    "Attempting to do red-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaRed ...\n" +
                    "Success!\n" +
                    "Attempting to do green-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaGreen ...\n" +
                    "Success!\n" +
                    "Attempting to do blue-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaBlue ...\n" +
                    "Success!\n" +
                    "Attempting to do value-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaValue ...\n" +
                    "Success!\n" +
                    "Attempting to do luma-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaLuma ...\n" +
                    "Success!\n" +
                    "Attempting to do intensity-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaIntensity ...\n" +
                    "Success!\n" +
                    "Attempting to do brighten.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaBrighter ...\n" +
                    "Success!\n" +
                    "Attempting to do darken.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaDarker ...\n" +
                    "Success!\n" +
                    "Attempting to do horizontal-flip.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaH ...\n" +
                    "Success!\n" +
                    "Attempting to do vertical-flip.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaV ...\n" +
                    "Success!\n" +
                    "Bye!\n",
            this.mockOutput.toString());
  }

  /**
   * Test that the load command in the controller startProcessor method produces the correct
   * view output.
   */
  @Test
  public void testLoadInvalidFromImage() throws IOException {
    this.setUp("red-component koala koalaRed " +
            "green-component koala koalaGreen " +
            "blue-component koala koalaBlue " +
            "value-component koala koalaValue " +
            "luma-component koala koalaLuma " +
            "intensity-component koala koalaIntensity " +
            "brighten koala koalaBrighter 50 " +
            "darken koala koalaDarker 50 " +
            "horizontal-flip koala koalaH " +
            "vertical-flip koala koalaV q\n");
    this.controller.startProcessor();

    assertEquals("WELCOME TO IMAGE PROCESSOR\n" +
                    "Enter 'q' to quite. Enter 'help' for a list of commands\n" +
                    "Attempting to do red-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaRed ...\n" +
                    " Given image name does not exist in this processor.\n" +
                    "Attempting to do green-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaGreen ...\n" +
                    " Given image name does not exist in this processor.\n" +
                    "Attempting to do blue-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaBlue ...\n" +
                    " Given image name does not exist in this processor.\n" +
                    "Attempting to do value-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaValue ...\n" +
                    " Given image name does not exist in this processor.\n" +
                    "Attempting to do luma-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaLuma ...\n" +
                    " Given image name does not exist in this processor.\n" +
                    "Attempting to do intensity-component.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaIntensity ...\n" +
                    " Given image name does not exist in this processor.\n" +
                    "Attempting to do brighten.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaBrighter ...\n" +
                    " Given image name does not exist in this processor.\n" +
                    "Attempting to do darken.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaDarker ...\n" +
                    " Given image name does not exist in this processor.\n" +
                    "Attempting to do horizontal-flip.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaH ...\n" +
                    " Given image name does not exist in this processor.\n" +
                    "Attempting to do vertical-flip.\n" +
                    "... From: koala ...\n" +
                    "... To: koalaV ...\n" +
                    " Given image name does not exist in this processor.\n" +
                    "Bye!\n",
            this.mockOutput.toString());
  }

  /**
   * Test that the load command in the controller startProcessor method produces the correct
   * view output.
   */
  @Test
  public void testControllerToModel() throws IOException {
    this.mockInput = new StringReader("load res/Koala.ppm koala " +
            "red-component koala koalaRed " +
            "green-component koala koalaGreen " +
            "blue-component koala koalaBlue " +
            "value-component koala koalaValue " +
            "luma-component koala koalaLuma " +
            "intensity-component koala koalaIntensity " +
            "brighten koala koalaBrighter 50 " +
            "darken koala koalaDarker 50 " +
            "horizontal-flip koala koalaH " +
            "vertical-flip koala koalaV q\n");

    Appendable modelLog = new StringBuilder();
    this.model = new MockImageProcessingModel(modelLog);
    this.controller = new TextScriptedImageProcessingController(this.model,
            new TextScriptImageProcessingView(new StringBuilder()),
            this.mockInput);
    this.controller.startProcessor();

    assertEquals("loading file to koala.\n" +
                    "red koala to koalaRed\n" +
                    "green koala to koalaGreen\n" +
                    "blue koala to koalaBlue\n" +
                    "value koala to koalaValue\n" +
                    "luma koala to koalaLuma\n" +
                    "intensity koala to koalaIntensity\n" +
                    "brighten koala to koalaBrighter 50\n" +
                    "darken koala to koalaDarker 50\n" +
                    "horizontal koala to koalaH\n" +
                    "vertical koala to koalaV\n",
            modelLog.toString());
  }

  /**
   * Tests that the controller throws an error if it does
   * not find inputs.
   */
  @Test(expected = IOException.class)
  public void testNoMoreInputs() throws IOException {
    this.setUp("");
    this.controller.startProcessor();
  }
}
