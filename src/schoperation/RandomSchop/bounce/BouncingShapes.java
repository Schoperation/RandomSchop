package schoperation.RandomSchop.bounce;

import schoperation.RandomSchop.core.Main;
import schoperation.RandomSchop.core.RSThing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BouncingShapes extends RSThing
{
    // Store rectangles
    public ArrayList<BouncingRect> rects = new ArrayList<>();

    /**
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
                g.clearRect(0, 0, Main.jframe.getWidth(), Main.jframe.getHeight());

                // Draw the rectangles
                for (BouncingRect rect : rects)
                {
                    g.setColor(rect.getColor());
                    g.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                }
            }
        };
    }

    @Override
    public void main()
    {
        // Create new rectangles
        Random random = new Random();
        int i;
        int numRects = random.nextInt(20);
        for (i = 0; i < numRects; i++)
            rects.add(new BouncingRect(random.nextInt(Main.jframe.getWidth()), random.nextInt(Main.jframe.getHeight()), random.nextInt(100), random.nextInt(100), new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))));

        // Loop
        while (this.mainPanel.isVisible())
        {
            for (BouncingRect rect : rects)
                rect.changePos(this.mainPanel.getHeight());

            this.mainPanel.repaint();

            try
            {
                Thread.sleep(17); // about 60 fps
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        rects.clear();
    }
}
