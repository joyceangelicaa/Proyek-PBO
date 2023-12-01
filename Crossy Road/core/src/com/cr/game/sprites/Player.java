package com.cr.game.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.cr.game.CrossyRoad;
import com.cr.game.PlayerSound;
import com.cr.game.data.ImageCache;

public class Player extends MovingSprite implements PlayerSound {
    public enum PlayerMovement {
        W,A,S,D,UP,LEFT,DOWN,RIGHT, IDLE;

    }
    public Animation<Sprite> frogUp;
    private int lives = 3;
    public int jumpSize = 32;
    public boolean isVisible;
    public Boolean isMoving;
    TextureRegion region = ImageCache.getTexture("frog");
    Sprite sprite1 = new Sprite(ImageCache.getTexture("frog_jump"));
    Sprite sprite2 = new Sprite(ImageCache.getTexture("frog"));
    public int tierIndex = 0;
    public boolean isOnALog;
    private PlayerMovement currentMovement = PlayerMovement.IDLE;
    public Player(com.cr.game.CrossyRoad game, float x, float y) {
        super(game, x, y);
        setPlayer();
        Array<Sprite> sprites = new Array<Sprite>();
        sprites.add(sprite1);
        sprites.add(sprite2);
        frogUp = new Animation(0.1f, sprites);
        isVisible = true;
        isMoving = false;
        isOnALog = false;
    }

    private void setPlayer() {
        setRegion(region);
        setColor(1, 1, 1, 1);
        setSize(region.getRegionWidth(), region.getRegionHeight());
        setOrigin(getWidth() / 2, getHeight() / 2);
    }

    public void moveFrog(PlayerMovement movement) {
        if (!isMoving) {
            switch (movement) {
                case W:
                case UP:
                    moveFrogUp();
                    break;
                case S:
                case DOWN:
                    moveFrogDown();
                    break;
                case A:
                case LEFT:
                    moveFrogLeft();
                    break;
                case D:
                case RIGHT:
                    moveFrogRight();
                    break;
                case IDLE:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + movement);
            }
        }
    }

    public void moveFrogUp() {
        setRotation(0f);
        setScale(1, 1);
        setY(getY() + jumpSize);
        isMoving = true;
        tierIndex += 1;
        game.gameData.score += 10;
        currentMovement = PlayerMovement.W;
        playJumpSound();
    }

    public void moveFrogDown() {
        setRotation(0f);
        setScale(1, -1);
        if (tierIndex > 0) {
            setY(getY() - jumpSize);
            isMoving = true;
            tierIndex -= 1;
            game.gameData.score -= 10;
            currentMovement = PlayerMovement.S;
            playJumpSound();
        }
    }

    public void moveFrogLeft() {
        if (getScaleY() == -1) {
            setRotation(-90f);
        } else {
            setRotation(90f);
        }
        if (getX() > 0) {
            setX(getX() - jumpSize);
            isMoving = true;
            game.gameData.score -= 2;
            currentMovement = PlayerMovement.A;
            playJumpSound();
        }
    }

    public void moveFrogRight() {
        if (getScaleY() == -1) {
            setRotation(90f);
        } else {
            setRotation(-90f);
        }
        if (getX() < (game.screenWidth - getWidth())) {
            setX(getX() + jumpSize);
            isMoving = true;
            game.gameData.score -= 2;
            currentMovement = PlayerMovement.D;
            playJumpSound();
        }
    }

    public int decLives() {
        int result = lives -= 1;
        if (result < 0) {
            playDeathSound();
        }
        return result;
    }

    public int getLives() {
        if (lives > -1)
            return lives;
        else return 0;
    }

    public void draw() {
        draw(game.batch);
    }

    public void reset() {
        tierIndex = 0;
        isOnALog = false;
        setPosition(320, 0);
        if (lives < 0 || game.currentState == game.GAME_STATE_PAUSE) {
            lives = 3;
        }
        isMoving = false;
        currentMovement = PlayerMovement.IDLE;
    }

    @Override
    public void playJumpSound() {
        CrossyRoad.getInstance().playSound("Jump.mp3");
    }

    @Override
    public void playDeathSound() {
        CrossyRoad.getInstance().playSound("Drown.mp3");
    }
}
