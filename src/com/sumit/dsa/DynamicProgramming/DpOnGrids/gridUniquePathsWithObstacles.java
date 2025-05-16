package com.sumit.dsa.DynamicProgramming.DpOnGrids;

import com.sumit.leetcodeInputParser.leetcodeInputParser;

import java.util.Arrays;

public class gridUniquePathsWithObstacles {

    /*
     * ✅ Recursive Approach (No Memoization)
     * TC: O(2^(m*n)) — very high due to overlapping subproblems
     * SC: O(m + n) — recursive stack space
     */
    private static int uniquePathsWithObstaclesRecursion(int i, int j, int[][] arr) {
        if (i >= 0 && j >= 0 && arr[i][j] == 1) return 0; // Obstacle cell
        if (i == 0 && j == 0) return 1;                   // Start cell
        if (i < 0 || j < 0) return 0;                     // Out of bounds

        int up = uniquePathsWithObstaclesRecursion(i - 1, j, arr);
        int left = uniquePathsWithObstaclesRecursion(i, j - 1, arr);
        return up + left;
    }

    /*
     * ✅ Top-down DP (Memoization)
     * TC: O(m*n)
     * SC: O(m*n) + O(m + n) stack space
     */
    private static int uniquePathsWithObstaclesDp(int i, int j, int[][] arr, int[][] dp) {
        if (i >= 0 && j >= 0 && arr[i][j] == 1) return 0;
        if (i == 0 && j == 0) return 1;
        if (i < 0 || j < 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        int up = uniquePathsWithObstaclesDp(i - 1, j, arr, dp);   // FIXED: used correct method
        int left = uniquePathsWithObstaclesDp(i, j - 1, arr, dp);

        return dp[i][j] = up + left;
    }

    /*
     * ✅ Bottom-up DP (Tabulation)
     * TC: O(m*n)
     * SC: O(m*n)
     */
    private static int uniquePathsWithObstaclesTabulation(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) {
                    dp[i][j] = 0; // Obstacle cell
                } else if (i == 0 && j == 0) {
                    dp[i][j] = 1; // Start point
                } else {
                    int up = (i > 0) ? dp[i - 1][j] : 0;
                    int left = (j > 0) ? dp[i][j - 1] : 0;
                    dp[i][j] = up + left;
                }
            }
        }

        return dp[n - 1][m - 1]; // Final destination
    }

    // 🔍 Driver Code
    public static void main(String[] args) {
        int[][] arr = leetcodeInputParser.convertBrackets2D("[[0,0,0],[0,1,0],[0,0,0]]");
        int n = arr.length;
        int m = arr[0].length;

        System.out.println("🔁 Recursion Ans: " + uniquePathsWithObstaclesRecursion(n - 1, m - 1, arr));

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        System.out.println("🧠 Memoization DP Ans: " + uniquePathsWithObstaclesDp(n - 1, m - 1, arr, dp));

        System.out.println("📋 Tabulation Ans: " + uniquePathsWithObstaclesTabulation(arr));
    }
}
