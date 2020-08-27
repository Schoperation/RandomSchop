package schoperation.RandomSchop.panel;

import schoperation.RandomSchop.HowOldAreTheyAnyway.HowOldAreTheyAnyway;
import schoperation.RandomSchop.core.Main;
import schoperation.RandomSchop.core.RSThing;
import schoperation.RandomSchop.fibonacci.Fibonacci;
import schoperation.RandomSchop.rainbow.Rainbow;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Panels
{
    /**
     * Class for showing, unshowing, and storing cookie cutter panels.
     */

    // Console panel, since most scripts will just deal with simple text I/O
    public static final ConsolePanel CONSOLE_PANEL = new ConsolePanel();

    // Initialized all scripts and panels here, to be added recursively in MenuCardsPanel
    protected static final List<RSThing> MASTER_LIST = Arrays.asList(
            new DummyThing("dummy", "Dummy Thing"),
            new HowOldAreTheyAnyway("how_old", "How Old Are They, Anyway?"),
            new Fibonacci("fibonacci", "Fibonacci Numbers"),
            new Rainbow("rainbow", "Rainbow Background")
    );

    // Menu panel, first panel user will see
    public static final MenuCardsPanel MENU_CARDS_PANEL = new MenuCardsPanel();

    /**
     * Change main panel with a display name
     * @param displayName
     */
    public static void changePanelWithDisplayName(String displayName)
    {
        // Show the panel
        CardLayout cl = (CardLayout)(Panels.MENU_CARDS_PANEL.getLayout());
        cl.show(Panels.MENU_CARDS_PANEL, displayName);

        // Find the object via index
        //String displayName = displayName;
        int index = Integer.parseInt(displayName.substring(displayName.length() - 1));

        // Clear main console panel, along with the script's personal one if applicable
        Panels.CONSOLE_PANEL.clear();
        if (Panels.MASTER_LIST.get(index).getMainPanel() instanceof ConsolePanel)
            ((ConsolePanel) Panels.MASTER_LIST.get(index).getMainPanel()).clear();

        // Execute correct main method... Do it on seperate thread, not event dispatching thread
        Thread thread = new Thread(() -> Panels.MASTER_LIST.get(index).setup());
        thread.start();
    }

    /**
     * Change main panel with a display name
     * @param name
     */
    // TODO AHHHHHHHHHHHHH
    public static void changePanel(String name)
    {
        // Find the object via index
        int index = Integer.parseInt(name.substring(name.length() - 1));

        // Show the panel. Go to the first one to reset the "queue"
        CardLayout cl = (CardLayout)(Panels.MENU_CARDS_PANEL.getLayout());
        cl.first(Panels.MENU_CARDS_PANEL);
        int i;
        for (i = 0; i < index; i++)
            cl.next(Panels.MENU_CARDS_PANEL);

        // Clear main console panel, along with the script's personal one if applicable
        Panels.CONSOLE_PANEL.clear();
        if (Panels.MASTER_LIST.get(index).getMainPanel() instanceof ConsolePanel)
            ((ConsolePanel) Panels.MASTER_LIST.get(index).getMainPanel()).clear();

        // Execute correct main method... Do it on seperate thread, not event dispatching thread
        Thread thread = new Thread(() -> Panels.MASTER_LIST.get(index).setup());
        thread.start();
    }
}
