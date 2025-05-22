package com.sumit.dsa.DynamicProgramming.DpOnSubsequences;

import java.util.Arrays;

import static java.lang.Math.abs;

public class partitionIntoTwoSubsetsMinDiff {
    public static void main(String[] args) {
        int[] arr = {3,9,7,3};
        System.out.println(minimumDifference(arr));
    }

    public static int minimumDifference(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        int n = arr.length - 1;
        int k = sum;

        boolean[][] dp = new boolean[n + 1][k + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], false);

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true; // target is 0 so true for every length
        }

        if (arr[0] <= k) dp[0][arr[0]] = true;

        for (int ind = 1; ind <= n; ind++) {
            for (int target = 1; target <= k; target++) {
                boolean notTake = dp[ind - 1][target];
                boolean take = false;
                if (arr[ind] <= target) take = dp[ind - 1][target - arr[ind]];
                dp[ind][target] = take | notTake;
            }
        }

        // dp[n - 1][0..sum]; will give ans
        // last row of dp will tell if a sum is possible or not
        int mini = Integer.MAX_VALUE;
        for (int s1 = 0; s1 <= sum / 2; s1++) {
            if (dp[n - 1][s1]) {
                int s2 = sum - s1;
                mini = Math.min(mini, abs(s2 - s1));
            }
        }
        return mini;
    }
}
