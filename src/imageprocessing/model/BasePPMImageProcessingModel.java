package imageprocessing.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * the {@code BasePPMImageProcessingModel} represents the operations and state of an image processor,
 * intended to process a set of PPM images. Operations include: get red/green/blue components,
 * get value/intensity/luma components, bright, darken, flip horizontally/vertically,
 * and load/save image to and from PPM.
 */
public class BasePPMImageProcessingModel implements ImageProcessingModel {
  private final Map<String, Image> images;

  public BasePPMImageProcessingModel() {
    this.images = new HashMap<String, Image>();
  }

  /**
   * Load the PPM image from the specified filePath and assign it
   * the given name. Overwrites the destination name if already taken.
   *
   * @param imageName the name to give the loaded image
   * @param filepath  the location to load the image from
   * @throws IllegalArgumentException if the filepath is invalid or the image is not found/invalid
   */
  @Override
  public void loadImageFromPPM(String imageName, String filepath) throws IllegalArgumentException {
    images.put(imageName, new BasePPMImage(filepath));
  }

  // demo main
  public static void main(String[] args) {
    BasePPMImageProcessingModel model = new BasePPMImageProcessingModel();

    model.loadImageFromPPM("koala", "res/Koala.ppm");
    System.out.println("done");
    try {model.saveImageToPPM("koala", "res/newKoala.ppm");}
    catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  /**
   * @param imageName the name of the image to save
   * @param filepath  the location to save the image to
   * @throws IOException if unable to write to file
   * @throws IllegalArgumentException if the image is not found or if the filepath is ill-formed
   */
  @Override
  public void saveImageToPPM(String imageName, String filepath) throws IOException, IllegalArgumentException {
    Image image;
    byte[] imageData;

    try {
      image = Objects.requireNonNull(this.images.get(imageName));
    } catch (Exception e) {
      throw new IllegalArgumentException("Image does not exist.");
    }

    imageData = image.convertToPPM();

    if(!filepath.substring(filepath.length()-4, filepath.length()).equals(".ppm")) {
      throw new IllegalArgumentException("Filepath must end in .ppm");
    }

    try {
      File fileObj = new File(filepath);
      if (!fileObj.createNewFile()) {
        throw new IllegalArgumentException(
                "Cannot save file because something already exists at the given location!");
      }
      FileOutputStream fos = new FileOutputStream(filepath);
      fos.write(imageData);
      fos.close();
    } catch (Exception e) {
      System.out.println(e.toString());
      throw new IOException("Unable to write to file.");
    }
  }

  /**
   * Create a greyscale using only the red component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the red component of
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public void redComponent(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).redComponent());
  }
  /**
   * Create greyscale using only the green component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the green component of
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public void greenComponent(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).greenComponent());
  }
  /**
   * Create a greyscale using only the blue component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the blue component of
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public void blueComponent(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).blueComponent());
  }
  /**
   * Create an image that is the horizontally flipped version
   * of the specified image, and load it with the given name.
   * Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to horizontally flip
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public void horizontalFlip(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).horizontalFlip());
  }
  /**
   * Create an image that is the vertically flipped version
   * of the specified image, and load it with the given name.
   * Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to vertically flip
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public void verticalFlip(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).verticalFlip());
  }
  /**
   * Create a greyscale using only the value component of a given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to get the value component of
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public void valueComponent(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).valueComponent());
  }
  /**
   * Create a greyscale using only the intensity component of a given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to get the intensity component of
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public void intensityComponent(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).intensityComponent());
  }
  /**
   * Create a greyscale using only the luma component of a given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to get the luma component of
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public void lumaComponent(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).lumaComponent());
  }
  /**
   * Create an image that is brighter than the given image by 10 units (unless
   * already fully brightened), and load it with the given name. Overwrites
   * the destination name if already taken.
   *
   * @param imageName     the name of the image to brighten
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public void brighten(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).brighten(10));
  }
  /**
   * Create an image that is darker than the given image by 10 units (unless
   * already fully darkened), and load it with the given name.
   * Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to darken
   * @param destImageName the name to give the new image
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public void darken(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).darken(10));
  }
}
