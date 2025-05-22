package com.sumit.dsa.DynamicProgramming.DpOnSubsequences;

import java.util.Arrays;

public class subsetSumEqualToTarget {
    public static boolean subSetSumRecursion(int ind, int target, int[] arr) {
        if (target == 0) return true;
        if (ind == 0) return target == arr[ind];

        boolean notTake = subSetSumRecursion(ind - 1, target, arr);
        boolean take = false;
        if (arr[ind] <= target) take = subSetSumRecursion(ind - 1, target - arr[ind], arr);

        return take | notTake;
    }

    public static boolean subSetSumDp(int ind, int target, int[] arr, int[][] dp) {
        if (target == 0) return true;
        if (ind == 0) return target == arr[ind];
        if (dp[ind][target] != -1) {
            return dp[ind][target] == 1;
        }

        boolean notTake = subSetSumDp(ind - 1, target, arr,dp);
        boolean take = false;
        if (arr[ind] <= target) take = subSetSumDp(ind - 1, target - arr[ind], arr,dp);

        dp[ind][target] = (take || notTake) ? 1 : 0;
        return dp[ind][target] == 1;
    }

    public static boolean subSetSumTabulation(int n, int k, int[] arr) {
        boolean[][] dp = new boolean[n + 1][k + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], false);

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true; // target is 0 so true for every length
        }

        dp[0][arr[0]] = true;

        for (int ind = 1; ind <= n; ind++) {
            for (int target = 1; target <= k; target++) {
                boolean notTake = dp[ind - 1][target];
                boolean take = false;
                if (arr[ind] <= target) take = dp[ind - 1][target - arr[ind]];
                dp[ind][target] = take | notTake;
            }
        }

        return dp[n - 1][k];
    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int n = arr.length;
        int sum = 9;

        System.out.println("Subset sum equal to k is possible = " + subSetSumRecursion(n - 1, sum, arr));

        int[][] dp = new int[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], -1);
        System.out.println("Subset sum equal to k  Dp is possible = " + subSetSumDp(n - 1, sum, arr, dp));

        System.out.println("Subset sum tabulation = " + subSetSumTabulation(n - 1, sum, arr));
    }


}
