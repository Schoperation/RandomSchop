package schoperation.RandomSchop.core;

import schoperation.RandomSchop.panel.Panels;

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
        JPanel test = new JPanel();
        JLabel label = new JLabel("hello there");
        test.add(label);
        Panels.changePanel(test);
        // TODO test panel works, as long as it's added to the jframe (duh) and is set to visible
        this.main();
        Panels.changePanel(Panels.MENU_PANEL);
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
