package schoperation.RandomSchop.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class Main
{
    /**
     * The main class with the window woooaahhh
     */
    public static JFrame jframe;
    public static JPanel jpanel;

    public static void main(String[] args)
    {
        // Creating jframe, setting properties
        createWindow();

        // Create new panel and change the background color
        jpanel = new JPanel();
        jpanel.setBackground(Color.pink);

        // Welcome text
        JLabel welcomeText = new JLabel("Click on a button to proceed...");
        jpanel.add(welcomeText);

        // Create buttons
        //addButtons();

        // Iterate through list
        Iterator<RSThing> iterator = MasterList.masterList.iterator();

        while (iterator.hasNext())
        {
            // Create button
            RSThing thing = iterator.next();
            JButton button = new JButton(thing.getDisplayName());
            button.addActionListener(e -> thing.main());

            jpanel.add(button);
        }

        // Add panel with all stuff to jframe
        jframe.add(jpanel);
    }

    /**
     * Initializes and shows the main window, along with the panel to add
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
     * All purpose method to add buttons linking to each script/thing/god-knows-what
     */
    private static void addButtons()
    {
        // Iterate through list
        Iterator<RSThing> iterator = MasterList.masterList.iterator();

        while (iterator.hasNext())
        {
            // Create button
            RSThing thing = iterator.next();
            JButton button = new JButton(thing.getDisplayName());
            button.addActionListener(e -> thing.main());

            jpanel.add(button);
        }
    }
}
