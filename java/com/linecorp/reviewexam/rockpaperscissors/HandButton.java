package com.linecorp.reviewexam.rockpaperscissors;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;

/**
 * A {@link Button} to play a hand. For each hand, a user can play 10 times at most.
 * This also controls a playable count left.
 */
public class HandButton extends AppCompatButton {

    /**
     * Playable count for this hand.
     */
    private Hand hand;

    public HandButton(Context context) {
        super(context);
    }

    public HandButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setHand(Hand hand) {
        this.hand = hand;
        updateText();
    }

    /**
     * Updates button text by means of {@link #getHandText()} and the rest count.
     */
    public void updateText() {
        setText(getHandText() + "\nrest: " + hand.getRest());
    }

    public int getRest() {
        return hand.getRest();
    }

    public void play() {
        hand.setRest(hand.getRest() - 1);
    }

    /**
     * Returns a hand name corresponding to this button.
     */
    public String getHandText() {
        return hand.getName();
    }

    public Hand getHand() {
        return hand;
    }
}
