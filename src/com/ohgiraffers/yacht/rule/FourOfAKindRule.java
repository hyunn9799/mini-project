package com.ohgiraffers.yacht.rule;
import java.util.Arrays;
import java.util.Map;

import static com.ohgiraffers.yacht.util.YachtUtils.countDice;

public class FourOfAKindRule extends ScoreRule {
    public FourOfAKindRule() {
        super("Four of a Kind");
    }

    @Override
    public int calculateScore(int[] dice) {
        Map<Integer, Integer> count = countDice(dice);

        for (int key : count.keySet()) {
            if (count.get(key) >= 4) {
                return Arrays.stream(dice).sum();
            }
        }
        return 0;
    }

    @Override
    public boolean isApplicable(int[] dice) {
        return calculateScore(dice) > 0;
    }
}