package schoperation.RandomSchop.panel;

import schoperation.RandomSchop.HowOldAreTheyAnyway.HowOldAreTheyAnyway;
import schoperation.RandomSchop.core.Main;
import schoperation.RandomSchop.core.RSThing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.List;

public class MenuCardsPanel extends JPanel
{
    /**
     * This consists of two panels; the cards panel itself (that's the class), and another panel constructed, which consists of a dropdown menu and welcome text.
     */

    /**
     * Initialized all scripts and panels here, to be added recursively in MenuCardsPanel
     */
    public static List<RSThing> masterList = Arrays.asList(
            new HowOldAreTheyAnyway("howold", "How Old Are They, Anyway?"),
            new DummyThing("dummy", "Dummy Thing")
    );

    public MenuCardsPanel()
    {
        // Set layout and background color
        this.setLayout(new CardLayout());
        //this.setBackground(Color.pink);

        // Add the dropdown menu
        JPanel dropDownMenuPanel = new JPanel();
        dropDownMenuPanel.setBackground(Color.yellow.darker());
        JLabel welcomeText = new JLabel("Use the dropdown to choose a thing: ");
        dropDownMenuPanel.add(welcomeText, BorderLayout.PAGE_START);

        JComboBox<String> dropDownMenu = new JComboBox<>();

        // Add menu items. We'll also add the panels as cards to this panel while we're iterating.
        for (RSThing thing : masterList)
        {
            dropDownMenu.addItem(thing.getDisplayName());

            // TODO might need to redo this once i implement things with multiple panels
            for (JPanel panel : thing.getAllPanels())
                this.add(thing.getDisplayName(), panel);
        }

        dropDownMenu.setEditable(false);
        dropDownMenu.addItemListener(new DropDownListener());
        dropDownMenuPanel.add(dropDownMenu);

        // The two panels are now complete. Add them to the Jframe
        Main.jframe.getContentPane().add(dropDownMenuPanel, BorderLayout.PAGE_START);
        Main.jframe.getContentPane().add(this, BorderLayout.CENTER);
    }

    /**
     * Event listener for the dropdown menu (when it changes, the method here fires)
     */
    private static class DropDownListener implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent e)
        {
            if (e.getStateChange() == ItemEvent.SELECTED)
            {
                CardLayout cl = (CardLayout)(Panels.MENU_CARDS_PANEL.getLayout());
                cl.show(Panels.MENU_CARDS_PANEL, (String) e.getItem());
            }
        }
    }
}
