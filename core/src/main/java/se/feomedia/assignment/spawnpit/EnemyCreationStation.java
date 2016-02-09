package se.feomedia.assignment.spawnpit;

import java.util.ArrayList;

import se.feomedia.assignment.constants.GameConstants;
import se.feomedia.assignment.handler.ImageHandler;
import se.feomedia.assignment.levels.Level1Data;
import se.feomedia.assignment.levels.LevelDataInterface;
import se.feomedia.assignment.objects.Enemy;

/**
 * Spawns an array of Enemies
 * Created by Niklas on 2015-12-04.
 */
public class EnemyCreationStation {

    /**
     * Creates enemy array from level data
     * Feature: handle more move patterns and styles
     * @param levelData
     * @param imageHandler
     * @return
     */
    public ArrayList<Enemy> getEnemies(LevelDataInterface levelData, ImageHandler imageHandler) {
        int[] enemyFormation = levelData.getEnemyFormation();
        int noRows = enemyFormation.length;
        int rowStartX;
        int rowStartY;
        int noEnemies;

        ArrayList<Enemy> listOfBadGuys = new ArrayList<>();

        for(int i = 0; i < noRows; i++) {
            noEnemies = enemyFormation[i];
            rowStartX = (GameConstants.WINDOW_WIDTH - ((noEnemies*levelData.getEnemyWidth())+(levelData.getPaddingSide()*(noEnemies-1))))/2;
            rowStartY = GameConstants.WINDOW_HEIGHT-levelData.getPaddingTop()*(i+1)-levelData.getEnemyHeight()*(i+1);
            for(int j = 0; j < noEnemies; j++) {
                if(levelData.doesAllRowsMoveTogether())
                    listOfBadGuys.add(new Enemy(rowStartX, rowStartY,levelData.getEnemyWidth(),levelData.getEnemyHeight(), imageHandler.getTexture(levelData.getEnemyTypes()[i])));
                else
                    listOfBadGuys.add(new Enemy(rowStartX, rowStartY,levelData.getEnemyWidth(),levelData.getEnemyHeight(), imageHandler.getTexture(levelData.getEnemyTypes()[i]), i%2 == 1 ? true : false));
                rowStartX += levelData.getPaddingSide()+levelData.getEnemyWidth();
            }
            rowStartY += levelData.getPaddingTop()+levelData.getEnemyHeight();
        }
        return listOfBadGuys;
    }
}
