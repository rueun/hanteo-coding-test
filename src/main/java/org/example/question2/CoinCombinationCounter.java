package org.example.question2;

public class CoinCombinationCounter {

    // 사용할 동전들의 배열
    private final int[] coins;
    // 목표 합계
    private final int targetSum;
    // 목표 합계를 만들 수 있는 방법의 수를 저장하는 배열
    private final int[] wayCounts;

    public CoinCombinationCounter(int[] coins, int targetSum) {
        this.coins = coins;
        this.targetSum = targetSum;
        this.wayCounts = new int[targetSum + 1];
    }

    /**
     * 동전들로 입력된 합계를 만들 수 있는 방법의 수를 계산하는 함수
     * @return 목표 합계를 만들 수 있는 방법의 수
     */

    public int countWays() {
        // 목표 합계가 0인 경우는 아무 동전도 사용하지 않는 한 가지 방법 존재
        wayCounts[0] = 1;

        for (int coin : coins) {
            for (int sum = coin; sum <= targetSum; sum++) {
                // sum 금액을 만들 수 있는 방법의 수 = 기존 sum 금액을 만들 수 있는 방법의 수 + coin 금액을 뺀 금액을 만들 수 있는 방법의 수
                wayCounts[sum] += wayCounts[sum - coin];
            }
        }
        return wayCounts[targetSum];
    }
}

