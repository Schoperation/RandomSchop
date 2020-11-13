package schoperation.RandomSchop.graphics.rainbow;

import schoperation.RandomSchop.core.RSThing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Rainbow extends RSThing
{
    /**
     * Constantly changes colors wooaahh
     *
     * <p>Created August 22nd 2020</p>
     */

    // Where we show the current rgb of the background
    private JLabel colorText;

    // For changing the speed of the background
    private JSlider speedSlider;
    private final int MIN_SPEED = 0;
    private final int MAX_SPEED = 100;
    private final SliderChange sliderChange;

    public Rainbow(String name, String displayName)
    {
        super(name, displayName);
        this.mainPanel = new JPanel();

        colorText = new JLabel();

        speedSlider = new JSlider(JSlider.HORIZONTAL, MIN_SPEED, MAX_SPEED, 50);
        sliderChange = new SliderChange();
        speedSlider.addChangeListener(sliderChange);
        speedSlider.createStandardLabels(20);
        speedSlider.setMajorTickSpacing(20);
        speedSlider.setMinorTickSpacing(10);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        this.mainPanel.add(colorText);
        this.mainPanel.add(speedSlider);
    }

    @Override
    public void main()
    {
        Color color = new Color(255, 0, 0);
        this.mainPanel.setBackground(color);

        while (this.mainPanel.isVisible())
        {
            color = rainbow(color);
            this.mainPanel.setBackground(color);
            colorText.setText("Current Color: " + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue());

            try
            {
                Thread.sleep(MAX_SPEED - sliderChange.getSpeed());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Apply the next step through the rainbow.
     *
     * First, start off with red. 255 0 0.
     * Add into the green component until 255 255 0.
     * Subtract the red component until 0 255 0.
     * Add into the blue component until 0 255 255.
     * Subtract the green component until 0 0 255.
     * Add into the red component until 255 0 255.
     * Subtract the blue component until 255 0 0.
     * Repeat! Each if else statement will be the above steps.
     *
     * @param color
     * @return color
     */
    private Color rainbow(Color color)
    {
        if (color.getRed() == 255 && color.getGreen() < 255 && color.getBlue() == 0)
            return new Color(255, color.getGreen() + 1, 0);
        else if (color.getRed() <= 255 && color.getRed() > 0 && color.getGreen() == 255 && color.getBlue() == 0)
            return new Color(color.getRed() - 1, 255, 0);
        else if (color.getRed() == 0 && color.getGreen() == 255 && color.getBlue() < 255)
            return new Color(0, 255, color.getBlue() + 1);
        else if (color.getRed() == 0 && color.getGreen() <= 255 && color.getGreen() > 0 && color.getBlue() == 255)
            return new Color(0, color.getGreen() - 1, 255);
        else if (color.getRed() < 255 && color.getGreen() == 0 && color.getBlue() == 255)
            return new Color(color.getRed() + 1, 0, 255);
        else
            return new Color(255, 0, color.getBlue() - 1);
    }

    /**
     * Event listener for the speed slider
     */
    private static class SliderChange implements ChangeListener
    {
        private int speed;

        public void stateChanged(ChangeEvent e)
        {
            JSlider slider = (JSlider) e.getSource();

            // Did the value actually change
            if (slider.getValueIsAdjusting())
                speed = slider.getValue();
        }

        public int getSpeed()
        {
            return this.speed;
        }
    }
}
