package schoperation.RandomSchop.bounce;

import schoperation.RandomSchop.core.RSThing;

import javax.swing.*;
import java.awt.*;

public class BouncingShapes extends RSThing
{
    /** TODO finish
     * Features a bunch of shapes that bounce around the screen.
     * Mostly for fun and testing purposes with drawing stuff on a JPanel.
     * @param name
     * @param displayName
     */
    public BouncingShapes(String name, String displayName)
    {
        super(name, displayName);

        this.mainPanel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {

            }
        };
    }
}
