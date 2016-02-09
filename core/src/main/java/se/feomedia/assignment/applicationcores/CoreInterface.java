package se.feomedia.assignment.applicationcores;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import se.feomedia.assignment.handler.ImageHandler;

/**
 * Interface to get same methods to call for all cores
 *
 * Created by Niklas on 2015-12-08.
 */
public interface CoreInterface {

    public void handle_controls();

    public void pause(boolean pauseIt);

    public String calculate();

    public void draw(SpriteBatch batch, ImageHandler imageHandler);

    public void reset();

    public void dispose();
}
