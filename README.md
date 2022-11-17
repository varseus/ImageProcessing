*Written in Markdown*

# README — ImageProcessing

The ImageProcessing package functions on the MVC design pattern. The model, coupled with utilities, handles all Image data.
It can load images, save images, and process images according to various commands. The controller fields and parses user input to determine what 
commands the model should execute. The view outputs results, serving as a communication interface to the user.

### Model

The Model is represented by the **ImageProcessingModel** interface, which can execute various operations on a set of Images. It is implemented by the 
**BasicImageProcessingModel** class, which can load, edit, and save images, while maintaining a running state of images being processed. Also, 
**MockImageProcessingModel** class logs all calls made to it to a log.

Each Image in the BasicImageProcessingModel is represented by the **Image** interface. Images have various operations to edit and 
import them, as implemented by a **BasicImage** or a **GreyscaleImage** (a black-and-white image). 

Images are composed of pixels, represented by the **Pixel** interface. Pixel's also have various operations to edit them, and are either an
**RGBPixel** or **GreyscalePixel** (a black-and-white pixel).

All import operations done by reading from a ppm file or
jpef/raw/tiff/bmp/png...

### Controller

The **ImageProcessingController** interface represents operations that should be offered by an image processor controller, 
consisting only of the startProcessor() method to start the controller. The **TextScriptedImageProcessingController**
implements this by reading text input from a specified source, and using a HashMap of commands 
to parse the input and call the relevant command on the model, while 
outputting results to the specified view.

### View

The **ImageProcessingView** interface outlines the operations of an image processing view, consisting of the 
renderMessage() and saveImageToFile(...) methods. Class **TextScriptImageProcessingView** implements it by transmitting messages to the
given Appendable output object.

### Tests and Resources

Each component is tested by its corresponding test class: **TestModel**, **TestView**, and **TestController**.
Tests are applied on sample data found in the res/ directory. The Controller->Model connection is tested
by the **MockImageProcessingModel** class, which logs all calls to the model to ensure that the controller
is correctly using the model.

## Version 2 Changes
In version 2, support for blur, sharpen, greyscale, and sepia operaitons
were added to the model by adding new methods for each operation. Support for the corresponding commands
for each operation were added to the controller as well.

The save operation was moved from the model to the view, to better
fit the MVC design pattern. Load and save
were refactored to use ImageIO, allowing for more file types 
(including .png and .jpeg) to supported for load and save. The load
and save are done primarily by new ImageReadUtils and ImageWriteUitls
utility classes.

---
Image citation:
Self drawn.
