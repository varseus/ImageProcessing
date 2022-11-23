package imageprocessing.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
    mainPanel.add(this.makeText(
            "Image Processor",
            SwingView.WIDTH / 25,
            SwingView.FOREGROUND_COLOR));


    // panel for histogram, image, and load/save buttons
    JPanel panel1 = new JPanel();
    panel1.setBackground(SwingView.BACKGROUND_COLOR);
    panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
    mainPanel.add(panel1);

    // image label
    imageLabel = this.makeText("No Image Loaded",
            SwingView.WIDTH / 50,
            SwingView.BACKGROUND_COLOR);
    imageLabel.setMaximumSize(new Dimension(SwingView.IMAGE_DIMENSION.width*9/10,
            SwingView.IMAGE_DIMENSION.height*9/10));
    imageLabel.setPreferredSize(new Dimension(SwingView.IMAGE_DIMENSION.width*9/10,
            SwingView.IMAGE_DIMENSION.height*9/10));

    // scroll pane for image
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
    loadSavePanel.add(this.makeButton("Load",
            SwingView.LOAD_BUTTON_DIMENSION,
            SwingView.WIDTH/70));

    // padding between load and save buttons
    JPanel loadSavePadding = new JPanel();
    //loadSavePadding.setMinimumSize(new Dimension(SwingView.WIDTH/5, SwingView.HEIGHT/10));
    loadSavePadding.setMaximumSize(new Dimension(SwingView.WIDTH / 5, SwingView.HEIGHT / 10));
    loadSavePadding.setBackground(SwingView.BACKGROUND_COLOR);
    loadSavePanel.add(loadSavePadding);

    // save button
    loadSavePanel.add(this.makeButton("Save",
            SwingView.LOAD_BUTTON_DIMENSION,
            SwingView.WIDTH/70));

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
    panel2.add(this.makeButton("Red Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel2.add(this.makeButton("Green Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel2.add(this.makeButton("Blue Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel2.add(this.makeButton("Flip Horizontally", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel2.add(this.makeButton("Flip Vertically", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel2.add(this.makeButton("Blur", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel2.add(this.makeButton("Sharpen", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel3.add(this.makeButton("Value Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel3.add(this.makeButton("Intensity Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel3.add(this.makeButton("Luma Component", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel3.add(this.makeButton("Brighten", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel3.add(this.makeButton("Darken", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel3.add(this.makeButton("Greyscale", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));
    panel3.add(this.makeButton("Sepia", SwingView.BUTTON_DIMENSION, SwingView.WIDTH/110));

    this.setVisible(true);
  }

  private JButton makeButton(String text,
                             Dimension size,
                             int textSize) {
    JButton button = new JButton(text);
    button.setContentAreaFilled(false);
    button.setBorder(new CompoundBorder(
            new EmptyBorder(SwingView.HEIGHT/75,SwingView.WIDTH/150,SwingView.HEIGHT/75,SwingView.WIDTH/150),
            new LineBorder(SwingView.MIDDLEGROUND_COLOR, 1, true)));
    button.setOpaque(true);
    button.setFocusPainted(false);
    button.setBackground(SwingView.BACKGROUND_COLOR);
    button.setContentAreaFilled(false);
    button.setForeground(SwingView.MIDDLEGROUND_COLOR);
    button.setFont(new Font("Sans Serif", Font.PLAIN, textSize));
    button.setMaximumSize(size);
    return button;
  }

  private JLabel makeText(String text, int size, Color color) {
    JLabel title = new JLabel(text);
    title.setBorder(new EmptyBorder(10, 10, 10, 10));
    title.setFont(new Font("Sans Serif", Font.BOLD, size));
    title.setAlignmentX(0.5F);
    title.setForeground(color);
    title.setBackground(SwingView.BACKGROUND_COLOR);
    title.setHorizontalAlignment(SwingConstants.CENTER);
    return title;
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