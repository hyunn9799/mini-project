package com.ohgiraffers.pratice;

import java.util.Arrays;

public class ChoiceRule extends ScoreRule {
    public ChoiceRule() {
        super("Choice");
    }

    @Override
    public int calculateScore(int[] dice) {
        return Arrays.stream(dice).sum();
    }

    @Override
    public boolean isApplicable(int[] dice) {
        return true;
    }
}