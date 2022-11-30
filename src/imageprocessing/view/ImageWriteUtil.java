package imageprocessing.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import imageprocessing.model.Pixel;


/**
 * This class contains utility methods to read and write PPM images to and from files.
 */
class ImageWriteUtil {

  /**
   * Saves the given pixel matrix, representing an image, to a file.
   *
   * @param pixels   pixels that compose the image to write to the file assumes pixels have byteSize
   *                 of 255
   * @param filepath to save image to
   * @throws IOException              if unable to write to file
   * @throws NullPointerException     if null args
   * @throws IllegalArgumentException if unrecognized filepath or illegal pixel array
   */
  public static void writePixelsToFile(ArrayList<ArrayList<Pixel>> pixels,
      String filepath)
      throws IOException, NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(filepath);
    Objects.requireNonNull(pixels);

    HashMap<String, Boolean> acceptedTypes = new HashMap<String, Boolean>();
    for (String type : ImageIO.getWriterFileSuffixes()) {
      acceptedTypes.put("." + type, true);
    }
    acceptedTypes.put(".ppm", true);

    if (filepath.lastIndexOf(".") < 0 ||
        acceptedTypes.get(filepath.substring(filepath.lastIndexOf("."))) == null) {
      throw new IllegalArgumentException("Unrecognized file suffix in filepath: " + filepath + "." +
          " File path must end in one of: ppm, " +
          Arrays.stream(ImageIO.getWriterFileSuffixes()).collect(
              Collectors.joining(", ", "", ".")));
    }
    String formatName = filepath.substring(filepath.lastIndexOf(".") + 1);

    File fileObj;
    try {
      fileObj = new File(filepath);
      fileObj.getParentFile().mkdirs();
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        throw new IllegalArgumentException("Invalid filepath " + filepath + ".");
      } else {
        throw new IOException("ERROR: could not handle file object.");
      }
    }

    BufferedImage bufferedImage = new BufferedImage(pixels.get(0).size(),
        pixels.size(),
        BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < pixels.size(); i++) {
      for (int j = 0; j < pixels.get(0).size(); j++) {
        bufferedImage.setRGB(j, i, pixels.get(i).get(j).intRGB());
      }
    }

    try {
      if (!ImageIO.write(bufferedImage, formatName, fileObj)) {
        ImageWriteUtil.writePPMPixelsToFile(pixels, filepath);
      }
    } catch (Exception imageIOError) {
      try {
        ImageWriteUtil.writePPMPixelsToFile(pixels, filepath);
      } catch (Exception ppmError) {
        throw imageIOError;
      }
    }

  }

  /**
   * Writes the given data to file at the specified filepath.
   *
   * @param pixels   to write to file
   * @param filepath filepath of the file to write
   * @throws IllegalArgumentException if invalid filepath
   * @throws IOException              if unable to write to file
   * @throws NullPointerException     if null args
   */
  public static void writePPMPixelsToFile(ArrayList<ArrayList<Pixel>> pixels, String filepath)
      throws IllegalArgumentException, IOException, NullPointerException {
    Objects.requireNonNull(pixels);
    Objects.requireNonNull(filepath);
    if (filepath.length() < 4 || !filepath.substring(filepath.length() - 4, filepath.length())
        .equals(".ppm")) {
      throw new IllegalArgumentException("Filepath must end in .ppm");
    }

    try {
      File fileObj = new File(filepath);
      fileObj.getParentFile().mkdirs();
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        throw new IllegalArgumentException("Cannot load from file, " + filepath +
            ". Please make sure it is a valid file.");
      } else {
        throw new IOException("ERROR: could not handle file object.");
      }
    }

    StringBuilder ppmData = new StringBuilder(
        String.format(
            "%s\n%d\n%d\n%d\n",
            "P3",
            pixels.get(0).size(),
            pixels.size(),
            pixels.get(0).get(0).byteSize()));

    int i = 1;
    for (ArrayList<Pixel> row : pixels) {
      i++;
      for (Pixel pixel : row) {
        ppmData.append(pixel.toString() + "\n");
      }
    }

    try {
      (new FileWriter(filepath, false).append(ppmData.toString())).close();
    } catch (Exception e) {
      throw new IOException("ERROR: unable to write to file.");
    }
  }
}


