import static org.junit.Assert.assertEquals;

import imageprocessing.ImageUtil;
import imageprocessing.model.BasePPMImageProcessingModel;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.nio.CharBuffer;

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
   * This subclass represents an Appendable that always throws
   * an IOException (for testing exception handling).
   */
  static class BadAppendable implements Appendable {

    /**
     * Throws an I/0 exception, for testing errors.
     *
     * @param csq The character sequence to append.  If {@code csq} is
     *            {@code null}, then the four characters {@code "null"} are
     *            appended to this Appendable.
     * @return never returns
     * @throws IOException always
     */
    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException("Unable to append");
    }

    /**
     * Throws an I/0 exception, for testing errors.
     *
     * @param csq   The character sequence from which a subsequence will be
     *              appended.  If {@code csq} is {@code null}, then characters
     *              will be appended as if {@code csq} contained the four
     *              characters {@code "null"}.
     * @param start The index of the first character in the subsequence
     * @param end   The index of the character following the last character in the
     *              subsequence
     * @return never returns
     * @throws IOException always
     */
    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException("Unable to append");
    }

    /**
     * Throws an I/O exception, for testing errors.
     *
     * @param c The character to append
     * @return A reference to this {@code Appendable}
     * @throws IOException always
     */
    @Override
    public Appendable append(char c) throws IOException {
      throw new IOException("Unable to append");
    }
  }

  /**
   * This subclass represents a Readable that always
   * throws an IOException (for testing exception handling).
   */
  static class BadReadable implements Readable {

    /**
     * Throws an I/O exception, for testing errors.
     *
     * @param cb the buffer to read characters into
     * @return never returns
     * @throws IOException always
     */
    @Override
    public int read(CharBuffer cb) throws IOException {
      throw new IOException("Cannot read.");
    }
  }

  /**
   * Test saving something.
   */
  @Test
  public void testSaveImageFromPPM() throws IOException {
    Appendable actual = new StringBuilder();
    String expected = ImageUtil.readPPM("res/Koala.ppm").toString()
            .replace("\n", " ");

    assertEquals(
            expected.toString(),
            this.model.saveImageToPPM("koala")
                    .toString().replace("\n", " "));
  }

  /**
   * Test loading something.
   */
  @Test
  public void testLoadImageToPPM() throws IOException {
    StringBuilder actual = new StringBuilder();
    String expected = ImageUtil.readPPM("res/Koala.ppm").toString()
            .replace("\n", " ");

    this.model.loadImageFromPPM(new StringReader(expected.toString()),
            "newKoala");

    assertEquals(
            expected.toString().replace("\n", " "),
            this.model.saveImageToPPM("newKoala")
                    .toString().replace("\n", " "));
  }

  /**
   * Test redComponent method.
   */
  @Test
  public void testRedComponent() throws IOException {
    StringBuilder actual = new StringBuilder();
    StringBuilder expected = new StringBuilder();

    this.model.redComponent("koala", "koalaComponent");
    actual = this.model.saveImageToPPM("koalaComponent");
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-red-greyscale.ppm"), "actualKoalaComponent");
    expected = this.model.saveImageToPPM("actualKoalaComponent");

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
    actual = this.model.saveImageToPPM("koalaComponent");
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-green-greyscale.ppm"), "actualKoalaComponent");
    expected = this.model.saveImageToPPM("actualKoalaComponent");

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
    actual = this.model.saveImageToPPM("koalaComponent");
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-blue-greyscale.ppm"), "actualKoalaComponent");
    expected = this.model.saveImageToPPM("actualKoalaComponent");

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
    actual = this.model.saveImageToPPM("koalaComponent");
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-value-greyscale.ppm"), "actualKoalaComponent");
    expected = this.model.saveImageToPPM("actualKoalaComponent");

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
    actual = this.model.saveImageToPPM("koalaComponent");
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-luma-greyscale.ppm"), "actualKoalaComponent");
    expected = this.model.saveImageToPPM("actualKoalaComponent");

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
    actual = this.model.saveImageToPPM("koalaComponent");
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-intensity-greyscale.ppm"), "actualKoalaComponent");
    expected = this.model.saveImageToPPM("actualKoalaComponent");

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
    actual = this.model.saveImageToPPM("koalaFlip");
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-horizontal.ppm"),
            "actualKoalaFlip");
    expected  =this.model.saveImageToPPM("actualKoalaFlip");

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
    actual = this.model.saveImageToPPM("koalaFlip");
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-vertical.ppm"),
            "actualKoalaFlip");
    expected = this.model.saveImageToPPM("actualKoalaFlip");

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
    actual = this.model.saveImageToPPM("koalaBrighten");
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-brighter-by-50.ppm"),
            "actualKoalaBrighten");
    expected = this.model.saveImageToPPM("actualKoalaBrighten");

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
    actual = this.model.saveImageToPPM("koalaDarken");
    this.model.loadImageFromPPM(ImageUtil.getFileReaderFromFilePath("res/koala-darken-by-50.ppm"),
            "actualKoalaDarken");
    expected = this.model.saveImageToPPM("actualKoalaDarken");

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
   * Tests loading null.
   */
  @Test(expected = NullPointerException.class)
  public void testLoadImageFromPPMFail3() throws IOException {
    this.model.loadImageFromPPM(null, "koala");
  }

  /**
   * Tests getting redComponent for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail3() throws IOException {
    this.model.redComponent("notKoala", "koala");
  }

  /**
   * Tests getting greenComponent for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail4() throws IOException {
    this.model.greenComponent("notKoala", "koala");
  }
  /**
   * Tests getting blueComponent for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail5() throws IOException {
    this.model.blueComponent("notKoala", "koala");
  }
  /**
   * Tests getting horizontalFlip for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail6() throws IOException {
    this.model.horizontalFlip("notKoala", "koala");
  }
  /**
   * Tests getting verticalFlip for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail7() throws IOException {
    this.model.verticalFlip("notKoala", "koala");
  }
  /**
   * Tests getting valueComponent for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail8() throws IOException {
    this.model.valueComponent("notKoala", "koala");
  }
  /**
   * Tests getting intensityComponent for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail9() throws IOException {
    this.model.intensityComponent("notKoala", "koala");
  }
  /**
   * Tests getting lumaComponent for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail10() throws IOException {
    this.model.lumaComponent("notKoala", "koala");
  }
  /**
   * Tests getting brighten for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail11() throws IOException {
    this.model.brighten("notKoala", "koala", 10);
  }
  /**
   * Tests getting darken for an image that doesn't exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testComponentFailFail12() throws IOException {
    this.model.darken("notKoala", "koala", 10);
  }

  /**
   * Test that the model throws an exception when reading from a BadReadable
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadReadable() throws IOException {
    this.model.loadImageFromPPM(new BadReadable(), "bad");
  }
}