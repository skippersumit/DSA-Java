package com.sumit.dsa.DynamicProgramming.DpOnGrids;

import java.util.Arrays;

public class gridUniquePaths {
    /*
    0,0 to m-1,n-1 only right and bottom
    * */
    /*
     * Q How to write Recurrence
     * 1. Express everything in terms of indexes (i,j) & write base cases
     * 2. Explore all paths/ Do all stuffs
     * 3. Sum up all ways/ Max / Min
     *
     * TC - O(2^n*m) SC O(path length) -> O(m+n)
     * f(i,j) {
     *   // base case
     *   if(i== 0 & j==0) return 1;
     *   if(i < 0 || j < 0) return 0;
     *
     *   up = f(i-1,j)
     *   left = f(i,j-1)
     *
     *   return up + left;
     * }
     * */


    public static int uniquePaths(int i, int j) {
        if (i == 0 && j == 0) return 1;
        if (i < 0 || j < 0) return 0;

        int up = uniquePaths(i - 1, j);
        int left = uniquePaths(i, j - 1);

        return up + left;
    }

    public static int uniquePathsDp(int i, int j, int[][] dp) {
        if (i == 0 && j == 0) return 1;
        if (i < 0 || j < 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];
        int up = uniquePaths(i - 1, j);
        int left = uniquePaths(i, j - 1);

        return dp[i][j] = up + left;
    }

    public static int uniquePathsTabulation(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) dp[i][j] = 1;
                else {
                    int up = 0, left = 0;
                    if (i > 0) up = dp[i - 1][j];
                    if (j > 0) left = dp[i][j - 1];
                    dp[i][j] = up + left;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main() {
        int m = 3, n = 7;
        System.out.println("Recursion ans is " + uniquePaths(m - 1, n - 1));

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
        System.out.println("Dp ans is " + uniquePathsDp(m - 1, n - 1, dp));

        System.out.println("Tabulation ans is " + uniquePathsTabulation(m, n));
    }
}
