package schoperation.RandomSchop.core;

public class RSThing
{
    /**
     * RandomSchop Thing. Idk just some way to make storage easier in MasterList class
     */

    // Name and display name
    private String name;
    private String displayName;

    public RSThing(String name, String displayName)
    {
        this.name = name;
        this.displayName = displayName;
    }

    /**
     * Override this when creating a new one. This method is executed upon button click.
     */
    public void main()
    {

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
