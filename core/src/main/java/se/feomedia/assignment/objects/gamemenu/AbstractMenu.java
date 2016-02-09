package se.feomedia.assignment.objects.gamemenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

import se.feomedia.assignment.constants.GameConstants;
import se.feomedia.assignment.objects.text.ImageText;
import se.feomedia.assignment.objects.text.WaveText;

/**
 * Abstract class handling pause and game over menu
 *
 * Created by Niklas on 2015-12-08.
 */
public class AbstractMenu {
    protected int index = 0;
    protected ArrayList<WaveText> menuText;
    protected String[] menuItems;
    protected int menu_x, menu_y;
    ArrayList<Sprite> headerText;
    ShapeRenderer rectangle;

    /**
     * Constructr creates menu header and menu options
     * @param inStrings
     * @param in_x
     * @param in_y
     * @param menuHeader
     */
    public AbstractMenu (String[] inStrings, int in_x, int in_y, String menuHeader) {
        menuItems = inStrings;
        menu_x = in_x;
        menu_y = in_y;
        int y = 400;
        int padding = 100;
        WaveText textToAdd;
        menuText = new ArrayList<>();
        headerText = ImageText.getText8pxRed(menuHeader);
        rectangle = new ShapeRenderer();

        for(int i = 0; i < inStrings.length; i++) {
            textToAdd = new WaveText(inStrings[i], 0, y-(padding*i), 20, 1, 6);
            textToAdd.centerXOn(GameConstants.WINDOW_WIDTH / 2);
            if(i != index)
                textToAdd.setSpeed(0);
            menuText.add(textToAdd);
        }
    }

    /**
     * handle selection and activation of options
     * @return
     */
    public String handle_controls() {

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

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            return menuItems[index];
        }

        return null;
    }

    /**
     * Draws rectangle and image options
     * @param batch
     */
    public void draw(SpriteBatch batch) {

        batch.end();
        rectangle.begin(ShapeRenderer.ShapeType.Filled);
        rectangle.setColor(1, 1, 1, 0.5f);
        rectangle.rect(menu_x-20, 200, GameConstants.WINDOW_WIDTH-((menu_x-20)*2), 400);
        rectangle.end();
        batch.begin();


        int size = 8;
        int draw_x = menu_x;

        for (Sprite img : headerText) {
            img.setPosition(draw_x, menu_y);
            img.setSize((int)(img.getRegionWidth()*size), (int)(img.getRegionHeight()*size));
            img.draw(batch);
            draw_x += img.getRegionWidth()*size;
        }

        for(WaveText wt: menuText) {
            wt.draw(batch);
        }
    }
}
