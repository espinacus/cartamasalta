package com.cartamasalta.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen extends ScreenAdapter {

    App app;
    OrthographicCamera guiCam;

    public GameScreen(App app) {
        this.app = app;

        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);
    }

    @Override
    public void render(float delta) {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        guiCam.update();
        app.batch.setProjectionMatrix(guiCam.combined);
        app.batch.enableBlending();
        app.batch.begin();
        app.batch.draw(Assets.background, 0, 0, 320, 480);
        app.batch.end();
    }
}