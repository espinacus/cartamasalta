package com.cartamasalta.game.domain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.cartamasalta.game.Assets;

public class Card {

    public static final int CARD_WIDTH = 80;
    public static final int CARD_HEIGHT = 128;

    private boolean upValue;
    private TextureRegion image = Assets.cardBack;

    public Card() {}

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
}
