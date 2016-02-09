package se.feomedia.assignment.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Abstract class to handle computations for all children
 * Created by Niklas on 2015-12-03.
 */
public abstract class DrawableObject implements DrawableObjectInterface {
    public int x;
    public int y;
    public Sprite image;
    public int width;
    public int height;

    /**
     * Constructor sets variables and Sprite
     * @param in_x
     * @param in_y
     * @param in_image
     * @param in_width
     * @param in_height
     */
    public DrawableObject(int in_x, int in_y, Texture in_image, int in_width, int in_height) {
        x = in_x;
        y = in_y;
        image = new Sprite(in_image);
        width = in_width;
        height = in_height;
    }

    /**
     * Change Sprites
     * @param in_image
     */
    public void set_image(Sprite in_image) {
        image = in_image;
    }

    /**
     * Draw Sprite
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        image.setSize(width, height);
        image.setPosition(x, y);
        image.draw(batch);
    }

    /**
     * Draw Sprite moved with offset
     * @param batch
     * @param off_x
     * @param off_y
     */
    public void drawWithOffset(SpriteBatch batch, int off_x, int off_y) {
        image.setSize(width, height);
        image.setPosition(x+off_x, y+off_y);
        image.draw(batch);
    }

    /**
     * Dispose of Sprite
     */
    public void dispose() {
        image.getTexture().dispose();
    }
}
