package com.cartamasalta.game.domain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Button {

    private Rectangle position;
    private TextureRegion image;

    public Button(TextureRegion image, Rectangle position) {
        this.image = image;
        this.position = position;
    }

    public Rectangle getPosition() {
        return position;
    }

    public void setImage(TextureRegion image) {
        this.image = image;
    }

    public TextureRegion getImage() {
        return image;
    }
}
