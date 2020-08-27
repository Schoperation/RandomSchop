package schoperation.RandomSchop.panel;

import javafx.scene.shape.Circle;
import schoperation.RandomSchop.core.Main;
import schoperation.RandomSchop.core.RSThing;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.function.Consumer;

public class DummyThing extends RSThing
{
    /**
     * Dummy panel for testing the menu
     */
    public DummyThing(String name, String displayName)
    {
        super(name, displayName);

        this.mainPanel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                g.drawOval(100,100,100,100);
            }
        };

        JButton submit = new JButton("lorem ipsum si dolor");
        submit.addActionListener(new SubmitListener());
        this.mainPanel.add(submit, BorderLayout.PAGE_START);
        this.mainPanel.setBackground(Color.pink);
    }

    @Override
    public void main()
    {
        System.out.println("Cmon");
    }

    /**
     * action listener for submit button
     */
    private static class SubmitListener implements ActionListener
    {
        public JTextArea textArea;
        public SubmitListener()
        {
            textArea = new JTextArea();
            textArea.setSize(100, 50);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Get text, figure out what's in it, and execute respective
            String text = textArea.getText();

            for (RSThing thing : Panels.MASTER_LIST)
            {
                if (thing.getName().equals(text))
                {

                }
            }
        }
    }
}
