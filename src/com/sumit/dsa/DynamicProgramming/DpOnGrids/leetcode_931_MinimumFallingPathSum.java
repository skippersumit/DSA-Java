package com.sumit.dsa.DynamicProgramming.DpOnGrids;

import com.sumit.leetcodeInputParser.leetcodeInputParser;

import java.util.Arrays;

/**
 * 🔢 Leetcode 931: Minimum Falling Path Sum
 * <p>
 * 🧩 Problem Statement:
 * Given an n x n integer matrix, return the minimum sum of any falling path through matrix.
 * A falling path starts at any element in the first row and chooses one element from each row.
 * The next row's choice must be in a column either directly below or diagonally left/right.
 * <p>
 * 🧪 Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * ✅ Output: 13
 * 💬 Explanation: The minimum path is 1 → 4 → 8 = 13
 * <p>
 * ✅ Approaches:
 * 1. Recursion (TLE for large inputs)
 * 2. Memoization (Top-down DP)
 * 3. Tabulation (Bottom-up DP - Most Optimal)
 * <p>
 * ⏱️ Time Complexity:
 * - Recursion: O(3^n)
 * - Memoization: O(n^2)
 * - Tabulation: O(n^2)
 * <p>
 * 📦 Space Complexity:
 * - Recursion: O(n) recursive stack
 * - Memoization: O(n^2) + O(n) stack
 * - Tabulation: O(n^2), can be optimized to O(n)
 */
public class leetcode_931_MinimumFallingPathSum {

    /*
    function minFallingPathSum(matrix):
    n = number of rows in matrix
    m = number of columns in matrix

    // Initialize dp array of same size as matrix
    dp = 2D array of size n x m

    // Base Case: first row values same as matrix
    for j from 0 to m-1:
    dp[0][j] = matrix[0][j]

    // Build dp from second row to last row
    for i from 1 to n-1:
    for j from 0 to m-1:

    // Values to consider from previous row
    straightUp = dp[i-1][j]
    leftDiagonal = (j-1 >= 0) ? dp[i-1][j-1] : Infinity
            rightDiagonal = (j+1 < m) ? dp[i-1][j+1] : Infinity

    // Current cell = matrix value + minimum of three options
    dp[i][j] = matrix[i][j] + min(straightUp, leftDiagonal, rightDiagonal)

    // The answer is minimum value in last row
    answer = minimum of dp[n-1][0...m-1]
            return answer
    */

    // 🔁 1. Recursion
    public static int fRec(int i, int j, int[][] matrix) {
        if (j < 0 || j >= matrix[0].length) return (int) 1e9;
        if (i == 0) return matrix[0][j];

        int up = matrix[i][j] + fRec(i - 1, j, matrix);
        int leftDiag = matrix[i][j] + fRec(i - 1, j - 1, matrix);
        int rightDiag = matrix[i][j] + fRec(i - 1, j + 1, matrix);

        return Math.min(up, Math.min(leftDiag, rightDiag));
    }

    public static int minPathSumRecursion(int[][] matrix) {
        int n = matrix.length;
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            min = Math.min(min, fRec(n - 1, j, matrix));
        }
        return min;
    }

    // 🧠 2. Memoization
    public static int fMemo(int i, int j, int[][] matrix, int[][] dp) {
        if (j < 0 || j >= matrix[0].length) return (int) 1e9;
        if (i == 0) return matrix[0][j];

        if (dp[i][j] != -1) return dp[i][j];

        int up = matrix[i][j] + fMemo(i - 1, j, matrix, dp);
        int leftDiag = matrix[i][j] + fMemo(i - 1, j - 1, matrix, dp);
        int rightDiag = matrix[i][j] + fMemo(i - 1, j + 1, matrix, dp);

        return dp[i][j] = Math.min(up, Math.min(leftDiag, rightDiag));
    }

    public static int minPathSumMemo(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            min = Math.min(min, fMemo(n - 1, j, matrix, dp));
        }
        return min;
    }

    // 📋 3. Tabulation
    public static int minPathSumTabulation(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        // Base case: first row is same
        System.arraycopy(matrix[0], 0, dp[0], 0, n);

        // Bottom-up DP
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int up = matrix[i][j] + dp[i - 1][j];
                int leftDiag = matrix[i][j] + (j > 0 ? dp[i - 1][j - 1] : (int) 1e9);
                int rightDiag = matrix[i][j] + (j < n - 1 ? dp[i - 1][j + 1] : (int) 1e9);
                dp[i][j] = Math.min(up, Math.min(leftDiag, rightDiag));
            }
        }

        // Find minimum in last row
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            min = Math.min(min, dp[n - 1][j]);
        }

        return min;
    }

    // 🔍 Main Method
    public static void main(String[] args) {
        int[][] matrix = leetcodeInputParser.convertBrackets2D("[[2,1,3],[6,5,4],[7,8,9]]");

        System.out.println("🔁 Recursion Result: " + minPathSumRecursion(matrix));
        System.out.println("🧠 Memoization Result: " + minPathSumMemo(matrix));
        System.out.println("📋 Tabulation Result: " + minPathSumTabulation(matrix));
    }
}
