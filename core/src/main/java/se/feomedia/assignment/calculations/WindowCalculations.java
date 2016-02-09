package se.feomedia.assignment.calculations;

import javax.swing.WindowConstants;

import se.feomedia.assignment.constants.GameConstants;

/**
 * Created by Niklas on 2015-12-03.
 */
public class WindowCalculations {

    /**
     * Calculates the width in consideration to the height.
     * No cheating with different resolutions!
     * @param in_height
     * @return width = height * 150%
     */
    public static int calculate_usable_width(int in_height) {
        return (int) (in_height*1.5);
    }

    /**
     * Calculates the height in consideration to the width.
     * No cheating with different resolutions!
     * @param in_width
     * @return height = width * 66.66666666666667... sigh
     */
    public static int calculate_usable_height(int in_width) {
        float temp = in_width*((float)2/3);
        return (int)temp; //alt in_width*(GameConstants.WINDOW_HEIGHT / GameConstants.WINDOW_WIDTH);
    }

    /**
     * Calculate which of the resolutions are the smallest
     * @param in_width
     * @param in_height
     * @return true if width is smallest, if height is smallest false is returned
     */
    public static boolean is_width_smallest(int in_width, int in_height) {
        return in_width < (in_height * 1.5);
    }
}
