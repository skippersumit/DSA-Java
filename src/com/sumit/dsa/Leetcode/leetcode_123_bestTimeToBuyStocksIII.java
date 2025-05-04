package com.sumit.dsa.Leetcode;

import java.util.Arrays;

public class leetcode_123_bestTimeToBuyStocksIII {
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][3][2]; // dp[day][transactions_left][buy/sell]

        for (int[][] layer : dp) {
            for (int[] arr : layer) {
                Arrays.fill(arr, -1);
            }
        }
        return find(0, 2, 1, n, prices, dp);
    }

    private static int find(int day, int trans, int buy, int n, int[] prices, int[][][] dp) {
        if (day == n || trans == 0) return 0;

        if (dp[day][trans][buy] != -1) return dp[day][trans][buy];

        if (buy != 0) { // buying case
            return dp[day][trans][buy] = Math.max(
                    -prices[day] + find(day + 1, trans, 0, n, prices, dp),
                    find(day + 1, trans, 1, n, prices, dp)
            );
        } else { // selling
            return dp[day][trans][buy] = Math.max(
                    prices[day] + find(day + 1, trans - 1, 1, n, prices, dp),
                    find(day + 1, trans, 0, n, prices, dp)
            );
        }
    }

    public static void main(String[] args) {

        /*
         * You are given an array prices where prices[i] is the price of a given stock on the ith day.
         * Find the maximum profit you can achieve. You may complete at most two transactions.
         * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
         */

        int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int ans = maxProfit(prices);
        System.out.println(Arrays.toString(prices) + " Ans = " + ans);

        prices = new int[]{1, 2, 3, 4, 5};
        ans = maxProfit(prices);
        System.out.println(Arrays.toString(prices) + " Ans = " + ans);

        prices = new int[]{7, 6, 4, 3, 1};
        ans = maxProfit(prices);
        System.out.println(Arrays.toString(prices) + " Ans = " + ans);

    }
}
