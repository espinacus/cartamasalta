package com.cartamasalta.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    final ArrayList<Card> cards = new ArrayList<Card>();

    public void initDeck(Texture texture) {
        for (int j = 0; j <= 3; j++) {
            for (int i = 0; i <= 11; i++) {
                Card card = new Card();
                card.upValue = i > 8;
                card.image = new TextureRegion(texture, i * 208, 319 * j, 208, 319);
                cards.add(card);
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
