import static org.junit.Assert.assertEquals;


import imageprocessing.model.BasicImageProcessingModel;

import imageprocessing.model.ImageReadUtil;
import imageprocessing.view.ImageProcessingView;
import imageprocessing.view.ImageWriteUtil;
import imageprocessing.view.TextScriptImageProcessingView;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The {@code TestModel} to test the methods in BasePPMImageProcessingModel class.
 */
public class TestModel {
  private BasicImageProcessingModel model;

  @Before
  public void setup() throws IOException {
    this.model = new BasicImageProcessingModel();
    this.model.loadImageFromFile("res/square.ppm", "square");
  }

  /**
   * Test loading ppm.
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
   * Test redComponent method.
   */
  @Test
  public void testRedComponent() throws IOException {
    this.model.redComponent("square", "squareComponent");

    this.model.loadImageFromFile("res/square-red-grayscale.ppm", "squareExpected");

    assertEquals(this.model.pixels("squareExpected").toString(),
            this.model.pixels("squareComponent").toString());
  }
//
//  /**
//   * Test greenComponent method.
//   */
//  @Test
//  public void testGreenComponent() throws IOException {
//    StringBuilder actual = new StringBuilder();
//    StringBuilder expected = new StringBuilder();
//
//    this.model.greenComponent("square", "squareComponent");
//    actual = this.model.saveImageToFile("squareComponent");
//    this.model.loadImageFromFile(ImageUtil.getFileReaderFromFilePath("res/square-green-greyscale.ppm"), "actualsquareComponent");
//    expected = this.model.saveImageToFile("actualsquareComponent");
//
//    assertEquals(expected.toString(), actual.toString());
//  }
//
//  /**
//   * Test blueComponent method.
//   */
//  @Test
//  public void testBlueComponent() throws IOException {
//    StringBuilder actual = new StringBuilder();
//    StringBuilder expected = new StringBuilder();
//
//    this.model.blueComponent("square", "squareComponent");
//    actual = this.model.saveImageToFile("squareComponent");
//    this.model.loadImageFromFile(ImageUtil.getFileReaderFromFilePath("res/square-blue-greyscale.ppm"), "actualsquareComponent");
//    expected = this.model.saveImageToFile("actualsquareComponent");
//
//    assertEquals(expected.toString(), actual.toString());
//  }
//
//  /**
//   * Test valueComponent method.
//   */
//  @Test
//  public void testValueComponent() throws IOException {
//    StringBuilder actual = new StringBuilder();
//    StringBuilder expected = new StringBuilder();
//
//    this.model.valueComponent("square", "squareComponent");
//    actual = this.model.saveImageToFile("squareComponent");
//    this.model.loadImageFromFile(ImageUtil.getFileReaderFromFilePath("res/square-value-greyscale.ppm"), "actualsquareComponent");
//    expected = this.model.saveImageToFile("actualsquareComponent");
//
//    assertEquals(expected.toString(), actual.toString());
//  }
//
//  /**
//   * Test lumaComponent method.
//   */
//  @Test
//  public void testLumaComponent() throws IOException {
//    StringBuilder actual = new StringBuilder();
//    StringBuilder expected = new StringBuilder();
//
//    this.model.lumaComponent("square", "squareComponent");
//    actual = this.model.saveImageToFile("squareComponent");
//    this.model.loadImageFromFile(ImageUtil.getFileReaderFromFilePath("res/square-luma-greyscale.ppm"), "actualsquareComponent");
//    expected = this.model.saveImageToFile("actualsquareComponent");
//
//    assertEquals(expected.toString(), actual.toString());
//  }
//
//  /**
//   * Test intensityComponent method.
//   */
//  @Test
//  public void testIntensityComponent() throws IOException {
//    StringBuilder actual = new StringBuilder();
//    StringBuilder expected = new StringBuilder();
//
//    this.model.intensityComponent("square", "squareComponent");
//    actual = this.model.saveImageToFile("squareComponent");
//    this.model.loadImageFromFile(ImageUtil.getFileReaderFromFilePath("res/square-intensity-greyscale.ppm"), "actualsquareComponent");
//    expected = this.model.saveImageToFile("actualsquareComponent");
//
//    assertEquals(expected.toString(), actual.toString());
//  }
//
//  /**
//   * Test horizontalFlip method.
//   */
//  @Test
//  public void testHorizontal() throws IOException {
//    StringBuilder actual = new StringBuilder();
//    StringBuilder expected = new StringBuilder();
//
//    this.model.horizontalFlip("square", "squareFlip");
//    actual = this.model.saveImageToFile("squareFlip");
//    this.model.loadImageFromFile(ImageUtil.getFileReaderFromFilePath("res/square-horizontal.ppm"),
//            "actualsquareFlip");
//    expected  =this.model.saveImageToFile("actualsquareFlip");
//
//    assertEquals(expected.toString(), actual.toString());
//  }
//
//  /**
//   * Test verticalFlip method.
//   */
//  @Test
//  public void testVertical() throws IOException {
//    StringBuilder actual = new StringBuilder();
//    StringBuilder expected = new StringBuilder();
//
//    this.model.verticalFlip("square", "squareFlip");
//    actual = this.model.saveImageToFile("squareFlip");
//    this.model.loadImageFromFile(ImageUtil.getFileReaderFromFilePath("res/square-vertical.ppm"),
//            "actualsquareFlip");
//    expected = this.model.saveImageToFile("actualsquareFlip");
//
//    assertEquals(expected.toString(), actual.toString());
//  }
//
//  /**
//   * Test brighten method.
//   */
//  @Test
//  public void testBrighten() throws IOException {
//    StringBuilder actual = new StringBuilder();
//    StringBuilder expected = new StringBuilder();
//
//    this.model.brighten("square", "squareBrighten", 50);
//    actual = this.model.saveImageToFile("squareBrighten");
//    this.model.loadImageFromFile(ImageUtil.getFileReaderFromFilePath("res/square-brighter-by-50.ppm"),
//            "actualsquareBrighten");
//    expected = this.model.saveImageToFile("actualsquareBrighten");
//
//    assertEquals(expected.toString(), actual.toString());
//  }
//
//  /**
//   * Test darken method.
//   */
//  @Test
//  public void testDarken() throws IOException {
//    StringBuilder actual = new StringBuilder();
//    StringBuilder expected = new StringBuilder();
//
//    this.model.darken("square", "squareDarken", 50);
//    actual = this.model.saveImageToFile("squareDarken");
//    this.model.loadImageFromFile(ImageUtil.getFileReaderFromFilePath("res/square-darken-by-50.ppm"),
//            "actualsquareDarken");
//    expected = this.model.saveImageToFile("actualsquareDarken");
//
//    assertEquals(expected.toString(), actual.toString());
//  }

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

}