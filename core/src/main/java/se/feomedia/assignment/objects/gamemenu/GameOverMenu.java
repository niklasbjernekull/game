package se.feomedia.assignment.objects.gamemenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

import se.feomedia.assignment.constants.GameConstants;
import se.feomedia.assignment.objects.text.WaveText;

/**
 * Creates Game over menu
 *
 * Created by Niklas on 2015-12-08.
 */
public class GameOverMenu extends AbstractMenu {

    private String[] menuItems = {"Retry", "Quit"};

    public GameOverMenu () {
        super(new String[]{"Retry", "Quit"}, 350, 520, "Game Over");
    }
}
