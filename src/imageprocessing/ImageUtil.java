package imageprocessing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents. Feel free to change this method
 * as required.
 */
public class ImageUtil {

  public static FileWriter getFileFromFilepath(String filepath)
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
      FileWriter writer = new FileWriter(filepath);
      return writer;
    } catch (Exception e) {
      throw new IOException("ERROR: unable to write to file.");
    }
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @author OOD professors
   */
  public static void readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
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


