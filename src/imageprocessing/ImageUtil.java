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
 * This class contains utility methods to read and write PPM
 * images to and from files.
 */
public class ImageUtil {
  /**
   * Writes the given data to file at the specified filepath.
   *
   * @param data     to write to file
   * @param filepath
   * @throws IllegalArgumentException if invalid filepath
   * @throws IOException              if unable to write to file
   */
  public static void writeToFile(StringBuilder data, String filepath)
          throws IllegalArgumentException, IOException {

    if (filepath.length() < 4 || !filepath.substring(filepath.length() - 4, filepath.length()).equals(".ppm")) {
      throw new IllegalArgumentException("Filepath must end in .ppm");
    }

    try {
      File fileObj = new File(filepath);
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        throw new IllegalArgumentException("Invalid filepath.");
      } else {
        throw new IOException("ERROR: could not handle file object.");
      }
    }

    try {
      ((new FileWriter(filepath, false)).append(data.toString())).close();
    } catch (Exception e) {
      throw new IOException("ERROR: unable to write to file.");
    }
  }

  /**
   * Exports the data from a file at a specified filepath to a readable object.
   *
   * @param filepath where the target file is
   * @return a readable object with the data
   * @throws IllegalArgumentException if filepath is invalid
   */
  public static FileReader getFileReaderFromFilePath(String filepath)
          throws IllegalArgumentException {
    if (filepath.length() < 4 || !filepath.substring(filepath.length() - 4, filepath.length()).equals(".ppm")) {
      throw new IllegalArgumentException("Invalid filepath, " + filepath + ", filepath must end in .ppm");
    }

    try {
      return new FileReader(new File(Objects.requireNonNull(filepath)));
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot load from file, " + filepath +
              ". Please make sure it is a valid file.");
    }
  }

  /**
   * Util to help testing.
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
}


