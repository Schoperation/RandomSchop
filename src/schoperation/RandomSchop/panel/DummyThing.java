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

        int numOfFib = 50;
        for (int i = 1; i <= numOfFib; i++)
            System.out.println("Num " + i + ": " + fibonacci(i));
        System.out.println("Done.");

    }

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

    private long fibonacci2(int n)
    {
        long previousTwo = 1;
        long previousOne = 1;
        return previousOne;

    }
}
