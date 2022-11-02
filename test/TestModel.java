import static org.junit.Assert.assertEquals;
import imageprocessing.controller.ImageProcessingController;
import imageprocessing.controller.TextScriptedImageProcessingController;
import imageprocessing.model.BasePPMImage;
import imageprocessing.model.BasePPMImageProcessingModel;
import imageprocessing.model.Image;
import imageprocessing.model.ImageProcessingModel;
import imageprocessing.model.Pixel;
import imageprocessing.model.RGBPixel;
import imageprocessing.view.ImageProcessingView;
import imageprocessing.view.TextScriptImageProcessingView;
import java.io.StringReader;
import java.util.ArrayList;
import org.junit.Test;
/**
 * the {@code TestModel} to test the methods in BasePPMImage class
 * and BasePPMImageProcessingModel class.
 */
public class TestModel {
  private Image I;
  private ImageProcessingModel model;
  private TextScriptImageProcessingView view;
  private ImageProcessingController controller;
  private Readable userInput;
  /**
   * test the constructor with null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNull(){
    ArrayList<ArrayList<Pixel>> pixels = null;
    BasePPMImage I = new BasePPMImage(pixels, 255);
  }
  /**
   * test the constructor with pixels' size is 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorPixelSize() {
    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<ArrayList<Pixel>>();
    this.I = new BasePPMImage(pixels, 255);
  }
/**
 * test the constructor when the Image is not a rectangular.
 */
@Test(expected = IllegalArgumentException.class)
public void testConstructorNotRectangular() {
  Pixel p1 = new RGBPixel(200, 250, 150, 255);
  Pixel p2 = new RGBPixel(200, 250, 150, 255);
  Pixel p3 = new RGBPixel(200, 250, 150, 255);
  ArrayList<ArrayList<Pixel>> pixels = new ArrayList<ArrayList<Pixel>>();
  ArrayList<Pixel> pixel1 = new ArrayList<Pixel>();
  ArrayList<Pixel> pixel2 = new ArrayList<Pixel>();
  pixel1.add(p1);
  pixel2.add(p2);
  pixel2.add(p3);
  pixels.add(pixel1);
  pixels.add(pixel2);
  this.I = new BasePPMImage(pixels, 255);
}
  /**
   * test the constructor when the Image with the wrong byte size.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWrongByteSize() {
    Pixel p = new RGBPixel(200, 250, 150, 255);
    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<ArrayList<Pixel>>();
    ArrayList<Pixel> pixel = new ArrayList<Pixel>();
    pixel.add(p);
    pixels.add(pixel);
    this.I = new BasePPMImage(pixels, 200);
  }
  /**
   * test the constructor when the Image with negative maxvalue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeMaxvalue() {
    Pixel p = new RGBPixel(200, 250, 150, -10);
    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<ArrayList<Pixel>>();
    ArrayList<Pixel> pixel = new ArrayList<Pixel>();
    pixel.add(p);
    pixels.add(pixel);
    this.I = new BasePPMImage(pixels, -10);
  }
  /**
   * test the constructor with filePath.
   */
  @Test
  public void testConstructorWithFilePath(){
  this.I = new BasePPMImage("Koala.ppm");
  assertEquals(I, "Koala.ppm"); // not sure is correct
  }
  /**
   * test the convert to PPM method.
   */
  @Test
  public void testConvertToPPM(){

  }
  /**
   * test the redComponent method.
   */
  @Test
  public void testRedComponent(){
    this.I = new BasePPMImage("Koala.ppm");
    I.redComponent();
    assertEquals(I, "koala-red-greyscale.png");
  }
}























