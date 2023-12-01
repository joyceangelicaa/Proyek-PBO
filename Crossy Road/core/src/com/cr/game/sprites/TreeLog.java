package com.cr.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.cr.game.data.ImageCache;

public class TreeLog extends MovingSprite {

    public TreeLog(com.cr.game.CrossyRoad game, float x, float y, String textureRegion) {
        super(game, x, y);
        this.game = game;
        setLogTexture(ImageCache.getTexture(textureRegion));
        game.screen.elements.add(this);
    }

    private void setLogTexture(TextureRegion region){
        setRegion(region);
        setColor(1, 1, 1, 1);
        setSize(region.getRegionWidth(), region.getRegionHeight());
        setOrigin(getWidth() / 2, getHeight() / 2);
    }
}
