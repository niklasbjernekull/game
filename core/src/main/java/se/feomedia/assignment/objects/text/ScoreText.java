package se.feomedia.assignment.objects.text;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Handles score text and numbers to be able to not create new Sprites everytime
 * score is updated
 * Created by Niklas on 2015-12-09.
 */
public class ScoreText {
    ArrayList<Sprite> score;
    ArrayList<Sprite> numbers;

    /**
     * Constructor creates sprite array
     */
    public ScoreText() {
        score = ImageText.getText8pxRed("SCORE  ");
        numbers = ImageText.getText8pxRed("0123456789");
    }

    /**
     * Draws score and score numbers bu using existing arrays
     * @param batch
     * @param in_score
     * @param x
     * @param y
     * @param size
     */
    public void draw(SpriteBatch batch, String in_score, int x, int y, int size) {
        int draw_x = x;
        for (Sprite img : score) {
            img.setPosition(draw_x, y);
            img.setSize((int)(img.getRegionWidth()*size), (int)(img.getRegionHeight()*size));
            img.draw(batch);
            draw_x += img.getRegionWidth()*size;
        }

        Sprite temp;
        for(int i=0; i < in_score.length(); i++) {
            char c = in_score.charAt(i);
            temp = numbers.get(Character.getNumericValue(c));
            if (temp != null) {
                temp.setPosition(draw_x, y);
                temp.setSize((int) (temp.getRegionWidth() * size), (int) (temp.getRegionHeight() * size));
                temp.draw(batch);
                draw_x += temp.getRegionWidth() * size;
            }
        }
    }
}
