package se.feomedia.assignment;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import se.feomedia.assignment.constants.GameConstants;

public class DesktopLauncher {
	public static void main (String[] args) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = true;

		if (args.length == 2) {
			config.width = Integer.valueOf(args[0]);
			config.height = Integer.valueOf(args[1]);
		} else {
			config.width = GameConstants.WINDOW_WIDTH;
			config.height = GameConstants.WINDOW_HEIGHT;
		}

		config.stencil = 8;
		config.title = "Feo Gamedev Assignment: Niklas Bjernekull";
		new LwjglApplication(new GameApplication(), config);
	}
}



