*Written in Markdown*

# ImageProcessing

The ImageProcessing package functions on the MVC design pattern. The model, coupled with utilities, handles all Image data.
It can load images, save images, and process images according to various commands. The controller fields and parses user input to determine what 
commands the model should execute. The view outputs results, serving as a communication interface to the user.

### Model

The Model is represented by the **ImageProcessingModel** interface, which can execute various operations on a set of Images. It is implemented by the 
**BasicImageProcessingModel** class, which can load, edit, and save images, while maintaining a running state of images being processed. Also, 
**MockImageProcessingModel** class logs all calls made to it to a log.

Each Image in the BasicImageProcessingModel is represented by the **Image** interface. Images have various operations to edit and 
import/export them, as implemented by a **BasicImage** or a **GreyscaleImage** (a black-and-white image). 

Images are composed of pixels, represented by the **Pixel** interface. Pixel's also have various operations to edit them, and are either an
**RGBPixel** or **GreyscalePixel** (a black-and-white pixel).

All import and export operations work by reading from a Readable object or returning an Appendable object, representing ASCII PPM data. 
To further save/read the data to/from file, the static **ImageUtil** class has methods to read/write data to a file. 

### Controller

The **ImageProcessingController** interface represents operations that should be offered by an image processor controller, 
consisting only of the startProcessor() method to start the controller. The **TextScriptedImageProcessingController**
implements this by reading text input from a specified source, and using a HashMap of commands 
to parse the input and call the relevant command on the model, while 
outputting results to the specified view.

### View

The **ImageProcessingView** interface outlines the operations of an image processing view, consisting only of the 
renderMessage() method. Class **TextScriptImageProcessingView** implements it by transmitting messages to the
given Appendable output object.

### Tests and Resources

Each component is tested by its corresponding test class: **TestModel**, **TestView**, and **TestController**.
Tests are applied on sample data found in the res/ directory. The Controller->Model connection is tested
by the **MockImageProcessingModel** class, which logs all calls to the model to ensure that the controller
is correctly using the model.

---
## To Run

Run the main() method of **TextScriptedImageProcessingController**, and enter commands into the console.

### Sample Script:
```
# load square image into the processor
load res/square.ppm square

# create the red component greyscale of the square image
red-component square squareRed

# create the value component greyscale of the square image
value-component square squareValue

# create the horintally flipped version of the value component greyscale of the square image
horizontal-flip squareValue squareValueHorizontal

# create the brightened by 50 version of the red component greyscale image of the square
brighten squareRed squareRedBright 50

# create the blur of the square image
blur square squareBlur

# create the sharpen of the square image
sharpen square squareSharpen

# create the greyscale of the square image
greyscale square squareGreyscale

# create the sepiaTone of the square image
sepiaTone square squareSepiaTone

# save all of the new images
save squareRed res/square-red-grayscale.ppm
save squareValue res/square-value.ppm
save squareValueHorizontal res/square-horizontal.ppm
save squareRedBright res/squarebrighten-by50ppm.ppm
save squareBlur res/square-blur.ppm
save squareSharpen res/square-sharpen.ppm
save squareGreyscale res/square-greyscale.ppm
save squareSepiaTone res/square-sepia-tone.ppm
save squareBlur res/square.bmp
save squareBlur res/square.png
save squareBlur res/square.jpeg
#quit
q
```

### Full list of commands (all commands case insensitive):
```
# get commands
h
help

# load a ppm image into the processor from IMAGE-PATH to IMAGE-NAME
load IMAGE-PATH IMAGE-NAME

# save an image from IMAGE-NAME in the processor to the IMAGE-PATH ppm file
save IMAGE-NAME IMAGE-PATH

# create the red component greyscale of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor
red-component IMAGE-NAME DEST-IMAGE-NAME

# create the blue component greyscale of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor
blue-component IMAGE-NAME DEST-IMAGE-NAME

# create the green component greyscale of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor
green-component IMAGE-NAME DEST-IMAGE-NAME

# create the value component greyscale of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor
value-component IMAGE-NAME DEST-IMAGE-NAME

# create the intensity component greyscale of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor
intensity-component IMAGE-NAME DEST-IMAGE-NAME

# create the luma component greyscale of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor
luma-component IMAGE-NAME DEST-IMAGE-NAME

# create the horintally flipped version of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor
horizontal-flip IMAGE-NAME DEST-IMAGE-NAME

# create the vertically flipped version of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor
vertical-flip IMAGE-NAME DEST-IMAGE-NAME

# create the brightened by INCREMENT amount version of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor
brighten IMAGE-NAME DEST-IMAGE-NAME INCREMENT

# create the darken by INCREMENT amount version of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor
darken IMAGE-NAME DEST-IMAGE-NAME INCREMENT

# create the blur of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor blur IMAGE-NAME DEST-IMAGE-NAME

# create the sharpen of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor sharpen IMAGE-NAME DEST-IMAGE-NAME

# create the greyscale of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor greyscale IMAGE-NAME DEST-IMAGE-NAME

# create the sepiaTone of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor sepiaTone IMAGE-NAME DEST-IMAGE-NAME

#quit
q
quit
```
---
Image citation:
Self drawn.
