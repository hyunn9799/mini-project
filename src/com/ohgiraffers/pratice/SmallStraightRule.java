package com.ohgiraffers.pratice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SmallStraightRule extends ScoreRule {
    public SmallStraightRule() {
        super("Small Straight");
    }

    @Override
    public int calculateScore(int[] dice) {
        Set<Integer> set = new HashSet<>();
        for (int d : dice) set.add(d);

        if (set.containsAll(Arrays.asList(1,2,3,4)) ||
                set.containsAll(Arrays.asList(2,3,4,5)) ||
                set.containsAll(Arrays.asList(3,4,5,6))) {
            return 30;
        }
        return 0;
    }

    @Override
    public boolean isApplicable(int[] dice) {
        return calculateScore(dice) > 0;
    }
}