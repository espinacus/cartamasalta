package com.cartamasalta.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class App extends Game {

    public static final int WIDTH = 320;
    public static final int HEIGHT = 480;

    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Assets.load();
        setScreen(new MenuScreen(this));
    }
}
