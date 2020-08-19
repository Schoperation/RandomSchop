package schoperation.RandomSchop.panel;

import javax.swing.*;
import javax.swing.text.Caret;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

public class ConsolePanel extends JPanel
{
    private JScrollPane scroller;
    private TextAreaOutputStream newOut;

    /**
     * A standard panel that just has a basic console on it, perfect for the vast majority of scripts/things. <p></p><p>
     * Either use Panels.CONSOLE_PANEL to be very generic, or create a new ConsolePanel for a more custom one. They'll function the same. </p>
     */
    public ConsolePanel()
    {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.PINK);

        // Initialize new output stream... The default outputstream is setup in RSThing.setup().
        newOut = new TextAreaOutputStream(new JTextArea());
        newOut.console.setBackground(Color.DARK_GRAY);
        newOut.console.setForeground(Color.WHITE);
        this.add(newOut.console, BorderLayout.CENTER);

        // Create scrollpane
        scroller = new JScrollPane(newOut.console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scroller, BorderLayout.CENTER);
    }

    /**
     * Clears the console.
     */
    public void clear()
    {
        ((JTextArea) scroller.getViewport().getView()).setText("");
    }

    /**
     * Returns the OutputStream used by this panel
     */
    public TextAreaOutputStream getOutputStream()
    {
        return this.newOut;
    }

    /**
     * Custom outputstream in jtextarea
     */
    private static class TextAreaOutputStream extends OutputStream
    {
        private JTextArea console;

        public TextAreaOutputStream(JTextArea textArea)
        {
            this.console = textArea;
        }

        @Override
        public void write(int b) throws IOException
        {
            console.append(new String(new byte[] { (byte) b }));
        }
    }
}
