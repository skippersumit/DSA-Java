package com.sumit.dsa.DynamicProgramming.DpOnGrids;

import com.sumit.leetcodeInputParser.leetcodeInputParser;

import java.util.Arrays;

public class MinimumPathSum {
    /*
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
     *  which minimizes the sum of all numbers along its path.
     * Note: You can only move either down or right at any point in time.
     * */

    /*
     * ✅ Approach 1: Pure Recursion (Top-down without Memoization)
     * Time Complexity: O(2^(m * n)) — Exponential due to repeated overlapping subproblems
     * Space Complexity: O(m + n) — Recursion stack depth
     */
    public static int minPathSumRecursion(int i, int j, int[][] grid) {
        if (i == 0 && j == 0) return grid[i][j];
        if (i < 0 || j < 0) return 1000000000;
        int up = grid[i][j] + minPathSumRecursion(i - 1, j, grid);
        int down = grid[i][j] + minPathSumRecursion(i, j - 1, grid);
        return Math.min(up, down);
    }

    /*
     * ✅ Approach 2: Memoization (Top-down DP with Caching)
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n) + O(m + n) for recursion stack
     */
    public static int minPathSumDp(int i, int j, int[][] grid, int[][] dp) {
        if (i == 0 && j == 0) return grid[i][j];
        if (i < 0 || j < 0) return 1000000000;
        if (dp[i][j] != -1) return dp[i][j];
        int up = grid[i][j] + minPathSumDp(i - 1, j, grid, dp);
        int down = grid[i][j] + minPathSumDp(i, j - 1, grid, dp);
        return dp[i][j] = Math.min(up, down);
    }

    /*
     * ✅ Approach 3: Tabulation (Bottom-up DP)
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n)
     */
    public static int minPathSumTabulation(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) dp[i][j] = arr[i][j];
                else {
                    int up =1000000000 , left = 1000000000;
                    if (i > 0) up = arr[i][j] + dp[i - 1][j];
                    if (j > 0) left = arr[i][j] + dp[i][j - 1];
                    dp[i][j] = Math.min(up, left);
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        int[][] arr = leetcodeInputParser.convertBrackets2D("[[1,3,1],[1,5,1],[4,2,1]]");
        int n = arr.length;
        int m = arr[0].length;
        System.out.println("🔁 Recursion Ans: " + minPathSumRecursion(n - 1, m - 1, arr));

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        System.out.println("🧠 Memoization DP Ans: " + minPathSumDp(n - 1, m - 1, arr, dp));


        System.out.println("📋 Tabulation Ans: " + minPathSumTabulation(arr));
    }


}
