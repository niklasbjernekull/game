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
 * Shows the control side
 *
 * Created by Niklas on 2015-12-08.
 */
public class ControlCore implements CoreInterface {

    private boolean changeState = false;
    private WaveText waveText;
    private Texture image;

    /**
     * constructor sets up wavetext and information image
     * @param imageHandler
     */
    public ControlCore(ImageHandler imageHandler) {
        waveText = new WaveText("Press Space To Go Back", 0, 50, 15, 1, 2);
        waveText.centerXOn(GameConstants.WINDOW_WIDTH/2);
        image  = imageHandler.getTexture(ImageConstants.CONTROLS);
    }


    /**
     * handles buttons for return to menu
     */
    @Override
    public void handle_controls() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
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
     * returns menu state when leaving
     * @return
     */
    @Override
    public String calculate() {
        if(changeState)
            return GameConstants.APPLICATION_MENU;

        return null;
    }

    /**
     * draws objects
     * @param batch
     * @param imageHandler
     */
    @Override
    public void draw(SpriteBatch batch, ImageHandler imageHandler) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        Sprite sprite = new Sprite(image);
        sprite.setSize(1000, 500);
        sprite.setPosition(GameConstants.WINDOW_WIDTH / 2 - 500, GameConstants.WINDOW_HEIGHT / 2 - 250);
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
     * dispose image
     */
    @Override
    public void dispose() {
        image.dispose();
    }
}
