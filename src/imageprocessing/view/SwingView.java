package imageprocessing.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SwingView extends JFrame implements ImageProcessingSwingView, ListSelectionListener {
  public static final int WIDTH = 9 * Toolkit.getDefaultToolkit().getScreenSize().width / 10;
  public static final int HEIGHT = 8 * Toolkit.getDefaultToolkit().getScreenSize().height / 10;
  public static final Color BACKGROUND_COLOR = Color.decode("#000000");
  public static final Color FOREGROUND_COLOR = Color.decode("#FFFFFF");
  public static final Color MIDDLEGROUND_COLOR = new Color(129, 129, 129);
  public static final Dimension IMAGE_DIMENSION = new Dimension(
          3 * SwingView.WIDTH / 6, 3 * SwingView.HEIGHT / 5);
  public static final Dimension BUTTON_DIMENSION = new Dimension(
          SwingView.WIDTH/7, SwingView.HEIGHT/10);
  public static final Dimension LOAD_BUTTON_DIMENSION = new Dimension(
          SwingView.WIDTH/5, SwingView.HEIGHT/10);
  private JLabel imageLabel;

  public SwingView() {
    // setup
    super();
    this.setTitle("Swing features");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(SwingView.WIDTH, SwingView.HEIGHT);
    this.setBackground(SwingView.BACKGROUND_COLOR);

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      //no default look and feel
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

    // histogram
    Histogram histogram = new Histogram(
            null,
            null,
            null,
            null);
    panel1.add(histogram);

    // image
    imageLabel = new Text("No Image Loaded",
            SwingView.WIDTH / 50,
            SwingView.BACKGROUND_COLOR);
    imageLabel.setMaximumSize(new Dimension(SwingView.IMAGE_DIMENSION.width*9/10,
            SwingView.IMAGE_DIMENSION.height*9/10));
    imageLabel.setPreferredSize(new Dimension(SwingView.IMAGE_DIMENSION.width*9/10,
            SwingView.IMAGE_DIMENSION.height*9/10));

    // scroll panel for image
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setBackground(SwingView.BACKGROUND_COLOR);
    imageScrollPane.setBorder(new EmptyBorder(10,10,10,10));
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
            SwingView.WIDTH/70));

    // padding between load and save buttons
    JPanel loadSavePadding = new JPanel();
    loadSavePadding.setMaximumSize(new Dimension(SwingView.LOAD_BUTTON_DIMENSION.width, SwingView.LOAD_BUTTON_DIMENSION.height/4));
    loadSavePadding.setBackground(SwingView.BACKGROUND_COLOR);
    loadSavePanel.add(loadSavePadding);

    // save button
    loadSavePanel.add(new Button("Save Image",
            SwingView.LOAD_BUTTON_DIMENSION,
            SwingView.WIDTH/70));

    // padding between load and save buttons
    JPanel loadSavePadding2 = new JPanel();
    loadSavePadding2.setMaximumSize(new Dimension(SwingView.LOAD_BUTTON_DIMENSION.width, SwingView.LOAD_BUTTON_DIMENSION.height/2));
    loadSavePadding2.setBackground(SwingView.BACKGROUND_COLOR);
    loadSavePanel.add(loadSavePadding2);

    // dropdown
    JComboBox imageOptions = new JComboBox();
    TitledBorder imageOptionsTitle = new TitledBorder(new EmptyBorder(0,0,0,0),
            "Select image:");
    imageOptionsTitle.setTitleFont(new Font("Sans Serif", Font.PLAIN, SwingView.WIDTH/100));
    imageOptionsTitle.setTitleColor(SwingView.MIDDLEGROUND_COLOR);
    imageOptions.setBorder(imageOptionsTitle);
    imageOptions.setMaximumSize(new Dimension(SwingView.WIDTH / 5, SwingView.HEIGHT / 10));
    imageOptions.setAlignmentX(SwingConstants.CENTER);
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
    panel2.add(new Button("Red Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel2.add(new Button("Green Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel2.add(new Button("Blue Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel2.add(new Button("Flip Horizontally", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel2.add(new Button("Flip Vertically", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel2.add(new Button("Blur", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel2.add(new Button("Sharpen", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel3.add(new Button("Value Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel3.add(new Button("Intensity Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel3.add(new Button("Luma Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel3.add(new Button("Brighten", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel3.add(new Button("Darken", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel3.add(new Button("Greyscale", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel3.add(new Button("Sepia", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));

    this.setVisible(true);
  }

  public static void main(String[] args) {
    new SwingView();
  }

  /**
   * Called whenever the value of the selection changes.
   *
   * @param e the event that characterizes the change.
   */
  @Override
  public void valueChanged(ListSelectionEvent e) {
  }
}