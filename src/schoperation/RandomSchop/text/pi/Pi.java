package schoperation.RandomSchop.text.pi;

import java.util.Random;

public class Pi
{
    /*
        Calculate pi stuff
     */
    public void main()
    {
        System.out.println("Yoohooo pi is 3.14");
        calc1(100000);
    }

    /**
     * Imagine the graph of y = sqrt(1 - x^2). The circle one.
     * We're going to take the first quadrant of it, from x = 0 to 1 and y = 0 to 1.
     * Then, we'll constantly throw darts (random coords), and calculate the ratio between those inside of the circle and those outside.
     *
     * Annnd it constantly prints out... 3.6611??
     * @param points
     */
    private void calc1(int points)
    {
        double insideCirc = 0;
        double outsideCirc = 1;
        Random rand = new Random();

        // Generate random point, then determine if inside or outside
        int i;
        while (true)
        {
            float x = rand.nextFloat();
            float y = rand.nextFloat();

            if (y < Math.sqrt(1 - x * x))
                insideCirc++;
            else
                outsideCirc++;

            // Print ratio
            System.out.println(insideCirc / outsideCirc);
        }
    }
}
