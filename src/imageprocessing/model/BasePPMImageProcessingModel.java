package imageprocessing.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The {@code BasePPMImageProcessingModel} represents the operations and state of an image processor,
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
   * @param file      the file to load the image from
   * @throws IllegalArgumentException if the file is invalid
   */
  @Override
  public Void loadImageFromPPM(Readable file, String imageName)
          throws IllegalArgumentException {
    images.put(imageName, new BasePPMImage(file));
    return null;
  }

  /**
   * @param imageName the name of the image to save
   * @param filepath  the location to save the image to
   * @throws IOException              if unable to write to file
   * @throws IllegalArgumentException if the image is not found
   */
  @Override
  public Void saveImageToPPM(String imageName, Appendable file)
          throws IOException, IllegalArgumentException {
    Image image;
    StringBuilder imageData;

    try {
      image = Objects.requireNonNull(this.images.get(imageName));
    } catch (Exception e) {
      throw new IllegalArgumentException("Image does not exist.");
    }

    imageData = image.convertToPPM();
    file.append(imageData);

    if (file instanceof FileWriter) {
      ((FileWriter) file).close();
    }

    return null;
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
  public Void redComponent(String imageName, String destImageName) throws IllegalArgumentException {
    try {
      images.put(destImageName, images.get(imageName).redComponent());
    } catch (Exception e) {
      throw new IllegalArgumentException("Given image name does not exist in this processor.");
    }
    return null;
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
  public Void greenComponent(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).greenComponent());
    return null;
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
  public Void blueComponent(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).blueComponent());
    return null;
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
  public Void horizontalFlip(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).horizontalFlip());
    return null;
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
  public Void verticalFlip(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).verticalFlip());
    return null;
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
  public Void valueComponent(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).valueComponent());
    return null;
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
  public Void intensityComponent(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).intensityComponent());
    return null;
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
  public Void lumaComponent(String imageName, String destImageName) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).lumaComponent());
    return null;
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
  public Void brighten(String imageName, String destImageName, int amount) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).brighten(amount));
    return null;
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
  public Void darken(String imageName, String destImageName, int amount) throws IllegalArgumentException {
    images.put(destImageName, images.get(imageName).darken(amount));
    return null;
  }
}
