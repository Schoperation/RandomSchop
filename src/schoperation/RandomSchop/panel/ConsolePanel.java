package schoperation.RandomSchop.panel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class ConsolePanel extends JPanel
{
    private JScrollPane scroller;

    public ConsolePanel()
    {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.PINK);

        //JTextArea console = new JTextArea();
        //this.add(console, BorderLayout.PAGE_START);

        PrintStream oldOut = System.out;
        TextAreaOutputStream newOut = new TextAreaOutputStream(new JTextArea());
        this.add(newOut.console);
        System.setOut(new PrintStream(newOut, true));

        scroller = new JScrollPane(newOut.console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scroller, BorderLayout.CENTER);


        // Store old outputstream and create new one

        //System.setIn(console.getText());


    }

    /**
     * Clears the console.
     */
    public void clear()
    {
        ((JTextArea) scroller.getViewport().getView()).setText("");
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
