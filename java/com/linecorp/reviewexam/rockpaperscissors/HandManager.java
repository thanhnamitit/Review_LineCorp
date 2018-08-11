package com.linecorp.reviewexam.rockpaperscissors;

public class HandManager {

    public static int DEFAULT_REST = 10;

    public static class Value {
        public static final int ROCK = 1;
        public static final int PAPER = 2;
        public static final int SCISSORS = 3;
    }

    public static class Name {
        public static final String ROCK = "ROCK";
        public static final String PAPER = "PAPER";
        public static final String SCISSORS = "SCISSORS";
    }

    public static class Object {
        public static final Hand ROCK = new Hand(Value.ROCK, Name.ROCK, DEFAULT_REST);
        public static final Hand PAPER = new Hand(Value.PAPER, Name.PAPER, DEFAULT_REST);
        public static final Hand SCISSORS = new Hand(Value.SCISSORS, Name.SCISSORS, DEFAULT_REST);
    }


    public static int compare(Hand a, Hand b) {
        if ((a.getHandValue() == Value.ROCK || a.getHandValue() == Value.SCISSORS) && (b.getHandValue() == Value.ROCK || b.getHandValue() == Value.SCISSORS)) {
            return a.getHandValue() - b.getHandValue();
        }
        return -a.getHandValue() + b.getHandValue();
    }

    private static final Hand[] HANDS = {
            Object.ROCK,
            Object.PAPER,
            Object.SCISSORS
    };

    public static Hand fromInt(int i) {
        return HANDS[i % HANDS.length];
    }

}
