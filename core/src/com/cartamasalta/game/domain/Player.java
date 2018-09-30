package com.cartamasalta.game.domain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.cartamasalta.game.Assets;

public class Player {
    private final Card card;
    private final Rectangle position;
    private TextureRegion showingCard;
    private boolean upAction;

    public Player(){
        this(new Card());
    }

    public Player(Card card){
        this.card = card;
        position = new Rectangle(Deck.position.x, Deck.position.y, Deck.position.width, Deck.position.height);
        showingCard = Assets.cardBack;
    }

    public Card getCard() {
        return card;
    }

    public Rectangle getPosition() {
        return position;
    }

    public TextureRegion getShowingCard() {
        return showingCard;
    }

    public void setShowingCard(TextureRegion showingCard) {
        this.showingCard = showingCard;
    }

    public boolean isUpAction() {
        return upAction;
    }

    public void setUpAction(boolean upAction) {
        this.upAction = upAction;
    }
}
