package com.cartamasalta.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Card {
    Rectangle position;
    boolean upValue;
    TextureRegion image;

    public Card() {
        position = new Rectangle(App.WIDTH / 2 - App.CARD_WIDTH / 2, App.HEIGHT - App.CARD_HEIGHT - 50, App.CARD_WIDTH, App.CARD_HEIGHT);
        image = Assets.cardBack;
    }
}
