package se.feomedia.assignment.objects;

import com.badlogic.gdx.graphics.Texture;

/**
 * Handles drawable object enemy
 *
 * Created by Niklas on 2015-12-03.
 */
public class Enemy extends MovableObject{
    public boolean goingRight;
    public boolean goingDown = false;
    private int jumpGoalX = 0;
    private int jumpGoalY = 0;

    /**
     * Constructor sends variable data to parent
     * Sets direction
     * @param in_x
     * @param in_y
     * @param in_width
     * @param in_height
     * @param in_name
     * @param in_goingRight
     */
    public Enemy (int in_x, int in_y, int in_width, int in_height, Texture in_name, boolean in_goingRight) {
        super(in_x, in_y, in_name, in_width, in_height);
        goingRight = in_goingRight;
    }

    /**
     * No direction constructor
     * @param in_x
     * @param in_y
     * @param in_width
     * @param in_height
     * @param in_name
     */
    public Enemy (int in_x, int in_y, int in_width, int in_height, Texture in_name) {
        super(in_x, in_y, in_name, in_width, in_height);
        goingRight = true;
    }

    /**
     * Sets goal for next jump
     * Is needed so that enemy doesn't wander
     * @param in_value
     */
    public void setJumpGoal (int in_value) {
        if(goingDown) {
            jumpGoalX = x;
            jumpGoalY = y - in_value;
        } else {
            if(goingRight)
                jumpGoalX = x + in_value;
            else
                jumpGoalX = x - in_value;
            jumpGoalY = y;
        }

    }

    /**
     * move to set jump goal
     */
    public void goToJumpGoal() {
        x = jumpGoalX;
        y = jumpGoalY;

        if(goingDown) {
            goingRight = !goingRight;
            goingDown = false;
        }
    }

    /**
     * Move enemy in desired direction
     * @param stepValue
     */
    public void move(int stepValue) {
        if(goingDown) {
            y -= stepValue;
        } else {
            if(goingRight)
                x += stepValue;
            else
                x -= stepValue;
        }
    }
}
