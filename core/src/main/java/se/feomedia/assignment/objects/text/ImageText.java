package se.feomedia.assignment.objects.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Text from text8px image
 *
 * The offset code in getText is used from:
 * http://stackoverflow.com/questions/24501813/java-use-png-image-as-font-java-awt-graphics
 * and converted to libGDX textures
 *
 * Created by Niklas on 2015-12-08.
 */
public class ImageText {

    /**
     * Creates texture for black text
     * Returns Sprite array created by getText
     * @param textToConvert
     * @return
     */
    public static ArrayList<Sprite> getText8px(String textToConvert) {
        Texture text8px = new Texture(Gdx.files.internal("text_black_8px2.png"));
        return getText(text8px, textToConvert);
    }

    /**
     * Creates texture for red text
     * Returns Sprite array created by getText
     * @param textToConvert
     * @return
     */
    public static ArrayList<Sprite> getText8pxRed(String textToConvert) {
        Texture text8px = new Texture(Gdx.files.internal("text_red_8px2.png"));
        return getText(text8px, textToConvert);
    }

    /**
     * Creates Texture regions depending on offset from char
     * @param text8px
     * @param textToConvert
     * @return
     */
    private static ArrayList<Sprite> getText(Texture text8px, String textToConvert) {
        ArrayList<Sprite> images = new ArrayList<>(25);

        for (char c : textToConvert.toCharArray()) {
            c = Character.toUpperCase(c);
            int smudge = 1;
            int offset = -1;
            if (c >= 48 && c <= 57) {
                offset = c - 48;
            } else if (c >= 65 && c <= 90) {
                offset = c - 65 + 10;
            } else if (c == 32) {
                offset = 49;
                smudge = 2;
            }

            if (offset >= 0) {
                images.add(new Sprite(new TextureRegion(text8px, (offset * 8) + smudge, 0, 8 - smudge, 8)));
            }
        }

        return images;
    }
}
