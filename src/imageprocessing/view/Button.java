package imageprocessing.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * The {@code Button} represent the button for the action which extend the JButton class.
 */
public class Button extends JButton {

  /**
   * the action of button when the user uses it.
   * @param text the text that entered
   * @param size the size of the image
   * @param textSize the size if the text
   */
  public Button(String text,
                Dimension size,
                int textSize,
                ActionListener actionListener) {
    super(text);
    this.setContentAreaFilled(false);
    this.setBorder(new CompoundBorder(
            new EmptyBorder(SwingView.HEIGHT/75,SwingView.WIDTH/150,SwingView.HEIGHT/75,SwingView.WIDTH/150),
            new LineBorder(SwingView.MIDDLEGROUND_COLOR, 1, true)));
    this.setOpaque(true);
    this.setFocusPainted(false);
    this.setBackground(SwingView.BACKGROUND_COLOR);
    this.setContentAreaFilled(false);
    this.setForeground(SwingView.MIDDLEGROUND_COLOR);
    this.setFont(new Font("Sans Serif", Font.PLAIN, textSize));
    this.setMaximumSize(size);

    this.addActionListener(actionListener);
    this.setActionCommand(text);
  }
}
