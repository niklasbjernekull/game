package se.feomedia.assignment.objects.text;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

/**
 * Creates animated text
 * Created by Niklas on 2015-12-08.
 */
public class AnimatedText {
    protected int x;
    protected int y;
    protected String textString;
    protected ArrayList<Sprite> textImages;
    protected float size = 1;

    /**
     * Sets coordinates and create Sprite array
     * @param inString
     * @param inX
     * @param inY
     */
    public AnimatedText(String inString, int inX, int inY) {
        textString = inString;
        x = inX;
        y = inY;

        textImages = ImageText.getText8px(textString);
    }

    /**
     * Calculates width for sum of all Sprites
     * @return
     */
    public int getTextWidth() {
        int sum = 0;
        for (TextureRegion img : textImages) {
            sum += img.getRegionWidth();
        }
        return sum;
    }

    /**
     * Finds largest y in array
     * @return
     */
    public int getTextHeight() {
        int yMax = 0;
        for (TextureRegion img : textImages) {
            if(img.getRegionHeight() > yMax)
                yMax = img.getRegionHeight();
        }
        return yMax;
    }

    /**
     * set x value so that center of array is on xValue
     * @param xValue
     */
    public void centerXOn (int xValue) {
        x = xValue - getTextWidth()/2;
    }

    /**
     * Draw all sprites
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        int draw_x = x;
        if(size != 1) {
            for (Sprite img : textImages) {
                img.setPosition(draw_x, y);
                img.draw(batch);
                draw_x += img.getRegionWidth();
            }
        } else {
            for (Sprite img : textImages) {
                img.setPosition(draw_x, y);
                img.setSize((int) (img.getRegionWidth() * size), (int) (img.getRegionHeight() * size));
                img.draw(batch);
                draw_x += img.getRegionWidth();
            }
        }
    }
}
