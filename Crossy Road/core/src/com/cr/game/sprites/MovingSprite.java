package com.cr.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.cr.game.data.GameData;

public class MovingSprite extends Sprite {
    public float speed;
    com.cr.game.CrossyRoad game;
    public float initialX;

    public MovingSprite (com.cr.game.CrossyRoad game, float x, float y) {
        this.game = game;
        setX(x);
        setY(y);
        initialX = x;
    }

    public void update (float dt) {
        if (getX() > -getWidth() && speed < 0 || getX() < game.screenWidth && speed > 0) {
            setX(getX() + speed * dt);
        } else {
            if (speed < 0) {
                setX(game.screenWidth);
            } else {
                setX(-GameData.TILE_SIZE);
            }
        }
    }
}
