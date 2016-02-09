package se.feomedia.assignment.handler;

import java.util.ArrayList;

import se.feomedia.assignment.constants.GameConstants;
import se.feomedia.assignment.objects.Enemy;

/**
 * Does calculations used with Enemy
 *
 * Created by Niklas on 2015-12-06.
 */
public class EnemyHandler {

    /**
     * Find smallest X in Enemy array
     * @param enemies
     * @return
     */
    public static int getSmallestX(ArrayList<Enemy> enemies) {
        int smallestX = GameConstants.WINDOW_WIDTH;
        for(Enemy badGuy: enemies) {
            if(badGuy.x < smallestX)
                smallestX = badGuy.x;
        }
        return smallestX;
    }

    /**
     * Find smallest Y in Enemy array
     * @param enemies
     * @return
     */
    public static int getSmallestY(ArrayList<Enemy> enemies) {
        int smallestY = GameConstants.WINDOW_HEIGHT;
        for(Enemy badGuy: enemies) {
            if(badGuy.y < smallestY)
                smallestY = badGuy.y;
        }
        return smallestY;
    }

    /**
     * Find largest X in Enemy array
     * @param enemies
     * @return
     */
    public static int getLargestX(ArrayList<Enemy> enemies) {
        int largestX = 0;
        for(Enemy badGuy: enemies) {
            if(badGuy.x > largestX)
                largestX = badGuy.x;
        }
        return largestX;
    }

    /*public static int getStepsPerRow(ArrayList<Enemy> enemies) {
        return GameConstants.WINDOW_WIDTH/width;
    }*/

}
