*Written in Markdown*
# USEME — ImageProcessing

## To Run

In the terminal, navigate to the root directory. Then to run the script, run:
```
java -jar ImageProcessing.jar res/script.txt
```
---
To run the full program to the console, run:
```
java -jar ImageProcessing.jar
```
and enter commands into the console.

---
Alternatively, run the main() method of **ImageProcessor**, and enter commands into the console.

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
# an image must be loaded before doing any operations
load IMAGE-PATH IMAGE-NAME

# save an image from IMAGE-NAME in the processor to the IMAGE-PATH file
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
blur IMAGE-NAME DEST-IMAGE-NAME

# create the sharpen of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor sharpen IMAGE-NAME DEST-IMAGE-NAME
sharpen IMAGE-NAME DEST-IMAGE-NAME

# create the greyscale of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor greyscale IMAGE-NAME DEST-IMAGE-NAME
greyscale IMAGE-NAME DEST-IMAGE-NAME

# create the sepiaTone of IMAGE-NAME in the processor to DEST-IMAGE-NAME in the processor sepiaTone IMAGE-NAME DEST-IMAGE-NAME
sepiatone IMAGE-NAME DEST-IMAGE-NAME

# quit
q
quit
```
