package com.cartamasalta.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen extends ScreenAdapter {

    App app;
    OrthographicCamera guiCam;
    Rectangle deckPosition;

    public GameScreen(App app) {
        this.app = app;

        guiCam = new OrthographicCamera(App.WIDTH, App.HEIGHT);
        guiCam.position.set(App.WIDTH / 2, App.HEIGHT / 2, 0);
        deckPosition = new Rectangle(App.WIDTH/2-App.CARD_WIDTH/2, App.HEIGHT/2-App.CARD_HEIGHT/2, App.CARD_WIDTH, App.CARD_HEIGHT);
    }

    @Override
    public void render(float delta) {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        guiCam.update();
        app.batch.setProjectionMatrix(guiCam.combined);
        app.batch.enableBlending();
        app.batch.begin();
        app.batch.draw(Assets.background, 0, 0, App.WIDTH, App.HEIGHT);
        app.batch.draw(Assets.cardBack, deckPosition.x, deckPosition.y, deckPosition.width, deckPosition.height);
        app.batch.end();
    }
}