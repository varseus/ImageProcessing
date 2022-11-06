package imageprocessing.model;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read and write PPM
 * images to and from files.
 */
public class ImageUtil {
  /**
   * Writes the given data to file at the specified filepath.
   *
   * @param data     to write to file
   * @param filepath filepath of the file to write
   * @throws IllegalArgumentException if invalid filepath
   * @throws IOException              if unable to write to file
   * @throws NullPointerException     if null args
   */
  public static void writeToFile(StringBuilder data, String filepath)
          throws IllegalArgumentException, IOException, NullPointerException {
    Objects.requireNonNull(data);
    Objects.requireNonNull(filepath);

    if (filepath.length() < 4 || !filepath.substring(filepath.length() - 4, filepath.length())
            .equals(".ppm")) {
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
          throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(filepath);

    if (filepath.length() < 4 || !filepath.substring(filepath.length() - 4, filepath.length())
            .equals(".ppm")) {
      throw new IllegalArgumentException(
              "Invalid filepath, " + filepath + ", filepath must end in .ppm");
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
   * @param filepath the path of the file.
   * @author OOD professors
   */
  public static ArrayList<ArrayList<Pixel>> readPPM(String filepath)
          throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(filepath);
    FileReader file;

    if (filepath.length() < 4 || !filepath.substring(filepath.length() - 4, filepath.length())
            .equals(".ppm")) {
      throw new IllegalArgumentException(
              "Invalid filepath, " + filepath + ", filepath must end in .ppm");
    }

    try {
      file = new FileReader(new File(Objects.requireNonNull(filepath)));
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot load from file, " + filepath +
              ". Please make sure it is a valid file.");
    }

    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<ArrayList<Pixel>>();
    Scanner sc;
    sc = new Scanner(file);

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.length() > 0 && s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc.close();
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    if (sc.hasNext()) {
      if (!sc.next().equals("P3")) {
        throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
      }
    } else {
      throw new IllegalArgumentException("Cannot read empty file.");
    }

    int width;
    int height;
    int maxValue;
    try {
      width = sc.nextInt();
      height = sc.nextInt();
      maxValue = sc.nextInt();
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot load from invalid file type.");
    }

    for (int i = 0; i < height; i++) {
      ArrayList<Pixel> row = new ArrayList<Pixel>();
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        try {
        } catch (Exception e) {
          throw new IllegalArgumentException("The given file is missing pixels.");
        }
        row.add(new RGBPixel(r, g, b, maxValue));
      }

      pixels.add(row);
    }

    return pixels;
  }

  // reads GIF, PNG, JPEG, BMP, and WBMP
  public static ArrayList<ArrayList<Pixel>> readFile(String filepath) throws IOException {
    HashMap<String, Boolean> acceptedTypes = new HashMap<String, Boolean>();
    acceptedTypes.put(".gif", true);
    acceptedTypes.put(".png", true);
    acceptedTypes.put(".jpeg", true);
    acceptedTypes.put(".bmp", true);
    BufferedImage imageBuffer;

    if (filepath.length() < 4 ||
            acceptedTypes.get(filepath.substring(filepath.length() - 4, filepath.length())) == null) {
      throw new IllegalArgumentException(
              "Invalid filepath, " + filepath + ", filepath must end in" +
                      acceptedTypes.keySet().stream().map(type -> " " + type).collect(Collectors.joining(",", "", ".")));
    }

    try {
      imageBuffer = ImageIO.read(new File(filepath));
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot load from file, " + filepath +
              ". Please make sure it is a valid file.");
    }


    BufferedImage convertedImageBuffer = new BufferedImage(imageBuffer.getWidth(),
            imageBuffer.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    convertedImageBuffer.getGraphics().drawImage(imageBuffer, 0, 0, null);

    System.out.println(convertedImageBuffer.getType() + "<-type \n");
    int[] pixelList = ((DataBufferInt) convertedImageBuffer.getRaster().getDataBuffer()).getData();

    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<ArrayList<Pixel>>();
    for (int i = 0; i < convertedImageBuffer.getHeight(); i++) {
      ArrayList<Pixel> row = new ArrayList<Pixel>();
      for (int j = 0; j < convertedImageBuffer.getWidth(); j += 3) {
        row.add(new RGBPixel(
                (pixelList[i * convertedImageBuffer.getWidth() + j]>>16)&255,
                (pixelList[i * convertedImageBuffer.getWidth() + j]>>8)&255,
                (pixelList[i * convertedImageBuffer.getWidth() + j])&255,
                255));
      }
      pixels.add(row);
    }
    return pixels;
  }

  public static void main(String[] args) throws IOException {
    ArrayList<ArrayList<Pixel>> pixels = ImageUtil.readFile("res/koala-vertical.png");

    for(int i = 0; i < 20; i++) {
      for (int j = 0; j < 20; j++) {
        System.out.println(pixels.get(i).get(j).toString());
      }
    }
  }
}


