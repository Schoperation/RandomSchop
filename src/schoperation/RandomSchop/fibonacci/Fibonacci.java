package schoperation.RandomSchop.fibonacci;

import schoperation.RandomSchop.core.Main;
import schoperation.RandomSchop.core.RSThing;
import schoperation.RandomSchop.panel.ConsolePanel;

import javax.swing.*;
import java.awt.*;

public class Fibonacci extends RSThing
{

    public Fibonacci(String name, String displayName)
    {
        super(name, displayName);

        this.mainPanel = new ConsolePanel();
        this.mainPanel.setBackground(Color.PINK);
    }

    @Override
    public void main()
    {
        // First ask if they want a sequence or nth number
        String[] options = {"Sequence", "Single Number"};
        String sequenceOrSingleNum = (String) JOptionPane.showInputDialog(Main.jframe, "Would you like to generate a sequence of numbers, or find some nth number?", "Fibonacci's Fibonachos", JOptionPane.PLAIN_MESSAGE, null, options, "Sequence");

        String inputMsg = "";
        if (sequenceOrSingleNum.equals("Sequence"))
            inputMsg = "How many fibonacci numbers would you like?";
        else
            inputMsg = "Which nth Fibonacci number would you like to calculate?\nKeep in mind, anything over 40 may take a little while, because it uses recursion...\nwhich is short and sweet but takes its sweet ass time!";

        // Ask for length of sequence or specific n
        int numOfFib = -1;
        while (numOfFib < 0)
        {
            try
            {
                numOfFib = Integer.parseInt(JOptionPane.showInputDialog(Main.jframe, inputMsg, "Fibonacci Sequence", JOptionPane.PLAIN_MESSAGE));
            }
            catch (NumberFormatException e)
            {
                System.out.println("I need a number, not whatever the hell that was.");
                continue;
            }

            if (numOfFib < 0)
                System.out.println("Number needs to be at least 0.");
        }

        if (sequenceOrSingleNum.equals("Sequence"))
            fibonacciSequence(numOfFib);
        else
        {
            System.out.println("Working...");
            System.out.println(fibonacci(numOfFib));
        }
    }

    /**
     * Recursive Fibonacci sequence method; returns the nth fibonacci number. No negative numbers, O(n^2)
     * @param n integer
     * @return nth Fibonacci number
     */
    private long fibonacci(int n)
    {
        switch (n)
        {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    /**
     * Prints out the Fibonacci sequence up to the nth FIbonacci number, inclusive. O(n)
     * @param n
     */
    private void fibonacciSequence(int n)
    {
        // Just do the minimum for zero
        if (n == 0)
        {
            System.out.println(0);
        }
        else
        {
            long previousTwo = 0;
            long previousOne = 1;
            long sum;

            // First num; for loop doesn't fire if n = 1
            System.out.println("FibNum " + 1 + ": " + 1);

            int i;
            for (i = 2; i <= n; i++)
            {
                sum = previousOne + previousTwo;
                System.out.println("FibNum " + i + ": " + sum);

                // Shift the numbers now
                previousTwo = previousOne;
                previousOne = sum;
            }
        }
    }
}
