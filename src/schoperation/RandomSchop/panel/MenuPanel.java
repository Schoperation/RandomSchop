package schoperation.RandomSchop.panel;

import schoperation.RandomSchop.core.MasterList;
import schoperation.RandomSchop.core.RSThing;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class MenuPanel extends JPanel
{
    /**
     * The first panel that is shown; menu for going to the things/scripts
     */
    public MenuPanel()
    {
        // Set layout and background color
        this.setLayout(new GridLayout(0, 3));
        this.setBackground(Color.pink);

        // Welcome text
        JLabel filler = new JLabel(" ");
        JLabel filler1 = new JLabel(" ");
        JLabel welcomeText = new JLabel("Click on a button to proceed...");

        this.add(filler);
        this.add(welcomeText);
        this.add(filler1);

        // Create buttons
        // Iterate through list of... things
        Iterator<RSThing> iterator = MasterList.masterList.iterator();

        while (iterator.hasNext())
        {
            // Create button
            RSThing thing = iterator.next();
            JButton button = new JButton(thing.getDisplayName());
            button.addActionListener(e -> thing.setup());

            this.add(button);
        }
    }
}
