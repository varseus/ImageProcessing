import static org.junit.Assert.assertEquals;

import imageprocessing.ImageUtil;
import imageprocessing.model.BasePPMImageProcessingModel;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

/**
 * The {@code TestModel} to test the methods in BasePPMImageProcessingModel class.
 */
public class TestModel {
  private BasePPMImageProcessingModel model;

  @Before
  public void setup() throws IOException {
    this.model = new BasePPMImageProcessingModel();
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/Koala.ppm"), "koala");
  }

  /**
   * Test saving something that has been loaded.
   */
  @Test
  public void testLoadImageFromPPM() throws IOException {
    Appendable actual = new StringBuilder();
    String expected = ImageUtil.readPPM("res/Koala.ppm").toString()
            .replace("\n", " ");

    this.model.saveImageToPPM("koala", actual);

    assertEquals(
            expected.toString(),
            actual.toString().replace("\n", " "));
  }

  /**
   * Test loading something that has been saved.
   */
  @Test
  public void testSaveImageToPPM() throws IOException {
    StringBuilder actual = new StringBuilder();
    StringBuilder expected = new StringBuilder();

    this.model.saveImageToPPM("koala", expected);
    this.model.loadImageFromPPM(new StringReader(expected.toString()),
            "newKoala");
    this.model.saveImageToPPM("newKoala", actual);

    assertEquals(
            expected.toString().replace("\n", " "),
            actual.toString().replace("\n", " "));
  }

  /**
   * Test redComponent method.
   */
  @Test
  public void testRedComponent() throws IOException {
    StringBuilder actual = new StringBuilder();
    StringBuilder expected = new StringBuilder();

    this.model.redComponent("koala", "koalaComponent");
    this.model.saveImageToPPM("koalaComponent", actual);
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-red-greyscale.ppm"), "actualKoalaComponent");
    this.model.saveImageToPPM("actualKoalaComponent", expected);

    assertEquals(expected.toString(), actual.toString());
  }

  /**
   * Test greenComponent method.
   */
  @Test
  public void testGreenComponent() throws IOException {
    StringBuilder actual = new StringBuilder();
    StringBuilder expected = new StringBuilder();

    this.model.greenComponent("koala", "koalaComponent");
    this.model.saveImageToPPM("koalaComponent", actual);
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-green-greyscale.ppm"), "actualKoalaComponent");
    this.model.saveImageToPPM("actualKoalaComponent", expected);

    assertEquals(expected.toString(), actual.toString());
  }

  /**
   * Test blueComponent method.
   */
  @Test
  public void testBlueComponent() throws IOException {
    StringBuilder actual = new StringBuilder();
    StringBuilder expected = new StringBuilder();

    this.model.blueComponent("koala", "koalaComponent");
    this.model.saveImageToPPM("koalaComponent", actual);
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-blue-greyscale.ppm"), "actualKoalaComponent");
    this.model.saveImageToPPM("actualKoalaComponent", expected);

    assertEquals(expected.toString(), actual.toString());
  }

  /**
   * Test valueComponent method.
   */
  @Test
  public void testValueComponent() throws IOException {
    StringBuilder actual = new StringBuilder();
    StringBuilder expected = new StringBuilder();

    this.model.valueComponent("koala", "koalaComponent");
    this.model.saveImageToPPM("koalaComponent", actual);
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-value-greyscale.ppm"), "actualKoalaComponent");
    this.model.saveImageToPPM("actualKoalaComponent", expected);

    assertEquals(expected.toString(), actual.toString());
  }

  /**
   * Test lumaComponent method.
   */
  @Test
  public void testLumaComponent() throws IOException {
    StringBuilder actual = new StringBuilder();
    StringBuilder expected = new StringBuilder();

    this.model.lumaComponent("koala", "koalaComponent");
    this.model.saveImageToPPM("koalaComponent", actual);
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-luma-greyscale.ppm"), "actualKoalaComponent");
    this.model.saveImageToPPM("actualKoalaComponent", expected);

    assertEquals(expected.toString(), actual.toString());
  }

  /**
   * Test intensityComponent method.
   */
  @Test
  public void testIntensityComponent() throws IOException {
    StringBuilder actual = new StringBuilder();
    StringBuilder expected = new StringBuilder();

    this.model.intensityComponent("koala", "koalaComponent");
    this.model.saveImageToPPM("koalaComponent", actual);
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-intensity-greyscale.ppm"), "actualKoalaComponent");
    this.model.saveImageToPPM("actualKoalaComponent", expected);

    assertEquals(expected.toString(), actual.toString());
  }

  /**
   * Test horizontalFlip method.
   */
  @Test
  public void testHorizontal() throws IOException {
    StringBuilder actual = new StringBuilder();
    StringBuilder expected = new StringBuilder();

    this.model.horizontalFlip("koala", "koalaFlip");
    this.model.saveImageToPPM("koalaFlip", actual);
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-horizontal.ppm"),
            "actualKoalaFlip");
    this.model.saveImageToPPM("actualKoalaFlip", expected);

    assertEquals(expected.toString(), actual.toString());
  }

  /**
   * Test verticalFlip method.
   */
  @Test
  public void testVertical() throws IOException {
    StringBuilder actual = new StringBuilder();
    StringBuilder expected = new StringBuilder();

    this.model.verticalFlip("koala", "koalaFlip");
    this.model.saveImageToPPM("koalaFlip", actual);
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-vertical.ppm"),
            "actualKoalaFlip");
    this.model.saveImageToPPM("actualKoalaFlip", expected);

    assertEquals(expected.toString(), actual.toString());
  }

  /**
   * Test brighten method.
   */
  @Test
  public void testBrighten() throws IOException {
    StringBuilder actual = new StringBuilder();
    StringBuilder expected = new StringBuilder();

    this.model.brighten("koala", "koalaBrighten", 50);
    this.model.saveImageToPPM("koalaBrighten", actual);
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-brighter-by-50.ppm"),
            "actualKoalaBrighten");
    this.model.saveImageToPPM("actualKoalaBrighten", expected);

    assertEquals(expected.toString(), actual.toString());
  }

  /**
   * Test darken method.
   */
  @Test
  public void testDarken() throws IOException {
    StringBuilder actual = new StringBuilder();
    StringBuilder expected = new StringBuilder();

    this.model.darken("koala", "koalaDarken", 50);
    this.model.saveImageToPPM("koalaDarken", actual);
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-darken-by-50.ppm"),
            "actualKoalaDarken");
    this.model.saveImageToPPM("actualKoalaDarken", expected);

    assertEquals(expected.toString(), actual.toString());
  }

  /**
   * Tests loading something that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageFromPPMFail1() throws IOException {
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/abc.ppm"), "koala");
  }

  /**
   * Tests loading something that is improperly named.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageFromPPMFail2() throws IOException {
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/Koala"), "koala");
  }

  /**
   * Tests getting redComponent for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageFromPPMFail3() throws IOException {
    this.model.redComponent("notKoala", "koala");
  }
}