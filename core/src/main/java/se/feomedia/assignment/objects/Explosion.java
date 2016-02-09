package se.feomedia.assignment.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Draws animated object explosion
 *
 * Created by Niklas on 2015-12-09.
 */
public class Explosion {
    private ArrayList<Sprite> images;
    private int index = 0;
    private int x, y, width, height;

    /**
     * Constructor sets variables
     * and creates textures
     * @param in_x
     * @param in_y
     * @param in_width
     * @param in_height
     */
    public Explosion (int in_x, int in_y, int in_width, int in_height) {
        x = in_x;
        y = in_y;
        width = in_width;
        height = in_height;
        images = new ArrayList<>();
        images.add(new Sprite(new Texture(Gdx.files.internal("explosion1.png"))));
        images.add(new Sprite(new Texture(Gdx.files.internal("explosion2.png"))));
        images.add(new Sprite(new Texture(Gdx.files.internal("explosion3.png"))));
        images.add(new Sprite(new Texture(Gdx.files.internal("explosion4.png"))));
        images.add(new Sprite(new Texture(Gdx.files.internal("explosion5.png"))));
        images.add(new Sprite(new Texture(Gdx.files.internal("explosion6.png"))));
    }

    /**
     * if animation sequence is done
     * @return
     */
    public boolean isDead() {
        if(index >= images.size())
            return true;

        return false;
    }

    /**
     * Draw current image decided by index
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        if(index >= images.size())
            return;

        Sprite temp = images.get(index);
        temp.setPosition(x, y);
        temp.setSize(width, height);
        temp.draw(batch);

        index++;
    }

    /**
     * Dispose all textures
     */
    public void dispose() {
        for(Sprite img: images)
            img.getTexture().dispose();
    }
}
