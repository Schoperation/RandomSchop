package schoperation.RandomSchop.text.pi;

import java.util.Random;

public class Pi
{
    /*
        Calculate pi stuff
     */
    public void main()
    {
        calc2(1000000);
    }

    /**
     * Imagine the graph of y = sqrt(1 - x^2). The circle one.
     * We're going to take the first quadrant of it, from x = 0 to 1 and y = 0 to 1.
     * Then, we'll constantly throw darts (random coords), and calculate the ratio between those inside of the circle and the total.
     *
     * That ratio turns out to be pi/4, so we'll multiply it by 4.
     * @param points
     */
    private void calc1(int points)
    {
        double insideCirc = 0;
        double outsideCirc = 1;
        Random rand = new Random();

        // Generate random point, then determine if inside or outside
        int i;
        for (i = 0; i < points; i++)
        {
            float x = rand.nextFloat();
            float y = rand.nextFloat();

            if (y < Math.sqrt(1 - x * x))
                insideCirc++;
            else
                outsideCirc++;

            // Print ratio
            System.out.println(insideCirc * 4 / (insideCirc + outsideCirc));
        }
    }

    /**
     * Uses the Leibniz formula to converge at the value of pi
     * 1 - 4/3 + 4/5 - 4/7 + 4/9...
     * Here, each term is two fractions in an infinite (well, not here but ya know) series
     *
     * sigma from n = 0 to infinity of:
     *
     *        4          4
     *    -------- - --------
     *     4n + 1     4n + 3
     * @param terms
     */
    private void calc2(int terms)
    {
        double sum = 0.0;
        int i;
        for (i = 0; i < terms; i++)
        {
            sum += ((4.0 / (4.0 * i + 1.0)) - (4.0 / (4.0 * i + 3.0)));
            System.out.println(sum);
        }
    }
}
