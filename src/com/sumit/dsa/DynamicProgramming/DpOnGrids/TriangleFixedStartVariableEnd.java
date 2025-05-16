package com.sumit.dsa.DynamicProgramming.DpOnGrids;

import com.sumit.leetcodeInputParser.leetcodeInputParser;

import java.util.Arrays;

public class TriangleFixedStartVariableEnd {

    /*
     * ✅ Approach 1: Recursion
     * Time Complexity: O(2^n) — exponential due to overlapping subproblems
     * Space Complexity: O(n) — recursion stack
     */
    public static int minimumTotalRecursion(int i, int j, int[][] arr) {
        int n = arr.length;

        // Base case: bottom row
        if (i == n - 1) return arr[n - 1][j];

        // Recursively calculate down and diagonal path sums
        int down = arr[i][j] + minimumTotalRecursion(i + 1, j, arr);
        int diagonal = arr[i][j] + minimumTotalRecursion(i + 1, j + 1, arr);

        return Math.min(down, diagonal);
    }

    /*
     * ✅ Approach 2: Memoization (Top-down DP)
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2) + O(n) for recursion stack
     */
    public static int minimumTotalDp(int i, int j, int[][] arr, int[][] dp) {
        int n = arr.length;

        // Base case
        if (i == n - 1) return arr[n - 1][j];

        // If already computed, return the stored value
        if (dp[i][j] != -1) return dp[i][j];

        int down = arr[i][j] + minimumTotalDp(i + 1, j, arr, dp);
        int diagonal = arr[i][j] + minimumTotalDp(i + 1, j + 1, arr, dp);

        return dp[i][j] = Math.min(down, diagonal);
    }

    /*
     * ✅ Approach 3: Tabulation (Bottom-up DP)
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     */
    public static int minimumTotalTabulation(int[][] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];

        // Base case: bottom row is the same
        System.arraycopy(arr[n - 1], 0, dp[n - 1], 0, n);

        // Build from second-last row to top
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int down = arr[i][j] + dp[i + 1][j];
                int diagonal = arr[i][j] + dp[i + 1][j + 1];

                dp[i][j] = Math.min(down, diagonal);
            }
        }

        // Final answer is stored at the top of triangle
        return dp[0][0];
    }

    // 🔍 Driver Code
    public static void main(String[] args) {
        // Sample triangle input
        int[][] arr = leetcodeInputParser.convertBrackets2D("[[2],[3,4],[6,5,7],[4,1,8,3]]");

        int n = arr.length;
        System.out.println("🔁 Recursion Ans: " + minimumTotalRecursion(0, 0, arr));

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        System.out.println("🧠 Memoization DP Ans: " + minimumTotalDp(0, 0, arr, dp));

        System.out.println("📋 Tabulation Ans: " + minimumTotalTabulation(arr));
    }
}
