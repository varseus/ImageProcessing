import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import imageprocessing.model.ImageProcessingModel;
import imageprocessing.view.ImageProcessingView;
import imageprocessing.view.TextScriptImageProcessingView;

import static org.junit.Assert.assertEquals;

/**
 * The {@code TestView} to test the methods in TestView class.
 */
public class TestView {
  private ImageProcessingView view;
  private Appendable output;

  @Before
  public void setup() {
    this.output = new StringBuilder();
    this.view = new TextScriptImageProcessingView(output);
  }

  /**
   * This subclass represents an Appendable that always throws
   * an IOException (for testing exception handling).
   */
  static class BadAppendable implements Appendable {

    /**
     * Throws an I/0 exception, for testing errors.
     *
     * @param csq The character sequence to append.  If {@code csq} is
     *            {@code null}, then the four characters {@code "null"} are
     *            appended to this Appendable.
     * @return never returns
     * @throws IOException always
     */
    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException("Unable to append");
    }

    /**
     * Throws an I/0 exception, for testing errors.
     *
     * @param csq   The character sequence from which a subsequence will be
     *              appended.  If {@code csq} is {@code null}, then characters
     *              will be appended as if {@code csq} contained the four
     *              characters {@code "null"}.
     * @param start The index of the first character in the subsequence
     * @param end   The index of the character following the last character in the
     *              subsequence
     * @return never returns
     * @throws IOException always
     */
    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException("Unable to append");
    }

    /**
     * Throws an I/O exception, for testing errors.
     *
     * @param c The character to append
     * @return A reference to this {@code Appendable}
     * @throws IOException always
     */
    @Override
    public Appendable append(char c) throws IOException {
      throw new IOException("Unable to append");
    }
  }

  /**
   * To test that constructing a view with null output throws an error.
   */
  @Test(expected = NullPointerException.class)
  public void testNullConstructor() {
    this.view = new TextScriptImageProcessingView((Appendable) null);
  }

  /**
   * To test that rendering a null message throws an error.
   */
  @Test(expected = NullPointerException.class)
  public void testRenderMessageFail() throws IOException {
    this.view.renderMessage(null);
  }

  /**
   * To test that a failed render throws IOException.
   */
  @Test(expected = IOException.class)
  public void testRenderMessageFail2() throws IOException {
    this.view = new TextScriptImageProcessingView(new BadAppendable());
    this.view.renderMessage("This is a message.");
  }

  /**
   * To test renderMessage.
   */
  @Test
  public void testRenderMessage() throws IOException {
    this.view.renderMessage("This is a message.");
    assertEquals("This is a message.", this.output.toString());
  }
}
