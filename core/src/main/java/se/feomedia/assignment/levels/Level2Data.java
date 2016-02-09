package se.feomedia.assignment.levels;

import se.feomedia.assignment.constants.GameConstants;
import se.feomedia.assignment.constants.ImageConstants;

/**
 * Data for level2
 *
 * Created by Niklas on 2015-12-04.
 */
public class Level2Data implements LevelDataInterface {
    private int[] enemyFormation = {2,3,4,5,6};
    private String[] enemyType =
            {ImageConstants.ENEMY3, ImageConstants.ENEMY1, ImageConstants.ENEMY2, ImageConstants.ENEMY1, ImageConstants.ENEMY3};

    private int playerWidth = 75;
    private int playerHeight = 75;

    private int enemyWidth = 60;
    private int enemyHeight = 60;

    private int padding_top = 10;
    private int padding_side = 20;

    private boolean isTimeBased = false;
    private int timeToFinish = 60000; //milliseconds
    private int pauseTimeBetweenJumps = 500; //milliseconds

    private boolean synchronizedMove = true;

    private String movementType = GameConstants.ENEMY_MOVEMENT_JUMP;

    @Override
    public int getPlayerWidth() {
        return playerWidth;
    }

    @Override
    public int getPlayerHeight() {
        return playerHeight;
    }

    @Override
    public int getEnemyWidth() {
        return enemyWidth;
    }

    @Override
    public int getEnemyHeight() {
        return enemyHeight;
    }

    @Override
    public int getPaddingTop() {
        return padding_top;
    }

    @Override
    public int getPaddingSide() {
        return padding_side;
    }

    @Override
    public int[] getEnemyFormation() {
        return enemyFormation;
    }

    @Override
    public String[] getEnemyTypes() {
        return enemyType;
    }

    @Override
    public boolean isLevelTimeBased() {
        return isTimeBased;
    }

    @Override
    public int getTimeInMiliSec() {
        return timeToFinish;
    }

    @Override
    public String getMoveType() {
        return movementType;
    }

    @Override
    public int getPauseTime() {
        return pauseTimeBetweenJumps;
    }

    @Override
    public boolean doesAllRowsMoveTogether() {
        return synchronizedMove;
    }

    @Override
    public boolean isMovementRandom() {
        return false;
    }
}
