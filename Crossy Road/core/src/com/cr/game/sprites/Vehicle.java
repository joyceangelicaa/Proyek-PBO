package com.cr.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.cr.game.data.ImageCache;

public class Vehicle extends MovingSprite {

    public Vehicle (com.cr.game.CrossyRoad game, float x, float y, String textureRegion) {
        super(game, x, y);
        this.game = game;
        setVehicle(ImageCache.getTexture(textureRegion));
        game.screen.elements.add(this);
    }

    private void setVehicle(TextureRegion region){
        setRegion(region);
        setColor(1, 1, 1, 1);
        setSize(region.getRegionWidth(), region.getRegionHeight());
        setOrigin(getWidth() / 2, getHeight() / 2);
    }

}
