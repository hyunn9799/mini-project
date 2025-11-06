package com.ohgiraffers.pratice;

public class NumberRule extends ScoreRule {
    private int number;

    public NumberRule(String name, int number) {
        super(name);
        this.number = number;
    }

    @Override
    public int calculateScore(int[] dice) {
        int sum = 0;
        for (int d : dice) {
            if (d == number) sum += number;
        }
        return sum;
    }

    @Override
    public boolean isApplicable(int[] dice) {
        return true;
    }
}