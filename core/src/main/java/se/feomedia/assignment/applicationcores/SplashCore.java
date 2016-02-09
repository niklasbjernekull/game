package se.feomedia.assignment.applicationcores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import se.feomedia.assignment.constants.GameConstants;
import se.feomedia.assignment.constants.ImageConstants;
import se.feomedia.assignment.handler.ImageHandler;
import se.feomedia.assignment.objects.text.WaveText;

/**
 * Handles drawing of Logo/Intro screen
 * Created by Niklas on 2015-12-08.
 */
public class SplashCore implements CoreInterface {

    private boolean changeState = false;
    private long startTime;
    private long waitBeforeUsingControls = 1000; //ms
    private boolean useButtons = false;
    private WaveText waveText;
    private Texture image;

    /**
     * sets current time to be used for control blocking
     * creates image objects
     * @param imageHandler
     */
    public SplashCore (ImageHandler imageHandler) {
        startTime = System.currentTimeMillis();
        waveText = new WaveText("Press Space To Start", 0, 50, 15, 1, 2);
        waveText.centerXOn(GameConstants.WINDOW_WIDTH/2);
        image  = imageHandler.getTexture(ImageConstants.SPLASH);
    }

    /**
     * handles controls to change stat and core
     * is blocked by timer for 1000ms to force
     * user to enjoy this screen
     */
    @Override
    public void handle_controls() {
        if(!useButtons) {
            if(System.currentTimeMillis() - startTime >= waitBeforeUsingControls)
                useButtons = true;
            else
                return;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            changeState = true;
        }
    }

    /**
     * not used
     * @param pauseIt
     */
    @Override
    public void pause(boolean pauseIt) {
        
    }

    /**
     * returns true if state change
     * @return
     */
    @Override
    public String calculate() {
        if(changeState)
            return GameConstants.APPLICATION_MENU;

        return null;
    }

    /**
     * draws image objects
     * @param batch
     * @param imageHandler
     */
    @Override
    public void draw(SpriteBatch batch, ImageHandler imageHandler) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        Sprite sprite = new Sprite(image);
        sprite.setSize(600, 400);
        sprite.setPosition(GameConstants.WINDOW_WIDTH / 2 - 300, GameConstants.WINDOW_HEIGHT / 2 - 200);
        sprite.draw(batch);

        waveText.draw(batch);
    }

    /**
     * changes state flag if core is used later
     */
    @Override
    public void reset() {
        changeState = false;
    }


    /**
     * Dispose of image object
     */
    @Override
    public void dispose() {
        image.dispose();
    }
}
