package imageprocessing.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Text extends JLabel {
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
