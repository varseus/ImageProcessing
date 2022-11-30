package imageprocessing.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * {@code Text} represent the text which extend the JLabel class.
 */
public class Text extends JLabel {

  /**
   * Instantiates a textbox with given content, size, color of text.
   *
   * @param text  the content of text
   * @param size  the size of text
   * @param color the color of text
   */
  public Text(String text, int size, Color color) {
    super(text);
    this.setBorder(new EmptyBorder(10, 10, 10, 10));
    this.setFont(new Font("Sans Serif", Font.BOLD, size));
    this.setAlignmentX(0.5F);
    this.setForeground(color);
    this.setBackground(SwingView.BACKGROUND_COLOR);
    this.setHorizontalAlignment(SwingConstants.CENTER);
  }
}
