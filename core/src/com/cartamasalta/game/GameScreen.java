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

    private final App app;
    private final OrthographicCamera guiCam;
    private final Rectangle deckPosition;
    private final Button up;
    private final Button down;
    private final Vector3 touchPoint;
    private final Deck deck;
    private final BitmapFont font;
    private boolean upAction;
    private Card myCard;
    private String message;

    public GameScreen(App app) {
        this.app = app;

        guiCam = new OrthographicCamera(App.WIDTH, App.HEIGHT);
        guiCam.position.set(App.WIDTH / 2, App.HEIGHT / 2, 0);
        font = new BitmapFont();
        touchPoint = new Vector3();

        deckPosition = Deck.position;
        up = new Button(Assets.up, new Rectangle(50, 30, 80, 80));
        down = new Button(Assets.down, new Rectangle(App.WIDTH - 50 - 80, 30, 80, 80));

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
        app.batch.draw(up.getImage(), up.getPosition().x, up.getPosition().y, up.getPosition().width, up.getPosition().height);
        app.batch.draw(down.getImage(), down.getPosition().x, down.getPosition().y, down.getPosition().width, down.getPosition().height);

        app.batch.draw(Assets.cardBack, deckPosition.x, deckPosition.y, deckPosition.width, deckPosition.height);

        if (myCard != null) {
            playingCard();
        }
        app.batch.end();

        if (message != null) {
            app.batch.begin();
            font.setColor(Color.BLUE);
            font.draw(app.batch, message, 100, 290);
            font.getData().setScale(2);
            app.batch.end();
        }

    }

    private void playingCard() {
        TextureRegion image = Assets.cardBack;
        if (!isAnimationEnded()) {
            myCard.getPosition().y = myCard.getPosition().y - 4;
        } else {
            // Movement is done, show card
            image = myCard.getImage();
            if (myCard.isUpValue() == upAction) {
                message = " You won!";
            } else {
                message = "  Loser! ";
            }
            // Reset buttons
            up.setImage(Assets.up);
            down.setImage(Assets.down);
        }
        app.batch.draw(image, myCard.getPosition().x, myCard.getPosition().y, myCard.getPosition().width, myCard.getPosition().height);
    }

    private boolean isAnimationEnded() {
        return myCard.getPosition().y < 130;
    }

    private void update() {
        if (Gdx.input.justTouched() && (myCard == null || isAnimationEnded())) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (up.getPosition().contains(touchPoint.x, touchPoint.y)) {
                takeCard();
                upAction = true;
                up.setImage(Assets.upSelected);
            } else if (down.getPosition().contains(touchPoint.x, touchPoint.y)) {
                takeCard();
                upAction = false;
                down.setImage(Assets.downSelected);
            }
        }
    }

    private void takeCard() {
        message = null;
        myCard = deck.cards.get(0);
        myCard.setPosition(new Rectangle(deckPosition.x, deckPosition.y, deckPosition.width, deckPosition.height));
        deck.shuffle();
    }
}