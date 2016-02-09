package se.feomedia.assignment.applicationcores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import se.feomedia.assignment.constants.GameConstants;
import se.feomedia.assignment.constants.ImageConstants;
import se.feomedia.assignment.handler.EnemyHandler;
import se.feomedia.assignment.handler.ImageHandler;
import se.feomedia.assignment.levels.Level1Data;
import se.feomedia.assignment.levels.Level2Data;
import se.feomedia.assignment.levels.Level3Data;
import se.feomedia.assignment.levels.Level4Data;
import se.feomedia.assignment.levels.Level5Data;
import se.feomedia.assignment.levels.LevelDataInterface;
import se.feomedia.assignment.objects.DrawableObject;
import se.feomedia.assignment.objects.Enemy;
import se.feomedia.assignment.objects.Explosion;
import se.feomedia.assignment.objects.Player;
import se.feomedia.assignment.objects.Projectile;
import se.feomedia.assignment.objects.gamemenu.GameOverMenu;
import se.feomedia.assignment.objects.gamemenu.PauseMenu;
import se.feomedia.assignment.objects.text.ImageText;
import se.feomedia.assignment.objects.text.ScoreText;
import se.feomedia.assignment.spawnpit.EnemyCreationStation;

/**
 * Extension (copy then extended) of ApplicationCore
 * Loads next level istead of giving game over screen
 * Created by Niklas on 2015-12-03.
 */
public class SurvivalCore implements CoreInterface {

    private Player player1;
    private ArrayList<Projectile> projectiles;
    private EnemyCreationStation spawnPit;
    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosions;
    private ImageHandler imageHandler;

    private long startTime;
    private int shotTime = 1000; //ms
    private long lastShot;
    private long enemyPauseTime;
    private long lastEnemyUpdate;
    private long calculationTime = 0;
    private int numberOfAnimationUnits = 10;
    private int animationFrame = 1;

    private long pauseTime;
    private String gameStatus;
    private int score = 0;
    private PauseMenu pauseMenu;
    private GameOverMenu gameOverMenu;

    private int intro_enemy_offset;
    private int intro_player_offset;
    private int intro_speed = 5;
    private long intro_start_time;

    private Enemy enemy_hero = null;
    private int outro_distance_x;
    private int outro_distance_y;

    ScoreText scoreText;
    ArrayList<Sprite> startText;

    private ArrayList<DrawableObject> toDispose;

    private LevelDataInterface currentLevel;
    private int levelNumber = 1;

    /**
     * Constructor sets up variables and loads level data
     * @param in_imageHandler
     */
    public SurvivalCore(ImageHandler in_imageHandler) {
        imageHandler = in_imageHandler;
        spawnPit = new EnemyCreationStation();

        pauseMenu = new PauseMenu();
        gameOverMenu = new GameOverMenu();

        scoreText = new ScoreText();
        startText = ImageText.getText8pxRed("START");

        toDispose = new ArrayList<>();

        loadLevel();
    }

    /**
     * Used to load data from level classes
     * resets some variables
     */
    public void loadLevel() {
        startTime = System.currentTimeMillis();
        lastShot = -shotTime;
        lastEnemyUpdate = 0;
        projectiles = new ArrayList<>();
        animationFrame = 1;

        enemies = new ArrayList<>();

        if(levelNumber == 1 ) {
            currentLevel = new Level1Data();
        } else if(levelNumber == 2 ) {
            currentLevel = new Level2Data();
        } else if(levelNumber == 3 ) {
            currentLevel = new Level3Data();
        } else if(levelNumber == 4 ) {
            currentLevel = new Level4Data();
        } else if(levelNumber == 5 ) {
            currentLevel = new Level5Data();
        } else {
            levelNumber = 1;
            currentLevel = new Level1Data();
        }
        enemies = spawnPit.getEnemies(currentLevel, imageHandler);

        explosions = new ArrayList<>();

        int pWidth = currentLevel.getPlayerWidth();
        int pHeight = currentLevel.getPlayerHeight();
        player1 = new Player((GameConstants.WINDOW_WIDTH/2)-pWidth/2, 10, pWidth, pHeight, imageHandler.getTexture(ImageConstants.PLAYER),imageHandler.getTexture(ImageConstants.PLAYER_LEFT),imageHandler.getTexture(ImageConstants.PLAYER_RIGHT));

        gameStatus = GameConstants.GAME_STATUS_INTRO;
        int smallY = EnemyHandler.getSmallestY(enemies);
        intro_enemy_offset = GameConstants.WINDOW_HEIGHT - smallY + intro_speed;
        intro_player_offset = player1.y + 1 + player1.height;

        if(enemy_hero != null) {
            enemy_hero = null;
        }
    }

    /**
     * Handles controls for game
     * and also pause and game over menu
     */
    public void handle_controls() {
        if(gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_PLAYING)) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
                    Gdx.input.isKeyPressed(Input.Keys.A)) {
                //System.out.println("Left!");
                player1.moveLeft();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
                    Gdx.input.isKeyPressed(Input.Keys.D)) {
                //System.out.println("Right!");
                player1.moveRight();
            }

            if (!Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                player1.stand_still();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                long current_time = System.currentTimeMillis()-startTime;

                if (current_time >= lastShot + shotTime) { //once every second (1000millisec)
                    lastShot = current_time;
                    projectiles.add(new Projectile(player1.x + (player1.width / 2), player1.y + player1.height, 10, 20, imageHandler.getTexture(ImageConstants.PROJECTILE)));
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            if (gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_PAUSED))
                pause(false);
            else if (gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_PLAYING))
                pause(true);
        }
        if(gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_PAUSED)) {
            String result = pauseMenu.handle_controls();
            if(result != null) {
                if (result.equalsIgnoreCase("Continue")) {
                    gameStatus = GameConstants.GAME_STATUS_PLAYING;
                    long current_time = System.currentTimeMillis()-startTime;
                    startTime = startTime + (current_time-pauseTime);
                } else if (result.equalsIgnoreCase("Quit")) {
                    gameStatus = GameConstants.GAME_STATUS_RETURN;
                }
            }
        }

        if(gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_GAME_OVER)) {
            String result = gameOverMenu.handle_controls();
            if(result != null) {
                if (result.equalsIgnoreCase("Retry")) {
                    gameStatus = GameConstants.GAME_STATUS_RETRY;
                } else if (result.equalsIgnoreCase("Quit")) {
                    gameStatus = GameConstants.GAME_STATUS_RETURN;
                }
            }
        }

    }

    /**
     * Is called by P or when window isn't visible
     * @param pauseIt
     */
    @Override
    public void pause(boolean pauseIt) {
        if(pauseIt && gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_PLAYING)) {
            gameStatus = GameConstants.GAME_STATUS_PAUSED;
            pauseTime = System.currentTimeMillis()-startTime;
        } else if(!pauseIt && gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_PAUSED)){
            gameStatus = GameConstants.GAME_STATUS_PLAYING;
            long current_time = System.currentTimeMillis()-startTime;
            startTime = startTime + (current_time-pauseTime);
        }
    }

    /**
     * Calculates movement and collision of objects
     * @return new state if exiting game
     */
    public String calculate() {
        if(gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_PLAYING)) {
            /***Movement***/
            for (Projectile proj : projectiles) {
                proj.move();
            }
            moveEnemies();

            /***Collisions***/
            calculatePlayerVsWall();
            calculateProjectileEscape();
            calculateCollisionEnemyVsProjectile();
            if (enemies.isEmpty()) {
                gameStatus = GameConstants.GAME_STATUS_CONTINUE;
            }
            calculateEnemyReachingPlayer();

            for(Explosion exp: explosions) {
                if(exp.isDead()) {
                    exp.dispose();
                    explosions.remove(exp);
                    break;
                }
            }
        }

        if(gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_RETURN))
             return GameConstants.APPLICATION_MENU;

        if(gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_RETRY)) {
            score = 0;
            levelNumber = 1;
            loadLevel();
        }

        if(gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_CONTINUE)) {
            levelNumber++;
            loadLevel();
        }

        if(gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_INTRO)) {
            intro_enemy_offset -= intro_speed;
            intro_player_offset--;
            if(intro_enemy_offset <= 0 && intro_player_offset <= 0) {
                gameStatus = GameConstants.GAME_STATUS_INTRO_START;
                intro_start_time = System.currentTimeMillis();
            } else if(intro_enemy_offset < 0) {
                intro_enemy_offset = 0;
            } else if(intro_player_offset < 0) {
                intro_player_offset = 0;
            }
        }

        if(gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_INTRO_START)) {
            long current = System.currentTimeMillis();
            if(current - intro_start_time >= 1000) {
                startTime = System.currentTimeMillis();
                gameStatus = GameConstants.GAME_STATUS_PLAYING;
            }
        }

        if(gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_OUTRO)) {
            if(enemy_hero == null) {
                selectEnemy();
                outro_distance_x = enemy_hero.x - player1.x;
                outro_distance_y = enemy_hero.y - player1.y;
            } else {
                if(calculateOverlap(enemy_hero, player1) || enemy_hero.y <= player1.y) {
                    gameStatus = GameConstants.GAME_STATUS_GAME_OVER;
                }

                enemy_hero.x -= outro_distance_x/outro_distance_y;
                enemy_hero.y -= outro_distance_y/outro_distance_y;
            }
        }

        return null;
    }

    /**
     * Has lowest enemy reached player row?
     */
    private void calculateEnemyReachingPlayer() {
        int y = EnemyHandler.getSmallestY(enemies);
        if(y < player1.y+player1.height) {
            System.out.println("******************");
            System.out.println("Smallest Y: " + y);
            System.out.println("Player X: " + player1.x);
            System.out.println("Player Y: " + player1.y);
            gameStatus = GameConstants.GAME_STATUS_OUTRO;
        }
    }

    /**
     * Method that calculates the enemies movement speed and direction
     *
     * The speed is calculated by finding out time left and number of steps left.
     * This is dynamic since enemies tend to get killed.
     */
    private void moveEnemies() {
        if(enemies.isEmpty())
            return;

        long current_time = System.currentTimeMillis()-startTime;

        if(animationFrame > numberOfAnimationUnits) {
            if(current_time > lastEnemyUpdate + enemyPauseTime)
                animationFrame = 1;
            return;
        }

        int width = currentLevel.getEnemyWidth();
        int height = currentLevel.getEnemyHeight();

        if(animationFrame == 1) {
            boolean goDown = false;
            int jumpGoal = width;
            calculationTime = current_time;
            if(enemies.get(0).goingRight) {
                if(EnemyHandler.getLargestX(enemies) > GameConstants.WINDOW_WIDTH-(width*2)) {
                    goDown = true;
                    jumpGoal = height;
                }
            } else {
                if(EnemyHandler.getSmallestX(enemies) < width) {
                    goDown = true;
                    jumpGoal = height;
                }
            }
            for(Enemy badGuy: enemies) {
                badGuy.goingDown = goDown;
                badGuy.setJumpGoal(jumpGoal);
            }
        }

        int moveDistance = width/numberOfAnimationUnits;
        for(Enemy badGuy: enemies) {
            badGuy.move(moveDistance);
        }

        animationFrame++;
        if(animationFrame > numberOfAnimationUnits) {
            for(Enemy badGuy: enemies) {
                badGuy.goToJumpGoal();
            }

            enemyPauseTime = calculateEnemyJumpTime(current_time, current_time-calculationTime);
            lastEnemyUpdate =  current_time;
            System.out.println("Pause time: " + enemyPauseTime);
            System.out.println("Run Time: " + current_time / 1000);
        }
    }

    /**
     * Calculates how long the enemies can pause before the next jump.
     * I no enemies die the pause time will stay almost the same.
     * If they do get killed the pause time is reduced.
     * @param current_time
     * @param calculation_time
     * @return
     */
    private long calculateEnemyJumpTime(long current_time, long calculation_time) {

        int totalStepsLeft = calculateStepsLeft();

        if(totalStepsLeft < 1)
            return 0;

        long timeLeft = currentLevel.getTimeInMiliSec()- current_time;

        long stepTime = (timeLeft - (calculation_time*totalStepsLeft)) / totalStepsLeft;

        return stepTime;
    }

    /**
     * Calculates the minimun number of steps left for enemies to reach player
     * @return
     */
    private int calculateStepsLeft() {
        boolean goingRight = enemies.get(0).goingRight;
        int smallestX = EnemyHandler.getSmallestX(enemies);
        int largestX = EnemyHandler.getLargestX(enemies);
        int smallestY = EnemyHandler.getSmallestY(enemies);
        int width = currentLevel.getEnemyWidth();
        int height = currentLevel.getEnemyHeight();
        int stepsLeftOnRow = 0;

        if(goingRight) {
            stepsLeftOnRow = (GameConstants.WINDOW_WIDTH-(largestX+width))/width;
        } else {
            stepsLeftOnRow = smallestX/width;
        }

        int stepsOnEachRow = (GameConstants.WINDOW_WIDTH-(largestX+width-smallestX))/width;

        int rowsLeft = (smallestY-player1.height)/height;

        return stepsLeftOnRow+(stepsOnEachRow*rowsLeft)+rowsLeft;
    }

    /**
     * Check if projectile hit enemy
     * Remove both
     */
    private void calculateCollisionEnemyVsProjectile() {
        ArrayList<Integer> kill_projectile = new ArrayList<>();
        ArrayList<Integer> kill_enemy = new ArrayList<>();

        for(Projectile projectile: projectiles) {
            for(Enemy enemy: enemies) {
                if(calculateOverlap(projectile, enemy)) {
                    if(!kill_projectile.contains(projectiles.indexOf(projectile)))
                        kill_projectile.add(projectiles.indexOf(projectile));
                    if(!kill_enemy.contains(enemies.indexOf(enemy)))
                        kill_enemy.add(enemies.indexOf(enemy));
                    /*System.out.println("Projectile Size " + projectiles.size());
                    System.out.println("Enemy Size " + enemies.size());
                    System.out.println("Projectile Index " + projectiles.indexOf(projectile));
                    System.out.println("Enemy Index " + enemies.indexOf(enemy));
                    System.out.println("Kill Size " + kill_projectile.size() + ", " + kill_enemy.size());*/
                    break; //Other option is to check if index exists in projectile array*/
                }
            }
        }

        if(kill_projectile.size() > 0) {
            for(int i: kill_projectile) {
                if(i <= projectiles.size()) {
                    toDispose.add(projectiles.get(i));
                    projectiles.remove(i);
                }
                System.out.println("Projectile " + i);
            }
        }

        if(kill_enemy.size() > 0) {
            for(int i: kill_enemy) {
                if(i <= enemies.size()) {
                    Enemy temp = enemies.get(i);
                    explosions.add(new Explosion(temp.x, temp.y, temp.width, temp.height));
                    toDispose.add(temp);
                    enemies.remove(i);
                    score++;
                }
                System.out.println("Enemy " + i);
            }
        }

    }

    /**
     * Calculate the overlap of two rectangles
     * @param a
     * @param b
     * @return
     */
    private boolean calculateOverlap(DrawableObject a, DrawableObject b) {
        if((a.x+a.width >= b.x && a.x <= b.x+b.width) && (a.y+a.height >= b.y && a.y <= b.y+b.height)) {
            return true;
        }
        return false;
    }

    /**
     * If projectile escapes off screen the in will be removed
     */
    private void calculateProjectileEscape() {
        ArrayList<Integer> killList = new ArrayList<>();    //killList is overkill since only one shot will be out of bounds at one time
        for(Projectile proj: projectiles) {
            if(proj.y-proj.height >= GameConstants.WINDOW_HEIGHT) {
                killList.add(projectiles.indexOf(proj));
            }
        }

        if(killList.size() > 0) {
            for(int i: killList) {
                toDispose.add(projectiles.get(i));
                projectiles.remove(i);
            }
        }
    }

    /**
     * Player can't go through the walls
     */
    private void calculatePlayerVsWall() {
        if(player1.x < 0)
            player1.set_x(0);
        if(player1.x + player1.width > GameConstants.WINDOW_WIDTH)
            player1.set_x(GameConstants.WINDOW_WIDTH-player1.width);
    }

    /**
     * For outro, the nearest enemy will be selected to attack player
     */
    private void selectEnemy() {
        if(enemies.size()<1)
            return;

        int distance = calculateDistance(player1.x, player1.y, enemies.get(0).x, enemies.get(0).y);;
        int temp_distance;
        Enemy temp_enemy = enemies.get(0);

        for(Enemy enemy: enemies) {
            temp_distance = calculateDistance(player1.x, player1.y, enemy.x, enemy.y);
            if(temp_distance < distance) {
                distance = temp_distance;
                temp_enemy = enemy;
            }
        }

        enemy_hero = temp_enemy;

    }

    /**
     * calculates distance between points
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    private int calculateDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(((x1-x2)* (x1-x2))+ ((y1-y2)* (y1-y2)));
    }

    /**
     * Draws the objects
     * @param batch
     * @param imageHandler
     */
    public void draw(SpriteBatch batch, ImageHandler imageHandler) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        if(gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_INTRO)) {
                player1.drawWithOffset(batch, 0, -intro_player_offset);

                for(Enemy badGuy: enemies) {
                    badGuy.drawWithOffset(batch, 0, intro_enemy_offset);
                }
        } else {
            player1.draw(batch);

            for(Enemy badGuy: enemies) {
                badGuy.draw(batch);
            }

            for(Projectile proj: projectiles) {
                proj.draw(batch);
            }

            for(Explosion exp: explosions) {
                exp.draw(batch);
            }
        }

        scoreText.draw(batch, Integer.toString(score), 10, 750, 4);

        if(gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_PAUSED)) {
            pauseMenu.draw(batch);
        }

        if(gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_GAME_OVER)) {
            gameOverMenu.draw(batch);
        }

        if(gameStatus.equalsIgnoreCase(GameConstants.GAME_STATUS_INTRO_START)) {
            int draw_x = 450;
            int draw_y = 200;
            int size = 8;
            for (Sprite img : startText) {
                img.setPosition(draw_x, draw_y);
                img.setSize((int)(img.getRegionWidth()*size), (int)(img.getRegionHeight()*size));
                img.draw(batch);
                draw_x += img.getRegionWidth()*size;
            }
        }
    }

    /**
     * resets data for reuse of core
     */
    @Override
    public void reset() {
        toDispose = new ArrayList<>();
        levelNumber = 1;
        loadLevel();
    }

    /**
     * puts all objects in array and returns
     * @return
     */
    private ArrayList<DrawableObject> getDrawableAssets() {
        ArrayList<DrawableObject> drawList = new ArrayList<DrawableObject>();
        drawList.add(player1);
        for(Projectile proj: projectiles) {
            drawList.add(proj);
        }
        for(Enemy badGuy: enemies) {
            drawList.add(badGuy);
        }
        return drawList;
    }

    /**
     * To dispose objects
     */
    @Override
    public void dispose() {
        ArrayList<DrawableObject> drawObjects = getDrawableAssets();
        for(DrawableObject aDrawObject: drawObjects) {
            aDrawObject.dispose();
        }

        for(DrawableObject objectToDispose: toDispose) {
            objectToDispose.dispose();
        }
    }
}
