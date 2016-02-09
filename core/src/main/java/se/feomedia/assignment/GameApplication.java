package se.feomedia.assignment;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import se.feomedia.assignment.applicationcores.CoreInterface;
import se.feomedia.assignment.constants.GameConstants;
import se.feomedia.assignment.handler.ApplicationStateHandler;
import se.feomedia.assignment.handler.ImageHandler;

/**
 * Handles startup of game and changes between states (cores)
 */
public class GameApplication extends ApplicationAdapter {

	private SpriteBatch batch;

	private ImageHandler imageHandler;
	private CoreInterface gameCore;

	private Viewport viewport;
	private Camera camera;

	private float deltaTime = 0;

	private ApplicationStateHandler appState;

	public GameApplication (){
	}

	/**
	 * Sets up variables and creates all textures and cores
	 * Sets viewport to use
	 * loads first game state
	 */
	@Override
	public void create() {
		imageHandler = new ImageHandler();

		appState = new ApplicationStateHandler(GameConstants.APPLICATION_SPLASH, imageHandler);
		batch = new SpriteBatch();

		camera = new PerspectiveCamera();
		viewport = new FitViewport(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT, camera);

		gameCore = appState.getCurrentCore();
	}

	/**
	 * Viewport keeps ratio intact
	 * @param width
	 * @param height
	 */
	@Override
	public void resize(int width, int height) {

		viewport.update(width, height);
	}

	/**
	 * Calculates data in core
	 * Changes state if needed
	 * Sets fps cap
	 * Handle control input
	 * Draw core drawables
	 */
	@Override
	public void render () {

		String stateReturn = gameCore.calculate();
		if(stateReturn != null) {
			gameCore = appState.setStateGetCore(stateReturn);
			gameCore.reset();
			return;
		}

		deltaTime += Gdx.graphics.getDeltaTime();
		if(deltaTime < 0.04) { return; }  //25 fps cap

		camera.update();
		gameCore.handle_controls();

		gameCore.draw(batch, imageHandler);


		batch.end();
	}

	/**
	 * Pause current core
	 */
	@Override
	public void pause() {
		gameCore.pause(true);
	}

	/**
	 * Resume current core
	 */
	@Override
	public void resume() {
		gameCore.pause(false);
	}

	/**
	 * Dispose of textures and cores
	 */
	@Override
	public void dispose() {
		imageHandler.dispose();
		appState.dispose();
		batch.dispose();
	}
}
