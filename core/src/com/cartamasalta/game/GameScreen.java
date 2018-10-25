package com.cartamasalta.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cartamasalta.game.domain.Button;
import com.cartamasalta.game.domain.Deck;
import com.cartamasalta.game.domain.Player;
import com.cartamasalta.game.domain.Status;

import static com.cartamasalta.game.domain.Status.PLAYING_CARD;


public class GameScreen extends ScreenAdapter {

    private final App app;
    private final OrthographicCamera guiCam;
    private final Button up;
    private final Button down;

    private final Vector3 touchPoint = new Vector3();
    private final Deck deck = new Deck();
    private final BitmapFont font = new BitmapFont();

    private final Table table = new Table();;
    private final Stage stage;
    private final Label counterLabel;
    private final Label endLabel;

    private Player player = new Player();
    private Status status = Status.STAND_BY;

    public GameScreen(App app) {
        this.app = app;

        guiCam = new OrthographicCamera(App.WIDTH, App.HEIGHT);
        guiCam.position.set(App.WIDTH / 2, App.HEIGHT / 2, 0);

        FitViewport viewport = new FitViewport(App.WIDTH, App.HEIGHT, guiCam);

        up = new Button(Assets.up, new Rectangle(50, 30, 80, 80));
        down = new Button(Assets.down, new Rectangle(App.WIDTH - 50 - 80, 30, 80, 80));

        deck.initDeck(Assets.cards);

        // Prepare layout for texts
        // TODO Improve this layout. Quite messy, isn't it?
        font.getData().setScale(3);
        counterLabel = new Label("0", new Label.LabelStyle(font, Color.WHITE));
        endLabel = new Label("", new Label.LabelStyle(font, Color.BLUE));
        table.top();
        table.setPosition(App.WIDTH / 2, App.HEIGHT);
        table.row().size(App.WIDTH, 50);
        table.add().width(450);
        table.add(counterLabel);
        table.row().height(300);
        table.add(endLabel).colspan(2).center();
        stage = new Stage(viewport, app.batch);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }

    private void update() {
        if (Gdx.input.justTouched() && status == Status.STAND_BY) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (up.getPosition().contains(touchPoint.x, touchPoint.y)) {
                takeCard();
                player.setUpAction(true);
                up.setImage(Assets.upSelected);
            } else if (down.getPosition().contains(touchPoint.x, touchPoint.y)) {
                takeCard();
                down.setImage(Assets.downSelected);
            }
        }
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
        app.batch.draw(Assets.cardBack, Deck.position.x, Deck.position.y, Deck.position.width, Deck.position.height);

        gameLogic();

        app.batch.end();

        //Show score
        counterLabel.setText(String.valueOf(app.getScore()));

        stage.draw();
    }

    private void gameLogic() {
        switch (status) {
            case PLAYING_CARD:
                player.getPosition().y = player.getPosition().y - 4;
                if (isAnimationEnded()) {
                    status = Status.SHOWING_CARD;
                }
                break;
            case SHOWING_CARD:
                // Movement is done, show card
                player.setShowingCard(player.getCard().getImage());
                if (player.getCard().isUpValue() == player.isUpAction()) {
                    win();
                } else {
                    lose();
                }
                // Reset buttons
                up.setImage(Assets.up);
                down.setImage(Assets.down);
                status = Status.STAND_BY;
        }
        app.batch.draw(player.getShowingCard(), player.getPosition().x, player.getPosition().y, player.getPosition().width, player.getPosition().height);
    }


    private void takeCard() {
        endLabel.setText("");
        player = new Player(deck.getCards().get(0));
        deck.shuffle();
        status = PLAYING_CARD;
    }

    private void win() {
        endLabel.setText("You won!");
        app.saveScore(app.getScore() + 1);
    }

    private void lose() {
        app.saveScore(0);
        endLabel.setText("Loser!");
    }

    private boolean isAnimationEnded() {
        return player.getPosition().y < 130;
    }
}