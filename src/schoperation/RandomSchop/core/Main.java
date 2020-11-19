package schoperation.RandomSchop.core;

import schoperation.RandomSchop.graphics.panel.Panels;
import schoperation.RandomSchop.text.calculator.Calculator;
import schoperation.RandomSchop.text.pi.Pi;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main
{
    /*
        TODO Dumb ideas to add

        - Simple calculator
        - Model solar system
        - Calculate state/federal taxes
        - Nuh uh uh! You didn't say the magic word!
        - Conspiracy parrot
        - God word (press a key for a random word)
        - Chess
        - dumb sorting algorithms
        - file compression tests huffman trees ++
     */

    /*
        List of all text code snippet things
     */
    private static final List<TextThing> textThingList = Arrays.asList(
            new TextThing("pi", "Calculate Pi", new Pi()),
            new TextThing("calc", "Basic Calculator", new Calculator())
    );

    /**
     * The main class with the window woooaahhh
     */
    public static JFrame jframe;

    public static void main(String[] args)
    {
        // Ask if they want to access the text or the graphics ooooo
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, welcome to RandomSchop... why are you here?");
        System.out.println("Type \"g\" for the graphical stuff, any other character for the boring text stuff.");
        String input = scanner.nextLine();

        if (input.toLowerCase().equals("g"))
        {
            // Creating jframe, setting properties... run on a different thread
            javax.swing.SwingUtilities.invokeLater(Main::createWindow);

            // Add menu
            Panels.MENU_CARDS_PANEL.setVisible(true);
            jframe.setVisible(true);
        }
        else
        {
            // Print all options
            textThingList.sort(Comparator.comparing(TextThing::getName));
            System.out.println("\nYour Options:");
            System.out.println("--------------------------");

            for (TextThing thing : textThingList)
                System.out.println(thing.getName() + " - " + thing.getDisplayName());

            System.out.println("--------------------------");

            // Ask for pick then go through options
            boolean match = false;
            TextThing textThing = null;
            while (!match)
            {
                System.out.println("\nType the name on the lefthand side of the corresponding thing you'd like to see.");
                input = scanner.nextLine();

                for (TextThing thing : textThingList)
                {
                    if (thing.getName().equals(input))
                    {
                        match = true;
                        textThing = thing;
                    }
                }
            }

            // Execute
            try {
                textThing.execute();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            scanner.close();
        }
    }

    /**
     * Initializes and shows the main window
     */
    private static void createWindow()
    {
        jframe = new JFrame("RandomSchop");
        jframe.setSize(1280, 720);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
