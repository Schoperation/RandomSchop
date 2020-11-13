package schoperation.RandomSchop.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TextThing
{
    /*
        Most text snippets won't deal with this class directly, this is to help link the Main class with the rest of the code
        Wooohoooooo
     */
    private String name;
    private String displayName;
    private Object obj;

    public TextThing(String name, String displayName, Object main)
    {
        this.name = name;
        this.displayName = displayName;
        this.obj = main;
    }

    /**
     * Returns computer name for this snippet.
     * @return name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns display name for this snippet.
     * @return displayName
     */
    public String getDisplayName()
    {
        return this.displayName;
    }

    /**
     * Runs this snippet's main function.
     */
    public void execute() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
    {
        Method mainMethod = obj.getClass().getDeclaredMethod("main");
        mainMethod.invoke(obj);
    }
}
