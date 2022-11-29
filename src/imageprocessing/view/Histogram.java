package imageprocessing.view;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;


/**
 * The {@code Histogram} for the pane which extend teh JPanel class.
 */
public class Histogram extends JPanel {
  public static Dimension HISTOGRAM_DIMENSION =
          new Dimension(
                  2 * SwingView.IMAGE_DIMENSION.width / 3,
                  2 * SwingView.IMAGE_DIMENSION.height / 3);
  private Map<Integer, Integer> redPixels;
  private Map<Integer, Integer> greenPixels;
  private Map<Integer, Integer> bluePixels;
  private Map<Integer, Integer> intensityPixels;


  /**
   * the histogram with the given pixels.
   *
   * @param redPixels       the map of redPixels
   * @param greenPixels     the map of greenPixels
   * @param bluePixels      the map of bluePixels
   * @param intensityPixels the map of intensityPixels
   */
  public Histogram(Map<Integer, Integer> redPixels,
                   Map<Integer, Integer> greenPixels,
                   Map<Integer, Integer> bluePixels,
                   Map<Integer, Integer> intensityPixels) {
    super();
    this.setBackground(SwingView.BACKGROUND_COLOR);
    this.setMaximumSize(Histogram.HISTOGRAM_DIMENSION);

    // x axis
    this.setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0),
            new MatteBorder(0, 0, 1, 0, SwingView.FOREGROUND_COLOR)));

    this.redPixels = redPixels;
    this.greenPixels = greenPixels;
    this.bluePixels = bluePixels;
    this.intensityPixels = intensityPixels;
  }

  public void setNewData(Map<Integer, Integer> redPixels,
                         Map<Integer, Integer> greenPixels,
                         Map<Integer, Integer> bluePixels,
                         Map<Integer, Integer> intensityPixels) {
    this.redPixels = redPixels;
    this.bluePixels = bluePixels;
    this.greenPixels = greenPixels;
    this.intensityPixels = intensityPixels;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // line graphs
    String[] lineGraphTypes = new String[]{"R", "G", "B", "intensity"};
    for (String lineGraphType : lineGraphTypes) {
      Color color = SwingView.FOREGROUND_COLOR;
      Map<Integer, Integer> pixels;
      try {
        switch (lineGraphType) {
          case "R":
            g2d.setColor(Color.red);
            pixels = Objects.requireNonNull(redPixels);
            break;
          case "G":
            g2d.setColor(Color.green);
            pixels = Objects.requireNonNull(greenPixels);
            break;
          case "B":
            g2d.setColor(Color.blue);
            pixels = Objects.requireNonNull(bluePixels);
            break;
          default:
            g2d.setColor(Color.white);
            pixels = Objects.requireNonNull(intensityPixels);
            break;
        }
      } catch (Exception e) {
        pixels = new HashMap<>();
      }
      for (Map.Entry<Integer, Integer> entry : pixels.entrySet()) {
        if (entry.getValue() == null) {
          entry.setValue(0);
        }
        if (pixels.get(entry.getKey() + 1) == null) {
          pixels.put(entry.getKey() + 1, 0);
        }

        if (entry.getKey() < 256) {
          g2d.drawLine((int)(entry.getKey() * (Histogram.HISTOGRAM_DIMENSION.width / 256.0)),
                  Histogram.HISTOGRAM_DIMENSION.height - entry.getValue(),
                  (int)((entry.getKey() + 1) * (Histogram.HISTOGRAM_DIMENSION.width / 256.0)),
                  Histogram.HISTOGRAM_DIMENSION.height - pixels.get(entry.getKey() + 1));
        }
      }
    }

    g2d.drawString("<-Blacker", 0, Histogram.HISTOGRAM_DIMENSION.height);
    g2d.drawString("Whiter->", Histogram.HISTOGRAM_DIMENSION.width-75, Histogram.HISTOGRAM_DIMENSION.height);
  }
}