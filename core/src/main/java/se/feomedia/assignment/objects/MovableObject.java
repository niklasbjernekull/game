package se.feomedia.assignment.objects;

import com.badlogic.gdx.graphics.Texture;

/**
 * Implements methods commonly used by MovableObjects
 * Created by Niklas on 2015-12-03.
 */
public abstract class MovableObject extends DrawableObject implements MovableObjectInterface{

    public MovableObject(int in_x, int in_y, Texture in_image, int in_width, int in_height) {
        super(in_x, in_y, in_image, in_width, in_height);
    }

    public void set_x(int in_x){
        x = in_x;
    }

    public void set_y(int in_y) {
        y = in_y;
    }

    public void set_width(int in_width) { width = in_width; }

    public void set_height(int in_height) {
        height = in_height;
    }
}
