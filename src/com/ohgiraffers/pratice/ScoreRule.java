package com.ohgiraffers.pratice;

public abstract class ScoreRule {
    protected String name;

    public ScoreRule(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int calculateScore(int[] dice);

    public abstract boolean isApplicable(int[] dice);
}