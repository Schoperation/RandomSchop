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
        // Creating jframe, setting properties... run on a different thread
        javax.swing.SwingUtilities.invokeLater(Main::createWindow);

        // Add menu
        //Panels.changePanel(Panels.MENU_PANEL);
        Panels.MENU_CARDS_PANEL.setVisible(true);
        jframe.setVisible(true);
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

    /*
        TODO Dumb ideas to add

        - Simple calculator
        - Model solar system
        - Calculate pi
        - Fibbonacci (import from haskell????)
        - Calculate state/federal taxes
        - Nuh uh uh! You didn't say the magic word!
        - Epilepsy warning
     */
}
