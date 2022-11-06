package imageprocessing.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The {@code BasePPMImageProcessingModel} represents the operations and state of an image
 * processor, intended to process a set of PPM images. Operations include:
 * get red/green/blue components, get value/intensity/luma components, bright, darken,
 * flip horizontally/vertically, and load/save image to and from PPM.
 */
public class BasePPMImageProcessingModel implements ImageProcessingModel {
  private final Map<String, Image> images;

  /**
   * Export the given image as a ppm.
   *
   * @param imageName the name of the image to save
   * @return StringBuilder containing the PPM data
   * @throws IllegalArgumentException if the image is not found
   */
  @Override
  public StringBuilder saveImageToPPM(String imageName)
          throws IllegalArgumentException {
    Image image;

    try {
      image = Objects.requireNonNull(this.images.get(imageName));
    } catch (Exception e) {
      throw new IllegalArgumentException("Image " + imageName + " does not exist.");
    }

    return image.convertToPPM();
  }

  public BasePPMImageProcessingModel() {
    this.images = new HashMap<String, Image>();
  }

  /**
   * Load the PPM image from the specified filePath and assign it
   * the given name. Overwrites the destination name if already taken.
   *
   * @param imageName the name to give the loaded image
   * @param file      the file to load the image from
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the file is invalid
   */
  @Override
  public Void loadImageFromPPM(Readable file, String imageName)
          throws IllegalArgumentException {
    images.put(imageName, new BasePPMImage(file));
    return null;
  }

  /**
   * Create a greyscale using only the red component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the red component of
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void redComponent(String imageName, String destImageName)
          throws IllegalArgumentException {
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
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void greenComponent(String imageName, String destImageName)
          throws IllegalArgumentException {
    try {
      images.put(destImageName, images.get(imageName).greenComponent());
    } catch (Exception e) {
      throw new IllegalArgumentException("Given image name does not exist in this processor.");
    }
    return null;
  }

  /**
   * Create a greyscale using only the blue component of the given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to take the blue component of
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void blueComponent(String imageName, String destImageName)
          throws IllegalArgumentException {
    try {
      images.put(destImageName, images.get(imageName).blueComponent());
    } catch (Exception e) {
      throw new IllegalArgumentException("Given image name does not exist in this processor.");
    }
    return null;
  }

  /**
   * Create an image that is the horizontally flipped version
   * of the specified image, and load it with the given name.
   * Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to horizontally flip
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void horizontalFlip(String imageName, String destImageName)
          throws IllegalArgumentException {
    try {
      images.put(destImageName, images.get(imageName).horizontalFlip());
    } catch (Exception e) {
      throw new IllegalArgumentException("Given image name does not exist in this processor.");
    }
    return null;
  }

  /**
   * Create an image that is the vertically flipped version
   * of the specified image, and load it with the given name.
   * Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to vertically flip
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void verticalFlip(String imageName, String destImageName)
          throws IllegalArgumentException {
    try {
      images.put(destImageName, images.get(imageName).verticalFlip());
    } catch (Exception e) {
      throw new IllegalArgumentException("Given image name does not exist in this processor.");
    }
    return null;
  }

  /**
   * Create a greyscale using only the value component of a given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to get the value component of
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void valueComponent(String imageName, String destImageName)
          throws IllegalArgumentException {
    try {
      images.put(destImageName, images.get(imageName).valueComponent());
    } catch (Exception e) {
      throw new IllegalArgumentException("Given image name does not exist in this processor.");
    }
    return null;
  }

  /**
   * Create a greyscale using only the intensity component of a given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to get the intensity component of
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void intensityComponent(String imageName, String destImageName)
          throws IllegalArgumentException {
    try {
      images.put(destImageName, images.get(imageName).intensityComponent());
    } catch (Exception e) {
      throw new IllegalArgumentException("Given image name does not exist in this processor.");
    }
    return null;
  }

  /**
   * Create a greyscale using only the luma component of a given image,
   * and load it with the given name. Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to get the luma component of
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void lumaComponent(String imageName, String destImageName)
          throws IllegalArgumentException {
    try {
      images.put(destImageName, images.get(imageName).lumaComponent());
    } catch (Exception e) {
      throw new IllegalArgumentException("Given image name does not exist in this processor.");
    }
    return null;
  }

  /**
   * Create an image that is brighter than the given image by 10 units (unless
   * already fully brightened), and load it with the given name. Overwrites
   * the destination name if already taken.
   *
   * @param imageName     the name of the image to brighten
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void brighten(String imageName, String destImageName, int amount)
          throws IllegalArgumentException {
    try {
      images.put(destImageName, images.get(imageName).brighten(amount));
    } catch (Exception e) {
      throw new IllegalArgumentException("Given image name does not exist in this processor.");
    }
    return null;
  }

  /**
   * Create an image that is darker than the given image by 10 units (unless
   * already fully darkened), and load it with the given name.
   * Overwrites the destination name if already taken.
   *
   * @param imageName     the name of the image to darken
   * @param destImageName the name to give the new image
   * @return null object for use in Callable<> lambda
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void darken(String imageName, String destImageName, int amount)
          throws IllegalArgumentException {
    try {
      images.put(destImageName, images.get(imageName).darken(amount));
    } catch (Exception e) {
      throw new IllegalArgumentException("Given image name does not exist in this processor.");
    }
    return null;
  }
  /**
   * create an image that is blur to the given image, and load it with the given name.
   * @param imageName the name of the image to blur
   * @param destImageName the name to give the new image
   * @return null for use in Callable<> lambda expression
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public Void blur(String imageName, String destImageName)
      throws IllegalArgumentException{
    try {
      images.put(destImageName, images.get(imageName).blur());
    } catch (Exception e) {
      throw new IllegalArgumentException("Given image name does not exist in this processor.");
    }
    return null;
  }

  /**
   * create an image that is sharpening to the given image, and load it with the given name.
   * @param imageName the name of the image to sharpening
   * @param destImageName the name to give the new image
   * @return null for use in Callable<> lambda expression
   * @throws IllegalArgumentException if the image does not exist
   */
@Override
 public Void sharpening(String imageName, String destImageName)
      throws IllegalArgumentException{
  try {
    images.put(destImageName, images.get(imageName).sharpening());
  } catch (Exception e) {
    throw new IllegalArgumentException("Given image name does not exist in this processor.");
  }
  return null;
}

  @Override
  public Void greyscale(String imageName, String destImageName) throws IllegalArgumentException {
      try {
        images.put(destImageName, images.get(imageName).greyscale());
      } catch (Exception e) {
        throw new IllegalArgumentException("Given image name does not exist in this processor.");
      }
      return null;
    }

  @Override
  public Void sepiaTone(String imageName, String destImageName) throws IllegalArgumentException {
        try {
          images.put(destImageName, images.get(imageName).sepiaTone());
        } catch (Exception e) {
          throw new IllegalArgumentException("Given image name does not exist in this processor.");
        }
        return null;
      }
}
