package imageprocessing.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import imageprocessing.controller.SwingAppFeatures;
import imageprocessing.model.ImageProcessingModel;
import imageprocessing.model.Pixel;

/**
 * Represents a Swing View for an Image Processor, that shows the current image being processed and
 * can do effects on it, and showing a histogram for the image.
 */
public class SwingView extends JFrame implements ImageProcessingSwingView, ActionListener {

  private final ImageProcessingModel model;
  private Histogram histogram;
  private SwingAppFeatures features;
  private JLabel imageLabel;
  private final JComboBox imageOptions;

  public static final int WIDTH = 9 * Toolkit.getDefaultToolkit().getScreenSize().width / 10;
  public static final int HEIGHT = 8 * Toolkit.getDefaultToolkit().getScreenSize().height / 10;
  public static final Color BACKGROUND_COLOR = Color.decode("#000000");
  public static final Color FOREGROUND_COLOR = Color.decode("#FFFFFF");
  public static final Color MIDDLEGROUND_COLOR = new Color(129, 129, 129);
  public static final Dimension IMAGE_DIMENSION = new Dimension(
      3 * SwingView.WIDTH / 7, 3 * SwingView.HEIGHT / 5);
  public static final Dimension BUTTON_DIMENSION = new Dimension(
      SwingView.WIDTH / 7, SwingView.HEIGHT / 10);
  public static final Dimension LOAD_BUTTON_DIMENSION = new Dimension(
      SwingView.WIDTH / 5, SwingView.HEIGHT / 10);

  /**
   * Instantiates this view with the given model.
   *
   * @param model the model to run the processor
   */
  public SwingView(ImageProcessingModel model) {
    super();
    this.model = Objects.requireNonNull(model);

    // setup
    this.setTitle("Swing features");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(SwingView.WIDTH, SwingView.HEIGHT);
    this.setBackground(SwingView.BACKGROUND_COLOR);
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      // no default look and feel
    }

    // main panel
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(SwingView.BACKGROUND_COLOR);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    this.add(mainPanel);

    // title
    mainPanel.add(new Text(
        "Image Processor",
        SwingView.WIDTH / 25,
        SwingView.FOREGROUND_COLOR));

    // panel for histogram, image, and load/save buttons
    JPanel panel1 = new JPanel();
    panel1.setBackground(SwingView.BACKGROUND_COLOR);
    panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
    mainPanel.add(panel1);

    // left padding between histogram and off screen
    JPanel paddingLeft = new JPanel();
    paddingLeft.setMaximumSize(new Dimension(10, 10));
    paddingLeft.setBackground(SwingView.BACKGROUND_COLOR);
    panel1.add(paddingLeft);

    // histogram
    histogram = new Histogram(
        null,
        null,
        null,
        null);
    panel1.add(histogram);

    // image
    imageLabel = new Text("No Image Loaded",
        SwingView.WIDTH / 50,
        SwingView.BACKGROUND_COLOR);

    // scroll panel for image
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setBackground(SwingView.BACKGROUND_COLOR);
    imageScrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
    imageScrollPane.setMaximumSize(SwingView.IMAGE_DIMENSION);
    imageScrollPane.setPreferredSize(SwingView.IMAGE_DIMENSION);
    panel1.add(imageScrollPane);

    // panel for buttons in panel1
    JPanel loadSavePanel = new JPanel();
    loadSavePanel.setBackground(SwingView.BACKGROUND_COLOR);
    loadSavePanel.setLayout(new BoxLayout(loadSavePanel, BoxLayout.PAGE_AXIS));
    panel1.add(loadSavePanel);

    // load button
    loadSavePanel.add(new Button("Load Image",
        SwingView.LOAD_BUTTON_DIMENSION,
        SwingView.WIDTH / 70,
        this));

    // padding between load and save buttons
    JPanel loadSavePadding = new JPanel();
    loadSavePadding.setMaximumSize(new Dimension(SwingView.LOAD_BUTTON_DIMENSION.width,
        SwingView.LOAD_BUTTON_DIMENSION.height / 4));
    loadSavePadding.setBackground(SwingView.BACKGROUND_COLOR);
    loadSavePanel.add(loadSavePadding);

    // save button
    loadSavePanel.add(new Button("Save Image",
        SwingView.LOAD_BUTTON_DIMENSION,
        SwingView.WIDTH / 70,
        this));

    // padding between load and save buttons
    JPanel loadSavePadding2 = new JPanel();
    loadSavePadding2.setMaximumSize(new Dimension(SwingView.LOAD_BUTTON_DIMENSION.width,
        SwingView.LOAD_BUTTON_DIMENSION.height / 2));
    loadSavePadding2.setBackground(SwingView.BACKGROUND_COLOR);
    loadSavePanel.add(loadSavePadding2);

    // dropdown
    imageOptions = new JComboBox();
    TitledBorder imageOptionsTitle = new TitledBorder(new EmptyBorder(0, 0, 0, 0),
        "Select image:");
    imageOptionsTitle.setTitleFont(new Font("Sans Serif", Font.PLAIN, SwingView.WIDTH / 100));
    imageOptionsTitle.setTitleColor(SwingView.MIDDLEGROUND_COLOR);
    imageOptions.setBorder(imageOptionsTitle);
    imageOptions.setMaximumSize(new Dimension(SwingView.WIDTH / 5, SwingView.HEIGHT / 10));
    imageOptions.setAlignmentX(SwingConstants.CENTER);
    imageOptions.setActionCommand("Change Imge");
    imageOptions.addActionListener(this);
    loadSavePanel.add(imageOptions);

    // panels for processing buttons
    JPanel panel2 = new JPanel();
    panel2.setBackground(SwingView.BACKGROUND_COLOR);
    panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
    mainPanel.add(panel2);
    JPanel panel3 = new JPanel();
    panel3.setBackground(SwingView.BACKGROUND_COLOR);
    panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
    mainPanel.add(panel3);

    // buttons
    panel2.add(
        new Button("Red Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH / 110, this));
    panel2.add(
        new Button("Green Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH / 110, this));
    panel2.add(
        new Button("Blue Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH / 110, this));
    panel2.add(
        new Button("Flip Horizontally", SwingView.BUTTON_DIMENSION, SwingView.WIDTH / 110, this));
    panel2.add(
        new Button("Flip Vertically", SwingView.BUTTON_DIMENSION, SwingView.WIDTH / 110, this));
    panel2.add(new Button("Blur", SwingView.BUTTON_DIMENSION, SwingView.WIDTH / 110, this));
    panel2.add(new Button("Sharpen", SwingView.BUTTON_DIMENSION, SwingView.WIDTH / 110, this));
    panel3.add(
        new Button("Value Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH / 110, this));
    panel3.add(
        new Button("Intensity Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH / 110, this));
    panel3.add(
        new Button("Luma Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH / 110, this));
    panel3.add(new Button("Brighten", SwingView.BUTTON_DIMENSION, SwingView.WIDTH / 110, this));
    panel3.add(new Button("Darken", SwingView.BUTTON_DIMENSION, SwingView.WIDTH / 110, this));
    panel3.add(new Button("Greyscale", SwingView.BUTTON_DIMENSION, SwingView.WIDTH / 110, this));
    panel3.add(new Button("Sepia", SwingView.BUTTON_DIMENSION, SwingView.WIDTH / 110, this));

    this.setVisible(true);
  }

  // ------------------ Controller Event Handlers ----------------- //

  @Override
  public void setFeatures(SwingAppFeatures features) {
    this.features = features;
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @return the string being rendered if successful
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public String renderMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.WARNING_MESSAGE);
    return message;
  }

  /**
   * Export the given image as a ppm.
   *
   * @param pixels the data of the image to save
   * @param filepath the path of file
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public void saveImageToFile(String filepath, ArrayList<ArrayList<Pixel>> pixels)
          throws IllegalArgumentException, IOException {
    ImageWriteUtil.writePixelsToFile(pixels, filepath);
  }

  @Override
  public void addImage(String imageName) {
    this.imageOptions.removeItem(imageName);
    this.imageOptions.addItem(imageName);
    this.imageOptions.setSelectedItem(imageName);
  }

  @Override
  public void displayImage(String filepath) {
    try {
      this.imageLabel.setIcon(new ImageIcon(ImageIO.read(new File(filepath))));
      this.imageLabel.setText("");
    } catch (Exception e) {
      System.out.println(e);
    }

    String currentImage = (String) this.imageOptions.getSelectedItem();
    HashMap<Integer, Integer> map = new HashMap<>();
    this.histogram.setNewData(
        this.model.makeHistogramHashmap(
            currentImage, "R", Histogram.HISTOGRAM_DIMENSION.height),
        this.model.makeHistogramHashmap(
            currentImage, "G", Histogram.HISTOGRAM_DIMENSION.height),
        this.model.makeHistogramHashmap(
            currentImage, "B", Histogram.HISTOGRAM_DIMENSION.height),
        this.model.makeHistogramHashmap(
            currentImage, "intensity", Histogram.HISTOGRAM_DIMENSION.height));
    System.out.println(this.model.makeHistogramHashmap(
        currentImage, "R", SwingView.IMAGE_DIMENSION.height).get(25));
  }

  @Override
  public void refresh() {
    this.revalidate();
    this.repaint();
  }

  // ------------------ Listeners -------------------- //


  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Load Image":
        JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Images", "jpg", "gif", "jpeg", "png", "ppm", "bmp", "tiff");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          this.features.loadImage(f.getAbsolutePath(),
              JOptionPane.showInputDialog("Please enter a name for this image."));
        }
        break;
      case "Save Image":
        JFileChooser fchooser2 = new JFileChooser(".");
        int retvalue2 = fchooser2.showSaveDialog(this);
        if (retvalue2 == JFileChooser.APPROVE_OPTION) {
          File f = fchooser2.getSelectedFile();
          this.features.saveImage((String) this.imageOptions.getSelectedItem(),
              f.getAbsolutePath());
        }
        break;
      case "Red Component":
        this.features.redComponent((String) this.imageOptions.getSelectedItem(),
            JOptionPane.showInputDialog("Please enter a name for this image."));
        break;
      case "Green Component":
        this.features.greenComponent((String) this.imageOptions.getSelectedItem(),
            JOptionPane.showInputDialog("Please enter a name for this image."));
        break;
      case "Blue Component":
        this.features.blueComponent((String) this.imageOptions.getSelectedItem(),
            JOptionPane.showInputDialog("Please enter a name for this image."));
        break;
      case "Value Component":
        this.features.valueComponent((String) this.imageOptions.getSelectedItem(),
            JOptionPane.showInputDialog("Please enter a name for this image."));
        break;
      case "Intensity Component":
        this.features.intensityComponent((String) this.imageOptions.getSelectedItem(),
            JOptionPane.showInputDialog("Please enter a name for this image."));
        break;
      case "Luma Component":
        this.features.lumaComponent((String) this.imageOptions.getSelectedItem(),
            JOptionPane.showInputDialog("Please enter a name for this image."));
        break;
      case "Blur":
        this.features.blur((String) this.imageOptions.getSelectedItem(),
            JOptionPane.showInputDialog("Please enter a name for this image."));
        break;
      case "Sharpen":
        this.features.sharpen((String) this.imageOptions.getSelectedItem(),
            JOptionPane.showInputDialog("Please enter a name for this image."));
        break;
      case "Greyscale":
        this.features.greyscale((String) this.imageOptions.getSelectedItem(),
            JOptionPane.showInputDialog("Please enter a name for this image."));
        break;
      case "Sepia":
        this.features.sepia((String) this.imageOptions.getSelectedItem(),
            JOptionPane.showInputDialog("Please enter a name for this image."));
        break;
      case "Flip Vertically":
        this.features.flipVertically((String) this.imageOptions.getSelectedItem(),
            JOptionPane.showInputDialog("Please enter a name for this image."));
        break;
      case "Flip Horizontally":
        this.features.flipHorizontally((String) this.imageOptions.getSelectedItem(),
            JOptionPane.showInputDialog("Please enter a name for this image."));
        break;
      case "Brighten":
        try {
          this.features.brighten((String) this.imageOptions.getSelectedItem(),
              JOptionPane.showInputDialog("Please enter a name for this image."),
              Integer.parseInt(
                  JOptionPane.showInputDialog("Please specify how much to brighten by.")));
        } catch (Exception error) {
          this.renderMessage("Can only brighten by integer values.");
        }
        break;
      case "Darken":
        try {
          this.features.darken((String) this.imageOptions.getSelectedItem(),
              JOptionPane.showInputDialog("Please enter a name for this image."),
              Integer.parseInt(
                  JOptionPane.showInputDialog("Please specify how much to darken by.")));
        } catch (Exception error) {
          this.renderMessage("Can only darken by integer values.");
        }
        break;
      case "Change Image":
        this.features.displayImage(
            "res/displayedImages/" + this.imageOptions.getSelectedItem().hashCode() + ".png");
        break;
      default:
        System.out.println("Unknown action");
        // shouldn't get here!
        break;
    }
  }
}