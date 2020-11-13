package schoperation.RandomSchop.graphics.panel;

import schoperation.RandomSchop.core.RSThing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                super.paintComponent(g);
                g.setColor(new Color(50, 135, 37));

                int i;
                int j;
                for (i = 0; i < 14; i++)
                {
                    for (j = 0; j < 14; j++)
                    {
                        if ((j % 2 == 0 && i % 2 == 0) || (j % 2 == 1 && i % 2 == 1))
                            g.fillOval(100 * j, 100*i, 100, 100);
                    }
                }
            }
        };

        this.mainPanel.setBackground(new Color(50, 175, 229));

        JButton submit = new JButton("lorem ipsum si dolor");
        SubmitListener submitListener = new SubmitListener();
        submit.addActionListener(submitListener);
        this.mainPanel.add(submit, BorderLayout.CENTER);
        this.mainPanel.add(submitListener.textArea, BorderLayout.CENTER);
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
            textArea.setColumns(30);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Get text, figure out what's in it, and execute respective
            String text = textArea.getText();
            textArea.setText("");
            Panels.changePanelWithName(text);
        }
    }
}
