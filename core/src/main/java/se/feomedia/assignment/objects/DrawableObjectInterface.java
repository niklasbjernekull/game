package se.feomedia.assignment.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Inteface to create unity in Drawable objects
 *
 * Created by Niklas on 2015-12-03.
 */
public interface DrawableObjectInterface {
    public void set_image(Sprite in_image);

    public void draw(SpriteBatch batch);

    public void dispose();
}
