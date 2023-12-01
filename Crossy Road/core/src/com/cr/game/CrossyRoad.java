package com.cr.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cr.game.data.GameData;
import com.cr.game.data.ImageCache;
import com.cr.game.screens.MenuScreen;
import com.cr.game.screens.Screen;
import com.cr.game.screens.GameScreen;
import java.util.HashMap;

public class CrossyRoad extends ApplicationAdapter {
    public static final int GAME_STATE_PLAY = 0;
    public static final int GAME_STATE_PAUSE = 1;
    private static CrossyRoad instance;
    public OrthographicCamera camera;
    public Screen screen;
    public HashMap<String, Screen> screens;
    public SpriteBatch batch;
    public int screenWidth = 0;
    public int screenHeight = 0;
    public GameData gameData;
    public int currentState;
    private AssetManager assetManager;
    private final String soundPath = "sound/";

    @Override
	public void create () {
        // load resources
        assetManager = new AssetManager();

        // load sounds
        assetManager.load("sound/Bottom.mp3", Sound.class);
        assetManager.load("sound/Bush.mp3", Sound.class);
        assetManager.load("sound/Car.mp3", Sound.class);
        assetManager.load("sound/Drown.mp3", Sound.class);
        assetManager.load("sound/Jump.mp3", Sound.class);
        assetManager.load("sound/Jump_stop.mp3", Sound.class);
        assetManager.load("sound/Score.mp3", Sound.class);
        assetManager.load("sound/Top.mp3", Sound.class);
        assetManager.load("sound/Train.mp3", Sound.class);
        assetManager.finishLoading();
        batch = new SpriteBatch();
        super.create();
        camera = new OrthographicCamera(640, 480);
        camera.position.set(640 * 0.5f, 480 * 0.5f, 0);
        screenWidth = 640;
        screenHeight = 480;
        ImageCache.load();
        screens = new HashMap<String, Screen>();
        batch = new SpriteBatch();
        setScreen("MenuScreen");
        currentState = GAME_STATE_PLAY;
    }

	@Override
	public void render () {
        if (screen != null) {
            screen.update(Gdx.graphics.getDeltaTime());
        } else {
            GL20 gl = Gdx.gl;
            gl.glClearColor(0, 0, 0, 1);
            gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }
	}

    public void setScreen (String screenClassName) {
        Screen newScreen = null;
        if (screens.containsKey(screenClassName) == false) {
            if (screenClassName.equals("GameScreen")) {
                newScreen = new GameScreen(this);
                screens.put("GameScreen", newScreen);
            } else if (screenClassName.equals("MenuScreen")) {
                newScreen = new MenuScreen(this);
                screens.put("MenuScreen", newScreen);
            }
        } else {
            newScreen = screens.get(screenClassName);
        }

        if (newScreen == null) return;

        if (screen != null) {
            screen.destroy();
        }
        screen = newScreen;
        screen.createScreen();
    }
    public static CrossyRoad getInstance() {
        return instance;
    }
    public AssetManager getAssetManager() {
        return assetManager;
    }
    public void playSound(String soundName) {
        playSound(soundName, 1.0f, 1.0f, 0f);
    }

    public void playSound(String soundName, float volume, float pitch, float pan) {
        Sound sound;
        sound = assetManager.get(soundPath + soundName, Sound.class);
        sound.play(volume, pitch, pan);
    }

    @Override
    public void dispose () {
        batch.dispose();
        assetManager.dispose();
    }
}
