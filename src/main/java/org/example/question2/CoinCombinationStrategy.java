package org.example.question2;

// 동전 조합을 계산하는 전략
public interface CoinCombinationStrategy {
    int countWays(int[] coins, int targetSum);
}