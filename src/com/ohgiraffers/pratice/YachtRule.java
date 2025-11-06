package com.ohgiraffers.pratice;

import java.util.Map;

import static com.ohgiraffers.pratice.YachtUtils.countDice;

public class YachtRule extends ScoreRule {
    public YachtRule() {
        super("Yacht");
    }

    @Override
    public int calculateScore(int[] dice) {
        Map<Integer, Integer> count = countDice(dice);

        if (count.size() != 1) {
            return 0;
        }

        if (count.get(dice[0]) == 5) {
            return 50;
        }

        return 0;
    }

    @Override
    public boolean isApplicable(int[] dice) {
        return calculateScore(dice) > 0;
    }
}