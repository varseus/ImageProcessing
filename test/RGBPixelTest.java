import static org.junit.Assert.assertEquals;

import imageprocessing.model.GreyscalePixel;
import imageprocessing.model.Pixel;
import imageprocessing.model.RGBPixel;
import org.junit.Test;

public class RGBPixelTest {

  private Pixel pixel;

  /**
   * to test the red component of this pixel is bigger than the max value.
   */
  @Test
  public void testInvalidPixel1() {
    try {
      this.pixel = new RGBPixel(300, 100, 100, 255);
    } catch (IllegalArgumentException e) {
      assertEquals("Pixel components cannot be larger than the max value.", e.getMessage());
    }
  }
  /**
   * to test the green component of this pixel is bigger than the max value.
   */
  @Test
  public void testInvalidPixel2() {
    try {
      this.pixel = new RGBPixel(100, 300, 100, 255);
    } catch (IllegalArgumentException e) {
      assertEquals("Pixel components cannot be larger than the max value.", e.getMessage());
    }
  }
  /**
   * to test the blue component of this pixel is bigger than the max value.
   */
  @Test
  public void testInvalidPixel3() {
    try {
      this.pixel = new RGBPixel(100, 100, 300, 255);
    } catch (IllegalArgumentException e) {
      assertEquals("Pixel components cannot be larger than the max value.", e.getMessage());
    }
  }

  /**
   * to make sure the pixel components are not the negative number.
   */
  @Test
  public void testInvalidPixelNegative() {
    try {
      this.pixel = new RGBPixel(-100, 100, 100, 255);
    } catch (IllegalArgumentException e) {
      assertEquals("Pixel components cannot be negative.", e.getMessage());
    }
    try {
      this.pixel = new RGBPixel(100, -100, 100, 255);
    } catch (IllegalArgumentException e) {
      assertEquals("Pixel components cannot be negative.", e.getMessage());
    }
    try {
      this.pixel = new RGBPixel(100, 100, -100, 255);
    } catch (IllegalArgumentException e) {
      assertEquals("Pixel components cannot be negative.", e.getMessage());
    }
    try {
      this.pixel = new RGBPixel(100, 100, 100, -255);
    } catch (IllegalArgumentException e) {
      assertEquals("Pixel components cannot be negative.", e.getMessage());
    }
  }

  /**
   * to test red, green, blue, value, intensity, luma component.
   */
  @Test
  public void testComponent(){
    this.pixel = new RGBPixel(200, 250, 150, 255);
    assertEquals(pixel.redComponent().toString(), new GreyscalePixel(200, 255).toString());
    assertEquals(pixel.greenComponent().toString(), new GreyscalePixel(250, 255).toString());
    assertEquals(pixel.blueComponent().toString(), new GreyscalePixel(150, 255).toString());
    assertEquals(pixel.valueComponent().toString(), new GreyscalePixel(250, 255).toString());
    assertEquals(pixel.intensityComponent().toString(), new GreyscalePixel(200, 255).toString());
    assertEquals(pixel.lumaComponent().toString(), new GreyscalePixel(232, 255).toString());
  }

  /**
   * to test the brighter pixel.
   */
  @Test
  public void testBrighten(){
    this.pixel = new RGBPixel(100, 200, 250, 255);
    assertEquals(pixel.brighten(50), new RGBPixel(150, 250, 255, 255));
  }
  /**
   * to test the darker pixel.
   */
  @Test
  public void testDarken(){
    this.pixel = new RGBPixel(100, 200, 250, 255);
    assertEquals(pixel.darken(20), new RGBPixel(80, 180, 230, 255));
  }
  /**
   * to test the toString method.
   */
  @Test
  public void testToString(){
    this.pixel = new RGBPixel(100, 200, 250, 255);
    assertEquals(pixel.toString(), "100 200 250");
  }
  /**
   * test byteSize method.
   */
  @Test
  public void testByteSize(){
    this.pixel = new RGBPixel(100, 200, 250, 255);
    assertEquals(pixel.byteSize(), 255);
  }
}






















