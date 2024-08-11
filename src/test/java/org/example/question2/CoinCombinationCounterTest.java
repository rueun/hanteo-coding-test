package org.example.question2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoinCombinationCounterTest {

    @Test
    void testCoinCombinations_case1() {
        // 첫 번째 테스트 케이스: 동전 [1, 2, 5]로 목표 합계 5를 만드는 방법의 수
        int[] coins = {1, 2, 5};
        int targetSum = 5;

        CoinCombinationStrategy strategy = new DynamicProgrammingCoinCombinationStrategy();
        CoinCombinationCounter counter = new CoinCombinationCounter(strategy, coins, targetSum);

        int ways = counter.countWays();
        assertEquals(4, ways);  // 1+1+1+1+1, 1+1+1+2, 1+2+2, 5 => 총 4가지 방법
    }

    @Test
    void testCoinCombinations_case2() {
        // 두 번째 테스트 케이스: 동전 [1, 2]로 목표 합계 0을 만드는 방법의 수
        int[] coins = {1, 2};
        int targetSum = 0;

        CoinCombinationStrategy strategy = new DynamicProgrammingCoinCombinationStrategy();
        CoinCombinationCounter counter = new CoinCombinationCounter(strategy, coins, targetSum);

        int ways = counter.countWays();
        assertEquals(1, ways);  // 목표 합계가 0이므로, 아무 동전도 사용하지 않는 1가지 방법 존재
    }

    @Test
    void testCoinCombinations_case3() {
        // 세 번째 테스트 케이스: 동전 [10]로 목표 합계 10을 만드는 방법의 수
        int[] coins = {10};
        int targetSum = 10;

        CoinCombinationStrategy strategy = new DynamicProgrammingCoinCombinationStrategy();
        CoinCombinationCounter counter = new CoinCombinationCounter(strategy, coins, targetSum);

        int ways = counter.countWays();
        assertEquals(1, ways);  // 10 => 총 1가지 방법
    }

    @Test
    void testResetCoinsAndTargetSum() {
        // 초기 설정: 동전 [1, 2]로 목표 합계 3을 만드는 방법의 수
        int[] initialCoins = {1, 2, 3};
        int initialTargetSum = 4;

        CoinCombinationStrategy strategy = new DynamicProgrammingCoinCombinationStrategy();
        CoinCombinationCounter counter = new CoinCombinationCounter(strategy, initialCoins, initialTargetSum);

        int initialWays = counter.countWays();
        assertEquals(4, initialWays);  // 1+1+1+1, 1+1+2, 1+3, 2+2 => 총 4가지 방법

        // 새로 설정: 동전 [2, 5]로 목표 합계 10을 만드는 방법의 수
        int[] newCoins = {2, 5, 3, 6};
        int newTargetSum = 10;

        // 동전들과 목표 합계를 초기화
        final CoinCombinationCounter counter2 = counter.resetCoinsAndTargetSum(newCoins, newTargetSum);
        int newWays = counter2.countWays();
        assertEquals(5, newWays);  // 2+2+2+2+2, 2+2+3+3, 2+2+6, 2+3+5, 5+5 => 총 5가지 방법
    }
}
