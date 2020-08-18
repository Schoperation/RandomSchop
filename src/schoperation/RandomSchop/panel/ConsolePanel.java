package schoperation.RandomSchop.panel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class ConsolePanel extends JPanel
{
    public ConsolePanel()
    {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.PINK);
        this.setPreferredSize(new Dimension(200, 200));

        JTextArea console = new JTextArea();
        this.add(console);

        JScrollPane scroller = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setPreferredSize(new Dimension(200, 200));
        this.add(scroller, BorderLayout.CENTER);

        // Store old outputstream and create new one
        PrintStream oldOut = System.out;
        TextAreaOutputStream newOut = new TextAreaOutputStream(console);
        System.setOut(new PrintStream(newOut, true));
        System.setIn(new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        });


    }

    private static class TextAreaOutputStream extends OutputStream
    {
        private JTextArea textArea;

        public TextAreaOutputStream(JTextArea textArea)
        {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException
        {
            textArea.append(new String(new byte[] { (byte) b }));
        }
    }

    private static class TextAreaInputStream extends InputStream
    {
        private JTextArea textArea;

        @Override
        public int read() throws IOException {
            return 0;
        }
    }
}
