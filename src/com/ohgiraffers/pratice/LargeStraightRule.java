package com.ohgiraffers.pratice;

import java.util.Arrays;

public class LargeStraightRule extends ScoreRule {
    public LargeStraightRule() {
        super("Large Straight");
    }

    @Override
    public int calculateScore(int[] dice) {
        int[] sortedDice = Arrays.copyOf(dice, dice.length);
        Arrays.sort(sortedDice);

        boolean isSeq = true;
        for (int i = 1; i < sortedDice.length; i++) {
            if (sortedDice[i] != sortedDice[i-1] + 1) {
                isSeq = false;
                break;
            }
        }

        return isSeq ? 40 : 0;
    }

    @Override
    public boolean isApplicable(int[] dice) {
        return calculateScore(dice) > 0;
    }
}