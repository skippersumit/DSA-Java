package com.sumit.dsa.DynamicProgramming;

import java.util.Arrays;

public class ninjasTrainingDp7 {

    /*
     * Problem Statement:
     * A ninja has to train for N days. Each day, he can perform one of 3 activities (0, 1, 2),
     * and can't do the same activity as the previous day. Each activity gives some points.
     * The goal is to maximize the total points.
     *
     * DP State: f(day, last) - max points from day 0 to 'day', given that on previous day
     * the task was 'last' (so we avoid repeating it today).
     */

    /*
     * Approach:
     * f(day, last)
     * 1. Base case: if(day == 0), find max points for all tasks except 'last'.
     * 2. For each task on the current day, if it's not equal to 'last',
     *    calculate the points and recurse for previous day with this task.
     * 3. Return the max among all choices.
     */

    // 🔁 Pure Recursive Solution (No Memoization)
    // TC: O(3^n), SC: O(n)
    private static int f(int day, int last, int[][] points) {
        if (day == 0) {
            int maxi = 0;
            for (int task = 0; task <= 2; task++) {
                if (task != last) {
                    maxi = Math.max(maxi, points[0][task]);
                }
            }
            return maxi;
        }

        int maxi = 0;
        for (int task = 0; task <= 2; task++) {
            if (task != last) {
                int point = points[day][task] + f(day - 1, task, points);
                maxi = Math.max(maxi, point);
            }
        }
        return maxi;
    }

    // 📦 Top-down DP (Recursion + Memoization)
    // TC: O(n*4*3), SC: O(n*4) + O(n) stack space
    private static int fWithMemorization(int day, int last, int[][] points, int[][] dp) {
        if (day == 0) {
            int maxi = 0;
            for (int task = 0; task <= 2; task++) {
                if (task != last) {
                    maxi = Math.max(maxi, points[0][task]);
                }
            }
            return dp[day][last] = maxi;
        }

        if (dp[day][last] != -1) return dp[day][last];

        int maxi = 0;
        for (int task = 0; task <= 2; task++) {
            if (task != last) {
                int point = points[day][task] + fWithMemorization(day - 1, task, points, dp);
                maxi = Math.max(maxi, point);
            }
        }
        return dp[day][last] = maxi;
    }

    // 📋 Bottom-up DP (Tabulation)
    // TC: O(n*4*3), SC: O(n*4)
    public static int fWithTabulation(int[][] points) {
        int n = points.length;
        int[][] dp = new int[n][4];

        // Base Case: day 0
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        int point = points[day][task] + dp[day - 1][task];
                        dp[day][last] = Math.max(dp[day][last], point);
                    }
                }
            }
        }

        return dp[n - 1][3]; // Max among all options on last day
    }

    // 💾 Bottom-up DP with Space Optimization
    // TC: O(n*4*3), SC: O(4) ~ constant
    public static int fWithTabulationWithSpaceOptimization(int[][] points) {
        int n = points.length;
        int[] prev = new int[4];

        // Base Case: day 0
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for (int day = 1; day < n; day++) {
            int[] tmp = new int[4];
            Arrays.fill(tmp, 0);

            for (int last = 0; last < 4; last++) {
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        int point = points[day][task] + prev[task];
                        tmp[last] = Math.max(tmp[last], point);
                    }
                }
            }
            prev = tmp;
        }

        return prev[3];
    }

    // 🔍 Driver Method
    public static void main(String[] args) {
        int[][] arr = {{10, 50, 1}, {5, 100, 1}};
        int n = arr.length;

        System.out.println("Ans with recursion: " + f(n - 1, 3, arr));

        int[][] dp = new int[n][4];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        System.out.println("Ans with memoization: " + fWithMemorization(n - 1, 3, arr, dp));

        System.out.println("Ans with tabulation: " + fWithTabulation(arr));

        System.out.println("Ans with space optimization: " + fWithTabulationWithSpaceOptimization(arr));
    }
}
