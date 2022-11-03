package imageprocessing;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents. Feel free to change this method
 * as required.
 */
public class ImageUtil {

  public static FileWriter getFileWriterFromFilepath(String filepath)
          throws IllegalArgumentException, IOException {

    if (filepath.length() < 4 || !filepath.substring(filepath.length() - 4, filepath.length()).equals(".ppm")) {
      throw new IllegalArgumentException("Filepath must end in .ppm");
    }

    try {
      File fileObj = new File(filepath);
      if (!fileObj.createNewFile()) {
        fileObj.mkdirs();
        fileObj.delete();
        fileObj.createNewFile();
      }
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        throw new IllegalArgumentException("Invalid filepath.");
      } else {
        throw new IOException("ERROR: could not handle file object.");
      }
    }

    try {
      return new FileWriter(filepath);
    } catch (Exception e) {
      throw new IOException("ERROR: unable to write to file.");
    }
  }

  public static FileReader getFileReaderFromFilePath(String filepath) {
    if (filepath.length() < 4 || !filepath.substring(filepath.length() - 4, filepath.length()).equals(".ppm")) {
      throw new IllegalArgumentException("Filepath must end in .ppm");
    }

    try {
      return new FileReader(new File(Objects.requireNonNull(filepath)));
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot load from that file. Please make sure it is a valid file.");
    }
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @author OOD professors
   */
  public static StringBuilder readPPM(String filename) throws IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    return builder;
  }

  // demo main
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "res/Koala.ppm";
    }

    ImageUtil.readPPM(filename);
  }
}


