package com.sumit.dsa.DynamicProgramming.DpOnGrids;

import com.sumit.leetcodeInputParser.leetcodeInputParser;
import java.util.Arrays;

/*
   alice is at 0.0 and bob is at 0,col-1
   they have to reach last row means (row-1, anyCol)

   they can take down, leftDiag, rightDiag so both will reach at the same time at last row

   when they both come at same cell then add the sum only once
   when they are at different cells add the sum two times
*/
public class leetcode_1463_CherryPickupII {

    // Time Complexity: O(9^n), exponential
    public static int cherryPickupRecursion(int i, int j1, int j2, int[][] arr) {
        int n = arr.length, m = arr[0].length;
        if (j1 < 0 || j2 < 0 || j1 >= m || j2 >= m) return (int) -1e8; // crosses the boundary
        if (i == n - 1) { // reached last row or Destination
            if (j1 == j2) {
                return arr[i][j1];
            } else return arr[i][j1] + arr[i][j2];
        }

        int maxi = (int) -1e8;
        // explore all paths of alice and bob simultaneously
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int val = (j1 == j2) ? arr[i][j1] : arr[i][j1] + arr[i][j2];
                val += cherryPickupRecursion(i + 1, j1 + dj1, j2 + dj2, arr);
                maxi = Math.max(maxi, val);
            }
        }
        return maxi;
    }

    // Time Complexity: O(n * m * m * 9), Space: O(n * m * m)
    public static int cherryPickupDp(int i, int j1, int j2, int[][] arr, int[][][] dp) {
        int n = arr.length, m = arr[0].length;
        if (j1 < 0 || j2 < 0 || j1 >= m || j2 >= m) return (int) -1e8;
        if (i == n - 1) {
            return (j1 == j2) ? arr[i][j1] : arr[i][j1] + arr[i][j2];
        }
        if (dp[i][j1][j2] != -1) return dp[i][j1][j2];

        int maxi = (int) -1e8;
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int val = (j1 == j2) ? arr[i][j1] : arr[i][j1] + arr[i][j2];
                val += cherryPickupDp(i + 1, j1 + dj1, j2 + dj2, arr, dp);
                maxi = Math.max(maxi, val);
            }
        }
        return dp[i][j1][j2] = maxi;
    }

    // Time Complexity: O(n * m * m * 9), Space: O(n * m * m)
    public static int cherryPickupDpTabulation(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int[][][] dp = new int[n][m][m];

        // Base case
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                dp[n - 1][j1][j2] = (j1 == j2) ? arr[n - 1][j1] : arr[n - 1][j1] + arr[n - 1][j2];
            }
        }

        // i-> n-2 till 0 (since n-1 already covered in base case)
        for (int i = n - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    int maxi = (int) -1e8;
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int val = (j1 == j2) ? arr[i][j1] : arr[i][j1] + arr[i][j2];
                            if (j1 + dj1 >= 0 && j1 + dj1 < m && j2 + dj2 >= 0 && j2 + dj2 < m) {
                                val += dp[i + 1][j1 + dj1][j2 + dj2];
                            } else {
                                val += (int) -1e9;
                            }
                            maxi = Math.max(maxi, val);
                        }
                    }
                    dp[i][j1][j2] = maxi;
                }
            }
        }
        return dp[0][0][m - 1];
    }

    // Time Complexity: O(n * m * m * 9), Space: O(m * m)
    public static int cherryPickupSpaceOptmized(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int[][] front = new int[m][m];
        int[][] cur = new int[m][m];

        // Base case
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                front[j1][j2] = (j1 == j2) ? arr[n - 1][j1] : arr[n - 1][j1] + arr[n - 1][j2];
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    int maxi = (int) -1e8;
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int val = (j1 == j2) ? arr[i][j1] : arr[i][j1] + arr[i][j2];
                            if (j1 + dj1 >= 0 && j1 + dj1 < m && j2 + dj2 >= 0 && j2 + dj2 < m) {
                                val += front[j1 + dj1][j2 + dj2];
                            } else {
                                val += (int) -1e9;
                            }
                            maxi = Math.max(maxi, val);
                        }
                    }
                    cur[j1][j2] = maxi;
                }
            }
            // Copy cur to front
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    front[j1][j2] = cur[j1][j2];
                }
            }
        }
        return front[0][m - 1];
    }

    public static void run(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        System.out.println("🔁 Recursion Result: " + cherryPickupRecursion(0, 0, m - 1, arr));
        int[][][] dp = new int[n][m][m];
        for (int[][] mat : dp)
            for (int[] row : mat)
                Arrays.fill(row, -1);
        System.out.println("🧠 Memoization Result: " + cherryPickupDp(0, 0, m - 1, arr, dp));
        System.out.println("📋 Tabulation Result: " + cherryPickupDpTabulation(arr));
        System.out.println("📦 Tabulation space Optimized Result: " + cherryPickupSpaceOptmized(arr));
        System.out.println("\n");
    }

    public static void main(String[] args) {
        int[][] arr = leetcodeInputParser.convertBrackets2D("[[3,1,1],[2,5,1],[1,5,5],[2,1,1]]");
        int[][] arr2 = leetcodeInputParser.convertBrackets2D("[[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]");
        run(arr);
        run(arr2);
    }
}
