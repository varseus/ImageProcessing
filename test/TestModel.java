import static org.junit.Assert.assertEquals;


import imageprocessing.model.BasicImageProcessingModel;

import imageprocessing.view.ImageProcessingView;
import imageprocessing.view.TextScriptImageProcessingView;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * The {@code TestModel} to test the methods in BasePPMImageProcessingModel class. Version 2
 * changes: added tests for new functionality of controller. Switched tests
 * to measure equality by comparing image pixels as strings.
 *
 * @version 2
 */
public class TestModel {
  private BasicImageProcessingModel model;

  @Before
  public void setup() throws IOException {
    this.model = new BasicImageProcessingModel();
    this.model.loadImageFromFile("res/square.ppm", "square");
  }

  /**
   * Test loading ppm and png.
   */
  @Test
  public void testSaveAndLoadImageFromPPMAndPNG() throws IOException {
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("square", "testRes/square.png");
    this.model.loadImageFromFile("testRes/square.png", "squareAfterLoadAndSave");

    assertEquals(this.model.pixels("square").toString(),
            this.model.pixels("squareAfterLoadAndSave").toString());
  }

  /**
   * Test loading ppm and bmp.
   */
  @Test
  public void testSaveAndLoadImageFromPPMAndBMP() throws IOException {
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("square", "testRes/square.bmp");
    this.model.loadImageFromFile("testRes/square.bmp", "squareAfterLoadAndSave");

    assertEquals(this.model.pixels("square").toString(),
            this.model.pixels("squareAfterLoadAndSave").toString());
  }

  /**
   * Test redComponent method.
   */
  @Test
  public void testRedComponent() throws IOException {
    this.model.redComponent("square", "squareComponent");
    this.model.loadImageFromFile("res/square-red-grayscale.ppm", "squareExpected");
    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }

  /**
   * Test blueComponent method.
   */
  @Test
  public void testBlueComponent() throws IOException {
    this.model.blueComponent("square", "squareComponent");
    this.model.loadImageFromFile("res/square-blue-grayscale.ppm", "squareExpected");
    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }

  /**
   * Test greenComponent method.
   */
  @Test
  public void testGreenComponent() throws IOException {
    this.model.greenComponent("square", "squareComponent");
    this.model.loadImageFromFile("res/square-green-grayscale.ppm", "squareExpected");
    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }

  /**
   * Test brighten method.
   */
  @Test
  public void testBrighten() throws IOException {
    this.model.brighten("square", "squareComponent", 50);
    this.model.loadImageFromFile("res/square-brighten-by50ppm.ppm", "squareExpected");
    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }

  /**
   * Test darken method.
   */
  @Test
  public void testDarken() throws IOException {
    this.model.darken("square", "squareComponent", 50);
    this.model.loadImageFromFile("res/square-darken-50.ppm", "squareExpected");
    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }

  /**
   * Test horizontal method.
   */
  @Test
  public void testHorizontal() throws IOException {
    this.model.horizontalFlip("square", "squareComponent");
    this.model.loadImageFromFile("res/square-horizontal.ppm", "squareExpected");
    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }

  /**
   * Test vertical method.
   */
  @Test
  public void testVertical() throws IOException {
    this.model.verticalFlip("square", "squareComponent");
    this.model.loadImageFromFile("res/square-vertical.ppm", "squareExpected");
    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }

  /**
   * Test horizontal and vertical method.
   */
  @Test
  public void testHorizontalAndVertical() throws IOException {
    this.model.horizontalFlip("square", "squareComponent");
    this.model.verticalFlip("squareComponent", "squareComponent");
    this.model.loadImageFromFile("res/square-horizontal-vertical.ppm", "squareExpected");
    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }

  /**
   * Test vertical and horizontal method.
   */
  @Test
  public void testVerticalAndHorizontal() throws IOException {
    this.model.verticalFlip("square", "squareComponent");
    this.model.horizontalFlip("squareComponent", "squareComponent");
    this.model.loadImageFromFile("res/square-vertical-horizontal.ppm", "squareExpected");
    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }

  /**
   * Test intensity method.
   */
  @Test
  public void testIntensity() throws IOException {
    this.model.intensityComponent("square", "squareComponent");
    this.model.loadImageFromFile("res/square-intensity-greyscale.ppm", "squareExpected");
    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }

  /**
   * Test luma method.
   */
  @Test
  public void testLuma() throws IOException {
    this.model.lumaComponent("square", "squareComponent");
    this.model.loadImageFromFile("res/square-luma-greyscale.ppm", "squareExpected");
    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }

  /**
   * Test blur method.
   */
  @Test
  public void testBlur() throws IOException {
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-blur.ppm", "squareExpected");
    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }

  /**
   * Test sharpen method.
   */
  @Test
  public void testSharpen() throws IOException {
    this.model.sharpen("square", "squareComponent");
    this.model.loadImageFromFile("res/square-sharpen.ppm", "squareExpected");
    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }

  /**
   * Test greyscale method.
   */
  @Test
  public void testGreyscale() throws IOException {
    this.model.greyscale("square", "squareComponent");
    this.model.loadImageFromFile("res/square-greyscale.ppm", "squareExpected");
    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }

  /**
   * Test sepia tone method.
   */
  @Test
  public void testSepiaTone() throws IOException {
    this.model.sepiaTone("square", "squareComponent");
    this.model.loadImageFromFile("res/square-sepia-tone.ppm", "squareExpected");
    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }

  /**
   * Test blur method with ppm, then convert it to png.
   */
  @Test
  public void testBlurPng() throws IOException {
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-blur.png", "squareExpected");
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("squareExpected", "testRes/square-blur.png");
    this.model.loadImageFromFile("testRes/square-blur.png", "squareAfterLoadAndSave");
    assertEquals(this.model.pixels("squareAfterLoadAndSave").toString(),
            this.model.pixels("squareExpected").toString());
  }

  /**
   * Test blur method with ppm, then convert it to bmp.
   */
  @Test
  public void testBlurBmp() throws IOException {
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-blur.bmp", "squareExpected");
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("squareExpected", "testRes/square-blur.bmp");
    this.model.loadImageFromFile("testRes/square-blur.bmp", "squareAfterLoadAndSave");
    assertEquals(this.model.pixels("squareAfterLoadAndSave").toString(),
            this.model.pixels("squareExpected").toString());
  }

  /**
   * Test sharpen method with ppm, then convert it to png.
   */
  @Test
  public void testSharpenPNG() throws IOException {
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-Sharpen.png", "squareExpected");
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("squareExpected", "testRes/square-sharpen.png");
    this.model.loadImageFromFile("testRes/square-sharpen.png", "squareAfterLoadAndSave");
    assertEquals(this.model.pixels("squareAfterLoadAndSave").toString(),
            this.model.pixels("squareExpected").toString());
  }

  /**
   * Test sharpen method with ppm, then convert it to bmp.
   */
  @Test
  public void testSharpenBmp() throws IOException {
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-sharpen.bmp", "squareExpected");
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("squareExpected", "testRes/square-sharpen.bmp");
    this.model.loadImageFromFile("testRes/square-sharpen.bmp", "squareAfterLoadAndSave");
    assertEquals(this.model.pixels("squareAfterLoadAndSave").toString(),
            this.model.pixels("squareExpected").toString());
  }

  /**
   * Test greyscale method with ppm, then convert it to png.
   */
  @Test
  public void testGreyscalePNG() throws IOException {
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-greyscale.png", "squareExpected");
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("squareExpected", "testRes/square-greyscale.png");
    this.model.loadImageFromFile("testRes/square-greyscale.png", "squareAfterLoadAndSave");
    assertEquals(this.model.pixels("squareAfterLoadAndSave").toString(),
            this.model.pixels("squareExpected").toString());
  }

  /**
   * Test greyscale method with ppm, then convert it to bmp.
   */
  @Test
  public void testGreyscaleBmp() throws IOException {
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-greyscale.bmp", "squareExpected");
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("squareExpected", "testRes/square-greyscale.bmp");
    this.model.loadImageFromFile("testRes/square-greyscale.bmp", "squareAfterLoadAndSave");
    assertEquals(this.model.pixels("squareAfterLoadAndSave").toString(),
            this.model.pixels("squareExpected").toString());
  }

  /**
   * Test sepia tone method with ppm, then convert it to png.
   */
  @Test
  public void testSepiaPNG() throws IOException {
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-sepia-tone.png", "squareExpected");
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("squareExpected", "testRes/square-sepia-tone.png");
    this.model.loadImageFromFile("testRes/square-sepia-tone.png", "squareAfterLoadAndSave");
    assertEquals(this.model.pixels("squareAfterLoadAndSave").toString(),
            this.model.pixels("squareExpected").toString());
  }

  /**
   * Test sepia tone method with ppm, then convert it to bmp.
   */
  @Test
  public void testSepiaBmp() throws IOException {
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-sepia-tone.bmp", "squareExpected");
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("squareExpected", "testRes/square-sepia-tone.bmp");
    this.model.loadImageFromFile("testRes/square-sepia-tone.bmp", "squareAfterLoadAndSave");
    assertEquals(this.model.pixels("squareAfterLoadAndSave").toString(),
            this.model.pixels("squareExpected").toString());
  }

  /**
   * Test loading ppm and png then to the blur.
   */
  @Test
  public void testPNGBlur() throws IOException {
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("square", "testRes/square.png");
    this.model.loadImageFromFile("testRes/square.png", "squareAfterLoadAndSave");
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-blur.png", "squareExpected");
    assertEquals(this.model.pixels("square").toString(),
            this.model.pixels("squareAfterLoadAndSave").toString());
  }

  /**
   * Test loading ppm and bmp then to the blur.
   */
  @Test
  public void testBmpBlur() throws IOException {
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("square", "testRes/square.bmp");
    this.model.loadImageFromFile("testRes/square.bmp", "squareAfterLoadAndSave");
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-blur.bmp", "squareExpected");
    assertEquals(this.model.pixels("square").toString(),
            this.model.pixels("squareAfterLoadAndSave").toString());
  }

  /**
   * Test loading ppm and png then to sharpen.
   */
  @Test
  public void testPNGSharpen() throws IOException {
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("square", "testRes/square.png");
    this.model.loadImageFromFile("testRes/square.png", "squareAfterLoadAndSave");
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-sharpen.png", "squareExpected");
    assertEquals(this.model.pixels("square").toString(),
            this.model.pixels("squareAfterLoadAndSave").toString());
  }

  /**
   * Test loading ppm and bmp then to sharpen.
   */
  @Test
  public void testBmpSharpen() throws IOException {
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("square", "testRes/square.bmp");
    this.model.loadImageFromFile("testRes/square.bmp", "squareAfterLoadAndSave");
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-sharpen.bmp", "squareExpected");
    assertEquals(this.model.pixels("square").toString(),
            this.model.pixels("squareAfterLoadAndSave").toString());
  }

  /**
   * Test loading ppm and png then to greyscale.
   */
  @Test
  public void testPNGGreyscale() throws IOException {
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("square", "testRes/square.png");
    this.model.loadImageFromFile("testRes/square.png", "squareAfterLoadAndSave");
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-greyscale.png", "squareExpected");
    assertEquals(this.model.pixels("square").toString(),
            this.model.pixels("squareAfterLoadAndSave").toString());
  }

  /**
   * Test loading ppm and bmp then to greyscale.
   */
  @Test
  public void testbmpGreyscale() throws IOException {
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("square", "testRes/square.bmp");
    this.model.loadImageFromFile("testRes/square.bmp", "squareAfterLoadAndSave");
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-greyscale.bmp", "squareExpected");
    assertEquals(this.model.pixels("square").toString(),
            this.model.pixels("squareAfterLoadAndSave").toString());
  }

  /**
   * Test loading ppm and png then to sepia-tone.
   */
  @Test
  public void testPNGSepiaTone() throws IOException {
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("square", "testRes/square.png");
    this.model.loadImageFromFile("testRes/square.png", "squareAfterLoadAndSave");
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-sepia-tone.png", "squareExpected");
    assertEquals(this.model.pixels("square").toString(),
            this.model.pixels("squareAfterLoadAndSave").toString());
  }

  /**
   * Test loading ppm and bmp then to sepia-tone.
   */
  @Test
  public void testbmpSepiaTone() throws IOException {
    ImageProcessingView view = new TextScriptImageProcessingView(new StringBuilder(), this.model);
    view.saveImageToFile("square", "testRes/square.bmp");
    this.model.loadImageFromFile("testRes/square.bmp", "squareAfterLoadAndSave");
    this.model.blur("square", "squareComponent");
    this.model.loadImageFromFile("res/square-sepia-tone.bmp", "squareExpected");
    assertEquals(this.model.pixels("square").toString(),
            this.model.pixels("squareAfterLoadAndSave").toString());
  }

  /**
   * Tests loading something that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageFromPPMFail1() throws IOException {
    this.model.loadImageFromFile("res/abc.ppm", "square");
  }

  /**
   * Tests loading something that is improperly named.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageFromPPMFail2() throws IOException {
    this.model.loadImageFromFile("res/square", "square");
  }

  /**
   * Tests getting redComponent for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail3() throws IOException {
    this.model.redComponent("notsquare", "square");
  }

  /**
   * Tests getting greenComponent for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail4() throws IOException {
    this.model.greenComponent("notsquare", "square");
  }

  /**
   * Tests getting blueComponent for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail5() throws IOException {
    this.model.blueComponent("notsquare", "square");
  }

  /**
   * Tests getting horizontalFlip for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail6() throws IOException {
    this.model.horizontalFlip("notsquare", "square");
  }

  /**
   * Tests getting verticalFlip for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail7() throws IOException {
    this.model.verticalFlip("notsquare", "square");
  }

  /**
   * Tests getting valueComponent for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail8() throws IOException {
    this.model.valueComponent("notsquare", "square");
  }

  /**
   * Tests getting intensityComponent for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail9() throws IOException {
    this.model.intensityComponent("notsquare", "square");
  }

  /**
   * Tests getting lumaComponent for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail10() throws IOException {
    this.model.lumaComponent("notsquare", "square");
  }

  /**
   * Tests getting brighten for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail11() throws IOException {
    this.model.brighten("notsquare", "square", 10);
  }

  /**
   * Tests getting darken for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail12() throws IOException {
    this.model.darken("notsquare", "square", 10);
  }

  /**
   * Tests getting blur for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail13() throws IOException {
    this.model.blur("notsquare", "square");
  }

  /**
   * Tests getting sharpen for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail14() throws IOException {
    this.model.sharpen("notsquare", "square");
  }

  /**
   * Tests getting greyscale for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail15() throws IOException {
    this.model.greyscale("notsquare", "square");
  }

  /**
   * Tests getting sepia tone for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail16() throws IOException {
    this.model.sepiaTone("notsquare", "square");
  }
}