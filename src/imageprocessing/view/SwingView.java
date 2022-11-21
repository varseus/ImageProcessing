package imageprocessing.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ScrollBarUI;

public class SwingView extends JFrame implements ImageProcessingSwingView {
  public static final int WIDTH = 9 * Toolkit.getDefaultToolkit().getScreenSize().width / 10;
  public static final int HEIGHT = 8 * Toolkit.getDefaultToolkit().getScreenSize().height / 10;
  public static final Color backgroundColor = Color.decode("#1d1238");
  public static final Color foregroundColor = Color.decode("#FFFFFF");
  private JLabel titleText;
  private JPanel mainPanel;

  public SwingView() {
    super();
    this.setTitle("Swing features");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(SwingView.WIDTH, SwingView.HEIGHT);
    this.setBackground(SwingView.backgroundColor);

    this.mainPanel = new JPanel();
    this.mainPanel.setBackground(SwingView.backgroundColor);
    this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    this.add(this.mainPanel);

    this.titleText = new JLabel("Image Processor");
    this.titleText.setBorder(new EmptyBorder(10, 10, 10, 10));
    this.titleText.setFont(new Font("Sans Serif", Font.BOLD, SwingView.WIDTH / 25));
    this.titleText.setAlignmentX(0.5F);
    this.titleText.setForeground(SwingView.foregroundColor);
    this.titleText.setBackground(SwingView.backgroundColor);
    mainPanel.add(this.titleText);

    JLabel imageLabel = new JLabel();
    imageLabel.setIcon(new ImageIcon("res/koala-vertical.png"));
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setMaximumSize(new Dimension(2*SwingView.WIDTH/3, 2*SwingView.HEIGHT/3));
    imageScrollPane.setPreferredSize(new Dimension(2*SwingView.WIDTH/3, 2*SwingView.HEIGHT/3));
    imageScrollPane.setBackground(SwingView.backgroundColor);
    imageScrollPane.setBorder(new EmptyBorder(10,10,10,10));
    //imageScrollPane.setAlignmentX(SwingConstants.LEFT);
    mainPanel.add(imageScrollPane);


    this.setVisible(true);
  }

  public static void main(String[] args) {
    new SwingView();
  }
}