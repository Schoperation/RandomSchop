package schoperation.RandomSchop.core;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class Main
{
    /**
     * The main class with the window woooaahhh
     */
    public static JFrame jframe;
    public static JPanel defaultJpanel;

    public static void main(String[] args)
    {
        // Creating jframe, setting properties
        createWindow();

        // Creating jpanel
        createPanel();
    }

    /**
     * Initializes and shows the main window
     */
    private static void createWindow()
    {
        jframe = new JFrame("RandomSchop");
        jframe.setSize(640, 480);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
    }

    /**
     * Initializes panel, where the actual content is
     */
    private static void createPanel()
    {
        // Create new panel and change the background color
        defaultJpanel = new JPanel(new GridLayout(0, 3));
        defaultJpanel.setBackground(Color.pink);

        // Welcome text
        JLabel filler = new JLabel(" ");
        JLabel filler1 = new JLabel(" ");
        JLabel welcomeText = new JLabel("Click on a button to proceed...");

        defaultJpanel.add(filler);
        defaultJpanel.add(welcomeText);
        defaultJpanel.add(filler1);

        // Create buttons
        // Iterate through list of... things
        Iterator<RSThing> iterator = MasterList.masterList.iterator();

        while (iterator.hasNext())
        {
            // Create button
            RSThing thing = iterator.next();
            JButton button = new JButton(thing.getDisplayName());
            button.addActionListener(e -> thing.setup());

            defaultJpanel.add(button);
        }

        // Add panel with all stuff to jframe
        jframe.add(defaultJpanel);
        defaultJpanel.setVisible(true);
    }

    /**
     * Method to toggle the showing of the default menu panel, in order to show/hide other things
     * @param show
     */
    public static void toggleDefaultPanel(boolean show)
    {
        defaultJpanel.setVisible(show);
    }
}
