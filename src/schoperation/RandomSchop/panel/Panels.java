package schoperation.RandomSchop.panel;

import schoperation.RandomSchop.core.Main;

import javax.swing.*;

public class Panels
{
    /**
     * Class for showing, unshowing, and storing cookie cutter panels.
     */

    // Menu panel, first panel user will see
    public static final MenuPanel MENU_PANEL = new MenuPanel();

    // Console panel, since most scripts will just deal with simple text I/O
    // TODO console panel

    // Panel we are currently showing
    private static JPanel currentlyShownPanel = null;

    /**
     * Changes the panel currently shown on the window.
     * @param newPanel
     */
    public static void changePanel(JPanel newPanel)
    {
        if (currentlyShownPanel == null)
        {
            // Put in the panel, no need to remove
            Main.jframe.getContentPane().add(newPanel);
            newPanel.setVisible(true);
            currentlyShownPanel = newPanel;
        }
        else if (!currentlyShownPanel.equals(newPanel))
        {
            // Remove old panel
            currentlyShownPanel.setVisible(false);
            Main.jframe.getContentPane().remove(currentlyShownPanel);

            // Add new panel
            Main.jframe.getContentPane().add(newPanel);
            newPanel.setVisible(true);
            currentlyShownPanel = newPanel;
            Main.jframe.getContentPane().repaint();
        }
        else
            System.out.println("This is already the currently shown panel!");
    }
}
