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

        this.mainPanel = new ConsolePanel();
        this.mainPanel.add(new JButton("lorem ipsum si dolor"), BorderLayout.PAGE_START);
        this.mainPanel.setBackground(Color.blue);
    }

    @Override
    public void main()
    {
        System.out.println("heeeyy this is the dummy thing selected.");
        System.out.println("Ya know, you should pick something actually useful. Use the dropdown.");
        System.out.println("Haaalleeelluuuujah, Haaaalleeellluuuuujah, Haaaalleeluuuuuujah, HaaalleeluuuuuuuuuuuuuuuuOOUooouuuuuuuuuujah");
    }
}
