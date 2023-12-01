package com.cr.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.cr.game.data.GameData;
import com.cr.game.data.ImageCache;
import com.cr.game.sprites.Player;
import com.cr.game.sprites.Alligator;
import com.cr.game.sprites.TreeLog;
import com.cr.game.sprites.MovingSprite;
import com.cr.game.sprites.Vehicle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameScreen extends Screen {
    private Player player;
    private BitmapFont score;
    private BitmapFont lives;
    private BitmapFont gameStatus;
    private String gameStatusMessage;
    Sprite msgGameOver;
    Sprite msgWin;
    float elapsedTime = 0f;
    float totalElapsedTime = 0f;

    List<Integer> waterTiers = new ArrayList(Arrays.asList(7, 8, 9, 10, 11, 12));
    Alligator alligator;
    Sprite alligatorKeyFrame;
    private Animation<Sprite> alligatorAnimation;

    public GameScreen(com.cr.game.CrossyRoad game) {
        super(game);
        elements = new ArrayList<Sprite>();
    }

    @Override
    public void createScreen() {
        score = new BitmapFont();
        lives = new BitmapFont();
        gameStatusMessage = "";
        gameStatus = new BitmapFont();
        gameStatus.getData().setScale(1.5f,1.5f);
        score.setColor(Color.WHITE);
        lives.setColor(Color.WHITE);

        player = new Player(game, 320, 0);
        if (elements.size() == 0) {
            elements.add(new Sprite(ImageCache.getTexture("background_640")));
        }


        Array<Sprite> alligatorSprites = new Array<Sprite>();
        alligatorSprites.add(new Sprite(ImageCache.getTexture("alligator1")));
        alligatorSprites.add(new Sprite(ImageCache.getTexture("alligator2")));
        alligatorAnimation = new Animation<Sprite>(0.2f, alligatorSprites);


        alligator = new Alligator(game, game.screenWidth/2, 13*GameData.TILE_SIZE);
        alligator.setAlpha(0f);
        alligator.speed = 70;


        initializeLogs(3, 50, GameData.RIGHT, 12, "wood2");
        initializeLogs(2, 40, GameData.LEFT, 11, "wood3");
        initializeLogs(3, 70, GameData.RIGHT, 10, "wood2");
        initializeLogs(2, 60, GameData.LEFT, 9, "wood4");
        initializeLogs(3, 50, GameData.RIGHT, 8, "wood3");
        initializeLogs(3, 60, GameData.LEFT, 7, "wood2");

        initializeVehicles(4, 100, GameData.LEFT, 5, "car1");
        initializeVehicles(5, 60, GameData.RIGHT, 4, "car2");
        initializeVehicles(4, 70, GameData.LEFT, 3, "car1");
        initializeVehicles(4, 40, GameData.RIGHT, 2, "truck");

        msgGameOver = new Sprite(ImageCache.getTexture("msg_gameover"));
        msgGameOver.setX(230);
        msgGameOver.setY(240);
        msgGameOver.setAlpha(0f);

        msgWin = new Sprite(ImageCache.getTexture("msg_win"));
        msgWin.setX(230);
        msgWin.setY(240);
        msgWin.setAlpha(0f);
    }

    private void initializeVehicles(int numberOfVehicles, float speed, int direction, int tierIndex, String textureRegion) {
        for (int i = 0; i < numberOfVehicles; i++) {
            Vehicle vehicle;
            if (direction == GameData.LEFT) {
               vehicle = new Vehicle(game, game.screenWidth / numberOfVehicles * i,
                        tierIndex * GameData.TILE_SIZE, textureRegion);
            } else {
                vehicle = new Vehicle(game, GameData.TILE_SIZE + game.screenWidth / numberOfVehicles * i,
                        tierIndex * GameData.TILE_SIZE, textureRegion);
                vehicle.flip(true, false);
            }
            vehicle.speed = speed * direction;
        }
    }

    private void initializeLogs(int numberOfLogs, float speed, int direction, int tierIndex, String textureRegion) {
        for (int i = 0; i < numberOfLogs; i++) {
            TreeLog treeLog;
            if (direction == GameData.LEFT) {
                treeLog = new TreeLog(game, game.screenWidth / numberOfLogs * i,
                        tierIndex * GameData.TILE_SIZE, textureRegion);
            } else {
                treeLog = new TreeLog(game, GameData.TILE_SIZE + game.screenWidth / numberOfLogs * i,
                        tierIndex * GameData.TILE_SIZE, textureRegion);
                treeLog.flip(true, false);
            }
            treeLog.speed = speed * direction;
        }
    }

    @Override
    public void update(float dt) {
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        totalElapsedTime+=dt;

        if (game.currentState != game.GAME_STATE_PAUSE) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.A)||Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                // Memanggil moveFrog dengan parameter sesuai dengan kondisi yang terpenuhi
                if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
                    player.moveFrog(Player.PlayerMovement.A);
                } else {
                    player.moveFrog(Player.PlayerMovement.LEFT);
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.D)||Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                // Memanggil moveFrog dengan parameter sesuai dengan kondisi yang terpenuhi
                if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
                    player.moveFrog(Player.PlayerMovement.D);
                } else {
                    player.moveFrog(Player.PlayerMovement.RIGHT);
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.W)||Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                // Memanggil moveFrog dengan parameter sesuai dengan kondisi yang terpenuhi
                if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                    player.moveFrog(Player.PlayerMovement.W);
                } else {
                    player.moveFrog(Player.PlayerMovement.UP);
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.S)||Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                // Memanggil moveFrog dengan parameter sesuai dengan kondisi yang terpenuhi
                if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
                    player.moveFrog(Player.PlayerMovement.S);
                } else {
                    player.moveFrog(Player.PlayerMovement.DOWN);
                }
            }

            for (Sprite element : elements) {
                if (element instanceof MovingSprite) {
                    ((MovingSprite) element).update(dt);
                }
            }
            alligatorKeyFrame = alligatorAnimation.getKeyFrame(totalElapsedTime, true);
            alligatorKeyFrame.setPosition(alligator.getX(), alligator.getY());

            Boolean collision;
            collision = checkCollision(dt);

            if(collision){
                if(!waterTiers.contains(player.tierIndex))
                    gameOver(1);
            }
            else {
                if(waterTiers.contains(player.tierIndex)){
                    gameOver(1);
                }
                else if (checkWin()){
                    gameOver(0);
                }
            }
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            player.reset();
            msgGameOver.setAlpha(0f);
            msgWin.setAlpha(0f);
            game.currentState = game.GAME_STATE_PLAY;
            game.gameData.score = 0;
        }

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.enableBlending();
        game.batch.begin();

        for (Sprite element : elements) {
            element.draw(game.batch);
        }

        alligatorKeyFrame.draw(game.batch);

        if (player.isMoving) {
            Sprite keyFrame = player.frogUp.getKeyFrame(elapsedTime, false);
            keyFrame.setPosition(player.getX(), player.getY());
            keyFrame.setRotation(player.getRotation());
            keyFrame.setScale(player.getScaleX(), player.getScaleY());
            if (!player.frogUp.isAnimationFinished(elapsedTime)) {
                elapsedTime += dt;
                keyFrame.draw(game.batch);
            } else {
                player.isMoving = false;
                elapsedTime = 0f;
                player.draw();
            }
        }
        else {
            player.draw();
        }

        if(game.gameData.score < 0){
            game.gameData.score = 0;
        }
        score.draw(game.batch, "SCORE: " + game.gameData.score, 10, 470);
        score.draw(game.batch, "LIVES: " + player.getLives(), 120, 470);

        if (game.currentState == game.GAME_STATE_PAUSE) {
            msgGameOver.draw(game.batch);
            msgWin.draw(game.batch);
            gameStatus.draw(game.batch, gameStatusMessage, 200, 240);
        }

        game.batch.end();
    }

    private boolean checkWin() {
        return player.tierIndex == 13;
    }

    private Boolean checkCollision(float dt) {
        for (Sprite sprite : elements) {
            if (!(sprite instanceof MovingSprite)) {
                continue;
            }
            MovingSprite element = (MovingSprite) sprite;

            if (player.getBoundingRectangle().overlaps(element.getBoundingRectangle())) {
                if (element instanceof Vehicle) {
                    return true;
                }
                if (element instanceof TreeLog) {
                    player.speed = element.speed;
                    player.update(dt);
                    return true;
                }
                if (element instanceof Alligator) {
                    return true;
                }
            }
        }
        return false;
    }

    private void gameOver(int n) {
        if(n == 0){
            game.gameData.score += game.gameData.POINTS_PER_LIFE * player.getLives();
            msgWin.setAlpha(1f);
            gameStatus.setColor(Color.YELLOW);
            gameStatusMessage = "      Your Score was: " + game.gameData.score + "\nPress ENTER to play again";
            game.currentState = game.GAME_STATE_PAUSE;
        }
        else if(player.decLives() < 0) {
            msgGameOver.setAlpha(1f);
            gameStatus.setColor(Color.RED);
            gameStatusMessage = "      Your score was: " + game.gameData.score + "\nPress ENTER to play again";
            game.currentState = com.cr.game.CrossyRoad.GAME_STATE_PAUSE;
        }
        else
            player.reset();
    }
}