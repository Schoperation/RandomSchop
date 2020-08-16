package schoperation.RandomSchop.core;

import javax.swing.*;

public class RSThing
{
    /**
     * RandomSchop Thing. Idk just some way to make storage easier in MasterList class
     */

    // Name and display name
    private String name;
    private String displayName;

    // The main panel/view associated with this thing. Of course, there may be multiple panels... soon
    // TODO add currentlyDisplayedPanel???
    private JPanel mainPanel;

    public RSThing(String name, String displayName)
    {
        this.name = name;
        this.displayName = displayName;
    }

    /** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *  !!! Override this when creating a new one. This method is executed upon button click. !!!
     *  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    public void main()
    {

    }

    /**
     * Contains some preemptive code that fires on every button press but would be redundant in every main() method. This is what is actually called, thus main() is called here.
     */
    public void setup()
    {
        Main.toggleDefaultPanel(false);
        // TODO test panel works, as long as it's added to the jframe (duh) and is set to visible
        this.main();
        Main.toggleDefaultPanel(true);
    }

    /**
     * Returns the name used by the program
     * @return string name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns the name that is displayed to the user
     * @return string displayName
     */
    public String getDisplayName()
    {
        return this.displayName;
    }
}
