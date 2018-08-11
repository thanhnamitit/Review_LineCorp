package com.linecorp.reviewexam.rockpaperscissors;

/**
 * A model class which represents one of rock-paper-scissors hands.
 */
public class Hand {


    private final int handValue;
    private final String name;
    private int rest;

    public Hand(int handValue, String name, int rest) {
        this.handValue = handValue;
        this.name = name;
        this.rest = rest;
    }

    @Override
    public String toString() {
        return name;
    }

    public int compareTo(Hand another) {
        return HandManager.compare(this, another);
    }

    public int getHandValue() {
        return handValue;
    }

    public String getName() {
        return name;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }
}
