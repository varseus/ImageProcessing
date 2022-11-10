package imageprocessing.model;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read and write PPM
 * images to and from files.
 */
public class ImageReadUtil {
  /**
   * Read an image file in the PPM format and produce
   * a corresponding matrix of pixels.
   *
   * @param filepath the path of the file.
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
  public static ArrayList<ArrayList<Pixel>> readFile(String filepath) throws IllegalArgumentException,
          NullPointerException {
    Objects.requireNonNull(filepath);

    HashMap<String, Boolean> acceptedTypes = new HashMap<String, Boolean>();
    for (String type : ImageIO.getReaderFileSuffixes()) {
      acceptedTypes.put("." + type, true);
    }
    acceptedTypes.put(".ppm", true);
    BufferedImage bufferedImage;

    if (filepath.indexOf(".") < 0 ||
            acceptedTypes.get(filepath.substring(filepath.indexOf("."))) == null) {
      throw new IllegalArgumentException(
              "Invalid filepath, " + filepath + ", filepath must end in one of:" +
                      acceptedTypes.keySet().stream().map(type -> " " + type).collect(
                              Collectors.joining(",", "", ".")));
    }

    try {
      bufferedImage = ImageIO.read(new File(filepath));
      Objects.requireNonNull(bufferedImage);
    } catch (Exception readError) {
      try {
        return ImageReadUtil.readPPM(filepath);
      } catch (Exception readPPMError) {
        throw new IllegalArgumentException("Cannot find file from file, " + filepath +
                ". Please make sure it is a valid file and is one of " +
                acceptedTypes.keySet().stream().map(type -> " " + type).collect(
                        Collectors.joining(",", "", ".")));
      }
    }


    BufferedImage convertedBufferedImage = new BufferedImage(bufferedImage.getWidth(),
            bufferedImage.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    convertedBufferedImage.getGraphics().drawImage(bufferedImage, 0, 0, null);

    int[] pixelList = ((DataBufferInt) convertedBufferedImage.getRaster().getDataBuffer()).getData();

    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<ArrayList<Pixel>>();
    for (int i = 0; i < convertedBufferedImage.getHeight(); i++) {
      ArrayList<Pixel> row = new ArrayList<Pixel>();
      for (int j = 0; j < convertedBufferedImage.getWidth(); j += 1) {
        row.add(new RGBPixel(
                (pixelList[i * convertedBufferedImage.getWidth() + j] >> 16) & 255,
                (pixelList[i * convertedBufferedImage.getWidth() + j] >> 8) & 255,
                (pixelList[i * convertedBufferedImage.getWidth() + j]) & 255,
                255));
      }
      pixels.add(row);
    }
    return pixels;
  }
}


