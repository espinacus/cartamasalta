package com.cartamasalta.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class GameScreen extends ScreenAdapter {

    App app;
    OrthographicCamera guiCam;
    Rectangle deckPosition;
    Rectangle upLocation;
    Rectangle downLocation;

    Vector3 touchPoint;
    Deck deck;
    Card myCard;

    BitmapFont font;

    boolean upAction;
    String message;

    public GameScreen(App app) {
        this.app = app;

        guiCam = new OrthographicCamera(App.WIDTH, App.HEIGHT);
        guiCam.position.set(App.WIDTH / 2, App.HEIGHT / 2, 0);
        font = new BitmapFont();
        touchPoint = new Vector3();

        deckPosition = new Rectangle(App.WIDTH / 2 - App.CARD_WIDTH / 2, App.HEIGHT - App.CARD_HEIGHT - 50, App.CARD_WIDTH, App.CARD_HEIGHT);
        upLocation = new Rectangle(50, 30, 80, 80);
        downLocation = new Rectangle(App.WIDTH - 50 - 80, 30, 80, 80);

        deck = new Deck();
        deck.initDeck(Assets.cards);
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }

    private void draw() {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        guiCam.update();
        app.batch.setProjectionMatrix(guiCam.combined);
        app.batch.enableBlending();
        app.batch.begin();
        app.batch.draw(Assets.background, 0, 0, App.WIDTH, App.HEIGHT);
        app.batch.draw(Assets.up, upLocation.x, upLocation.y, upLocation.width, upLocation.height);
        app.batch.draw(Assets.down, downLocation.x, downLocation.y, downLocation.width, downLocation.height);

        app.batch.draw(Assets.cardBack, deckPosition.x, deckPosition.y, deckPosition.width, deckPosition.height);

        if (myCard != null) {
            TextureRegion image = Assets.cardBack;
            if (myCard.position.y > 130) {
                myCard.position.y = myCard.position.y - 4;
            } else {
                image = myCard.image;
                if (myCard.upValue == upAction) {
                    message = " You won!";
                } else {
                    message = "  Loser! ";
                }
            }
            app.batch.draw(image, myCard.position.x, myCard.position.y, myCard.position.width, myCard.position.height);
        }
        app.batch.end();

        if (message!= null){
            app.batch.begin();
            font.setColor(Color.BLUE);
            font.draw(app.batch, message, 100, 290);
            font.getData().setScale(2);
            app.batch.end();
        }

    }

    private void update() {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (upLocation.contains(touchPoint.x, touchPoint.y)) {
                takeCard();
                upAction = true;
            } else if (downLocation.contains(touchPoint.x, touchPoint.y)) {
                takeCard();
                upAction = false;
            }
        }
    }

    private void takeCard() {
        message = null;
        myCard = deck.cards.get(0);
        myCard.position = new Rectangle(deckPosition.x, deckPosition.y, deckPosition.width, deckPosition.height);
        deck.shuffle();
    }
}