package se.feomedia.assignment.levels;

/**
 * Interface to keep continuity between level data
 * Created by Niklas on 2015-12-04.
 */
public interface LevelDataInterface {

    public int getPlayerWidth();

    public int getPlayerHeight();

    public int getEnemyWidth();

    public int getEnemyHeight();

    public int getPaddingTop();

    public int getPaddingSide();

    public int[] getEnemyFormation();

    public String[] getEnemyTypes();

    public boolean isLevelTimeBased();

    public int getTimeInMiliSec();

    public String getMoveType();

    public int getPauseTime();

    public boolean doesAllRowsMoveTogether();

    public boolean isMovementRandom();
}
