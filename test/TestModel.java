import static org.junit.Assert.assertEquals;
import imageprocessing.controller.ImageProcessingController;
import imageprocessing.model.BasePPMImageProcessingModel;
import imageprocessing.view.TextScriptImageProcessingView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * The {@code TestModel} to test the methods in BasePPMImageProcessingModel class.
 */
public class TestModel {
  private BasePPMImageProcessingModel model;

  @Before
  public void setup() {
    this.model = new BasePPMImageProcessingModel();
  }

  /**
   * Test the loadImageFromPPM.
   */
  @Test
  public void testLoadImageFromPPM(){
    this.model.loadImageFromPPM("res/Koala", "koala");

  }
  /**
   * test the redComponent method.
   */
  @Test
  public void testRedComponent(){
    this.image = new BasePPMImage("res/Koala.ppm");
    Image newImage = this.image.redComponent();
    Image expectedImage = new BasePPMImage("res/koala-red-greyscale.ppm");
    assertEquals(newImage.toString(), expectedImage.toString());
  }
}























