package imageprocessing.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import imageprocessing.model.Pixel;

/**
 * The {@code Histogram} for the pane which extend teh JPanel class.
 */
public class Histogram extends JPanel {
  public static Dimension HISTOGRAM_DIMENSION = new Dimension(2*SwingView.WIDTH/3, 2*SwingView.HEIGHT/3);

  /**
   * the histogram with the given pixels.
   * @param redPixels the map of redPixels
   * @param greenPixels the map of greenPixels
   * @param bluePixels the map of bluePixels
   * @param intensityPixels the map of intensityPixels
   */
  public Histogram(Map<Integer, Integer> redPixels,
                   Map<Integer, Integer> greenPixels,
                   Map<Integer, Integer> bluePixels,
                   Map<Integer, Integer> intensityPixels) {
    super();
    this.setBackground(SwingView.BACKGROUND_COLOR);
    this.setLayout(null);
    this.setAlignmentY(0.5F);
    this.setMaximumSize(Histogram.HISTOGRAM_DIMENSION);

    // x axis
    this.setBorder(new MatteBorder(0,0,1,0, SwingView.FOREGROUND_COLOR));

    // line graphs
    String[] lineGraphTypes = new String[]{"R", "G", "B", "intensity"};
    for(String lineGraphType : lineGraphTypes) {
      Color color = SwingView.FOREGROUND_COLOR;
      Map<Integer, Integer> pixels;
      try {
        switch (lineGraphType) {
          case "R":
            color = Color.red;
            pixels = Objects.requireNonNull(redPixels);
            break;
          case "G":
            color = Color.green;
            pixels = Objects.requireNonNull(greenPixels);
            break;
          case "B":
            color = Color.blue;
            pixels = Objects.requireNonNull(bluePixels);
            break;
          default:
            color = Color.yellow;
            pixels = Objects.requireNonNull(intensityPixels);
            break;
        }
      } catch (Exception e) {
        pixels = new HashMap<>();
      }
      for(Map.Entry<Integer, Integer> entry : pixels.entrySet()) {
        JPanel lineSegment = new JPanel();
        lineSegment.setBackground(SwingView.BACKGROUND_COLOR);
        lineSegment.setBounds(entry.getKey() * (Histogram.HISTOGRAM_DIMENSION.width / 256),
                Histogram.HISTOGRAM_DIMENSION.height - entry.getValue(),
                Histogram.HISTOGRAM_DIMENSION.width / 256,
                1);
        lineSegment.setBorder(new MatteBorder(1,0,0,0, color));
        this.add(lineSegment);
      }
    }
  }
}
