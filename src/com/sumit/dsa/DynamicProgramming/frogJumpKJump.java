package com.sumit.dsa.DynamicProgramming;

import java.util.Arrays;

public class frogJumpKJump {
    private static int minimumEnergyDp(int[] arr, int idx, int[] dp, int k) {
        if (idx == 0) dp[idx] = 0;
        if (dp[idx] != -1) return dp[idx];

        int minSteps = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (idx - j >= 0) {
                int jump = minimumEnergyDp(arr, idx - j, dp, k) + Math.abs(arr[idx] - arr[idx - j]);
                minSteps = Math.min(minSteps, jump);
            }
        }
        return dp[idx] = minSteps;
    }

    public static void main(String[] args) {
        int[] arr = {30, 10, 60, 10, 60, 50};
        int n = arr.length - 1;
        int[] dp = new int[arr.length + 1];
        int k = 2;
        Arrays.fill(dp, -1);
        System.out.println("Minimum energy required with Dp " + minimumEnergyDp(arr, n, dp, k));
    }
}
