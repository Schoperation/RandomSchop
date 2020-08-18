package schoperation.RandomSchop.panel;

import schoperation.RandomSchop.core.RSThing;

import javax.swing.*;
import java.awt.*;

public class DummyThing extends RSThing
{
    /**
     * Dummy panel for testing the menu
     */
    public DummyThing(String name, String displayName)
    {
        super(name, displayName);
        this.mainPanel = new JPanel();
        this.mainPanel.add(new JButton("hhhhh"));
        this.mainPanel.add(new JButton("lorem ipsum si dolor"));
        this.mainPanel.setBackground(Color.blue);
    }

    @Override
    public void main()
    {
        System.out.println("heeeyy this is the dummy thing selected.");
    }
}
