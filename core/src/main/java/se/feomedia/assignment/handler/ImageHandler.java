package se.feomedia.assignment.handler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Map;

import se.feomedia.assignment.constants.ImageConstants;

/**
 * Handles creation of common textures
 * Created by Niklas on 2015-12-03.
 */
public class ImageHandler {
    private Map<String, Texture> imageMap;

    /**
     * creates textures and puts in map
     */
    public ImageHandler() {
        imageMap = new HashMap<String, Texture>();

        imageMap.put(ImageConstants.PLAYER, new Texture(Gdx.files.internal("ship.png")));
        imageMap.put(ImageConstants.PLAYER_LEFT, new Texture(Gdx.files.internal("ship_left.png")));
        imageMap.put(ImageConstants.PLAYER_RIGHT, new Texture(Gdx.files.internal("ship_right.png")));

        imageMap.put(ImageConstants.ENEMY1, new Texture(Gdx.files.internal("enemy1.png")));
        imageMap.put(ImageConstants.ENEMY2, new Texture(Gdx.files.internal("enemy2.png")));
        imageMap.put(ImageConstants.ENEMY3, new Texture(Gdx.files.internal("enemy3.png")));
        imageMap.put(ImageConstants.PROJECTILE, new Texture(Gdx.files.internal("shot.png")));

        imageMap.put(ImageConstants.SPLASH, new Texture(Gdx.files.internal("splash.png")));
        imageMap.put(ImageConstants.MENU, new Texture(Gdx.files.internal("menu.png")));
        imageMap.put(ImageConstants.ABOUT, new Texture(Gdx.files.internal("about.png")));
        imageMap.put(ImageConstants.CONTROLS, new Texture(Gdx.files.internal("controls.png")));
    }

    /**
     * retrieves texture from map
     * @param name
     * @return
     */
    public Texture getTexture (String name) {
        if(!imageMap.containsKey(name))
            return null;

        return imageMap.get(name);

    }

    /**
     * dispose of all textures
     */
    public void dispose() {
        for(Map.Entry<String, Texture> entry : imageMap.entrySet()) {
            Texture temp = entry.getValue();
            temp.dispose();
        }
    }

    /*private Map<String, Sprite> imageMap;
    private Texture text8px;

    public ImageHandler() {
        imageMap = new HashMap<String, Sprite>();

        imageMap.put(ImageConstants.PLAYER, new Sprite(new Texture(Gdx.files.internal("ship.png"))));
        imageMap.put(ImageConstants.PLAYER_LEFT, new Sprite(new Texture(Gdx.files.internal("ship_left.png"))));
        imageMap.put(ImageConstants.PLAYER_RIGHT, new Sprite(new Texture(Gdx.files.internal("ship_right.png"))));

        imageMap.put(ImageConstants.ENEMY1, new Sprite(new Texture(Gdx.files.internal("enemy1.png"))));
        imageMap.put(ImageConstants.ENEMY2, new Sprite(new Texture(Gdx.files.internal("enemy2.png"))));
        imageMap.put(ImageConstants.ENEMY3, new Sprite(new Texture(Gdx.files.internal("enemy3.png"))));
        imageMap.put(ImageConstants.PROJECTILE, new Sprite(new Texture(Gdx.files.internal("shot.png"))));

        imageMap.put(ImageConstants.SPLASH, new Sprite(new Texture(Gdx.files.internal("splash.png"))));
        imageMap.put(ImageConstants.MENU, new Sprite(new Texture(Gdx.files.internal("menu.png"))));
        imageMap.put(ImageConstants.ABOUT, new Sprite(new Texture(Gdx.files.internal("about.png"))));
        imageMap.put(ImageConstants.CONTROLS, new Sprite(new Texture(Gdx.files.internal("controls.png"))));

        text8px = new Texture(Gdx.files.internal("text_black_8px2.png"));
    }

    public Texture getText8px() {
        return text8px;
    }

    public Sprite getSprite (String name) {
        if(!imageMap.containsKey(name))
            return null;

        return imageMap.get(name);

    }

    public void dispose() {
        for(Map.Entry<String, Sprite> entry : imageMap.entrySet()) {
            Sprite temp = entry.getValue();
            temp.getTexture().dispose();
        }
    }*/
}
