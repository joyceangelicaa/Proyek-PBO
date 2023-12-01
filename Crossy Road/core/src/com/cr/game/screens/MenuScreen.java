package com.cr.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.cr.game.data.ImageCache;

public class MenuScreen extends Screen {

    private BitmapFont title;
    public AssetManager assets;
    private Animation<Sprite> animation;
    private float elapsedTime = 0f;
    Array<Sprite> sprites;
    Array<Integer> frogsX;
    Array<Integer> frogsY;
    Sprite keyFrame;
    float y = 0;

    public MenuScreen (com.cr.game.CrossyRoad game) {
        super(game);
    }

    @Override
    public void createScreen() {
        title = new BitmapFont();

        initializeFrogsPos();

        if (elements.size() == 0) {
            sprites = new Array<Sprite>();
            assets = new AssetManager();
            Sprite sprite1 = new Sprite(ImageCache.getTexture("frog"));
            Sprite sprite2 = new Sprite(ImageCache.getTexture("frog_jump"));
            sprites.add(sprite1);
            sprites.add(sprite2);
            animation = new Animation<Sprite>(0.3f, sprites);
        }

        for (int i = 0; i < frogsX.size; i++){
            keyFrame = animation.getKeyFrame(elapsedTime, true);
            keyFrame.setX(frogsX.get(i));
            keyFrame.setY(frogsY.get(i));
            elements.add(keyFrame);
        }
    }

    @Override
    public void update(float dt) {
        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen("GameScreen");
        }
        else {
            GL20 gl = Gdx.gl;
            gl.glClearColor(1, 1, 1, 1);
            gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // exhibit message
            game.camera.update();
            game.batch.begin();
            title.setColor(Color.GREEN);
            title.draw(game.batch, "               Frogger\n\nPress ENTER or Click to play", 220, 280);

            // move and animate frogs
            y+=1.5f;
            elapsedTime += dt;

            for(int i = 0; i < frogsX.size; i++){
                keyFrame = animation.getKeyFrame(elapsedTime, true);
                keyFrame.setX(frogsX.get(i));
                keyFrame.setY((frogsY.get(i)+y)%game.screenHeight);
                keyFrame.draw(game.batch);
            }
            game.batch.end();
        }
    }

    private void initializeFrogsPos(){
        frogsX = new Array<Integer>();
        frogsY = new Array<Integer>();
        frogsX.add(50);
        frogsY.add(0);
        frogsX.add(100);
        frogsY.add(20);
        frogsX.add(220);
        frogsY.add(30);
        frogsX.add(300);
        frogsY.add(60);
        frogsX.add(350);
        frogsY.add(40);
        frogsX.add(400);
        frogsY.add(30);
        frogsX.add(450);
        frogsY.add(40);
        frogsX.add(500);
        frogsY.add(20);
    }
}