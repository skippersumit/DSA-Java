package com.sumit.dsa.DynamicProgramming;

import com.sumit.leetcodeInputParser.leetcodeInputParser;

import java.util.Arrays;

public class frogJumpSingleOrDoubleJump {
    private static int minimumEnergyDp(int[] arr, int idx, int[] dp) {
        if (idx == 0) dp[idx] = 0;
        if (dp[idx] != -1) return dp[idx];

        int left = minimumEnergyDp(arr, idx - 1, dp) + Math.abs(arr[idx] - arr[idx - 1]);
        int right = Integer.MAX_VALUE;
        if (idx > 1) right = minimumEnergyDp(arr, idx - 2, dp) + Math.abs(arr[idx] - arr[idx - 2]);
        return dp[idx] = Math.min(left, right);
    }

    private static int minimumEnergyRecursion(int[] arr, int idx) {
        if (idx == 0) return 0;
        int left = minimumEnergyRecursion(arr, idx - 1) + Math.abs(arr[idx] - arr[idx - 1]);
        int right = Integer.MAX_VALUE;
        if (idx > 1) right = minimumEnergyRecursion(arr, idx - 2) + Math.abs(arr[idx] - arr[idx - 2]);
        return Math.min(left, right);
    }

    private static int minimumEnergyDpTopDown(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 0);

        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            int fs = dp[i - 1] + Math.abs(arr[i] - arr[i - 1]);
            int ss = Integer.MAX_VALUE;
            if (i > 1) ss = dp[i - 2] + Math.abs(arr[i] - arr[i - 2]);
            dp[i] = Math.min(fs, ss);
        }
        return dp[n - 1];
    }

    private static int minimumEnergyDpTopDownSpaceOptimised(int[] arr) {
        int n = arr.length;
        int prev = 0;
        int prev2 = 0;
        for (int i = 1; i < n; i++) {
            int fs = prev + Math.abs(arr[i] - arr[i - 1]);
            int ss = Integer.MAX_VALUE;
            if (i > 1) ss = prev2 + Math.abs(arr[i] - arr[i - 2]);
            int curAns = Math.min(fs, ss);
            prev2 = prev;
            prev = curAns;
        }
        return prev;
    }

    private static void run(int[] arr){
        System.out.println(Arrays.toString(arr));
        int n = arr.length - 1;
        System.out.println("Minimum energy required with Recursion " + minimumEnergyRecursion(arr, n));

        int[] dp = new int[arr.length + 1];
        Arrays.fill(dp, -1);
        System.out.println("Minimum energy required with Dp " + minimumEnergyDp(arr, n, dp));

        System.out.println("Minimum energy required with Dp Top Down = " + minimumEnergyDpTopDown(arr));

        System.out.println("Minimum energy required with Dp Top Down Space Opmized = " + minimumEnergyDpTopDownSpaceOptimised(arr) + "\n\n");
    }

    public static void main(String[] args) {
        int[] arr = new int[]{10, 20, 30, 10};
        int[] arr2 = leetcodeInputParser.convertBrackets1D("[30, 20, 50, 10, 40]");
        int[] arr3 = leetcodeInputParser.convertBrackets1D("[20, 30, 40, 20] ");

        run(arr);
        run(arr2);
        run(arr3);
    }
}
