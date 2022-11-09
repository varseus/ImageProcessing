package imageprocessing.model;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read and write PPM
 * images to and from files.
 */
public class ImageUtil {
  /**
   * @param pixels
   * @param formatName
   * @param filepath
   * @throws IOException              if unable to write to file
   * @throws NullPointerException     if nullargs
   * @throws IllegalArgumentException if unrecognized filepath or illegal pixel array
   */
  public static void writePixelsToFile(ArrayList<ArrayList<Pixel>> pixels,
                                       String filepath)
          throws IOException, NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(filepath);
    Objects.requireNonNull(pixels);

    if (filepath.length() < 4 ||
            filepath.lastIndexOf(".") < 0 ||
            !(Arrays.stream(ImageIO.getWriterFileSuffixes()).anyMatch(fileSuffix ->
                    fileSuffix.equals(filepath.substring(filepath.lastIndexOf(".") + 1)))
                    || filepath.substring(filepath.length() - 3).equals(".ppm"))) {
      throw new IllegalArgumentException("Unrecognized file suffix in filepath: " + filepath + ".");
    }
    String formatName = filepath.substring(filepath.lastIndexOf(".") + 1);

    File fileObj;
    try {
      fileObj = new File(filepath);
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
      ImageIO.write(bufferedImage, formatName, fileObj);
    } catch (Exception imageIOError) {
      try {
        ImageUtil.writePPMPixelsToFile(pixels, filepath);
      } catch (Exception ppmError) {
        throw imageIOError;
      }
    }

  }

  /**
   * Writes the given data to file at the specified filepath.
   *
   * @param data     to write to file
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
      ((new FileWriter(filepath, false)).append(ppmData.toString())).close();
    } catch (Exception e) {
      throw new IOException("ERROR: unable to write to file.");
    }
  }


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
              "Invalid filepath, " + filepath + ", filepath must end in " +
                      acceptedTypes.keySet().stream().map(type -> " " + type).collect(
                              Collectors.joining(",", "", ".")));
    }

    try {
      bufferedImage = ImageIO.read(new File(filepath));
      Objects.requireNonNull(bufferedImage);
    } catch (Exception readError) {
      try {
        return ImageUtil.readPPM(filepath);
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


