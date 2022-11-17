package imageprocessing;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import imageprocessing.controller.TextScriptedImageProcessingController;
import imageprocessing.model.BasicImageProcessingModel;
import imageprocessing.view.TextScriptImageProcessingView;

/**
 * Entry point for running an Image Processor.
 */
public class ImageProcessor {


  /**
   * Runs an Image processing controller,
   * inputting and outputting to console, or reading from the
   * script given by command line args.
   *
   * @param args the script to read from, if applicable
   * @throws IOException              if unable to transmit/read data
   * @throws IllegalArgumentException if unable to find script to read from
   */
  public static void main(String[] args) throws IOException, IllegalArgumentException {
    Readable in = new InputStreamReader(System.in);
    if (args.length > 0 && args[0] != null) {
      try {
        in = new FileReader(args[0]);
      } catch (Exception e) {
        throw new IllegalArgumentException("Cannot read from given filepath " + args[0] + ".");
      }
    }
    BasicImageProcessingModel model = new BasicImageProcessingModel();
    TextScriptImageProcessingView view = new TextScriptImageProcessingView(System.out, model);
    TextScriptedImageProcessingController controller = new TextScriptedImageProcessingController(
            model,
            view,
            in);

    controller.startProcessor();
  }
}
