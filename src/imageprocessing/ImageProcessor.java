package imageprocessing;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import imageprocessing.controller.SwingAppFeatures;
import imageprocessing.controller.SwingController;
import imageprocessing.controller.TextScriptedImageProcessingController;
import imageprocessing.model.BasicImageProcessingModel;
import imageprocessing.model.ImageProcessingModel;
import imageprocessing.view.ImageProcessingSwingView;
import imageprocessing.view.SwingView;
import imageprocessing.view.TextScriptImageProcessingView;

/**
 * Entry point for running an Image Processor.
 */
public class ImageProcessor {


  /**
   * Runs an Image processing controller, inputting and outputting to console, or reading from the
   * script given by command line args.
   *
   * @param args the script to read from, if applicable
   * @throws IOException              if unable to transmit/read data
   * @throws IllegalArgumentException if unable to find script to read from
   */
  public static void main(String[] args) throws IOException, IllegalArgumentException {
    System.gc();
    Readable in = new InputStreamReader(System.in);
    if (args.length > 0 && args[0] != null) {
      if (args.length == 2 && args[1] != null) {
        try {
          in = new FileReader(args[1]);
        } catch (Exception e) {
          throw new IllegalArgumentException("Cannot read from given filepath " + args[0] + ".");
        }
      }
      if ((args[0].equals("-file") && args.length == 2) || (args[0].equals("-text")
          && args.length == 1)) {
        BasicImageProcessingModel model = new BasicImageProcessingModel();
        TextScriptImageProcessingView view =
            new TextScriptImageProcessingView(System.out, model);
        TextScriptedImageProcessingController controller =
            new TextScriptedImageProcessingController(
            model,
            view,
            in);

        controller.startProcessor();
      } else {
        throw new IllegalArgumentException("Invalid args.");
      }
    } else {
      ImageProcessingModel model = new BasicImageProcessingModel();
      ImageProcessingSwingView view = new SwingView(model);
      SwingAppFeatures controller = new SwingController(model, view);
      view.setFeatures(controller);
    }
  }
}
