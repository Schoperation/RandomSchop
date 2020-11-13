package schoperation.RandomSchop.graphics.panel;

import schoperation.RandomSchop.core.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MenuCardsPanel extends JPanel
{
    /**
     * This consists of two panels; the cards panel itself (that's the class), and another panel constructed, which consists of a dropdown menu and welcome text.
     */
    public MenuCardsPanel()
    {
        // Set layout and background color
        this.setLayout(new CardLayout());

        // Add the dropdown menu
        JPanel dropDownMenuPanel = new JPanel();
        dropDownMenuPanel.setBackground(Color.LIGHT_GRAY);
        JLabel welcomeText = new JLabel("Use the dropdown to choose a thing: ");
        dropDownMenuPanel.add(welcomeText, BorderLayout.PAGE_START);

        JComboBox<String> dropDownMenu = new JComboBox<>();

        // Add menu items. We'll also add the panels as cards to this panel while we're iterating.
        for (int i = 0; i < Panels.MASTER_LIST.size(); i++)
        {
            // The index is appended to the end of the string in order to make finding the script easier
            dropDownMenu.addItem(Panels.MASTER_LIST.get(i).getDisplayName() + " " + i);

            // TODO might need to redo this once i implement things with multiple panels
            for (JPanel panel : Panels.MASTER_LIST.get(i).getAllPanels())
                this.add(Panels.MASTER_LIST.get(i).getName(), panel);
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
                Panels.changePanel((String) e.getItem());
        }
    }
}
