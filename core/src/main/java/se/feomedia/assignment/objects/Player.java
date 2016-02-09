package se.feomedia.assignment.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import se.feomedia.assignment.constants.ImageConstants;

/**
 * Handles player drawing and moving
 *
 * Created by Niklas on 2015-12-03.
 */
public class Player extends MovableObject{

    private double momentum = 0;
    private double movement = 0.5;
    private int maxMovement = 10;
    private Sprite image_straight;
    private Sprite image_left;
    private Sprite image_right;

    /**
     * Constructor sets variable and texture
     * @param in_x
     * @param in_y
     * @param in_width
     * @param in_height
     * @param in_image
     * @param in_image_left
     * @param in_image_right
     */
    public Player (int in_x, int in_y, int in_width, int in_height, Texture in_image, Texture in_image_left, Texture in_image_right) {
        super(in_x, in_y, in_image, in_width, in_height);
        image_straight = new Sprite(in_image);
        image_left = new Sprite(in_image_left);
        image_right = new Sprite(in_image_right);
    }

    /**
     * Calculate momentum while left arrow is pushed
     */
    public void moveLeft() {
        if(momentum == 0)
            set_image(image_left);

        momentum -= movement;
        if(momentum < -maxMovement)
            momentum = -maxMovement;

        set_x(x + (int)momentum);
    }

    /**
     * Calculate momentum while right arrow is pushed
     */
    public void moveRight() {
        if(momentum == 0)
            set_image(image_right);

        momentum += movement;
        if(momentum > maxMovement)
            momentum = maxMovement;

        set_x(x + (int)momentum);
    }

    /**
     * Calculate momentum while no button is pushed
     */
    public void stand_still() {
        if(momentum == 0) {
            set_image(image_straight);
        } else if(momentum < 0) {
            momentum += movement;
            set_x(x + (int)momentum);
        } else if(momentum > 0) {
            momentum -= movement;
            set_x(x + (int)momentum);
        }
    }
}
