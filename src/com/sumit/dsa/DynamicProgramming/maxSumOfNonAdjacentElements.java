package com.sumit.dsa.DynamicProgramming;

import java.util.Arrays;

public class maxSumOfNonAdjacentElements {
// pseudo code
//     f(ind)
//     {
//        if(ind == 0) return arr[0]
//        if(ind < 0 ) return 0;
//
//        pick = arr[ind] + f(ind-2)
//        notPick = 0 + f(ind-1)
//
//        return max(pick,notPick)
//     }

    private static int maximumNonAdjacentElements(int ind, int[] nums) {
        // Recursion
        if (ind == 0) return nums[0];
        if (ind < 0) return 0;

        int pick = nums[ind] + maximumNonAdjacentElements(ind - 2, nums);
        int notPick = maximumNonAdjacentElements(ind - 1, nums);
        return Math.max(pick, notPick);
    }

    private static int maximumNonAdjacentElementsDp(int ind, int[] nums, int[] dp) {
        // DP approach
        if (ind == 0) return nums[0];
        if (ind < 0) return 0;
        if (dp[ind] != -1) return dp[ind];

        int pick = nums[ind] + maximumNonAdjacentElementsDp(ind - 2, nums, dp);
        int notPick = maximumNonAdjacentElementsDp(ind - 1, nums, dp);
        return dp[ind] = Math.max(pick, notPick);
    }

    public static int maximumNonAdjacentElementsDpTabulation(int[] nums) {
        /*
        Tabulation is bottom up
        * int[] dp = new int[n] -> 0 declare it as 0
        * dp[0] = 0; // base case
        * int neg = 0 // negative index is 0

        for(int i=1;i<n;i++) {
            take = arr[ind] + dp[i-2]; ?? edge case for i<=1
            Or handle edge case like this
                    -> take = arr[ind]; if(i>1) take += dp[i-2]
            notTake = 0 + dp[i-1];
            dp[i] = max(take,notTake)
        }
        return dp[n-1];

        TC O(n)
        SC O(n)
        * */

        /*
         * Tabulation + space Optimization
         * for dp[i] we just need dp[i-2] and dp[i-1]
         * Just require two spaces
         * fun() {
         *   int prev2 = 0;
         *   prev = arr[0]
         *   for(int i=1;i<n;i++)
         *       {
         *       take = arr[ind] + prev2;
         *       notTake = 0 + prev;
         *       curi = max(take,notTake)
         *       prev2 = prev;
         *       prev = curi;
         *       }
         *   return prev <-  last value will be at prev
         *   }
         * TC - O(n)
         * SC - O(1)
         * */
        int n = nums.length;
        int prev = nums[0];
        int prev2 = 0;
        for (int i = 1; i < n; i++) {
            int take = nums[i];
            if (i > 1) take += prev2;
            int notTake = prev;
            int curi = Math.max(take, notTake);

            prev2 = prev;
            prev = curi;
        }
        return prev;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 9};
        int n = arr.length;
        System.out.println("Ans is " + maximumNonAdjacentElements(n - 1, arr));

        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        System.out.println("Ans is DP = " + maximumNonAdjacentElementsDp(n - 1, arr, dp));

        System.out.println("Ans is DP with tabulation = " + maximumNonAdjacentElementsDpTabulation(arr));
    }
}
