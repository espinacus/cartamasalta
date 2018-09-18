package com.cartamasalta.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Card {

    public static final int CARD_WIDTH = 80;
    public static final int CARD_HEIGHT = 128;

    private Rectangle position;
    private boolean upValue;
    private TextureRegion image;

    public Card(TextureRegion image, boolean upValue) {
        this.image = image;
        this.upValue = upValue;
    }

    public boolean isUpValue() {
        return upValue;
    }

    public TextureRegion getImage() {
        return image;
    }

    public Rectangle getPosition() {
        return position;
    }

    public void setPosition(Rectangle position) {
        this.position = position;
    }
}
