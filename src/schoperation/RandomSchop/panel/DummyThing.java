package schoperation.RandomSchop.panel;

import schoperation.RandomSchop.core.Main;
import schoperation.RandomSchop.core.RSThing;

import javax.swing.*;
import javax.swing.border.Border;
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

        this.mainPanel = new ConsolePanel();
        JButton submit = new JButton("lorem ipsum si dolor");
        submit.addActionListener(new SubmitListener());
        this.mainPanel.add(submit, BorderLayout.PAGE_START);
        this.mainPanel.setBackground(Color.pink);
    }

    @Override
    public void main()
    {
        System.out.println("DASodjasiodjasiodjasodiasjdias");
    }

    /**
     * action listener for submit button
     */
    private static class SubmitListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // TODO button action
        }
    }
}
