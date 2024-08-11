package org.example.question2;

public class Main {
    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int targetSum = 4;

        // 동적 프로그래밍 전략을 사용하여 동전 조합 계산
        CoinCombinationStrategy dpStrategy = new DynamicProgrammingCoinCombinationStrategy();
        CoinCombinationCounter coinCombinationCounter = new CoinCombinationCounter(dpStrategy, coins, targetSum);
        System.out.println(coinCombinationCounter.countWays());

        int[] coins2 = {2, 5, 3, 6};
        int targetSum2 = 10;
        final CoinCombinationCounter coinCombinationCounter2 = coinCombinationCounter.resetCoinsAndTargetSum(coins2, targetSum2);
        System.out.println(coinCombinationCounter2.countWays());
    }
}
