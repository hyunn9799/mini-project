package com.ohgiraffers.pratice;

import java.util.*;


public class YachtDiceGame {
    private static List<ScoreRule> rules = new ArrayList<>();
    private static Map<String, Integer> scoreboard = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initRules();
        System.out.println("🎲 요트다이스 게임에 오신 걸 환영합니다! 🎲\n");

        for (int turn = 1; turn <= 12; turn++) {
            System.out.println("=== 턴 " + turn + " ===");
            int[] dice = rollDice();
            System.out.println("주사위: " + Arrays.toString(dice));

            showAvailableRules(dice);
            selectRule(dice);
            showScoreboard();
        }

        int total = scoreboard.values().stream()
                .filter(score -> score != -1) // -1(미사용) 제외
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("\n🎉 게임 종료! 총점: " + total + "점 🎉");
    }

    private static void initRules() {
        rules.add(new NumberRule("Ones", 1));
        rules.add(new NumberRule("Twos", 2));
        rules.add(new NumberRule("Threes", 3));
        rules.add(new NumberRule("Fours", 4));
        rules.add(new NumberRule("Fives", 5));
        rules.add(new NumberRule("Sixes", 6));
        rules.add(new ThreeOfAKindRule());
        rules.add(new FourOfAKindRule());
        rules.add(new FullHouseRule());
        rules.add(new SmallStraightRule());
        rules.add(new LargeStraightRule());
        rules.add(new YachtRule());
        rules.add(new ChoiceRule());

        for (ScoreRule rule : rules) {
            scoreboard.put(rule.getName(), -1); // -1 = 미사용
        }
    }

    private static int[] rollDice() {
        Random r = new Random();
        int[] dice = new int[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = r.nextInt(6) + 1;
        }
        Arrays.sort(dice);
        return dice;
    }

    private static void showAvailableRules(int[] dice) {
        System.out.println("\n선택 가능한 항목:");
        for (int i = 0; i < rules.size(); i++) {
            ScoreRule rule = rules.get(i);
            if (scoreboard.get(rule.getName()) == -1) {
                int score = rule.calculateScore(dice);
                String mark = rule.isApplicable(dice) ? "⭕" : "❌";
                System.out.printf("%d. %s: %d점 %s%n",
                        i+1, rule.getName(), score, mark);
            }
        }
    }

    private static void selectRule(int[] dice) {
        while (true) {
            System.out.print("\n선택할 번호를 입력하세요 (1~" + rules.size() + "): ");
            if (!sc.hasNextInt()) {
                System.out.println("숫자를 입력해 주세요!");
                sc.next();
                continue;
            }
            int choice = sc.nextInt();

            if (choice < 1 || choice > rules.size()) {
                System.out.println("잘못된 번호입니다!");
                continue;
            }

            ScoreRule selected = rules.get(choice - 1);
            if (scoreboard.get(selected.getName()) != -1) {
                System.out.println("이미 사용한 항목입니다!");
                continue;
            }

            int score = selected.calculateScore(dice);
            scoreboard.put(selected.getName(), score);
            System.out.println("✅ " + selected.getName() + " 선택! " + score + "점 획득!");
            break;
        }
    }

    private static void showScoreboard() {
        System.out.println("\n현재 점수판:");
        int total = 0;
        for (String name : scoreboard.keySet()) {
            int score = scoreboard.get(name);
            String status = score == -1 ? "미사용" : score + "점";
            System.out.printf("  %-18s : %s%n", name, status);
            if (score != -1) total += score;
        }
        System.out.println("  총점: " + total + "점");
        System.out.println("─".repeat(40));
    }
}