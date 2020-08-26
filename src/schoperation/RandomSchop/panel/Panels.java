package schoperation.RandomSchop.panel;

import schoperation.RandomSchop.HowOldAreTheyAnyway.HowOldAreTheyAnyway;
import schoperation.RandomSchop.core.RSThing;
import schoperation.RandomSchop.fibonacci.Fibonacci;
import schoperation.RandomSchop.rainbow.Rainbow;

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
}
