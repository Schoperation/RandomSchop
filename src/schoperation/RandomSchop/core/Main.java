package schoperation.RandomSchop.core;

import schoperation.RandomSchop.panel.Panels;

import javax.swing.*;

public class Main
{
    /**
     * The main class with the window woooaahhh
     */
    public static JFrame jframe;

    public static void main(String[] args)
    {
        // Creating jframe, setting properties
        createWindow();

        // Set initial panel
        Panels.changePanel(Panels.MENU_PANEL);
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
}
