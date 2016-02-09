package se.feomedia.assignment.applicationcores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import se.feomedia.assignment.constants.GameConstants;
import se.feomedia.assignment.constants.ImageConstants;
import se.feomedia.assignment.handler.ImageHandler;
import se.feomedia.assignment.objects.text.WaveText;

/**
 * Handles Main menu
 * Created by Niklas on 2015-12-08.
 */
public class MenuCore implements CoreInterface {
    private boolean changeState = false;

    private ArrayList<WaveText> menuText;
    private String[] menuStates = {GameConstants.APPLICATION_ASSIGNMENT, GameConstants.APPLICATION_SURVIVAL, GameConstants.APPLICATION_CONTROLS, GameConstants.APPLICATION_ABOUT};
    private int index = 0;
    private Texture image;

    /**
     * Sets objects to draw
     * title image and menu options
     * @param imageHandler
     */
    public MenuCore (ImageHandler imageHandler) {

        image  = imageHandler.getTexture(ImageConstants.MENU);

        int y = 400;
        int padding = 100;
        menuText = new ArrayList<>();

        WaveText textToAdd = new WaveText("Assignment", 0, y, 20, 1, 6);
        textToAdd.centerXOn(GameConstants.WINDOW_WIDTH/2);
        menuText.add(textToAdd);

        textToAdd = new WaveText("Survival", 0, y-padding, 20, 1, 6);
        textToAdd.centerXOn(GameConstants.WINDOW_WIDTH / 2);
        textToAdd.setSpeed(0);
        menuText.add(textToAdd);

        textToAdd = new WaveText("Controls", 0, y-padding*2, 20, 1, 6);
        textToAdd.centerXOn(GameConstants.WINDOW_WIDTH / 2);
        textToAdd.setSpeed(0);
        menuText.add(textToAdd);

        textToAdd = new WaveText("About", 0, y-padding*3, 20, 1, 6);
        textToAdd.centerXOn(GameConstants.WINDOW_WIDTH/2);
        textToAdd.setSpeed(0);
        menuText.add(textToAdd);

    }

    /**
     * handles keys for selection and activation of options
     */
    @Override
    public void handle_controls() {
        /*if(!useButtons) {
            if(System.currentTimeMillis() - startTime >= waitBeforeUsingControls)
                useButtons = true;
            else
                return;
        }*/

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            changeState = true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            menuText.get(index).setSpeed(0);
            index++;
            if(index >= menuText.size())
                index = 0;
            menuText.get(index).setSpeed(1);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            menuText.get(index).setSpeed(0);
            index--;
            if(index < 0)
                index = menuText.size()-1;
            menuText.get(index).setSpeed(1);
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
     * Change stat = change core usage
     * @return
     */
    @Override
    public String calculate() {
        if(changeState)
            return menuStates[index];

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
        sprite.setSize(1000, 300);
        sprite.setPosition(GameConstants.WINDOW_WIDTH / 2 - 500, GameConstants.WINDOW_HEIGHT - 300);
        sprite.draw(batch);

        for(WaveText wt: menuText) {
            wt.draw(batch);
        }
        //moveText.draw(batch);
    }

    /**
     * changes state flag if core is used later
     */
    @Override
    public void reset() {
        changeState = false;
    }

    /**
     * dispose title image
     */
    @Override
    public void dispose() {
        image.dispose();
    }
}
