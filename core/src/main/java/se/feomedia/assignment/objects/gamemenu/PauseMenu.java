package se.feomedia.assignment.objects.gamemenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

import se.feomedia.assignment.constants.GameConstants;
import se.feomedia.assignment.objects.text.ImageText;
import se.feomedia.assignment.objects.text.WaveText;

/**
 * Creates Pause menu
 *
 * Created by Niklas on 2015-12-08.
 */
public class PauseMenu extends AbstractMenu {

    public PauseMenu() {
        super(new String[]{"Continue", "Quit"}, 420, 520, "Paused");
    }
}
