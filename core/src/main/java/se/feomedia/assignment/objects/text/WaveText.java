package se.feomedia.assignment.objects.text;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Animates text into waves
 * Created by Niklas on 2015-12-08.
 */
public class WaveText extends AnimatedText{
    private int max = 10;
    private int speed = 1;
    private int animation = 0;

    /**
     * Constructor, set variables
     * @param inString
     * @param inX
     * @param inY
     * @param inMax
     * @param inSpeed
     * @param inSize
     */
    public WaveText(String inString, int inX, int inY, int inMax, int inSpeed, float inSize) {
        super(inString, inX, inY);
        max = inMax;
        speed = inSpeed;
        size = inSize;
    }

    /**
     * No size constructor
     * @param inString
     * @param inX
     * @param inY
     * @param inMax
     * @param inSpeed
     */
    public WaveText(String inString, int inX, int inY, int inMax, int inSpeed) {
        super(inString, inX, inY);
        max = inMax;
        speed = inSpeed;
    }

    /*
    public WaveText(String inString, int inX, int inY) {
        super(inString, inX, inY);
    }*/

    /**
     * Change speed
     * 0 = no wave
     * @param newSpeed
     */
    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    /**
     * Get height of text
     * @return
     */
    @Override
    public int getTextHeight() {
        return max;
    }

    /**
     * set x value so that center of array is on xValue
     * @param xValue
     */
    @Override
    public void centerXOn (int xValue) {
        x = (int) (xValue - (getTextWidth()*size)/2);
    }

    /**
     * draw text
     * @param batch
     */
    @Override
    public void draw(SpriteBatch batch) {
        //Graphics2D g2d = (Graphics2D) g.create();
        int draw_x = x;
        int draw_y = animation;
        Sprite aSprite;

        for (Sprite img : textImages) {
            if(draw_y > max) {
                img.setPosition(draw_x, y + max*2 - draw_y);
                img.setSize((int)(img.getRegionWidth()*size), (int)(img.getRegionHeight()*size));
                img.draw(batch);
            } else {
                img.setPosition(draw_x, y + draw_y);
                img.setSize((int)(img.getRegionWidth()*size), (int)(img.getRegionHeight()*size));
                img.draw(batch);
            }
            draw_x += img.getRegionWidth()*size;
            draw_y += speed;
            draw_y = draw_y%(max*2);
        }

        animation += speed;
        if(animation > max*2) {
            animation = 0;
        }
    }

}
