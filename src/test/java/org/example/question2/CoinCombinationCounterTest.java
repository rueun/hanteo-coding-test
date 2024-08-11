package org.example.question2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoinCombinationCounterTest {

    @Test
    void testCoinCombinations_case1() {
        // 첫 번째 테스트 케이스: 동전 [1, 2, 5]로 목표 합계 5를 만드는 방법의 수
        int[] coins = {1, 2, 5};
        int targetSum = 5;
        CoinCombinationCounter counter = new CoinCombinationCounter(coins, targetSum);
        int ways = counter.countWays();
        assertEquals(4, ways);  // 1+1+1+1+1, 1+1+1+2, 1+2+2, 5 => 총 4가지 방법
    }

    @Test
    void testCoinCombinations_case2() {
        // 두 번째 테스트 케이스: 동전 [2, 3]로 목표 합계 7을 만드는 방법의 수
        int[] coins = {1, 2};
        int targetSum = 0;
        CoinCombinationCounter counter = new CoinCombinationCounter(coins, targetSum);
        int ways = counter.countWays();
        assertEquals(1, ways);  // 2+2+3 => 총 1가지 방법
    }

    @Test
    void testCoinCombinations_case3() {
        // 세 번째 테스트 케이스: 동전 [10]로 목표 합계 10을 만드는 방법의 수
        int[] coins = {10};
        int targetSum = 10;
        CoinCombinationCounter counter = new CoinCombinationCounter(coins, targetSum);
        int ways = counter.countWays();
        assertEquals(1, ways);  // 10 => 총 1가지 방법
    }

    @Test
    void testResetCoinsAndTargetSum() {
        // 초기 설정: 동전 [1, 2]로 목표 합계 3을 만드는 방법의 수
        int[] initialCoins = {1, 2};
        int initialTargetSum = 3;
        CoinCombinationCounter counter = new CoinCombinationCounter(initialCoins, initialTargetSum);
        int initialWays = counter.countWays();
        assertEquals(2, initialWays);  // 1+1+1, 1+2 => 총 2가지 방법

        // 새로 설정: 동전 [2, 5]로 목표 합계 10을 만드는 방법의 수
        int[] newCoins = {2, 5};
        int newTargetSum = 10;
        counter = counter.resetCoinsAndTargetSum(newCoins, newTargetSum);
        int newWays = counter.countWays();
        assertEquals(2, newWays);  // 2+2+2+2+2, 5+5 총 2가지 방법
    }
}
