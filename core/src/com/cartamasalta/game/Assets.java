package com.cartamasalta.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static TextureRegion background;
    public static Texture cards;
    public static TextureRegion cardBack;
    public static TextureRegion up;
    public static TextureRegion down;
    public static TextureRegion upSelected;
    public static TextureRegion downSelected;
    public static TextureRegion playButton;

    private static Texture loadTexture(String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load() {
        background = new TextureRegion(loadTexture("data/background.png"), 0, 0, 320, 480);
        cards = loadTexture("data/cards.png");
        cardBack = new TextureRegion(cards, 208, 319 * 4, 208, 319);

        //TODO Find better way to keep the button images
        down = new TextureRegion(loadTexture("data/down.png"), 0, 0, 512, 512);
        up = new TextureRegion(loadTexture("data/down.png"), 0, 0, 512, 512);
        up.flip(false, true);
        downSelected = new TextureRegion(loadTexture("data/down-blue.png"), 0, 0, 512, 512);
        upSelected = new TextureRegion(loadTexture("data/down-blue.png"), 0, 0, 512, 512);
        upSelected.flip(false, true);
        playButton = new TextureRegion(loadTexture("data/play.png"), 500, 230);
    }
}
