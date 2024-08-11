package org.example.question2;

public class CoinCombinationCounter {

    // 사용할 동전들의 배열
    private final int[] coins;
    // 목표 합계
    private final int targetSum;
    // 동전 조합을 계산하는 전략
    private final CoinCombinationStrategy strategy;

    public CoinCombinationCounter(final CoinCombinationStrategy strategy, final int[] coins, final int targetSum) {
        this.strategy = strategy;
        this.coins = coins;
        this.targetSum = targetSum;
    }

    /**
     * 동전 조합 계산 전략을 초기화
     * @param strategy 새로운 동전 조합 계산 전략
     * @return 초기화된 새로운 CoinCombinationCounter 객체
     */
    public CoinCombinationCounter resetStrategy(final CoinCombinationStrategy strategy) {
        return new CoinCombinationCounter(strategy, coins, targetSum);
    }

    /**
     * 동전들과 목표 합계를 초기화
     * @param coins 동전들의 배열
     * @param targetSum 목표 합계
     * @return 초기화된 새로운 CoinCombinationCounter 객체
     */
    public CoinCombinationCounter resetCoinsAndTargetSum(int[] coins, int targetSum) {
        return new CoinCombinationCounter(strategy, coins, targetSum);
    }

    /**
     * 전략을 사용하여 동전들로 목표 합계를 만들 수 있는 방법의 수 계산
     * @return 목표 합계를 만들 수 있는 방법의 수
     */
    public int countWays() {
        return strategy.countWays(coins, targetSum);
    }
}

