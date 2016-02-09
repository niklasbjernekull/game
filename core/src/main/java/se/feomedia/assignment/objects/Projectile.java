package se.feomedia.assignment.objects;

import com.badlogic.gdx.graphics.Texture;

import se.feomedia.assignment.constants.ImageConstants;

/**
 * Handles object projectile
 *
 * Created by Niklas on 2015-12-03.
 */
public class Projectile extends MovableObject{

    public Projectile (int in_x, int in_y, int in_width, int in_height, Texture in_image) {
        super(in_x, in_y, in_image, in_width, in_height);
    }

    /**
     * Move with speed of 10
     */
    public void move() {
        y+=10;
    }
}
