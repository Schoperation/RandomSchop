package schoperation.RandomSchop.bounce;

import schoperation.RandomSchop.core.Main;

import java.awt.*;
import java.util.Random;

public class BouncingRect
{
    // Basic vars
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    // Used to indicate direction
    private int changeX;
    private int changeY;
    Random randomizer;

    /**
     * Creates a rectangle (probably with random points) that will move around the screen via method
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public BouncingRect(int x, int y, int width, int height, Color color)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;

        // Randomize speeds
        randomizer = new Random();
        changeX = randomizer.nextInt(5);
        changeY = randomizer.nextInt(5);

        // Determine directions
        boolean direc = randomizer.nextBoolean();
        if (!direc)
            changeX *= -1;

        direc = randomizer.nextBoolean();
        if (!direc)
            changeY *= -1;
    }

    protected int getX()
    {
        return this.x;
    }

    protected int getY()
    {
        return this.y;
    }

    protected int getWidth()
    {
        return this.width;
    }

    protected int getHeight()
    {
        return this.height;
    }

    protected Color getColor()
    {
        return this.color;
    }

    /**
     * Change position of the rectangle... panelheight is a parameter so we can access that instead of the main jframe height,
     * as they are different due to the menu bar
     * @param panelHeight
     */
    protected void changePos(int panelHeight)
    {
        x += changeX;

        // Check if we're out of bounds
        if ((x + this.width) > Main.jframe.getWidth())
        {
            changeX *= -1;
            x = Main.jframe.getWidth() - this.width - 1;
        }
        else if (x < 0)
        {
            changeX *= -1;
            x = 1;
        }

        y += changeY;
        if ((y + this.height) > panelHeight)
        {
            changeY *= -1;
            y = panelHeight - this.height - 1;
        }
        else if (y < 0)
        {
            changeY *= -1;
            y = 1;
        }
    }
}
