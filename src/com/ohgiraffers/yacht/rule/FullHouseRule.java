package com.ohgiraffers.yacht.rule;

import java.util.Map;

import static com.ohgiraffers.yacht.util.YachtUtils.countDice;

public class FullHouseRule extends ScoreRule {
    public FullHouseRule() {
        super("Full House");
    }

    @Override
    public int calculateScore(int[] dice) {
        Map<Integer, Integer> count = countDice(dice);

        if (count.size() != 2) {
            return 0;
        }

        boolean hasThree = false, hasTwo = false;

        for (int c : count.values()) {
            if (c == 3) hasThree = true;
            if (c == 2) hasTwo = true;
        }

        return (hasThree && hasTwo) ? 25 : 0;
    }

    @Override
    public boolean isApplicable(int[] dice) {
        return calculateScore(dice) > 0;
    }
}