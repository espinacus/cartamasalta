package com.cartamasalta.game.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.cartamasalta.game.App;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    public static final Rectangle position = new Rectangle(
            App.WIDTH / 2 - Card.CARD_WIDTH / 2,
            App.HEIGHT - Card.CARD_HEIGHT - 50,
            Card.CARD_WIDTH,
            Card.CARD_HEIGHT);

    final ArrayList<Card> cards = new ArrayList<Card>();

    public void initDeck(Texture texture) {
        for (int j = 0; j <= 3; j++) {
            for (int i = 0; i <= 11; i++) {
                TextureRegion image = new TextureRegion(texture, i * 208, 319 * j, 208, 319);
                Card card = new Card(image, i > 8);

                cards.add(card);
            }
        }
        shuffle();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
